# App Integration instructions

## android-app
This is the android app for cocktail shop

### Setting Switches
Project is currently set to remote environment, if want to switch:

Change the baseUrl in 
androidRetro/src/main/java/edu/uchicago/gerber/androidretro/data/repository/ApiProvider

Change lines 22-23 & 33-34
.baseUrl("http://10.0.2.2:8080/")            //local
.baseUrl("http://54.147.236.105:8080/")      //remote

.baseUrl("http://10.0.2.2:3000/")            //local
.baseUrl("https://xzyk2zj8d3.execute-api.us-east-1.amazonaws.com/Prod/") //remote

### local
1.Open Docker Desktop and make sure it is running
2.Open beers_quarkus_gradle with IntelliJ, and run 
docker run -d -it --rm  -p 27017:27017 mongo 
in the terminal
3.Run the beers_quarkus_gradle.main run config and browse http://localhost:8080/
4.Fire up the aws ses service 
sam build --use-container
sam local start-api
with the link http://localhost:3000/mail
5.Open androidRetro with androidstudio and select PIXEL 5 API 31 emulator and press start
6.Testing favorite page: Click on the favorite Page to see current favorite cocktails. On the search page, search "vodka" or any cocktail, press "add favorite" as many cocktails as you want. Return to the favorite page and click "refresh" button to see the change.
7.Testing contact page: after putting information on subject and message (or using default placeholder), press "send message", registered aws-verified email wz1305@uchicago.edu can receive the email.

### remote

Follow Setting Switches section and local 5-7 section to test the remote environment.

Notes:
aws_contact has been deployed to aws with the stack name "emailer-wz" with the link: https://xzyk2zj8d3.execute-api.us-east-1.amazonaws.com/Prod/mail/

Built, pushed and Deployed to a docker container on a Lightsail instance
as wz1305/beers_quarkus_gradle-jvm
AWS Lightsail instance: Amazon_Linux_2023-1
go to a browser and type: 54.147.236.105:8080/favorite

favorite.postman_collection.json in the root of my quarkus subproject with all the appropriate CRUD operations tested both positively and negatively in the remote env. If want to test quarkus locally, run local step 1-3 and change the postman ip address to localhost.
