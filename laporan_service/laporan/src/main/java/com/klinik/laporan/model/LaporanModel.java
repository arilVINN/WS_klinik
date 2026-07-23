package com.klinik.laporan.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "laporan")
@Schema(name = "LaporanModel", description = "Model data laporan keuangan yang disimpan di MongoDB")
public class LaporanModel {

    @Id
    @Schema(description = "ID unik laporan", example = "6495c1a2f5e4c97bd9d3d5fa")
    private String id;

    @Schema(description = "ID jadwal yang terkait dengan laporan", example = "123", required = true)
    private Integer idJadwal;

    @Schema(description = "Nama pasien", example = "Budi Santoso", required = true)
    private String namaPasien;
    private Double hargaTindakan;
    private Double totalHargaObat;
    private Double grandTotal;
    private String metodePembayaran;
    private LocalDateTime tanggalLaporan;

    public LaporanModel() {
        this.tanggalLaporan = LocalDateTime.now();
    }

    public LaporanModel(Integer idJadwal, String namaPasien, Double hargaTindakan, 
                   Double totalHargaObat, String metodePembayaran) {
        this.idJadwal = idJadwal;
        this.namaPasien = namaPasien;
        this.hargaTindakan = hargaTindakan != null ? hargaTindakan : 0.0;
        this.totalHargaObat = totalHargaObat != null ? totalHargaObat : 0.0;
        this.kalkulasiGrandTotal();
        this.metodePembayaran = metodePembayaran != null ? metodePembayaran.toUpperCase() : "TUNAI";
        this.tanggalLaporan = LocalDateTime.now();
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

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public Double getHargaTindakan() {
        return hargaTindakan;
    }

    public void setHargaTindakan(Double hargaTindakan) {
        this.hargaTindakan = hargaTindakan;
    }

    public Double getTotalHargaObat() {
        return totalHargaObat;
    }

    public void setTotalHargaObat(Double totalHargaObat) {
        this.totalHargaObat = totalHargaObat;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public LocalDateTime getTanggalLaporan() {
        return tanggalLaporan;
    }

    public void setTanggalLaporan(LocalDateTime tanggalLaporan) {
        this.tanggalLaporan = tanggalLaporan;
    }

    // --- Methods dari Class Diagram ---
    public void generateLaporan() {
        System.out.println("Laporan di-generate untuk pasien: " + this.namaPasien);
    }

    public int kalkulasiGrandTotal() {
        double calc = 0.0;
        if (this.hargaTindakan != null) calc += this.hargaTindakan;
        if (this.totalHargaObat != null) calc += this.totalHargaObat;
        this.grandTotal = calc;
        return (int) calc;
    }

    public void cetakTagihan() {
        System.out.println("Mencetak tagihan untuk " + this.namaPasien + ". Grand Total: Rp" + this.grandTotal);
    }
}
