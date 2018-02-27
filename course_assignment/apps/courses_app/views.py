# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.shortcuts import render, HttpResponse, redirect
from .models import Description, Course
from django.contrib import messages
from datetime import datetime
from time import gmtime, strftime

def index(request):
    data = {
        "courses": Course.objects.all()
    }
    return render(request, 'courses_app/index.html', data)

def create(request):
    course_returned = Course.objects.course_valid(request.POST)
    if course_returned[0]:
        messages.add_message(request, messages.SUCCESS, "Congratulations! You have added the course")
        return redirect('/')
    else:
        for error in course_returned[1]:
            messages.add_message(request, messages.ERROR, error)
        return redirect('/')

def destroy(request, course_id): 
    data = {
        "course": Course.objects.get(id=course_id)
    }
    return render(request, 'courses_app/destroy.html', data)

def delete(request, course_id):
    course = Course.objects.get(id=course_id)
    desc = Description.objects.get(id=course.desc.id)
    desc.delete()
    course.delete()
    return redirect('/')