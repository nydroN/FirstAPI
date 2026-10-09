package com.muhammed.firstProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice // SİHİR BURADA: Bu etiket, sınıfı projenin üzerine görünmez bir ağ gibi serer. Tüm hatalar buraya düşer.
public class GlobalExceptionHandler {

    // 1. BİZİM FIRLATTIĞIMIZ HATALARI YAKALAYAN METOT (404 Not Found, 409 Conflict vb.)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<HataCevabi> ozelHatalariYakala(ResponseStatusException ex) {

        // Hata şablonumuzu dolduruyoruz
        HataCevabi hataPaketi = new HataCevabi(
                LocalDateTime.now(),
                ex.getStatusCode().value(), // Örneğin: 409
                ex.getReason()              // Örneğin: "Bu kitap zaten var"
        );

        return new ResponseEntity<>(hataPaketi, ex.getStatusCode());
    }

    // 2. @Valid ETİKETİNDEN DÖNEN DOĞRULAMA HATALARINI YAKALAYAN METOT (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HataCevabi> dogrulamaHatalariniYakala(MethodArgumentNotValidException ex) {

        // @NotBlank veya @Min etiketine yazdığımız o ilk mesajı içinden cımbızla çekiyoruz
        String mesaj = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        HataCevabi hataPaketi = new HataCevabi(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), // 400
                mesaj                           // Örneğin: "Kitap fiyatı negatif olamaz!"
        );

        return new ResponseEntity<>(hataPaketi, HttpStatus.BAD_REQUEST);
    }
}