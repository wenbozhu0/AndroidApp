# all the following deps are referenced directly from the python sdk
import json
from typing import Dict
from message import Message
import boto3
from botocore.exceptions import ClientError

def lambda_handler(event, context):

    #headers we use to send back data to the caller
    headers: Dict[str, str] = {
        "Content-Type": "application/json",
        "X-Custom-Header": "application/json"
    }

    try:
        #attempt to parse the json into Message by passing in a dictionary
        message = json.loads(event["body"], object_hook=lambda d: Message(**d))
    except json.JSONDecodeError as json_err:
        return {
            "body": json.dumps({"error": "Failed to decode JSON: " + str(json_err)}),
            "headers": headers,
            "statusCode": 400
        }
    except ValueError as value_err:
        return {
            "body": json.dumps({"error": str(value_err)}),
            "headers": headers,
            "statusCode": 400
        }

    # info on how to use the context object https://docs.aws.amazon.com/lambda/latest/dg/python-context.html
    # convert the message object back into a json
    json_object = {
        "context-arn": str(context.invoked_function_arn),
        "event": str(event),
        "event-body": str(event["body"]),  # the event-body is effectively the message

        #we parse the event-body (aka message) above
        "message-subject": message.subject,
        "message-body": message.body,
        "message-email": message.email
    }

    #send to SES code
    # Create an SES client
    ses_client = boto3.client('ses', region_name='us-east-1')

    # Specify the recipient's email address (your email)
    recipient_email = "wz1305@uchicago.edu"

    # Create the email parameters
    email_params = {
        'Source': message.email, # Sender's email from the parsed message object
        'Destination': {
            'ToAddresses': [recipient_email],
        },
        'Message': {
            'Subject': {
                'Data': message.subject, # Subject from the parsed message object
            },
            'Body': {
                'Text': {
                    'Data': message.body, # Body from the parsed message object
                }
            }
        }
    }

    # Send the email
    try:
        ses_client.send_email(**email_params)
    except ClientError as e:
        return {
            "body": json.dumps({"error": f"Error sending email: {e}"}),
            "headers": headers,
            "statusCode": 500
        }

    # and return it
    return {
        "body": json.dumps(json_object),
        "headers": headers,
        "statusCode": 201
    }
