package com.klinik.laporan.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LaporanRequest", description = "Payload untuk membuat laporan keuangan klinik gigi")
public class LaporanDTO {
    @Schema(description = "ID jadwal pemeriksaan", example = "jadwal-001")
    private String idJadwal;

    @Schema(description = "Nama pasien", example = "Siti Aminah")
    private String namaPasien;

    @Schema(description = "Biaya tindakan perawatan", example = "250000")
    private Double hargaTindakan;

    @Schema(description = "Total biaya obat yang dipakai", example = "120000")
    private Double totalHargaObat;

    @Schema(description = "Metode pembayaran", example = "TUNAI")
    private String metodePembayaran;

    public LaporanDTO() {}

    public LaporanDTO(String idJadwal, String namaPasien, Double hargaTindakan, 
                      Double totalHargaObat, String metodePembayaran) {
        this.idJadwal = idJadwal;
        this.namaPasien = namaPasien;
        this.hargaTindakan = hargaTindakan;
        this.totalHargaObat = totalHargaObat;
        this.metodePembayaran = metodePembayaran;
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

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
}
