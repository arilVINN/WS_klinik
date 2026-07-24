# Web Service Studi Kasus Klinik Gigi

## Deskripsi Project
Proyek ini adalah implementasi web service untuk sistem manajemen klinik khusus gigi berbasis arsitektur microservice. Sistem ini dirancang untuk menangani proses operasional klinik mulai dari inventaris barang dan obat, penjadwalan pasien, pembuatan resep obat, hingga pelaporan keuangan.

Project ini menggunakan pendekatan service-oriented architecture dengan beberapa layanan terpisah yang saling terhubung melalui API Gateway dan Eureka Service Discovery.

## Tujuan Sistem
Sistem ini bertujuan untuk membantu klinik gigi dalam:
- Mengelola inventaris barang, alat, dan obat secara terintegrasi.
- Mengatur jadwal pemeriksaan pasien.
- Membuat resep obat berdasarkan hasil pemeriksaan dokter.
- Mencatat transaksi keuangan selama proses pemeriksaan pasien.
- Menyediakan layanan web service yang terdistribusi dan mudah dikembangkan.

## Teknologi yang Digunakan
- Bahasa pemrograman: Java
- Framework backend: Spring Boot
- Database:
  - MySQL untuk data relasional utama
  - MongoDB untuk data dokumen yang fleksibel
  - Firestore (Google) untuk data real-time atau data pendukung aplikasi
- Arsitektur:
  - DTO (Data Transfer Object)
  - API Gateway
  - Eureka Server
- Tools pendukung:
  - Maven
  - Postman / Swagger (opsional)

## Arsitektur Sistem
Sistem ini terdiri dari beberapa service independen:
- API Gateway sebagai pintu masuk utama semua request client.
- Eureka Server sebagai service discovery agar service dapat saling menemukan.
- Beberapa microservice yang menangani domain tertentu.

### Alur Sistem
1. Client mengakses API Gateway.
2. API Gateway meneruskan request ke service yang sesuai.
3. Service bekerja menggunakan database yang relevan.
4. Hasil response dikembalikan ke client melalui API Gateway.

## Daftar Service

### 1. Inventaris Service
Service ini menangani pengelolaan barang, alat, dan obat klinik.

Fitur utama:
- Menyimpan data barang dan obat.
- Mengatur stok barang dan obat.
- Mengurangi stok obat saat pasien diperiksa atau saat obat digunakan.
- Memastikan inventaris selalu sinkron dengan aktivitas pelayanan klinik.

Contoh data yang diolah:
- Nama barang / alat
- Jumlah stok
- Harga satuan
- Kategori barang / obat
- Status ketersediaan

### 2. Jadwal Service
Service ini menangani data jadwal pemeriksaan pasien.

Fitur utama:
- Menyimpan data pasien yang akan diperiksa.
- Menentukan jadwal pemeriksaan.
- Menentukan dokter atau petugas yang menangani pasien.
- Mengupdate status pasien setelah proses pemeriksaan dan pemberian resep.

Contoh data yang diolah:
- Nama pasien
- Nama dokter / petugas
- Jadwal pemeriksaan
- Status pemeriksaan
- Keterangan hasil kunjungan

### 3. Resep Obat Service
Service ini menangani pembuatan resep obat sesuai hasil pemeriksaan dokter.

Fitur utama:
- Menyimpan resep obat yang diberikan dokter.
- Menghubungkan resep dengan obat yang ada di inventaris.
- Mengurangi stok obat saat resep diproses.
- Menyediakan data resep untuk kebutuhan pemeriksaan dan administrasi.

Contoh data yang diolah:
- ID pasien
- Nama dokter
- Daftar obat yang diresepkan
- Dosis dan aturan pakai
- Tanggal pembuatan resep

### 4. Laporan Keuangan Service
Service ini menangani pencatatan dan pelaporan transaksi keuangan klinik.

Fitur utama:
- Mencatat transaksi pembayaran selama proses pemeriksaan pasien.
- Menyimpan data biaya pemeriksaan, obat, dan layanan tambahan.
- Menghasilkan laporan keuangan harian, bulanan, atau periodik.
- Membantu pihak klinik dalam memantau pendapatan dan pengeluaran.

Contoh data yang diolah:
- ID pasien
- Jenis transaksi
- Jumlah biaya
- Metode pembayaran
- Tanggal transaksi

