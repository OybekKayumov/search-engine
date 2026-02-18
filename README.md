# Search Engine App

![Search Engine Architecture](./docs/images/architecture.png)

## 🚀 About The Project

Search Engine App is a multi-service search engine built with Spring Boot.  
The project is designed using a 2-services architecture where indexing and search logic are separated for better scalability and maintainability.

The application provides REST API endpoints for:

- Website indexing
- Keyword-based search
- Statistics monitoring
- Managing indexing processes

---

## 🏗️ Architecture

The project is based on a microservice-style structure:

- **Indexing Service** – responsible for crawling and indexing websites
- **Search Service** – responsible for processing search queries and ranking results

![Architecture Diagram](./docs/images/architecture.png)

---

## 🧰 Tech Stack

| Layer        | Technology |
|-------------|------------|
| Backend     | Java 17 |
| Framework   | Spring Boot |
| Build Tool  | Maven |
| Database    | MySQL / PostgreSQL |
| API         | REST |

---

## 📂 Project Structure

```
search-engine/
├── service-indexer/
├── service-search/
├── src/
├── docs/
│   └── images/
├── pom.xml
└── README.md
```

---

## ⚙️ Installation & Run

### 1️⃣ Clone Repository

```bash
git clone https://github.com/OybekKayumov/search-engine.git
cd search-engine
```

### 2️⃣ Checkout Branch

```bash
git checkout 2-services
```

### 3️⃣ Build Project

```bash
mvn clean install
```

### 4️⃣ Run Application

```bash
mvn spring-boot:run
```

---

## 🔌 API Endpoints

### Start Indexing

```
POST /api/v1/index/start
```

### Stop Indexing

```
POST /api/v1/index/stop
```

### Search

```
GET /api/v1/search?query=keyword
```

### Statistics

```
GET /api/v1/statistics
```

---

## 🖼️ Screenshots

### 🔎 Search Interface

![Search UI](./docs/images/search-ui.png)

### 📊 Search Results

![Results UI](./docs/images/results-ui.png)

---

## 🛠️ Configuration

Configuration file example (`application.yml`):

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/search_engine
    username: root
    password: password
```

---

## 🧪 Testing

To run tests:

```bash
mvn test
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License.

---

## 👤 Author

**Oybek Kayumov**  
GitHub: https://github.com/OybekKayumov


