# ⭐ Star Wars App

An Android application built with **Kotlin** that explores the Star Wars universe — characters, planets, starships, and more using the SWAPI (Star Wars API).

---

---

## 🚀 Features

- Browse Star Wars characters, planets, films, starships, and vehicles
- Clean and intuitive UI inspired by the Star Wars theme
- Data fetched from the Star Wars public API (SWAPI)
- Unit tested with dedicated test projects

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin (93.9%), Java (6.1%) |
| Platform | Android |
| Architecture | MVVM (Model-View-ViewModel) |
| Networking | Retrofit / OkHttp |
| Async | Coroutines / LiveData |
| Testing | JUnit / Espresso |

---

## 📁 Project Structure

```
StarWars/
├── TestProjects/       # Unit and integration test projects
├── .gitignore
└── README.md
```

---

## ⚙️ Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 21+
- Kotlin 1.9+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/aksk8021/StarWars.git
   ```

2. **Open in Android Studio**
   - File → Open → Select the cloned folder

3. **Sync Gradle**
   - Click "Sync Now" when prompted

4. **Run the app**
   - Connect a device or start an emulator
   - Click ▶ Run

---

## 🌐 API Reference

This app uses the Star Wars API (SWAPI), a free public REST API.

Example endpoint:
```
GET https://swapi.dev/api/people/1/
```

---

## 🧪 Testing

Test projects are located in the `TestProjects/` directory.

To run tests:
```bash
./gradlew test          # Unit tests
./gradlew connectedTest # Instrumented tests
```

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is open-source. Feel free to use and modify it.

---

## 👤 Author

**aksk8021**  
GitHub: [@aksk8021](https://github.com/aksk8021)

---

> *"May the Force be with you."* ⚔️
