from flask import Flask,url_for,jsonify
from flask_sqlalchemy import SQLAlchemy
import base64
from flask_cors import CORS

app = Flask(__name__)
CORS(app, resources={r"*": {"origins": "*"}})
app.secret_key = "sales_db"

app.config['SQLALCHEMY_DATABASE_URI']='mysql://root:@127.0.0.1/sales_db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS']=False
db = SQLAlchemy(app)


class Sales(db.Model):
    id_sales = db.Column('id_sales',db.Integer,primary_key=True)
    name = db.Column('name',db.String(100))
    username = db.Column('username',db.String(100))
    password = db.Column('password',db.String(10))
    def __init__(self, name, username, password): 
        self.name = name
        self.username = username
        self.password = password


class Customer(db.Model):
    id_customer = db.Column('id_customer',db.Integer,primary_key=True)
    id_sales = db.Column('id_sales',db.Integer)
    name = db.Column('name',db.String(100))
    phone = db.Column('phone',db.String(100))
    address = db.Column('address',db.String(100))
    def __init__(self,id_sales, name, phone, address): 
        self.name = name
        self.id_sales = id_sales
        self.phone = phone
        self.address = address

class Item(db.Model):
    id_item = db.Column('id_item',db.Integer,primary_key=True)
    id_category = db.Column('id_category',db.Integer)
    name = db.Column('name',db.String(100))
    picture_url = db.Column('picture_url',db.String(100))
    price = db.Column('price',db.Integer)
    def __init__(self,id_category, name, picture_url, price): 
        self.id_category = id_category
        self.name = name
        self.picture_url = picture_url
        self.price = price

class Category(db.Model):
    id_category = db.Column('id_category',db.Integer,primary_key=True)
    name = db.Column('name',db.String(100))
    def __init__(self,id_category,name):
        self.id_category = id_category
        self.name = name

class Transaction(db.Model):
    id_transaction = db.Column('id_transaction',db.Integer,primary_key=True)
    date = db.Column('date',db.Date)
    id_customer = db.Column('id_customer',db.Integer)
    total_price = db.Column('total_price',db.Integer)
    def __init__(self,date, id_customer, total_price):
        self.date = date
        self.id_customer = id_customer
        self.total_price = total_price

class Det_Transaction(db.Model):
    id_det_transaction = db.Column('id_det_transaction',db.Integer,primary_key=True)
    id_transaction = db.Column('id_transaction',db.Integer)
    id_item = db.Column('id_item',db.Integer)
    qty = db.Column('qty',db.Integer)
    def __init__(self, id_transaction, id_item, qty):
        self.id_transaction = id_transaction
        self.id_item = id_item
        self.qty = qty

class Inventory(db.Model):
    id_item = db.Column('id_item',db.Integer)
    warehouse_stock = db.Column('warehouse_stock',db.Integer)
    order_qty = db.Column('order_qty',db.Integer)
    available_qty = db.Column('available_qty',db.Integer)
    def __init__(self, id_item, warehouse_stock, order_qty, available_qty):
        self.id_item = id_item
        self.warehouse_stock = warehouse_stock
        self.order_qty = order_qty
        self.available_qty = available_qty


# DUMMY
def initiate_table():
    with app.app_context():
        db.drop_all()
        db.create_all()
        adm1 = Sales('James Patrick','patrick','111')
        adm2 = Sales('Noel Mandak','noel','222')
        adm3 = Sales('Elbert Chandra','ebe','333')
        db.session.add_all([adm1,adm2,adm3])
        db.session.commit()
        add_dummy_data()

def add_dummy_data():
    with app.app_context():
        cat = [[1,'foods'],[2,'beverages'],[3,'snacks']]
        for i,j in cat:
            kat = Category(i,j)
            db.session.add(kat)
            db.session.commit()

# FUNCTIONAL

def login_sales(username,password):
    with app.app_context():
        adm = db.session.query(Sales.name, Sales.id_sales, Sales.username).filter(Sales.username==username,Sales.password==password).all()
        for nama,id,username in adm:
            data = {
                'nama':nama,
                'id':id,
                'status':'success',
                'encoded': str(base64.b64encode(bytes(username)))[2:-1],
            }
            print("login berhasil")
            return data
        print("login gagal")
        return {'status':'failed',
        }


def get_customers(username):
    with app.app_context():
        customers = db.session.query(Customer.id_customer, Customer.name, Customer.phone, Customer.address).join(Sales,Customer.id_sales==Sales.id_sales).filter(Sales.username==username).all()
        list_customers = []
        for id_customer ,name ,phone ,address in customers:
            data = {
                'id_customer' : id_customer,
                'name':name,
                'phone':phone,
                'address':address,
            }
            print("List Customer berhasil ditampilkan")
            list_customers.append(data)
        return data

def get_items():
    with app.app_context():
        items = db.session.query(Item.id_item,Category.name,Item.name,Item.picture_url,Item.price).join(Category,Category.id_category == Item.id_category).all()
        list_items = []
        for id_item, category, name, picture_url, price in items:
            stock = check_stock(id_item)
            data = {
                'id_item' : id_item,
                'category':category,
                'name':name,
                'picture_url':picture_url,
                'price':price,
                'stock':stock,
            }
            list_items.append(data)
        return list_items

def check_stock(id_item):
    with app.app_context():
        stock = db.session.query(Inventory.available_qty).join(Item,Inventory.id_item == Item.id_item).filter(Item.id_item==id_item).all()
        return stock[0] #?

def checkout(id_item,order_qty,available_qty): #?
    with app.app_context():
        item = Inventory.query.filter_by(Inventory.id_item == id_item)
        item.order_qty = order_qty
        item.available_qty = available_qty
        db.session.commit()
        return 'Success' 

def delivered(id_item,warehouse_stock,order_qty):
    with app.app_context():
        item = Inventory.query.filter_by(Inventory.id_item == id_item)
        item.order_qty = order_qty
        item.warehouse_stock = warehouse_stock
        db.session.commit()
        return 'Success'


