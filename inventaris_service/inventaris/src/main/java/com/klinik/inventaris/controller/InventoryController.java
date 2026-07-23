package com.klinik.inventaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klinik.inventaris.dto.InventoryCreateDTO;
import com.klinik.inventaris.dto.InventoryResponseDTO;
import com.klinik.inventaris.dto.RestockDTO;
import com.klinik.inventaris.service.InventoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inventory")
@Tag(name = "Inventaris Service", description = "API endpoints untuk layanan inventaris klinik")
public class InventoryController {

    @Autowired
    private InventoryService service;

    // URL: GET http://localhost:61/inventory
    @Operation(summary = "Ambil semua inventaris", description = "Mengambil daftar seluruh item inventaris")
    @GetMapping
    public List<InventoryResponseDTO> getAll() {
        return service.getAllInventory();
    }

    // URL: GET http://localhost:61/inventory/{id}
    @Operation(summary = "Ambil inventaris berdasarkan ID", description = "Mengambil item inventaris berdasarkan ID")
    @GetMapping("/{id}")
    public InventoryResponseDTO getById(@PathVariable Long id) {
        return service.getInventoryById(id);
    }

    // URL: POST http://localhost:61/inventory
    @Operation(summary = "Buat item inventaris baru", description = "Menambahkan item inventaris baru ke sistem")
    @PostMapping
    public InventoryResponseDTO create(@RequestBody InventoryCreateDTO dto) {
        return service.createInventory(dto);
    }

    // URL: PATCH http://localhost:61/inventory/{id}/restock
    @Operation(summary = "Restock inventaris", description = "Menambahkan stok item inventaris berdasarkan ID")
    @PatchMapping("/{id}/restock")
    public InventoryResponseDTO restock(@PathVariable Long id, @RequestBody RestockDTO dto) {
        return service.restock(id, dto);
    }

    // URL: PATCH http://localhost:61/inventory/{id}/reduce-stock
    @Operation(summary = "Kurangi stok inventaris", description = "Mengurangi stok item inventaris berdasarkan ID")
    @PatchMapping("/{id}/reduce-stock")
    public InventoryResponseDTO reduceStock(@PathVariable Long id, @RequestBody RestockDTO dto) {
        return service.reduceStock(id, dto);
    }
}
