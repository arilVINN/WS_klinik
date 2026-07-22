package com.klinik.resep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "resep")
public class ResepModel {

    @Id
    private String id;
    private Integer idJadwal;
    private String penyakitKeluhan;
    private Integer idObat;
    private Integer jumlahObat;
    private String namaDokter;
    private String dosisAturanPakai;
    private Double hargaSatuan;
    private Double totalHargaObat;
    private LocalDateTime tanggalResep;

    public ResepModel() {
        this.tanggalResep = LocalDateTime.now();
    }

    public ResepModel(Integer idJadwal, String penyakitKeluhan, Integer idObat, Integer jumlahObat, 
                 String namaDokter, String dosisAturanPakai, Double hargaSatuan, Double totalHargaObat) {
        this.idJadwal = idJadwal;
        this.penyakitKeluhan = penyakitKeluhan;
        this.idObat = idObat;
        this.jumlahObat = jumlahObat;
        this.namaDokter = namaDokter;
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

    public Integer getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getPenyakitKeluhan() {
        return penyakitKeluhan;
    }

    public void setPenyakitKeluhan(String penyakitKeluhan) {
        this.penyakitKeluhan = penyakitKeluhan;
    }

    public Integer getIdObat() {
        return idObat;
    }

    public void setIdObat(Integer idObat) {
        this.idObat = idObat;
    }

    public Integer getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(Integer jumlahObat) {
        this.jumlahObat = jumlahObat;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
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

    public void tambahResep(int idObat, int jumlah) {
        this.idObat = idObat;
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
