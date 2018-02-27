# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.db import models
from ..login_reg.models import User
from datetime import datetime
from time import strftime, gmtime

class BookReviewManager(models.Manager):
    def book_valid(self, post_data, user_id):
        errors = []
        if len(post_data['title']) < 1: 
            errors.append("Title must be entered")
        elif len(post_data['title']) < 2:
            errors.append("Title must be more than 2 characters")
        check_title = Book.objects.filter(title = post_data['title'])
        if len(check_title) > 0:
            errors.append("Book already exists, please go that book's page to post a review.")

        if len(post_data['author1']) < 1:
            if len(post_data['author2']) < 1: 
                errors.append("Author must be entered")
            elif len(post_data['author2']) < 2:
                errors.append("Author name must be more than 2 characters")

            check_author = Author.objects.filter(name = post_data['author2'])
            if len(check_author) > 0:
                errors.append("Author already exists, please select from the dropdown menu")
            else: 
                newauthor = Author.objects.create(
                    name = post_data['author2']
                )
                chosen_author = newauthor.id
        else:
            chosen_author = post_data['author1']

        if len(post_data['comment']) < 1: 
            errors.append("Review must be entered")
        elif len(post_data['comment']) < 10:
            errors.append("Review must be more than 10 characters")

        if len(post_data['rating']) < 1: 
            errors.append("Please give a rating")
        
        if len(errors) > 0:
            return (False, errors)
        else: 
            book = Book.objects.create(
                title = post_data['title'],
                author = Author.objects.get(id=chosen_author),
                poster = User.objects.get(id=user_id)
            )
            review = Review.objects.create(
                comment = post_data['comment'],
                rating = post_data['rating'],
                book = Book.objects.get(id=book.id),
                reviewer = User.objects.get(id=user_id)
            )
            return(True, book, chosen_author, review)


    def review_valid(self, post_data, book_id, user_id):
        errors = []
        if len(post_data['review']) < 1: 
            errors.append("Review must be entered")
        elif len(post_data['review']) < 10:
            errors.append("Review must be more than 10 characters")

        if len(post_data['rating']) < 1: 
            errors.append("Please give a rating")
        
        if len(errors) > 0:
            return (False, errors)
        else: 
            review = Review.objects.create(
                comment = post_data['review'],
                rating = post_data['rating'],
                book = Book.objects.get(id=book_id),
                reviewer = User.objects.get(id=user_id)
            )
            return(True, review)

class Author(models.Model):
    name = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = BookReviewManager()

class Book(models.Model):
    title = models.CharField(max_length=255)
    author = models.ForeignKey(Author, related_name="books")
    poster = models.ForeignKey(User, related_name="books_posted")
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = BookReviewManager()

class Review(models.Model):
    comment = models.TextField()
    rating = models.PositiveSmallIntegerField()
    book = models.ForeignKey(Book, related_name="reviews")
    reviewer = models.ForeignKey(User, related_name="reviews_posted")
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = BookReviewManager()
