package com.muhammed.firstProject;

import com.muhammed.firstProject.dto.KitapRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service // 1. SİHİR: Spring Boot'a "Burası benim iş kurallarımın (Business Logic) merkezidir" diyoruz.
public class KitapService {

    // 2. KÖPRÜ: Service katmanı, veritabanına ulaşmak için Repository'yi kullanır.
    private final KitapRepository kitapRepository;

    public KitapService(KitapRepository kitapRepository) {
        this.kitapRepository = kitapRepository;
    }

    // --- ASIL İŞLEMLER BURADA YAPILACAK ---

    public List<Kitap> tumKitaplariGetir() {
        return kitapRepository.findAll();
    }

    public Kitap kitapEkle(KitapRequest istek) {


        // 1. Boş bir veritabanı şablonu yarat
        Kitap yeniKitap = new Kitap();



        // 2. Kuryeden (DTO) gelen güvenli verileri şablona aktar (Buna Mapping denir)
        yeniKitap.setAd(istek.getAd());
        yeniKitap.setYazar(istek.getYazar());
        yeniKitap.setFiyat(istek.getFiyat());

        if (kitapRepository.existsByAd(istek.getAd())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Hata: '" + istek.getAd()+ "' isminde bir kitap zaten mevcut.");
        }
        else{
            // 3. Şablonu veritabanına kaydet
            return kitapRepository.save(yeniKitap);
        }


    }

    // DİKKAT: Return tipini String yerine void (hiçbir şey dönmez) yaptık
    public void kitapSil(int id) {
        if (kitapRepository.existsById(id)) {
            kitapRepository.deleteById(id);
        } else {
            // Kitap yoksa doğrudan 404 NOT FOUND fırlatıyoruz!
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinmek istenen kitap bulunamadı.");
        }
    }

    public Kitap kitapGuncelle(int id, KitapRequest guncelVeriler) {
        // Kitabı bulamazsa doğrudan bizim GlobalExceptionHandler'a düşecek 404 hatasını fırlatıyoruz
        Kitap kitap = kitapRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenmek istenen kitap bulunamadı.")
        );

        // Kitap bulunduysa, kuryeden (DTO) gelen yeni verileri şablona aktar
        kitap.setAd(guncelVeriler.getAd());
        kitap.setYazar(guncelVeriler.getYazar());
        kitap.setFiyat(guncelVeriler.getFiyat());

        // Güncellenmiş haliyle veritabanına kaydet ve döndür
        return kitapRepository.save(kitap);
    }

    // --- FİLTRELEME İŞLEMLERİ ---

    public List<Kitap> yazaraGoreGetir(String yazar) {
        return kitapRepository.findByYazar(yazar);
    }

    public List<Kitap> ismeGoreAra(String kelime) {
        return kitapRepository.findByAdContaining(kelime);
    }

    public List<Kitap> ucuzKitaplariGetir(double maxFiyat) {
        return kitapRepository.findByFiyatLessThan(maxFiyat);
    }
}