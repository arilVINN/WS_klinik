package com.klinik.inventaris.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "InventoryResponseDTO", description = "DTO untuk menampilkan data item inventaris")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {
    @Schema(description = "ID unik item inventaris", example = "1")
    private Long id;

    @Schema(description = "Nama item", example = "Stetoskop")
    private String name;

    @Schema(description = "Jumlah stok item", example = "10")
    private Integer quantity;

    @Schema(description = "Kategori item inventaris", example = "Alat Medis")
    private String category;

    @Schema(description = "Harga satuan item", example = "120000")
    private Integer price;
}
