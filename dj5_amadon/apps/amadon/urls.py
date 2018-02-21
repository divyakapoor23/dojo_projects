from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^$', views.index),
    url(r'^amadon/buy$', views.buy),
    url(r'^amadon/checkout$', views.cart),
    url(r'^purchase$', views.purchase),
    url(r'^newcart$', views.newcart),
    url(r'^empty$', views.empty),
    url(r'^reset$', views.reset)
]