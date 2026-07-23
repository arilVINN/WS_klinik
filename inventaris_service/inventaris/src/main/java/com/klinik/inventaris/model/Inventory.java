package com.klinik.inventaris.model;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;
    private String category;
    private Integer price;

    // --- Methods dari Class Diagram ---
    public int cekStok() {
        return this.quantity != null ? this.quantity : 0;
    }

    public boolean kurangiStok(int jumlah) {
        if (this.quantity != null && this.quantity >= jumlah) {
            this.quantity -= jumlah;
            return true;
        }
        return false;
    }

    public int getHargaObat() {
        return this.price != null ? this.price : 0;
    }
}
