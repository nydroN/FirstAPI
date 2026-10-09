package com.muhammed.firstProject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity // 1. SİHİR: "Bu sınıfı al, veritabanında 'kitap' adında bir tabloya dönüştür"
public class Kitap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Kitap adı boş bırakılamaz!")
    private String ad;

    @NotBlank(message = "Yazar adı boş bırakılamaz!")
    private String yazar;

    @Min(value = 0, message = "Kitap fiyatı negatif olamaz!")
    private double fiyat;
    // Arka plan işlemleri için zorunlu boş Constructor
    public Kitap() {
    }

    // Nesne üretmek için kullandığımız dolu Constructor
    public Kitap(Integer id, String ad, String yazar, double fiyat) {
        this.id = id;
        this.ad = ad;
        this.yazar = yazar;
        this.fiyat = fiyat;
    }

    // Getter ve Setter metotları (Aynı kalacak, silme)
    public Integer getId() { return id; }
    public String getAd() { return ad; }
    public String getYazar() { return yazar; }
    public double getFiyat() { return fiyat; }

    public void setAd(String ad) { this.ad = ad; }
    public void setYazar(String yazar) { this.yazar = yazar; }
    public void setFiyat(double fiyat) { this.fiyat = fiyat; }
}
