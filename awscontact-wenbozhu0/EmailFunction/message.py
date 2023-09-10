import re

class Message:
    def __init__(self, subject, body, email):
        if not re.match(r"[^@]+@[^@]+\.[^@]+", email):
            raise ValueError("Invalid email format")

        if not subject.strip() or not body.strip():
            raise ValueError("Subject and body fields must be non-empty")

        self.subject = subject
        self.body = body
        self.email = email


    def __str__(self):
        return f"Message{{subject='{self.subject}', body='{self.body}', email='{self.email}'}}"
