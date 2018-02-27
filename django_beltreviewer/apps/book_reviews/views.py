# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.shortcuts import render, redirect, HttpResponse
from .models import User, Author, Book, Review
from django.contrib import messages
from datetime import datetime

def bookhome(request):
    if 'email' not in request.session:
        return redirect('/')
    data = {
        "user": User.objects.get(email=request.session['email']),
        "books": Book.objects.all(),
        "reviews": Review.objects.all().order_by('-id')[:3]
    }
    
    return render(request, 'login_reg/bookhome.html', data)

def books_add(request):
    data = {
        "authors": Author.objects.all()
    }
    return render(request, 'book_reviews/addbook.html', data)

def add(request):
    user = User.objects.get(email=request.session['email'])
    user_id = user.id
    newbook = Book.objects.book_valid(request.POST, user_id)
    if newbook[0]: 
        return redirect('/books/'+str(newbook[1].id))
    else:
        for error in newbook[1]:
            messages.add_message(request, messages.ERROR, error)
        return redirect('/books/add')

def review(request, book_id):
    user = User.objects.get(email=request.session['email'])
    user_id = user.id
    newreview = Review.objects.review_valid(request.POST, book_id, user_id)
    if newreview[0]: 
        messages.add_message(request, messages.SUCCESS, "Review posted!")
        return redirect('/books/'+str(newreview[1].book.id))
    else:
        for error in newreview[1]:
            messages.add_message(request, messages.ERROR, error)
        return redirect('/books/'+str(book_id))


def bookpage(request, book_id):
    revs = Book.objects.get(id=book_id).reviews.all().filter(book=book_id)
    sumrev = 0
    numrev = 0 
    for x in revs:
        sumrev += x.rating
        numrev += 1
    avgrating = sumrev/numrev
    data = {
        "book": Book.objects.get(id=book_id),
        "reviews": Book.objects.get(id=book_id).reviews.all().filter(book=book_id).order_by('-id'),
        "overall": avgrating
    }
    return render(request, 'book_reviews/bookpage.html', data)