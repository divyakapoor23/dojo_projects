from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
import re

app = Flask(__name__)
app.secret_key = "Victorias_Secret"
mysql = MySQLConnector(app,'the_wall')

NAME_REGEX = re.compile(r"^[a-zA-Z-']+$")
EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')

@app.route('/')
def local():
    return redirect('/users')

@app.route('/users')
def index():
    users = mysql.query_db("SELECT users.id, CONCAT(users.first_name, ' ', users.last_name) AS full_name, users.email, users.created_at FROM users;")
    return render_template('users_index.html', users=users)

@app.route('/users/new')
def new():    
    return render_template('users_new.html')

@app.route('/users/<id>/edit') #STILL NOT REFRESHING PAGE AFTER UPDATE METHOD :[
def edit(id):
    user = mysql.query_db("SELECT users.id, CONCAT(users.first_name, ' ', users.last_name) AS full_name, users.email, users.created_at FROM users WHERE id={};".format(id))
    users = mysql.query_db("SELECT * FROM users;")
    return render_template('users_edit.html', user=user, users=users)

@app.route('/users/<id>') 
def show(id):
    user = mysql.query_db("SELECT users.id, CONCAT(users.first_name, ' ', users.last_name) AS full_name, users.email, users.created_at FROM users WHERE id={};".format(id))
    users = mysql.query_db("SELECT * FROM users;")
    return render_template('users_show.html', user=user, users=users)

@app.route('/users/<id>', methods=['POST']) 
def update(id):
    valid = True
    if len(request.form['first_name']) < 1 or len(request.form['last_name']) < 1 or len(request.form['email']) < 1:
        flash("Entries cannot be left blank!")
        valid = False
    if len(request.form['first_name']) < 2 or len(request.form['last_name']) < 2:
        flash("Name(s) must be at least 2 letters")
        valid = False
    if not NAME_REGEX.match(request.form['first_name']) or not NAME_REGEX.match(request.form['last_name']):
        flash("Name(s) must be letters only")
        valid = False
    if not EMAIL_REGEX.match(request.form['email']):
        flash("Invalid Email Address")
        valid = False
    else: 
        check_emails = mysql.query_db("SELECT * FROM users WHERE email = :email", request.form)
        if len(check_emails) > 0:
            flash("Email already exists")
            valid = False

    if not valid:
        return redirect('/users/<id>/edit')
    else: 
        user = mysql.query_db("SELECT users.id, CONCAT(users.first_name, ' ', users.last_name) AS full_name, users.email, users.created_at FROM users WHERE id={};".format(id))
        users = mysql.query_db("SELECT * FROM users;")

        query = "UPDATE users SET first_name = :first_name, last_name = :last_name, email = :email, updated_at = NOW() WHERE id = {}".format(id)
        data = {
            "first_name": request.form["first_name"],
            "last_name": request.form["last_name"],
            "email": request.form["email"]
        }
        mysql.query_db(query, data)
        # return redirect('/users/<id>')
        return render_template('users_show.html', user=user, users=users)


@app.route('/users/create', methods=['POST'])
def create():
    valid = True
    if len(request.form['first_name']) < 1 or len(request.form['last_name']) < 1 or len(request.form['email']) < 1:
        flash("Entries cannot be left blank!")
        valid = False
    if len(request.form['first_name']) < 2 or len(request.form['last_name']) < 2:
        flash("Name(s) must be at least 2 letters")
        valid = False
    if not NAME_REGEX.match(request.form['first_name']) or not NAME_REGEX.match(request.form['last_name']):
        flash("Name(s) must be letters only")
        valid = False
    if not EMAIL_REGEX.match(request.form['email']):
        flash("Invalid Email Address")
        valid = False

    if not valid:
        return redirect('/users/new')
    else: 
        data = {
			"first_name": request.form["first_name"],
			"last_name": request.form["last_name"],
			"email": request.form["email"]
		}
        query = "INSERT INTO users (first_name, last_name, email, created_at) VALUES (:first_name, :last_name, :email, NOW())"
        user_id = mysql.query_db(query, data)
        return render_template('users_new.html')

@app.route('/users/<id>/destroy')
def destroy(id):
    user = mysql.query_db("SELECT * FROM users WHERE id={};".format(id))
    users = mysql.query_db("SELECT * FROM users;")
    remove = mysql.query_db("DELETE FROM users WHERE id = {};".format(id))
    return redirect('/users') 


app.run(debug=True)

