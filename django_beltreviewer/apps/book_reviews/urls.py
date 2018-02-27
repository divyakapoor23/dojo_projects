from django.conf.urls import url
from . import views


urlpatterns = [
    url(r'^books$', views.bookhome),
    url(r'^books/add$', views.books_add),
    url(r'^add$', views.add),
    url(r'^review/(?P<book_id>\d+)$', views.review),    
    url(r'^books/(?P<book_id>\d+)$', views.bookpage),
]