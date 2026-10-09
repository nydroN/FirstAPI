package com.muhammed.firstProject.exception;

import java.time.LocalDateTime;

// Bu sınıf, kullanıcılara döneceğimiz standart hata paketinin (kutusunun) ta kendisidir.
public class HataCevabi {
    private LocalDateTime zaman;
    private int durumKodu;
    private String mesaj;

    public HataCevabi(LocalDateTime zaman, int durumKodu, String mesaj) {
        this.zaman = zaman;
        this.durumKodu = durumKodu;
        this.mesaj = mesaj;
    }

    // JSON'a dönüşebilmesi için Getter'lar şarttır
    public LocalDateTime getZaman() { return zaman; }
    public int getDurumKodu() { return durumKodu; }
    public String getMesaj() { return mesaj; }
}