package com.klinik.resep.service;

import com.klinik.resep.dto.ResepDTO;
import com.klinik.resep.model.ResepModel;
import com.klinik.resep.repository.ResepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResepService {

    @Autowired
    private ResepRepository resepRepository;

    private final WebClient webClient;

    public ResepService() {
        this.webClient = WebClient.create();
    }

    public List<ResepModel> getAllResep() {
        return resepRepository.findAll();
    }

    public Optional<ResepModel> getResepById(String id) {
        return resepRepository.findById(id);
    }

    public List<ResepModel> getResepByIdJadwal(String idJadwal) {
        return resepRepository.findByIdJadwal(idJadwal);
    }

    public List<ResepModel> getResepByNamaPasien(String namaPasien) {
        return resepRepository.findByNamaPasienContainingIgnoreCase(namaPasien);
    }

    public ResepModel simpanResep(ResepModel resep) {
        if (resep.getHargaSatuan() != null && resep.getJumlahObat() != null) {
            resep.setTotalHargaObat(resep.getHargaSatuan() * resep.getJumlahObat());
        }
        
        // Opsional: kirim notifikasi pengurangan stok obat ke inventaris_service (port 61)
        if (resep.getIdObat() != null && resep.getJumlahObat() != null) {
            try {
                webClient.patch()
                        .uri("http://localhost:61/inventory/" + resep.getIdObat() + "/reduce-stock")
                        .bodyValue(Map.of("quantity", resep.getJumlahObat()))
                        .retrieve()
                        .bodyToMono(String.class)
                        .subscribe(
                                result -> System.out.println("Pengurangan stok inventaris berhasil: " + result),
                                error -> System.err.println("Gagal update stok inventaris: " + error.getMessage())
                        );
            } catch (Exception e) {
                System.err.println("Komunikasi ke Inventaris Service skip: " + e.getMessage());
            }
        }

        return resepRepository.save(resep);
    }

    public ResepModel tambahResepDariDTO(ResepDTO dto) {
        String namaObat = dto.getNamaObat();
        Double harga = dto.getHargaSatuan() != null ? dto.getHargaSatuan() : 0.0;

        if (dto.getIdObat() != null) {
            try {
                Map<?, ?> response = webClient.get()
                        .uri("http://localhost:61/inventory/" + dto.getIdObat())
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();
                if (response != null) {
                    if (response.containsKey("name")) {
                        namaObat = (String) response.get("name");
                    }
                    if (dto.getHargaSatuan() == null && response.containsKey("price")) {
                        Number priceNum = (Number) response.get("price");
                        if (priceNum != null) {
                            harga = priceNum.doubleValue();
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Gagal mengambil info obat dari inventaris-service: " + e.getMessage());
            }
        }

        Integer jumlah = dto.getJumlahObat() != null ? dto.getJumlahObat() : 0;

        ResepModel resep = new ResepModel(
                dto.getIdJadwal(),
                dto.getPenyakitKeluhan(),
                dto.getIdObat(),
                namaObat,
                jumlah,
                dto.getNamaPasien(),
                dto.getDosisAturanPakai(),
                harga,
                null // let hitungTotalHargaObat handle it
        );
        resep.catatKeluhan(dto.getPenyakitKeluhan());
        resep.tambahResep(dto.getIdObat() != null ? dto.getIdObat() : 0L, namaObat, jumlah);
        resep.setTotalHargaObat((double) resep.hitungTotalHargaObat());

        return simpanResep(resep);
    }

    public Optional<ResepModel> updateResep(String id, ResepDTO dto) {
        Optional<ResepModel> optionalResep = resepRepository.findById(id);
        if (optionalResep.isPresent()) {
            ResepModel r = optionalResep.get();
            if (dto.getIdJadwal() != null) r.setIdJadwal(dto.getIdJadwal());
            if (dto.getPenyakitKeluhan() != null) r.setPenyakitKeluhan(dto.getPenyakitKeluhan());
            if (dto.getIdObat() != null) {
                r.setIdObat(dto.getIdObat());
                try {
                    Map<?, ?> response = webClient.get()
                            .uri("http://localhost:61/inventory/" + dto.getIdObat())
                            .retrieve()
                            .bodyToMono(Map.class)
                            .block();
                    if (response != null) {
                        if (response.containsKey("name")) {
                            r.setNamaObat((String) response.get("name"));
                        }
                        if (dto.getHargaSatuan() == null && response.containsKey("price")) {
                            Number priceNum = (Number) response.get("price");
                            if (priceNum != null) {
                                r.setHargaSatuan(priceNum.doubleValue());
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Gagal mengambil info obat dari inventaris-service saat update: " + e.getMessage());
                }
            }
            if (dto.getNamaObat() != null) r.setNamaObat(dto.getNamaObat());
            if (dto.getJumlahObat() != null) r.setJumlahObat(dto.getJumlahObat());
            if (dto.getNamaPasien() != null) r.setNamaPasien(dto.getNamaPasien());
            if (dto.getDosisAturanPakai() != null) r.setDosisAturanPakai(dto.getDosisAturanPakai());
            if (dto.getHargaSatuan() != null) r.setHargaSatuan(dto.getHargaSatuan());
            
            Double total = (r.getHargaSatuan() != null ? r.getHargaSatuan() : 0.0) * (r.getJumlahObat() != null ? r.getJumlahObat() : 0);
            r.setTotalHargaObat(total);

            return Optional.of(resepRepository.save(r));
        }
        return Optional.empty();
    }

    public boolean hapusResep(String id) {
        if (resepRepository.existsById(id)) {
            resepRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
