package com.klinik.resep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "resep")
public class ResepModel {

    @Id
    private String id;
    private String idJadwal;
    private String penyakitKeluhan;
    private Long idObat;
    private String namaObat;
    private Integer jumlahObat;
    private String namaPasien;
    private String dosisAturanPakai;
    private Double hargaSatuan;
    private Double totalHargaObat;
    private LocalDateTime tanggalResep;

    public ResepModel() {
        this.tanggalResep = LocalDateTime.now();
    }

    public ResepModel(String idJadwal, String penyakitKeluhan, Long idObat, String namaObat, Integer jumlahObat, 
                 String namaPasien, String dosisAturanPakai, Double hargaSatuan, Double totalHargaObat) {
        this.idJadwal = idJadwal;
        this.penyakitKeluhan = penyakitKeluhan;
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.jumlahObat = jumlahObat;
        this.namaPasien = namaPasien;
        this.dosisAturanPakai = dosisAturanPakai;
        this.hargaSatuan = hargaSatuan;
        this.totalHargaObat = totalHargaObat;
        this.tanggalResep = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getPenyakitKeluhan() {
        return penyakitKeluhan;
    }

    public void setPenyakitKeluhan(String penyakitKeluhan) {
        this.penyakitKeluhan = penyakitKeluhan;
    }

    public Long getIdObat() {
        return idObat;
    }

    public void setIdObat(Long idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public Integer getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(Integer jumlahObat) {
        this.jumlahObat = jumlahObat;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getDosisAturanPakai() {
        return dosisAturanPakai;
    }

    public void setDosisAturanPakai(String dosisAturanPakai) {
        this.dosisAturanPakai = dosisAturanPakai;
    }

    public Double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(Double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public Double getTotalHargaObat() {
        return totalHargaObat;
    }

    public void setTotalHargaObat(Double totalHargaObat) {
        this.totalHargaObat = totalHargaObat;
    }

    public LocalDateTime getTanggalResep() {
        return tanggalResep;
    }

    public void setTanggalResep(LocalDateTime tanggalResep) {
        this.tanggalResep = tanggalResep;
    }

    // --- Methods dari Class Diagram ---
    public void catatKeluhan(String keluhan) {
        this.penyakitKeluhan = keluhan;
    }

    public void tambahResep(Long idObat, String namaObat, int jumlah) {
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.jumlahObat = jumlah;
    }

    public int hitungTotalHargaObat() {
        if (this.totalHargaObat != null) {
            return this.totalHargaObat.intValue();
        } else if (this.hargaSatuan != null && this.jumlahObat != null) {
            return (int) (this.hargaSatuan * this.jumlahObat);
        }
        return 0;
    }
}
