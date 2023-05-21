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

@app.route('/customer')
def all_customers():
    encoded = request.headers.get('token')
    print(encoded)
    if encoded:
        username = str(base64.b64decode(bytes(encoded,'utf-8')))[2:-1]
        print(username,"hehe")
        return jsonify(get_customers(username))

# @app.route('/item')
# def all_items():
#     return jsonify(get_items())

@app.route('/item/<id_category>')
def item_per_category(id_category):
    return jsonify(get_items_category(id_category))

@app.route('/stock')
def get_item_stock():
    return jsonify(get_all_stock())

@app.route('/checkout',methods=['POST'])
def checkout():
    if request.method == 'POST':
        # print('hello2')
        data = request.get_json()
        customer_id = data["customerId"]
        items = data["cart"]
        return jsonify(add_transaction(customer_id,items))

@app.route('/sent_order/<id>')
def send_order(id):
    return jsonify(sent_transaction(id))

@app.route('/cancel_order/<id>')
def cancle_order(id):
    return jsonify(cancle_transaction(id))

@app.route('/transaction')
def all_transaction():
    return jsonify(get_all_transaction())

@app.route('/item', methods=['GET'])
def get_item():
    if request.method == 'GET':
        if "q" in request.args:
            search = request.args["q"]
            print(search)
            return jsonify(search_item_name(search))
        else:
            return jsonify(get_items())



if __name__ == '__main__':
    # initiate_table()
    hostname = socket.gethostname()
    ip_address = socket.gethostbyname(hostname)
    app.run( port=5000, debug=True, host="0.0.0.0")