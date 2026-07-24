package com.klinik.laporan.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "laporan")
@Schema(name = "Laporan", description = "Data laporan keuangan klinik gigi")
public class LaporanModel {

    @Id
    @Schema(description = "ID laporan", example = "laporan-001")
    private String id;

    @Schema(description = "ID jadwal pemeriksaan", example = "jadwal-001")
    private String idJadwal;

    @Schema(description = "Nama pasien", example = "Siti Aminah")
    private String namaPasien;

    @Schema(description = "Biaya tindakan perawatan", example = "250000")
    private Double hargaTindakan;

    @Schema(description = "Total biaya obat", example = "120000")
    private Double totalHargaObat;

    @Schema(description = "Grand total yang harus dibayar", example = "370000")
    private Double grandTotal;

    @Schema(description = "Metode pembayaran", example = "TUNAI")
    private String metodePembayaran;

    @Schema(description = "Tanggal pembuatan laporan", example = "2026-07-24T09:00:00")
    private LocalDateTime tanggalLaporan;

    public LaporanModel() {
        this.tanggalLaporan = LocalDateTime.now();
    }

    public LaporanModel(String idJadwal, String namaPasien, Double hargaTindakan, 
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

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
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
