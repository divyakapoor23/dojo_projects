from __future__ import unicode_literals
from django.shortcuts import render, HttpResponse, redirect

cat_plushie = {
    "name": "Cat Plushie",
    "prod_id": 1,
    "available": 16,
    "price": 12.99
}
cow_plushie= {
    "name": "Cow Plushie",
    "prod_id": 2,
    "available": 23,
    "price": 14.99
}
narwhal_plushie= {
    "name": "Narwhal Plushie",
    "prod_id": 3, 
    "available": 5,
    "price": 17.99
}
hamster_plushie= {
    "name": "Hamster Plushie",
    "prod_id": 4,
    "available": 239,
    "price": 6.99
}
ALLCAPS = [cat_plushie, cow_plushie, narwhal_plushie, hamster_plushie]

def index(request):
    if 'total' not in request.session:
        request.session['total'] = 0
    if 'total_items' not in request.session:  
        request.session['total_items'] = 0
    return render(request, 'amadon/index.html', {'products': ALLCAPS})

def buy(request):
    if request.method == 'POST':
        for product in ALLCAPS:
            if int(request.POST['quantity']) > 1:
                pass
            if int(request.POST['buy_id']) == product["prod_id"]:
                request.session['total_items'] += int(request.POST['quantity'])
                prod_total = float(product["price"]) * int(request.POST['quantity'])
                request.session['total'] += prod_total 
                product["available"] -= int(request.POST['quantity'])
                item = {
                    "prod_id": product['prod_id'],
                    "name": product['name'],
                    "num": request.POST['quantity'],
                    "subtotal": prod_total
                }
                if 'cart' not in request.session:
                    request.session['cart'] = [] 
                    request.session['cart'].append(item)
                else:
                    request.session['cart'].append(item)
                    request.session.modified = True
    return redirect('/')

def cart(request):
    return render(request, 'amadon/checkout.html')
    
def purchase(request):
    if 'running_total' not in request.session:
        request.session['running_total'] = 0
    if 'running_items' not in request.session:  
        request.session['running_items'] = 0
    request.session['running_total'] += request.session['total']
    request.session['running_items'] += request.session['total_items']

    return render(request, 'amadon/purchased.html')

def newcart(request):
    del request.session['cart']
    del request.session['total']
    del request.session['total_items']
    return redirect('/')

def empty(request):
    for product in ALLCAPS:
        for item in request.session['cart']:
            if item['prod_id'] == product['prod_id']:
                product['available'] += int(item['num'])
    del request.session['cart']
    del request.session['total']
    del request.session['total_items']
    return redirect('/')

def reset(request):
    for x in ALLCAPS:
        if x['prod_id'] == 1:
            x['available'] = 16
        if x['prod_id'] == 2:
            x['available'] = 23
        if x['prod_id'] == 3:
            x['available'] = 5
        if x['prod_id'] == 4:
            x['available'] = 239
    request.session.clear()
    return redirect('/')
