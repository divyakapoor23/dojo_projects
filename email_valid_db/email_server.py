from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
import re

app = Flask(__name__)
app.secret_key = "Ceci n'est pas un secret."
mysql = MySQLConnector(app,'emaildb')
EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')


@app.route('/')
def index():
    return render_template('email_index.html')

@app.route('/process', methods=['POST'])
def process():
    if not EMAIL_REGEX.match(request.form['email']):
        flash("Email is not valid!") 
        return redirect('/')
    else:
        query = "INSERT INTO emails (email, created_at) VALUES (:email, NOW())"
        data = {
                'email':  request.form['email']
            }
        mysql.query_db(query, data)
        session['email'] = request.form['email']
        return redirect('/success')

@app.route('/success')
def success():
    query = "SELECT * FROM emails"                           
    emails = mysql.query_db(query) 
    return render_template('email_success.html', emails=emails, email=session['email'])

@app.route('/remove/<id>')
def delete(id):
    email = mysql.query_db("SELECT * FROM emails WHERE id={};".format(id))
    emails = mysql.query_db("SELECT * FROM emails;")
    remove = mysql.query_db("DELETE FROM emails WHERE id = {};".format(id))
    return redirect('/')

app.run(debug=True)