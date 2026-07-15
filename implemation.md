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
