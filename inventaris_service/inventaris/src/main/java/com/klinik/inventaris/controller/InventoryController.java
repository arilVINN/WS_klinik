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
@Tag(name = "Inventaris", description = "API untuk mengelola stok alat dan obat di klinik gigi")
public class InventoryController {

    @Autowired
    private InventoryService service;

    // URL: GET http://localhost:61/inventaris
    @GetMapping
    @Operation(summary = "Daftar seluruh inventaris", description = "Mengambil semua item stok untuk kebutuhan klinik gigi")
    public List<InventoryResponseDTO> getAll() {
        return service.getAllInventory();
    }

    // URL: GET http://localhost:61/inventaris/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Detail inventaris", description = "Mengambil data satu item inventaris berdasarkan ID")
    public InventoryResponseDTO getById(@PathVariable Long id) {
        return service.getInventoryById(id);
    }

    // URL: POST http://localhost:61/inventaris
    @PostMapping
    @Operation(summary = "Tambah item inventaris", description = "Membuat item inventaris baru untuk kebutuhan klinik gigi")
    public InventoryResponseDTO create(@RequestBody InventoryCreateDTO dto) {
        return service.createInventory(dto);
    }

    // URL: PATCH http://localhost:61/inventaris/{id}/restock
    @PatchMapping("/{id}/restock")
    @Operation(summary = "Restok inventaris", description = "Menambah jumlah stok item inventaris setelah pembelian atau penerimaan barang")
    public InventoryResponseDTO restock(@PathVariable Long id, @RequestBody RestockDTO dto) {
        return service.restock(id, dto);
    }

    // URL: PATCH http://localhost:61/inventaris/{id}/reduce-stock
    @PatchMapping("/{id}/reduce-stock")
    @Operation(summary = "Kurangi stok inventaris", description = "Mengurangi stok item inventaris setelah pemakaian atau pengeluaran")
    public InventoryResponseDTO reduceStock(@PathVariable Long id, @RequestBody RestockDTO dto) {
        return service.reduceStock(id, dto);
    }
}
