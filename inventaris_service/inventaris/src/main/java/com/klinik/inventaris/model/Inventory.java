package com.klinik.inventaris.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventories")
@Schema(name = "Inventory", description = "Model data item inventaris yang tersimpan di database")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unik item inventaris", example = "1")
    private Long id;

    @Schema(description = "Nama item inventaris", example = "Stetoskop")
    private String name;

    @Schema(description = "Jumlah stok item", example = "10")
    private Integer quantity;

    @Schema(description = "Kategori item inventaris", example = "Alat Medis")
    private String category;

    @Schema(description = "Harga satuan item", example = "120000")
    private Integer price;
}
