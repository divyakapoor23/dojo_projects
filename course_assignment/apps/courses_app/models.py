# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.db import models

class CourseDescManager(models.Manager):
    def course_valid(self, post_data):
        errors = []
        if len(post_data['name']) < 1 or len(post_data['desc']) < 1:
            errors.append("Entries cannot be left blank!")
        elif len(post_data['name']) < 5:
            errors.append("Course Name must be at least 5 characters")
        elif len(post_data['desc']) < 15:
            errors.append("Course Description must be at least 15 characters long")
        
        if len(errors) > 0:
            return (False, errors)
        else: 
            desc = Description.objects.create(
                desc = post_data['desc'],
            )
            course = Course.objects.create(
                name = post_data['name'],
                desc = Description.objects.get(id=desc.id)
            )
            return(True, course, desc)

class Description(models.Model):
    desc = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = CourseDescManager()

class Course(models.Model):
    name = models.CharField(max_length=255)
    desc = models.OneToOneField(Description, related_name="course")
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = CourseDescManager()