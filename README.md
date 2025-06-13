# 🧰 FRAMA (Framework CLI Java Mini)

**Frama** adalah CLI sederhana berbasis Java native untuk mempermudah pengelolaan file project (MVC: model, controller, view) dan database migration, terinspirasi dari artisan milik Laravel.

---

## 📁 Struktur Folder

```
FRAMA/
├── app/
│   ├── bin/
│   ├── templates/
│   │   ├── controller.template
│   │   ├── model.template
│   │   └── view.template
│   ├── Framav1.java   <-- Main CLI handler
│   └── Main.java
├── bin/              <-- Output .class hasil kompilasi migration
├── cli/              <-- Frama.bat disimpan di sini
├── lib/              <-- mysql-connector.jar disimpan di sini
├── src/
│   ├── bin/          <-- Opsional jika ingin simpan hasil compile per modul
│   ├── config/
│   │   ├── DBConnection.java
│   │   └── Migrations.java
│   ├── controllers/
│   ├── models/
│   ├── views/
│   └── Main.java
├── Frama.bat
└── README.md
```

---

## ⚙️ Cara Penggunaan

Jalankan semua perintah dari root project menggunakan `Frama.bat`.

### 📦 1. Generate File Template

```bash
./Frama make:<type> <name>
```

- `type`: bisa `models`, `controllers`, atau `views` (boleh pakai "s" atau tidak).
- `name`: nama file yang akan dibuat.

#### Contoh:
```bash
./Frama make:models user
```

📁 Output:
- File `User.java` di `src/models/`, berdasarkan `app/templates/model.template`

```bash
./Frama make:controllers note
```

📁 Output:
- File `NoteController.java` di `src/controllers/`, berdasarkan `app/templates/controller.template`

```bash
./Frama make:views user
```

📁 Output:
- File `UserView.java` di `src/views/`, berdasarkan `app/templates/view.template`

---

### 🧨 2. Jalankan Migration

```bash
./Frama migrate
```

🛑 Akan menampilkan konfirmasi:
```
WARNING: This will delete all data if exists. Are you sure? (Y/n):
```

✅ Jika `Y`, maka:

- Compile `src/config/DBConnection.java` dan `Migrations.java`
- Jalankan `config.Migrations` menggunakan classpath ke `lib/mysql-connector`
- Animasi loading muncul
- Hasil output tiap langkah muncul seperti:
  ```
  ✓ Dropped table users
  ✓ Created table users
  ✓ Dropped table notes
  ✓ Created table notes
  ```

> Pastikan sudah meletakkan file `mysql-connector-*.jar` ke folder `/lib`

---

## 🖥️ Konfigurasi Database

File: `src/config/DBConnection.java`

```java
private static final String URL = "jdbc:mysql://localhost:8081/your_db";
private static final String USER = "root";
private static final String PASS = "";
```

> Gantilah konfigurasi sesuai kebutuhan proyekmu.

---

## 🧪 Build Manual (Opsional)

Compile CLI:
```bash
javac -d . app/Framav1.java
```

Compile Migration:
```bash
javac -d bin src/config/*.java
```

Run Migration:
```bash
java -cp bin;lib/mysql-connector-*.jar config.Migrations
```

---

## 📜 Lisensi

Project ini hanya untuk latihan dan keperluan pengembangan internal. Feel free to fork ✨
