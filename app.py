from flask import Flask, render_template,request,session,redirect,url_for,flash,send_from_directory
from flask_cors import CORS
from sales_db import *
import base64
import socket
import os

# app = Flask(__name__)


APP_ROOT = os.path.dirname(os.path.abspath(__file__))
app.config['UPLOADED_PHOTOS_DEST'] = os.path.join(APP_ROOT, 'images')
CORS(app)

@app.route('/login',methods=['POST','GET'])
def verify_admin():
    # print("hello")
    if request.method == 'POST':
        # print('hello2')
        data = request.get_json()
        # print(data)
        # print(data['username'])
        curr_username  = data['username']
        curr_password  = data['password']
        return jsonify(login_sales(curr_username,curr_password))
    return

@app.route('/customer/<encoded>')
def all_customers(encoded):
    username = str(base64.b64decode(bytes(encoded)))[2:-1]
    return jsonify(get_customers(username))

@app.route('/item/<encoded>')
def all_items(encoded):
    return jsonify(get_items())


if __name__ == '__main__':
    # initiate_table()
    hostname = socket.gethostname()
    ip_address = socket.gethostbyname(hostname)
    app.run( port=5000, debug=True, threaded=False)