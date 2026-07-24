package com.klinik.resep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "resep")
@Schema(name = "Resep", description = "Data resep obat pasien klinik gigi")
public class ResepModel {

    @Id
    @Schema(description = "ID resep", example = "resep-001")
    private String id;

    @Schema(description = "ID jadwal pemeriksaan", example = "jadwal-001")
    private String idJadwal;

    @Schema(description = "Keluhan atau penyakit pasien", example = "Gigi berlubang")
    private String penyakitKeluhan;

    @Schema(description = "ID obat", example = "10")
    private Long idObat;

    @Schema(description = "Nama obat", example = "Amoxicillin")
    private String namaObat;

    @Schema(description = "Jumlah obat", example = "10")
    private Integer jumlahObat;

    @Schema(description = "Nama pasien", example = "Siti Aminah")
    private String namaPasien;

    @Schema(description = "Dosis dan aturan pakai", example = "3x1 sehari setelah makan")
    private String dosisAturanPakai;

    @Schema(description = "Harga satuan obat", example = "15000")
    private Double hargaSatuan;

    @Schema(description = "Total harga obat", example = "150000")
    private Double totalHargaObat;

    @Schema(description = "Tanggal pembuatan resep", example = "2026-07-24T09:00:00")
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
