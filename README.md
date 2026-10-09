# 📚 BookStore REST API - Spring Boot Kurumsal Mimari Projesi

Bu proje, masaüstü uygulama geliştirmeden (JavaFX, WPF) modern web backend mimarisine geçiş sürecimde geliştirdiğim, sektör standartlarına uygun tam kapsamlı bir Spring Boot REST API projesidir. Temel CRUD işlemlerinin ötesine geçerek; güvenli veri transferi, merkezi hata yönetimi ve kurumsal katmanlı mimari (N-Tier) prensipleri uygulanmıştır.

## 🚀 Kullanılan Teknolojiler (Tech Stack)
* **Backend:** Java, Spring Boot (v3.x / v4.x)
* **Veritabanı & ORM:** MySQL, Spring Data JPA, Hibernate
* **Dokümantasyon:** OpenAPI (Swagger UI)
* **Araçlar:** Maven, Postman, Lombok (İsteğe bağlı eklenebilir)

## 🏗️ Mimari ve Öne Çıkan Özellikler

- **Katmanlı Mimari (N-Tier Architecture):** Proje; `Controller`, `Service` ve `Repository` katmanlarına ayrılarak "Separation of Concerns" (Sorumlulukların Ayrılması) prensibine tam uyumlu hale getirilmiştir.
- **DTO (Data Transfer Object) Pattern:** Dış dünyadan (Client) gelen veriler, doğrudan veritabanı varlıklarına (Entity) dönüştürülmeden `KitapRequest` gibi aracı DTO sınıflarıyla karşılanarak güvenlik ve esneklik sağlanmıştır.
- **Merkezi Hata Yönetimi (Global Exception Handling):** `@RestControllerAdvice` ve `@ExceptionHandler` kullanılarak, API genelindeki tüm istisnalar (404 Not Found, 400 Bad Request, 409 Conflict) yakalanmış ve Front-End tarafının kolayca okuyabileceği standart, temiz bir JSON şablonuna (`HataCevabi`) dönüştürülmüştür.
- **Otomatik Doğrulama (Validation):** Hibernate Validator (`@Valid`, `@NotBlank`, `@Min`) ile iş kuralları doğrulamaları otomatikleştirilerek Service katmanı gereksiz `if-else` bloklarından arındırılmıştır.
- **Özel Sorgular (Custom Queries):** Spring Data JPA'nın metot isimlendirme kuralları (örn: `findByAdContaining`, `findByFiyatLessThan`) kullanılarak veritabanında esnek filtreleme işlemleri yapılmıştır.

## 📸 API Dokümantasyonu (Swagger UI)

<img width="1831" height="625" alt="image" src="https://github.com/user-attachments/assets/ec4d2441-509a-4306-9b88-1e3cffa23549" />


API uç noktalarını test etmek ve incelemek için projeyi ayağa kaldırdıktan sonra tarayıcınızda şu adrese gidebilirsiniz:
`http://localhost:8080/swagger-ui/index.html`

## 📡 API Uç Noktaları (Endpoints)

| HTTP Metodu | Uç Nokta (Endpoint) | Açıklama | HTTP Durum Kodu |
| :--- | :--- | :--- | :--- |
| `POST` | `/kitaplar` | Sisteme yeni bir kitap ekler. | 201 Created |
| `GET` | `/kitaplar` | Veritabanındaki tüm kitapları listeler. | 200 OK |
| `PUT` | `/kitaplar/{id}` | Belirtilen ID'ye sahip kitabı DTO üzerinden günceller. | 200 OK |
| `DELETE`| `/kitaplar/{id}` | Belirtilen ID'ye sahip kitabı siler. | 204 No Content |
| `GET` | `/kitaplar/ara?kelime=` | İsmi belirtilen kelimeyi içeren kitapları getirir. | 200 OK |
| `GET` | `/kitaplar/ucuzlar?maxFiyat=` | Fiyatı belirtilen değerin altında olan kitapları getirir.| 200 OK |

## ⚙️ Kurulum ve Çalıştırma

1. Projeyi bilgisayarınıza klonlayın:
   ```bash
   git clone [https://github.com/nydroN/FirstProject.git](https://github.com/nydroN/FirstProject.git)
