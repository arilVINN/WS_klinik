```mermaid
graph TD
    %% Definisi Aktor & Infrastruktur Utama
    Client[📱 Client App / Web / Mobile]
    Gateway[🌐 API Gateway <br> Port: 67]
    Eureka[🔍 Eureka Server <br> Service Discovery - Port: 90]

    %% Subgraph Service Inventaris Obat (MySQL)
    subgraph Serv_Inventaris [📦 Service Inventaris]
        Inv_Ctrl[Inventaris Obat Controller]
        Inv_DTO[Inventaris Obat DTO Layer]
        Inv_Repo[Inventaris Obat Repository <br> Spring Data JPA]
        Inv_Model[Model: InventarisObat]
    end

    %% Subgraph Service Jadwal (Firestore)
    subgraph Serv_Jadwal [📅 Service Jadwal]
        Jad_Ctrl[Jadwal Controller]
        Jad_DTO[DTO Layer]
        Jad_Repo[Jadwal Repository <br> Firestore SDK]
        Jad_Model[Model: Jadwal]
    end

    %% Subgraph Service Resep (Mongo - Spring Data)
    subgraph Serv_Resep [💊 Service Resep]
        Res_Ctrl[Resep Controller]
        Res_DTO[DTO Layer]
        Res_Repo[Resep Repository <br> Spring Data Mongo]
        Res_Model[Model: Resep]
    end

    %% Subgraph Service Laporan (Mongo - MongoTemplate)
    subgraph Serv_Laporan [📊 Service Laporan]
        Lap_Ctrl[Laporan Controller]
        Lap_DTO[DTO Layer]
        Lap_Repo[Laporan Repository <br> MongoTemplate]
        Lap_Model[Model: Laporan]
    end

    %% Definisi Database
    DB_MySQL[(🗄️ MySQL Database <br> Database-per-Service)]
    DB_Firestore[(☁️ Firebase Firestore <br> NoSQL Cloud)]
    DB_Mongo[(🍃 Shared MongoDB <br> Shared Database)]

    %% Hubungan Routing & Registrasi
    Client -->|HTTP Requests| Gateway
    Gateway -.->|Fetch Routes| Eureka
    
    %% Registrasi ke Eureka
    Inv_Ctrl -.->|Register| Eureka
    Jad_Ctrl -.->|Register| Eureka
    Res_Ctrl -.->|Register| Eureka
    Lap_Ctrl -.->|Register| Eureka

    %% Routing dari Gateway ke Controller
    Gateway --->|/api/v1/inventaris-obat/**| Inv_Ctrl
    Gateway --->|/api/v1/jadwal/**| Jad_Ctrl
    Gateway --->|/api/v1/resep/**| Res_Ctrl
    Gateway --->|/api/v1/laporan/**| Lap_Ctrl

    %% Aliran Layering Internal
    Inv_Ctrl --> Inv_DTO --> Inv_Repo --> Inv_Model
    Jad_Ctrl --> Jad_DTO --> Jad_Repo --> Jad_Model
    Res_Ctrl --> Res_DTO --> Res_Repo --> Res_Model
    Lap_Ctrl --> Lap_DTO --> Lap_Repo --> Lap_Model

    %% Koneksi ke Database
    Inv_Repo ====>|JPA / SQL| DB_MySQL
    Jad_Repo ====>|NoSQL SDK| DB_Firestore
    Res_Repo ====>|NoSQL / Spring Data| DB_Mongo
    Lap_Repo ====>|NoSQL / MongoTemplate| DB_Mongo

    %% Styling Diagram
    classDef gateway fill:#2c3e50,stroke:#34495e,stroke-width:2px,color:#fff;
    classDef eureka fill:#d35400,stroke:#e67e22,stroke-width:2px,color:#fff;
    classDef db fill:#27ae60,stroke:#2ecc71,stroke-width:2px,color:#fff;
    classDef client fill:#7f8c8d,stroke:#95a5a6,stroke-width:2px,color:#fff;

    class Gateway gateway;
    class Eureka eureka;
    class DB_MySQL,DB_Firestore,DB_Mongo db;
    class Client client;
```
jadi 
inevstaris berisi tentang list obat obatan dan harga obat

jadwal berisi tentang pasien,jadwal periksa, periksa/tindakan, status periksa(belum/sudah)

resep berisi tentang resep obat setiap penyakit/setelah tindakan

laporan berisi tentang laporan nama pasien, harga periksa/tindakan , dan obatan yang dibeli dan total semua dari periksa/tindakan dan harga obat jika ada

jadi flow nya 
pasien akan bikin jadwal periksa/tindakan, jika belom status nya belom, setelah dilakukan periksa/tindakan, akan diberikan obat atau tidak, nanti status nya menjadi sudah periksa, jika ada obat yang perlu akan memberikan resep dan mengurangi stok obat di investaris, dan akan  total nya akan masuk dalam laporan keuangan.
```mermaid
erDiagram
    JADWAL ||--o{ RESEP : "diberikan"
    INVENTARIS ||--o{ RESEP : "terdapat dalam"
    JADWAL ||--|| LAPORAN : "menghasilkan"

    INVENTARIS {
        int id_obat PK
        string nama_obat
        int harga_obat
        int stok_obat
    }

    JADWAL {
        int id_jadwal PK
        string nama_pasien
        datetime jadwal_periksa
        string periksa_tindakan
        int harga_tindakan
        string status "belum / sudah"
    }

    RESEP {
        int id_resep PK
        int id_jadwal FK
        string penyakit_keluhan
        int id_obat FK
        int jumlah_obat
    }

    LAPORAN {
        int id_laporan PK
        int id_jadwal FK
        string nama_pasien
        int harga_tindakan
        int total_harga_obat
        int grand_total
    }
```
```mermaid
classDiagram
    class JADWAL {
        -int id_jadwal
        -String nama_pasien
        -datetime jadwal_periksa
        -String periksa_tindakan
        -int harga_tindakan
        -String status
        +buatJadwal() void
        +ubahStatus(statusBaru: String) void
        +getHargaTindakan() int
    }

    class INVENTARIS {
        -int id_obat
        -String nama_obat
        -int harga_obat
        -int stok_obat
        +cekStok() int
        +kurangiStok(jumlah: int) boolean
        +getHargaObat() int
    }

    class RESEP {
        -int id_resep
        -int id_jadwal
        -String penyakit_keluhan
        -int id_obat
        -int jumlah_obat
        +catatKeluhan(keluhan: String) void
        +tambahResep(idObat: int, jumlah: int) void
        +hitungTotalHargaObat() int
    }

    class LAPORAN {
        -int id_laporan
        -int id_jadwal
        -String nama_pasien
        -int harga_tindakan
        -int total_harga_obat
        -int grand_total
        +generateLaporan() void
        +kalkulasiGrandTotal() int
        +cetakTagihan() void
    }

    %% Relasi antar Class
    JADWAL "1" *-- "0..*" RESEP : memiliki
    INVENTARIS "1" <-- "0..*" RESEP : mengambil data obat
    JADWAL "1" --> "1" LAPORAN : memicu pembuatan
```
