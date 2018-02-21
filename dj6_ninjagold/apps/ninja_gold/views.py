from __future__ import unicode_literals
from django.shortcuts import render, HttpResponse, redirect
import random
import datetime

random.randrange(1, 101)

def index(request):
    if 'golds' not in request.session:
        request.session['golds'] = 0
    if request.session['golds'] >=0:
        request.session['topcolor'] = "green"
    else:
        request.session['topcolor'] = "red"
    if 'log' not in request.session:
        request.session['log'] = []
    return render(request, 'ninja_gold/index.html')

def process(request):
    if request.method == 'POST':
        place = request.POST['building']
        if place == "farm":
            golds = random.randrange(10, 21)
        elif place == "cave":
            golds = random.randrange(5, 11)
        elif place == "house":
            golds = random.randrange(2, 6)
        else:
            golds = random.randrange(-50, 51)   

        log_entry = {}
        
        request.session['golds'] += golds
        if golds > 0:
            earned = "Earned"
            log_entry['color'] = "green"
        else:
            earned = "Lost"
            log_entry['color'] = "red"


        now = datetime.datetime.now()

        message = "{} {} golds from the {}! ({})".format(earned, golds, place, now.strftime("%Y/%m/%d %H:%M:%S"))
        log_entry['message'] = message
        request.session['log'].insert(0, log_entry)

    return redirect('/')

def reset(request):
    request.session.clear()
    return redirect('/')