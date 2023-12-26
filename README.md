# Karaoke App

## Deskripsi Proyek

Proyek ini adalah aplikasi karaoke sederhana yang dikembangkan menggunakan Java. Aplikasi ini memiliki beberapa fitur utama, termasuk halaman login, halaman registrasi, halaman pemilihan paket, pemutar musik, dan halaman nota transaksi.

## Struktur Proyek
karaoke_app  
├── src/  
│ ├── Main  
│ ├── Model  
│ ├── Util  
│ ├── View  
│ ├── Controller  
│ ├── source_ui (custom component)

### Penjelasan Struktur Proyek

- `Main`: Berisi kelas utama (`Main.java`) untuk menjalankan aplikasi.
- `Model`: Berisi kelas-kelas yang merepresentasikan model data.
- `Util`: Berisi kelas utilitas yang dapat digunakan di seluruh proyek.
- `View`: Berisi kelas-kelas untuk antarmuka pengguna.
- `Controller`: Berisi kelas-kelas pengendali/logika bisnis.
- `source_ui`: Berisi komponen UI khusus yang dapat digunakan kembali.

## Tampilan Aplikasi

### Halaman Login
![Halaman Login](/src/View/images/login_page.png)

Pada halaman login, pengguna diminta untuk memasukkan informasi login seperti nama pengguna dan kata sandi.

### Halaman Registrasi
![Halaman Registrasi](/src/View/images/register_page.png)

Halaman registrasi memungkinkan pengguna untuk membuat akun baru dengan mengisi formulir pendaftaran.

### Halaman Pemilihan Paket
![Halaman Pemilihan Paket](/src/View/images/paket_page.png)

Pada halaman ini, pengguna dapat memilih paket karaoke yang diinginkan sebelum memulai sesi karaoke.

### Pemutar Musik
![Halaman Pemutar Musik](/src/View/images/media_player_page.png)

Halaman pemutar musik memungkinkan pengguna untuk memutar musik dari koleksi yang tersedia.

### Halaman Nota Transaksi
![Halaman Nota Transaksi](/src/View/images/nota_page.png)

Setelah sesi karaoke selesai, pengguna akan melihat nota transaksi yang berisi ringkasan transaksi mereka.

## Cara Menjalankan Aplikasi

1. Pastikan JDK sudah terinstal di komputer Anda.
2. Unduh proyek ini dan buka dengan IDE Java favorit Anda (misalnya, NetBeans atau IntelliJ IDEA).
3. Jalankan kelas `Main.java` untuk memulai aplikasi.
