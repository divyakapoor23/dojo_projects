# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.db import models
import re
import bcrypt 
from datetime import datetime

EMAIL_REGEX = re.compile(r"^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$")
NAME_REGEX = re.compile(r"^[a-zA-Z-']+$")
PASS_REGEX = re.compile(r"^[a-zA-Z0-9.+_-]{8,}$")


class UserManager(models.Manager):
    def register(self, post_data):
        errors = []
        if len(post_data['first_name']) < 1 or len(post_data['last_name']) < 1 or len(post_data['email']) < 1 or len(post_data['password']) < 1 or len(post_data['confirm']) < 1:
            errors.append("All entries must be filled in!")
        elif len(post_data['first_name']) < 2 or len(post_data['last_name']) < 2:
            errors.append("Name(s) must be at least 2 letters")
        elif len(post_data['password']) < 8 or len(post_data['confirm']) < 8:
            errors.append("Password should be at least 8 characters")

        if not NAME_REGEX.match(post_data['first_name']):
            errors.append("Invalid characters in First Name")
        if not NAME_REGEX.match(post_data['last_name']):
            errors.append("Invalid characters in Last Name")

        if not EMAIL_REGEX.match(post_data['email']):
            errors.append("Invalid Email Address")
        check_emails = User.objects.filter(email = post_data['email'].lower())
        if len(check_emails) > 0:
            errors.append("Email already exists")

        if len(post_data['birthday']) < 1:
            errors.append("Date of Birth is required") 
        else:
            dob = datetime.strptime(post_data["birthday"], "%Y-%m-%d")
            if dob > datetime.now():
                errors.append("Date of Birth must be in the past")
                
        if len(errors) > 0:
            return (False, errors)
        else: 
            user = User.objects.create(
                first_name = post_data['first_name'],
                last_name = post_data['last_name'],
                email = post_data['email'],
                birthday = post_data['birthday'],
                password = bcrypt.hashpw(post_data['password'].encode(), bcrypt.gensalt())
            )
            return(True, user)

    def login(self, post_data):
        errors = []
        if len(post_data['email']) < 1 or len(post_data['password']) < 1:
            errors.append("All entries must be filled in!")
        elif len(post_data['password']) < 8:
            errors.append("Password should be at least 8 characters")
        elif not EMAIL_REGEX.match(post_data['email']):
            errors.append("Invalid Email Address")

        check_emails = User.objects.filter(email = post_data['email'].lower())
        if len(check_emails) == 0:
            errors.append("Email does not exist")
            return (False, errors)
        else:
            user = check_emails[0]
            if not bcrypt.checkpw(post_data["password"].encode(), user.password.encode()):
                errors.append("Invalid Password")

            if len(errors) > 0:
                return (False, errors)
            else: 
                return(True, user)

class User(models.Model):
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.CharField(max_length=255)
    birthday = models.DateField()
    password = models.CharField(max_length=255)
    birthday = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = UserManager()
