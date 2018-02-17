from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
import re
import bcrypt 

app = Flask(__name__)
app.secret_key = "Ceci n'est pas un secret."
mysql = MySQLConnector(app,'log_reg_db')

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
NAME_REGEX = re.compile(r'^[a-zA-Z]+$')
PASS_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]{8,}$')


@app.route('/')
def index():
    return render_template('logreg_index.html')

@app.route('/login', methods=['POST'])
def login():
    check_email = mysql.query_db("SELECT * FROM users WHERE email = :email;", request.form) 
    valid = True

    if len(request.form['email']) < 1 or len(request.form['password']) < 1:
        flash("Entries cannot be left blank!")
        valid = False
    elif len(request.form['password']) < 8:
        flash("Password is too short")
        valid = False
    elif not EMAIL_REGEX.match(request.form['email']):
        flash("Invalid email") 
        valid = False
    else:
        if len(check_email) < 1:
            flash("Email does not exist")
            valid = False
    if not valid: 
         return redirect('/')
         
    user = check_email[0]
    if bcrypt.checkpw(request.form["password"].encode(), user["password"].encode()):
        session['user_id'] = user["id"]
        session['user_name'] = user["first_name"]
        session['action'] = "logged in"
        return redirect('/success')
    else:
        flash("Invalid password")
        return redirect('/')

        

@app.route('/register', methods=['POST'])
def register():
    valid = True
    if len(request.form['first_name']) < 1 or len(request.form['last_name']) < 1 or len(request.form['email']) < 1 or len(request.form['password']) < 1 or len(request.form['confirm']) < 1:
        flash("Entries cannot be left blank!")
        valid = False
    elif len(request.form['first_name']) < 2 or len(request.form['last_name']) < 2:
        flash("Name(s) must be at least 2 letters")
        valid = False
    elif len(request.form['password']) < 8 or len(request.form['confirm']) < 8:
        flash("Your password must be at least 8 characters")
        valid = False

    if not NAME_REGEX.match(request.form['first_name']) or not NAME_REGEX.match(request.form['last_name']):
        flash("Name(s) can be alpha characters only")
        valid = False

    if not EMAIL_REGEX.match(request.form['email']):
        flash("Invalid Email Address")
        valid = False
    else: 
        list_of_users_with_matching_emails = mysql.query_db("SELECT * FROM users WHERE email = :email", request.form)
        if len(list_of_users_with_matching_emails) > 0:
            flash("Email already exists")
            valid = False

    if not PASS_REGEX.match(request.form['password']) or not PASS_REGEX.match(request.form['confirm']):
        flash("Invalid password")
        valid = False
    if request.form['password'] != request.form['confirm']:
        flash("Passwords do not match")
        valid = False
  
    if valid == False: 
        return redirect('/') 
    else:
        query = "INSERT INTO users (first_name, last_name, email, password, created_at, updated_at) VALUES (:first_name, :last_name, :email, :password, NOW(), NOW())"
        hash = bcrypt.hashpw(request.form['password'].encode(), bcrypt.gensalt())
        data = {
                'first_name': request.form['first_name'],
                'last_name': request.form['last_name'],
                'email':  request.form['email'],
                'password': hash
            }
        user_id = mysql.query_db(query, data)
        session['user_id'] = user_id
        session['user_name'] = request.form['first_name']
        session['action'] = "registered"
        return redirect('/success')

@app.route('/success')
def success():
    if 'user_id' not in session:
        return redirect('/')
    return render_template('success.html')

@app.route('/logout')
def logout():
    session.clear()
    return redirect('/')

app.run(debug=True)