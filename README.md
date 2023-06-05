# Sales Notes Apps
Aplikasi berbasis android ini adalah aplikasi yang dibuat untuk memudahkan Salesman untuk mencatat pesanan. Sales bisa memilih barang, menyeleksi sesuai kategori, mencari berdasarkan nama barang dan menentukan jumlah barang pesanan dengan mudah. Terdapat juga fitur untuk memantau stok barang, transaksi yang terjadi, dan juga detail custommer. 


Aplikasi ini dibuat dengan arsitektur MVVM dan menggunakan Flask Rest API. Ini adalah proyek UAS dari kelas IBDA3012 - Pengembangan Aplikasi Dunia Seluler - Semester Genap 2022/2023, Calvin Institute of Technology

## Anggota Kelompok:
- 202000249  -  Audrey Josephine
- 202000241  -  James Patrick Oentoro
- 202000436  -  Noel Christevent Mandak


## Petunjuk Instalasi
1. Buka XAMPP Control Panel lalu nyalakan Apache dan MySQL
2. Buka PHPMyAdmin dan buat database baru dengan nama "sales_db"
3. Buka terminal, cd ke lokasi proyek
4. Install library yang diperlukan dengan cara: pip install -r requirement.txt
5. Uncomment baris terakhir pada sales_db.py "# initiate_table()" lalu run "python sales_db.py" diterminal untuk menginisiasi tabel
6. Run "python app.py" diterminal untuk menjalankan Flask Rest API, akan muncul url dimana server dijalankan. Contohnya "* Running on http://192.168.0.105:5000"
7. Buka RetrofitInstance.kt lalu ganti BASE_URL dengan url server
8. Build and Run aplikasi dari Android Studio
9. Aplikasi siap digunakan dengan emulator ataupun ponsel android yang menyalakan usb debug


## Akun terdaftar

| Username | Password |
|----------|----------|
| patrick  | 111      |
| noel     | 222      |
| udey     | 333      |