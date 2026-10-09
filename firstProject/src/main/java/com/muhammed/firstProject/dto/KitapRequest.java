package com.muhammed.firstProject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// Bu sınıf sadece Postman'den gelen "Ekleme" isteklerini karşılayacak veznedardır.
// Dikkat et, içinde "id" yok! Çünkü yeni eklenen kitabın ID'si olmaz, onu veritabanı verir.
public class KitapRequest {

    // Doğrulama kurallarını Entity'den alıp buraya (dış kapıya) taşıyoruz
    @NotBlank(message = "Kitap adı boş bırakılamaz!")
    private String ad;

    @NotBlank(message = "Yazar adı boş bırakılamaz!")
    private String yazar;

    @Min(value = 0, message = "Kitap fiyatı negatif olamaz!")
    private double fiyat;

    // Getter ve Setter metotları (Sistemin verileri okuyabilmesi için şart)
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }

    public String getYazar() { return yazar; }
    public void setYazar(String yazar) { this.yazar = yazar; }

    public double getFiyat() { return fiyat; }
    public void setFiyat(double fiyat) { this.fiyat = fiyat; }
}