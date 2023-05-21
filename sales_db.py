from flask import Flask,url_for,jsonify
from flask_sqlalchemy import SQLAlchemy
import base64
from datetime import datetime
from flask_cors import CORS
import pytz # $ pip install pytz

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
        self.id_sales = id_sales
        self.name = name
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
    date = db.Column('date',db.String(100))
    id_customer = db.Column('id_customer',db.Integer)
    total_price = db.Column('total_price',db.Integer)
    status = db.Column('status',db.String(100))
    def __init__(self,date, id_customer, total_price,status):
        self.date = date
        self.id_customer = id_customer
        self.total_price = total_price
        self.status = status

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
    id_inventory = db.Column('id_inventory',db.Integer,primary_key=True)
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
        adm3 = Sales('Udeytama','udey','333')
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
        items = [[1,"Ayam Goreng","static/foods/ayamgoreng.jpg",17000],
                 [1,"Nasi Goreng","static/foods/nasigoreng.jpg",15000],
                 [1,"Mie Goreng","static/foods/miegoreng.jpg",15000],
                 [1,"Kwetiau goreng","static/foods/kwetiau-goreng.jpeg",17000],
                 [1,"Sate Ayam","static/foods/sateayam.jpg",15000],
                 [2,"Boba Drink","static/beverages/bobadrink.jpg",12000],
                 [2,"Matcha Latte","static/beverages/matchalatte.jpg",10000],
                 [2,"Taro Milk Tea","static/beverages/boba-taro.jpg",12000],
                 [2,"Lemon Tea","static/beverages/lemontea.jpg",10000],
                 [2,"Lychee Tea","static/beverages/lycheetea.jpg",10000],
                 [3,"Jamur Goreng","static/snacks/jamurgoreng.jpg",10000],
                 [3,"Pisang Goreng","static/snacks/pisanggoreng.jpg",10000],
                 [3,"Tahu Goreng","static/snacks/tahugoreng.jpg",10000],
                 [3,"Tempe Goreng","static/snacks/tempegoreng.jpg",10000]
                 [3,"Bakwan","static/snacks/bakwan.jpg",10000]]
        for id,nama,url,harga in items:
            item = Item(id,nama,url,harga)
            db.session.add(item)
            db.session.commit()
        customers = [[1,"Renata","0000002313","RMCI"],
                     [2,"Jen","123231231","KOS"],
                     [2,"Jejes","39394990209","situ lah poko"],
                     [1,"Memet","393030929232","disitu juga"],
                     [3,"Jabez","92938939239","apart"],
                     [3,"Anugerah","77777777777","titip mama udey"]]
# harga belum nambah
        for name,sales,phone,address in customers:
            customer = Customer(name,sales,phone,address)
            db.session.add(customer)
            db.session.commit()
        inventories = [[1,100,0,100],
                       [2,100,0,100],
                       [3,100,0,100],
                       [4,100,0,100],
                       [5,100,0,100],
                       [6,100,0,100],
                       [7,100,0,100],]
        for id_item, warehouse_stock, order_qty, available_qty in inventories:
            inventory = Inventory(id_item, warehouse_stock, order_qty, available_qty)
            db.session.add(inventory)
            db.session.commit()

# FUNCTIONAL

