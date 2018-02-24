# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.db import models
import re

NAME_REGEX = re.compile(r"^[a-zA-Z-']+$")
EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')


class UserManager(models.Manager):
    def newuser(self, post_data):
        errors = []
        if len(post_data['first']) < 1 or len(post_data['last']) < 1 or len(post_data['email']) < 1:
            errors.append("Entries cannot be left blank!")
        elif len(post_data['first']) < 2 or len(post_data['last']) <2:
            errors.append("Name(s) must be at least 2 letters")

        if not NAME_REGEX.match(post_data['first']) or not NAME_REGEX.match(post_data['last']):
            errors.append("Name(s) must be letters only")

        if not EMAIL_REGEX.match(post_data['email']):
            errors.append("Invalid Email Address")
        check_emails = User.objects.filter(email = post_data['email'].lower())

        if len(check_emails) > 0:
            errors.append("Email already exists")

        if len(errors) > 0:
            return (False, errors)
        else: 
            user = User.objects.create(
            first_name = post_data['first'],
            last_name = post_data['last'],
            email = post_data['email']
            )
            return(True, user)

    def edituser(self, post_data, user_id):
        errors = []
        if len(post_data['first']) < 1 or len(post_data['last']) < 1 or len(post_data['email']) < 1:
            errors.append("Entries cannot be left blank!")
        elif len(post_data['first']) < 2 or len(post_data['last']) < 1:
            errors.append("Name(s) must be at least 2 letters")

        if not NAME_REGEX.match(post_data['first']) or not NAME_REGEX.match(post_data['last']):
            errors.append("Name(s) must be letters only")

        if not EMAIL_REGEX.match(post_data['email']):
            errors.append("Invalid Email Address")
        check_emails = User.objects.filter(email = post_data['email'].lower())

        if len(check_emails) > 0:
            errors.append("Email already exists")

        if len(errors) > 0:
            return (False, errors)
        else: 
            user = User.objects.get(id=user_id)
            user.first_name = post_data['first']
            user.last_name = post_data['last']
            user.email = post_data['email']
            user.save()
            return(True, user)


class User(models.Model):
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = UserManager()
