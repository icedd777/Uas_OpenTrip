Manajemen Open Trip
Aplikasi desktop CRUD berbasis JavaFX untuk mengelola data open trip wisata. Aplikasi ini memungkinkan pengguna menambah, mengedit, menghapus, dan melihat daftar trip wisata.

Fitur
Tambah data open trip

Edit informasi trip

Hapus data trip

Tabel dinamis menampilkan semua data

Tampilan UI sederhana dan modern

Validasi input pengguna

Teknologi
Java 17+

JavaFX 17+ (SDK 24.0.1)

IDE: Visual Studio Code (rekomendasi)

Struktur Proyek
css
Salin
Edit
project-folder/
├── src/
│   └── uas/
│       ├── App.java
│       ├── OpenTrip.java
│       └── OpenTripController.java
├── .vscode/
│   └── launch.json
└── README.md
Cara Menjalankan
Install Java & JavaFX:

Pastikan sudah install Java JDK 17+

Download JavaFX SDK dari: https://gluonhq.com/products/javafx/

Konfigurasi launch.json:

json
Salin
Edit
"vmArgs": "--module-path \"C:/lokasi/javafx-sdk-24.0.1/lib\" --add-modules javafx.controls,javafx.fxml"
Ganti path "--module-path" sesuai lokasi lib JavaFX SDK di komputer Anda.

Buka di VS Code:

Klik Run → pilih Run OpenTrip App

Screenshot
<!-- Ganti ini dengan nama file gambar UI jika tersedia -->

Penjelasan Kelas
File	Deskripsi
App.java	Kelas utama aplikasi JavaFX dan tampilan UI
OpenTrip.java	Model data OpenTrip
OpenTripController.java	Logika pengelolaan data trip (tambah, edit, hapus)

Developer
Rofiqul Risat
📧 rofiqul@example.com (ganti dengan kontak asli jika perlu)

📃 Lisensi
MIT License. Bebas digunakan untuk keperluan pembelajaran maupun pengembangan lanjutan.

