from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector

app = Flask(__name__)
mysql = MySQLConnector(app,'my_friends')

@app.route('/')
def index():
    query = "SELECT * FROM friends"                           
    friends = mysql.query_db(query)                          
    return render_template('index.html', all_friends=friends)

@app.route('/friends', methods=['POST'])
def create():    query = "INSERT INTO friends (name, age, friend_since, updated_at) VALUES (:name, :age, NOW(), NOW())"
    data = {
             'name': request.form['name'],
             'age':  request.form['age']
           }
    mysql.query_db(query, data)

    return redirect('/')

@app.route('/friends/<id>')
def show(id):
    friend = mysql.query_db("SELECT * FROM friends WHERE id={};".format(id))
    friends = mysql.query_db("SELECT * FROM friends;")

    return render_template('friend.html', friend=friend, friends=friends)

@app.route('/update_friend/<id>', methods=['POST'])
def update(id):
    friend = mysql.query_db("SELECT * FROM friends WHERE id={};".format(id))
    friends = mysql.query_db("SELECT * FROM friends;")

    query = "UPDATE friends SET name = :name, age = :age, updated_at = NOW() WHERE id = {}".format(id)
    data = {
             'name': request.form['name'],
             'age':  request.form['age'],
           }
    mysql.query_db(query, data)
    return render_template('friend.html', friend=friend, friends=friends) #redirect ('/update_friend/<id>') 


@app.route('/remove_friend/<id>')
def delete(id):
    friend = mysql.query_db("SELECT * FROM friends WHERE id={};".format(id))
    friends = mysql.query_db("SELECT * FROM friends;")
    remove = mysql.query_db("DELETE FROM friends WHERE id = {};".format(id))
    return redirect('/')

app.run(debug=True)
