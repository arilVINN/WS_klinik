package com.klinik.inventaris.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "InventoryResponse", description = "Representasi item inventaris klinik gigi")
public class InventoryResponseDTO {
    @Schema(description = "ID item inventaris", example = "1")
    private Long id;

    @Schema(description = "Nama item inventaris", example = "Bahan bleaching")
    private String name;

    @Schema(description = "Jumlah stok tersedia", example = "20")
    private Integer quantity;

    @Schema(description = "Kategori item", example = "perawatan")
    private String category;

    @Schema(description = "Harga satuan item", example = "150000")
    private Integer price;
}
