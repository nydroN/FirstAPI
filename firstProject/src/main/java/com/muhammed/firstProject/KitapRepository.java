package com.muhammed.firstProject;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// <Hangi Sınıf İçin Çalışacak, O Sınıfın ID Değişkeninin Tipi Ne>
public interface KitapRepository extends JpaRepository<Kitap, Integer> {
    // İçi tamamen boş kalacak! Spring Boot bütün metotları (save, findAll, deleteById) arka planda kendi yazacak.

    /*Sistem şu şekilde işliyor: Sen Java tarafında sadece kitapRepository.save(yeniKitap) metodunu çağırıyorsun.
     JPA arka planda saniyeden kısa sürede o Java nesnesini alıp bir INSERT INTO SQL sorgusuna çeviriyor ve MySQL'e fırlatıyor.
     kitapRepository.findAll() dediğinde SELECT * FROM sorgusunu çalıştırıp sana tabloyu doğrudan liste olarak veriyor.
     deleteById(1) dediğinde ise DELETE FROM sorgusunu tetikliyor.*/

    List<Kitap> findByYazar(String yazar);

    List<Kitap> findByAdContaining(String kelime);

    List<Kitap> findByFiyatLessThan(double maxFiyat);

    boolean existsByAd(String ad);

}
