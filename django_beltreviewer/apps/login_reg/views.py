# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.shortcuts import render, redirect
from django.contrib import messages
from .models import User
from ..book_reviews.models import Review

def index(request):
    return render(request, 'login_reg/index.html')

def register(request):
    reginfo = User.objects.register(request.POST)
    if reginfo[0]: #if reginfo index 0 is TRUE
        request.session['email'] = reginfo[1].email #user email from index 1
        return redirect('/books')
    else: #if reginfo index 0 is FALSE
        for error in reginfo[1]: 
            messages.add_message(request, messages.ERROR, error)
        return redirect('/')

def login(request):
    logininfo = User.objects.login(request.POST)
    if logininfo[0]: 
        request.session['email'] = logininfo[1].email 
        return redirect('/books')
    else: 
        for error in logininfo[1]:
            messages.add_message(request, messages.ERROR, error)
        return redirect('/')

    return redirect('/books')

def userpage(request, user_id): 
    data = {
        "user": User.objects.get(id=user_id),
        "reviews": Review.objects.filter(reviewer=user_id)
    }
    return render(request, 'login_reg/userpage.html', data)

def logout(request):
    request.session.clear()
    return redirect('/')