## Penyimpanan Data (Polyglot Persistence)
Sistem ini mengimplementasikan konsep *Polyglot Persistence*, yaitu penggunaan berbagai jenis database sesuai dengan karakteristik dan kebutuhan masing-masing *service*.

### 1. MySQL (Inventaris Service)
Digunakan khusus untuk data yang bersifat relasional dan membutuhkan konsistensi transaksi (ACID) yang ketat.
- **Kenapa MySQL:** Inventaris obat membutuhkan sistem transaksi yang kaku untuk mencegah *race condition* (seperti stok minus akibat akses bersamaan) dan menjaga integritas relasi antar data (Obat, Kategori, Supplier).
- **Kenapa bukan NoSQL (Firebase/MongoDB):** NoSQL umumnya menggunakan sistem *eventual consistency* yang berisiko menyebabkan hilangnya penguncian transaksi (*lost update*) pada perhitungan stok yang sangat krusial. Selain itu, manajemen relasi *(JOIN)* di NoSQL lebih rumit untuk data yang sangat terstruktur seperti inventaris.

### 2. Firestore / Firebase (Jadwal Service)
Digunakan untuk data antrean dan jadwal pemeriksaan yang membutuhkan pembaruan *real-time*.
- **Kenapa Firebase:** Memiliki kemampuan *real-time sync* dan *push-based update*. Saat dokter mengubah status pemeriksaan pasien, informasi antrean di layar resepsionis dan pasien akan ter-update seketika tanpa perlu *refresh*.
- **Kenapa bukan SQL/MongoDB:** MySQL dan MongoDB bersifat *pull-based*. Jika digunakan untuk antrean *real-time*, aplikasi harus melakukan *polling* (menarik data berulang-ulang setiap detik) yang akan memberikan beban (*overhead*) sangat besar pada server database.

### 3. MongoDB (Resep & Laporan Service)
Digunakan untuk data berbentuk dokumen yang strukturnya fleksibel (*schema-less*) dan membutuhkan kecepatan pembacaan tinggi.
- **Kenapa MongoDB untuk Resep:** Sebuah resep memiliki bentuk yang dinamis (jumlah obat dan instruksi bisa berbeda-beda untuk tiap pasien). MongoDB memungkinkan seluruh resep disimpan dalam satu dokumen JSON utuh.
- **Kenapa MongoDB untuk Laporan:** Laporan adalah agregasi (gabungan) snapshot data dari berbagai service. Menyimpan dokumen rekapitulasi data yang besar dan tidak berskema sangat optimal di MongoDB.
- **Kenapa bukan MySQL:** Menyimpan resep dinamis di MySQL mengharuskan pembuatan banyak tabel dan query `JOIN` berlapis yang lambat dan kaku. Menyimpan rangkuman dokumen agregasi kompleks (seperti laporan) di tabel SQL akan menghilangkan manfaat relasional SQL itu sendiri.

## DTO (Data Transfer Object)
Setiap service menggunakan DTO untuk memisahkan model data internal dengan format data yang dikirimkan melalui API. Hal ini berguna untuk:
- Menjaga keamanan data
- Mempermudah validasi input
- Menyederhanakan komunikasi antar service

## API Gateway dan Eureka

### API Gateway
API Gateway berfungsi sebagai satu titik akses untuk semua service. Dengan gateway, client tidak perlu menghubungi service secara langsung.

### Eureka Server
Eureka Server digunakan untuk service discovery, sehingga setiap service dapat saling mengenali dan terhubung secara dinamis.

## Struktur Project
Berikut adalah struktur utama project yang digunakan:
- api_gateaway/ - layanan API Gateway
- eureka_server/ - layanan service discovery
- inventaris_service/ - layanan inventaris barang dan obat
- jadwal_service/ - layanan jadwal pemeriksaan pasien
- resep_service/ - layanan pengelolaan resep obat
- laporan_service/ - layanan laporan keuangan

## Kesimpulan
Sistem ini merupakan contoh implementasi web service untuk klinik khusus gigi berbasis microservice. Dengan pemisahan layanan berdasarkan domain, sistem menjadi lebih modular, scalable, dan mudah dikembangkan sesuai kebutuhan klinik di masa depan.
