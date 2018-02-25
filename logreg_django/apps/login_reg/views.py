# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.shortcuts import render, redirect
from .models import User
from django.contrib import messages


def index(request):
    return render(request, 'login_reg/index.html')

def register(request):
    reginfo = User.objects.register(request.POST)
    if reginfo[0]: #if reginfo index 0 is TRUE
        request.session['email'] = reginfo[1].email #user email from index 1
        messages.add_message(request, messages.SUCCESS, 'Successfully registered!')
        return redirect('/success')
    else: #if reginfo index 0 is FALSE
        for error in reginfo[1]:
            messages.add_message(request, messages.ERROR, error)
        return redirect('/')

def login(request):
    logininfo = User.objects.login(request.POST)
    if logininfo[0]: 
        request.session['email'] = logininfo[1].email 
        messages.add_message(request, messages.SUCCESS, 'Successfully logged in!')
        return redirect('/success')
    else: 
        for error in logininfo[1]:
            messages.add_message(request, messages.ERROR, error)
        return redirect('/')

    return redirect('/success')

def success(request):
    if 'email' not in request.session:
        return redirect('/')
    data = {
        "user": User.objects.get(email=request.session['email'])
    }
    return render(request, 'login_reg/success.html', data)

def logout(request):
    request.session.clear()
    return redirect('/')
