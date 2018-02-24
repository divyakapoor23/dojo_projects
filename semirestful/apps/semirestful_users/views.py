# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.shortcuts import render, HttpResponse, redirect
from .models import User
from django.contrib import messages
from datetime import datetime

def local(request):
    return redirect('/users')

def index(request):
    data = {
        "users": User.objects.all()
    }
    return render(request, 'semirestful_users/index.html', data) 

def new(request):
    return render(request, 'semirestful_users/new.html') 

def edit(request, user_id):
    data = {
        "user": User.objects.get(id=user_id)
    }
    request.session['user_id'] = user_id
    return render(request, 'semirestful_users/edit.html', data) 
def show(request, user_id):
    data = {
        "user": User.objects.get(id=user_id)
    }
    return render(request, 'semirestful_users/show.html', data) 
def create(request):
    new_returned = User.objects.newuser(request.POST)
    if new_returned[0]:
        messages.add_message(request, messages.SUCCESS, "Congratulations! You are now registered")
        return redirect('/users/'+str(new_returned[1].id))
    else:
        for error in new_returned[1]:
            messages.add_message(request, messages.ERROR, error)
        return redirect('/users/new')
def destroy(request, user_id):
    user =  User.objects.get(id=user_id)
    user.delete()
    return redirect('/users', user)
def update(request):
    request.session['user_id']
    edit_returned = User.objects.edituser(request.POST, request.session['user_id'])
    if edit_returned[0]:
        messages.add_message(request, messages.SUCCESS, "You have updated this record!")
        return redirect('/users/'+str(edit_returned[1].id))
    else:
        for error in edit_returned[1]:
            messages.add_message(request, messages.ERROR, error)
        return redirect('/users/'+str(edit_returned[1].id)+'/edit')
