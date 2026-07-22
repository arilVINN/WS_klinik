# link postman
https://spaceflight-geoscientist-94013943-s-team.postman.co/workspace/My-Workspace~da59181a-70b1-4ae8-8b64-eb3e94e81c31/collection/38292278-8d010770-465e-45c3-b11d-c53be261c01f?action=share&source=copy-link&creator=38292278



# Panduan Testing API di Postman

Berikut adalah cara untuk menguji masing-masing endpoint dari **Resep Service** (Port `62`) dan **Laporan Service** (Port `64`) menggunakan Postman.

---

## 1. Resep Service (Port: 62)

### A. Tambah Resep Baru (POST)
Endpoint ini digunakan untuk menambahkan resep obat baru. Sistem akan otomatis menghitung `totalHargaObat` (hargaSatuan × jumlahObat) dan mengirim HTTP request ke *Inventaris Service* untuk mengurangi stok.
* **Method**: `POST`
* **URL**: `http://localhost:62/api/v1/resep/add`
* **Headers**: `Content-Type: application/json`
* **Body (raw - JSON)**:
```json
{
  "idJadwal": 105,
  "penyakitKeluhan": "Gigi Berlubang Parah",
  "idObat": 5,
  "namaObat": "Paracetamol",
  "jumlahObat": 2,
  "namaPasien": "Budi Santoso",
  "dosisAturanPakai": "3x1 setelah makan",
  "hargaSatuan": 20000.0
}
```

### B. Ambil Semua Resep (GET)
* **Method**: `GET`
* **URL**: `http://localhost:62/api/v1/resep/getall`
* **Body**: *(Kosong / None)*

### C. Ambil Resep berdasarkan ID (GET)
* **Method**: `GET`
* **URL**: `http://localhost:62/api/v1/resep/get/ISI_DENGAN_ID_RESEP`
* **Body**: *(Kosong / None)*

### D. Update Resep (PUT)
Ubah parameter `id` di URL dengan ID resep yang ingin diupdate (didapat dari hasil GET/POST sebelumnya).
* **Method**: `PUT`
* **URL**: `http://localhost:62/api/v1/resep/update?id=ISI_DENGAN_ID_RESEP`
* **Headers**: `Content-Type: application/json`
* **Body (raw - JSON)**: (Bisa mengirim hanya kolom yang ingin diubah)
```json
{
  "dosisAturanPakai": "2x1 pagi dan malam",
  "namaObat": "Paracetamol 500mg"
}
```

### E. Ambil Resep berdasarkan ID Jadwal (GET)
* **Method**: `GET`
* **URL**: `http://localhost:62/api/v1/resep/jadwal/101`
* **Body**: *(Kosong / None)*

### F. Ambil Resep berdasarkan Nama Pasien (GET)
Pencarian bersifat *case-insensitive* dan *partial match* (mengandung kata tersebut).
* **Method**: `GET`
* **URL**: `http://localhost:62/api/v1/resep/pasien/budi`
* **Body**: *(Kosong / None)*

### G. Hapus Resep (DELETE)
* **Method**: `DELETE`
* **URL**: `http://localhost:62/api/v1/resep/delete/ISI_DENGAN_ID_RESEP`

---

## 2. Laporan Service (Port: 64)

### A. Generate Laporan Keuangan Baru (POST)
Endpoint ini digunakan ketika pasien selesai melakukan pembayaran. Sistem akan otomatis menghitung `grandTotal` (hargaTindakan + totalHargaObat).
* **Method**: `POST`
* **URL**: `http://localhost:64/api/v1/laporan/generate`
* **Headers**: `Content-Type: application/json`
* **Body (raw - JSON)**:
```json
{
  "idJadwal": 105,
  "namaPasien": "Andi Syahputra",
  "hargaTindakan": 150000.0,
  "totalHargaObat": 40000.0,
  "metodePembayaran": "QRIS"
}
```

### B. Ambil Semua Laporan (GET)
* **Method**: `GET`
* **URL**: `http://localhost:64/api/v1/laporan/getall`
* **Body**: *(Kosong / None)*

### C. Ambil Laporan berdasarkan ID (GET)
* **Method**: `GET`
* **URL**: `http://localhost:64/api/v1/laporan/get/ISI_DENGAN_ID_LAPORAN`
* **Body**: *(Kosong / None)*

### D. Update Laporan (PUT)
Ubah parameter `id` di URL dengan ID laporan yang ingin diupdate. Sistem akan otomatis menghitung ulang `grandTotal` jika `hargaTindakan` atau `totalHargaObat` diperbarui.
* **Method**: `PUT`
* **URL**: `http://localhost:64/api/v1/laporan/update?id=ISI_DENGAN_ID_LAPORAN`
* **Headers**: `Content-Type: application/json`
* **Body (raw - JSON)**:
```json
{
  "metodePembayaran": "Cash",
  "hargaTindakan": 160000.0
}
```

### E. Lihat Rekapitulasi & Summary Keuangan (GET)
Melihat total pendapatan (Tindakan, Obat, dan Keseluruhan).
* **Method**: `GET`
* **URL**: `http://localhost:64/api/v1/laporan/summary`

### F. Ambil Laporan berdasarkan ID Jadwal (GET)
* **Method**: `GET`
* **URL**: `http://localhost:64/api/v1/laporan/jadwal/101`
* **Body**: *(Kosong / None)*

### G. Hapus Laporan (DELETE)
* **Method**: `DELETE`
* **URL**: `http://localhost:64/api/v1/laporan/delete/ISI_DENGAN_ID_LAPORAN`

---

## Tips Tambahan: Reset Data Dummy
Jika Anda tidak ingin mengetik manual dan ingin langsung mendapatkan data awalan, jalankan ini di Postman:
* **Reset Data Resep (POST)**: `http://localhost:62/api/v1/resep/reset-dummy`
* **Reset Data Laporan (POST)**: `http://localhost:64/api/v1/laporan/reset-dummy`
