package com.klinik.jadwal.controller;

import com.klinik.jadwal.model.JadwalModel;
import com.klinik.jadwal.service.JadwalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jadwal")
public class JadwalController {
    @Autowired
    private JadwalService js;

    @PostMapping
    public boolean createJadwal(@RequestBody JadwalModel jadwal) {
        return js.createJadwal(jadwal);
    }

    @GetMapping
    public List<JadwalModel> getAllJadwal() {
        return js.getAllJadwal();
    }
}

