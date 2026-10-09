package com.muhammed.firstProject;

import com.muhammed.firstProject.dto.KitapRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MerhabaController {

    // 1. KÖPRÜ: Artık Repository yok! Controller sadece Service ile konuşur.
    private final KitapService kitapService;

    public MerhabaController(KitapService kitapService) {
        this.kitapService = kitapService;
    }

    // --- CONTROLLER SADECE YÖNLENDİRME YAPAR ---

    @GetMapping("/kitaplar")
    public ResponseEntity<List<Kitap>> tumKitaplariGetir() {
        // ResponseEntity.ok() -> Başarılı liste getirme işleminde "200 OK" kodunu yapıştırır.
        return ResponseEntity.ok(kitapService.tumKitaplariGetir());
    }

    @PostMapping("/kitaplar")
    public ResponseEntity<Kitap> kitapEkle(@Valid @RequestBody KitapRequest yeniKitapIstegi) {
        // Kapıdan sadece KitapRequest (DTO) girebilir. O da doğrulamadan (@Valid) geçer.
        Kitap eklenenKitap = kitapService.kitapEkle(yeniKitapIstegi);
        return ResponseEntity.status(HttpStatus.CREATED).body(eklenenKitap);
    }

    @DeleteMapping("/kitaplar/{id}")
    public ResponseEntity<Void> kitapSil(@PathVariable int id) {
        kitapService.kitapSil(id);
        // Silme işlemi başarılı olduğunda dönecek veri yoktur, bu yüzden "204 NO CONTENT" yapıştırılır.
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/kitaplar/{id}")
    public ResponseEntity<Kitap> kitapGuncelle(@PathVariable int id, @Valid @RequestBody KitapRequest guncelVeriler) {
        // Kapıdan sadece doğrulanan (@Valid) KitapRequest (DTO) girebilir
        Kitap guncellenenKitap = kitapService.kitapGuncelle(id, guncelVeriler);
        return ResponseEntity.ok(guncellenenKitap);
    }

    @GetMapping("/kitaplar/filtrele")
    public List<Kitap> yazaraGoreGetir(@RequestParam String yazar) {
        return kitapService.yazaraGoreGetir(yazar); // İşi Service'e devrettik
    }

    @GetMapping("/kitaplar/ara")
    public List<Kitap> ismeGoreAra(@RequestParam String kelime) {
        return kitapService.ismeGoreAra(kelime); // İşi Service'e devrettik
    }

    @GetMapping("/kitaplar/ucuzlar")
    public List<Kitap> ucuzKitaplariGetir(@RequestParam double maxFiyat) {
        return kitapService.ucuzKitaplariGetir(maxFiyat); // İşi Service'e devrettik
    }
}