def login_sales(username,password):
    with app.app_context():
        print("masuk login")
        adm = db.session.query(Sales.name, Sales.id_sales, Sales.username).filter(Sales.username==username,Sales.password==password).all()
        for nama,id,username in adm:
            print(username)
            print(base64.b64encode(bytes(username,'utf-8')))
            data = {
                'nama':nama,
                'id':id,
                'status':'success',
                'encoded': str(base64.b64encode(bytes(username,'utf-8')))[2:-1],
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
        print(customers)
        for id_customer ,name ,phone ,address in customers:
            data = {
                'id_customer' : id_customer,
                'name':name,
                'phone':phone,
                'address':address,
            }
            print("List Customer berhasil ditampilkan")
            list_customers.append(data)
        return list_customers

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
        print(len(list_items))
        return list_items

def check_stock(id_item):
    with app.app_context():
        stock = db.session.query(Inventory.available_qty).join(Item,Inventory.id_item == Item.id_item).filter(Inventory.id_item==id_item).all()
        return stock[0][0] #?

def when_checkout(id_item,order_qty,available_qty): #?
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

def get_items_category(id_category):
    with app.app_context():
        items = db.session.query(Item.id_item,Category.name,Item.name,Item.picture_url,Item.price).join(Category,Category.id_category == Item.id_category).filter(Item.id_category==id_category).all()
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
        print(len(list_items))
        return list_items

def get_all_stock():
    with app.app_context():
        items = db.session.query(Item.name,Item.id_item,Inventory.warehouse_stock,Inventory.order_qty,Inventory.available_qty).join(Inventory,Inventory.id_item == Item.id_item).all()
        list_items = []
        for item_name, item_id, warehouse_stock, order_qty, available_qty in items:
            data = {
                'item_name' : item_name,
                'item_id':item_id,
                'warehouse_stock':warehouse_stock,
                'order_qty':order_qty,
                'available_qty':available_qty,
            }
            list_items.append(data)
        print(len(list_items))
        return list_items

def find_item_price(item_id):
    with app.app_context():
        price = db.session.query(Item.price).filter(Item.id_item == item_id).all()
        print(price)
        return price[0][0]
    
def add_transaction(customer_id,items):
    with app.app_context():
        price = 0
        for item in items:
            price += find_item_price(item["id_item"])*item["qty"]
            inv = Inventory.query.filter_by(id_item = item["id_item"]).first()
            inv.available_qty -= item["qty"]
            inv.order_qty += item["qty"]
        trans = Transaction(datetime.fromtimestamp(0, pytz.timezone('US/Pacific')),customer_id,price,"Processed")
        db.session.add(trans)
        db.session.flush()
        id_trans = trans.id_transaction
        for item in items:
            det_trans = Det_Transaction(id_trans,item["id_item"],item["qty"])
            db.session.add(det_trans)
        db.session.commit()
        resp = {
            "status" : "success",
            "id_transaction" : id_trans,
            "total" : price
        }
        print(resp)
        return resp
    
def cancle_transaction(trans_id):
    with app.app_context():
        trans = Transaction.query.filter_by(id_transaction = trans_id).first()
        if trans:
            dets = Det_Transaction.query.filter_by(id_transaction = trans.id_transaction).all()
            for det in dets:
                inv = Inventory.query.filter_by(id_item = det.id_item).first()
                inv.available_qty += det.qty
                inv.order_qty -= det.qty
            trans.status = "Canceled"
            db.session.commit()
            return {"status":"success"}
        return {"status":"failed"}
    

def sent_transaction(trans_id):
    with app.app_context():
        trans = Transaction.query.filter_by(id_transaction = trans_id).first()
        if trans:
            dets = Det_Transaction.query.filter_by(id_transaction = trans.id_transaction).all()
            for det in dets:
                inv = Inventory.query.filter_by(id_item = det.id_item).first()
                inv.order_qty -= det.qty
                inv.warehouse_stock -= det.qty
            trans.status = "Sent"
            db.session.commit()
            return {"status":"success"}
        return {"status":"failed"}
    
def get_all_transaction():
    with app.app_context():
        transactions = db.session.query(Customer.name, Transaction.id_transaction, Transaction.total_price, Transaction.status, Transaction.date).join(Transaction,Customer.id_customer == Transaction.id_customer).all()
        list_transactions = []
        for name, id, total, status, date in transactions:
            data = {
                'name' : name,
                'id':id,
                'total':total,
                'status':status,
                'date': date,
            }
            list_transactions.append(data)
        return list_transactions

def search_item_name(name):
    with app.app_context():
        print(name)
        search = "%{}%".format(name)
        all_item = db.session.query(Item.id_item,Category.name,Item.name,Item.picture_url,Item.price).join(Category,Category.id_category == Item.id_category).filter(Item.name.like(search)).all()
        list_items = []
        for id_item, category, name, picture_url, price in all_item:
            stock = check_stock(id_item)
            data = {
                'id_item' : id_item,
                'category' : category,
                'name':name,
                'picture_url':picture_url,
                'price':price,
                'stock':stock,
            }
            list_items.append(data)
        return list_items
    

def search_transaction(trans):
    with app.app_context():
        search = "%{}%".format(trans)
        all_transaction = db.session.query(Customer.name, Transaction.id_transaction, Transaction.total_price, Transaction.status, Transaction.date).join(Transaction,Customer.id_customer == Transaction.id_customer).filter(Item.name.like(search)).all()
        list_transactions = []
        for name, id, total, status, date in all_transaction:
            data = {
                'name' : name,
                'id':id,
                'total':total,
                'status':status,
                'date': date,
            }
            list_transactions.append(data)
        return list_transactions

# COBA
initiate_table()