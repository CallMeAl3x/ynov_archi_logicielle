# TP Architecture Logicielle - Solutions Complètes

Solutions complètes pour le TP Architecture Logicielle en Java/Spring Boot.

---

## Table des Matières

1. [Exercice 1 — Design Patterns](#exercice-1--design-patterns)
2. [Exercice 2 — Principes SOLID](#exercice-2--principes-solid)
3. [Exercice 3 — Monolithe Spring Boot](#exercice-3--monolithe-spring-boot)
4. [Exercice 4 — Microservices](#exercice-4--microservices)

---

## Exercice 1 — Design Patterns

**Répertoire :** `exercice-1-patterns/`
**Type :** Maven (Java 17, sans Spring)

### Patterns implémentés

| Pattern | Package | Description |
|---------|---------|-------------|
| Builder | `com.ynov.patterns.builder` | Création d'objets `Book` avec champs optionnels |
| Factory | `com.ynov.patterns.factory` | Création de notifications (Email/SMS/Push) |
| Singleton | `com.ynov.patterns.singleton` | Connexion DB thread-safe (double-checked locking) |
| Prototype | `com.ynov.patterns.prototype` | Clonage de livres physiques et numériques |

### Prérequis

- Java 17+
- Maven 3.8+

### Lancement

```bash
cd exercice-1-patterns

# Compiler
mvn compile

# Lancer tous les patterns
mvn exec:java -Dexec.mainClass="com.ynov.patterns.PatternsDemo"

# Ou lancer un pattern spécifique
mvn exec:java -Dexec.mainClass="com.ynov.patterns.builder.BuilderDemo"
mvn exec:java -Dexec.mainClass="com.ynov.patterns.factory.FactoryDemo"
mvn exec:java -Dexec.mainClass="com.ynov.patterns.singleton.SingletonDemo"
mvn exec:java -Dexec.mainClass="com.ynov.patterns.prototype.PrototypeDemo"
```

---

## Exercice 2 — Principes SOLID

**Répertoire :** `exercice-2-solid/`
**Type :** Maven (Java 17, sans Spring)

### Principes implémentés

Chaque principe inclut un package `problem/` (code incorrect) et `solution/` (code correct).

| Principe | Package | Problème illustré | Solution |
|----------|---------|-------------------|----------|
| S — SRP | `com.ynov.solid.srp` | God class `UserManager` | Classes séparées par responsabilité |
| O — OCP | `com.ynov.solid.ocp` | `if/else` pour types clients | Pattern Strategy |
| L — LSP | `com.ynov.solid.lsp` | `Square extends Rectangle` | Implémentations indépendantes de `Shape` |
| I — ISP | `com.ynov.solid.isp` | Interface monolithique | Interfaces ségrégées |
| D — DIP | `com.ynov.solid.dip` | Dépendances concrètes directes | Injection par interface |

### Lancement

```bash
cd exercice-2-solid

# Compiler
mvn compile

# Lancer tous les principes SOLID
mvn exec:java -Dexec.mainClass="com.ynov.solid.SOLIDDemo"

# Ou un principe spécifique
mvn exec:java -Dexec.mainClass="com.ynov.solid.srp.SRPDemo"
mvn exec:java -Dexec.mainClass="com.ynov.solid.ocp.OCPDemo"
mvn exec:java -Dexec.mainClass="com.ynov.solid.lsp.LSPDemo"
mvn exec:java -Dexec.mainClass="com.ynov.solid.isp.ISPDemo"
mvn exec:java -Dexec.mainClass="com.ynov.solid.dip.DIPDemo"
```

---

## Exercice 3 — Monolithe Spring Boot

**Répertoire :** `exercice-3-monolith/`
**Type :** Spring Boot 3.2.0, H2 en mémoire, Swagger UI

### Architecture

```
LibraryApplication
├── model/      Book, AppUser, Loan, LoanStatus
├── repository/ BookRepository, UserRepository, LoanRepository
├── dto/        BookDTO, CreateBookDTO, UserDTO, CreateUserDTO, LoanDTO, CreateLoanDTO
├── service/    BookService, UserService, LoanService
├── controller/ BookController, UserController, LoanController
├── exception/  ResourceNotFoundException, BusinessException, GlobalExceptionHandler
└── config/     OpenApiConfig, DataInitializer (5 livres, 3 users, 2 emprunts)
```

### Prérequis

- Java 17+
- Maven 3.8+

### Lancement

```bash
cd exercice-3-monolith
mvn spring-boot:run
```

L'application démarre sur le port **8080**.

### Endpoints REST

#### Livres — `/api/books`

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/api/books` | Liste tous les livres |
| GET | `/api/books/{id}` | Détail d'un livre |
| POST | `/api/books` | Créer un livre |
| PUT | `/api/books/{id}` | Modifier un livre |
| DELETE | `/api/books/{id}` | Supprimer un livre |
| GET | `/api/books/available` | Livres disponibles |
| GET | `/api/books/search?author=` | Recherche par auteur |

#### Utilisateurs — `/api/users`

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/api/users` | Liste tous les utilisateurs |
| GET | `/api/users/{id}` | Détail d'un utilisateur |
| POST | `/api/users` | Créer un utilisateur |
| PUT | `/api/users/{id}` | Modifier un utilisateur |
| DELETE | `/api/users/{id}` | Supprimer un utilisateur |

#### Emprunts — `/api/loans`

| Méthode | URL | Description |
|---------|-----|-------------|
| POST | `/api/loans` | Créer un emprunt `{"bookId":1,"userId":1}` |
| PUT | `/api/loans/{id}/return` | Retourner un livre |
| GET | `/api/loans` | Tous les emprunts |
| GET | `/api/loans/active` | Emprunts actifs |
| GET | `/api/loans/overdue` | Emprunts en retard (>30 jours) |

### Interfaces

- **Swagger UI :** http://localhost:8080/swagger-ui.html
- **H2 Console :** http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:librarydb`)
- **API Docs JSON :** http://localhost:8080/api-docs

---

## Exercice 4 — Microservices

**Répertoire :** `exercice-4-microservices/`
**Type :** Spring Boot 3.2.0 + Spring Cloud 2023.0.0

### Architecture

```
exercice-4-microservices/
├── discovery-service  (port 8761) — Eureka Server
├── api-gateway        (port 8080) — Spring Cloud Gateway
├── book-service       (port 8081) — Gestion des livres
├── user-service       (port 8082) — Gestion des utilisateurs
├── loan-service       (port 8083) — Gestion des emprunts (OpenFeign)
└── review-service     (port 8084) — Gestion des avis
```

### Communication inter-services

`loan-service` communique avec `book-service` et `user-service` via **OpenFeign** :
- Vérifie l'existence du livre et de l'utilisateur avant de créer un emprunt
- Met à jour la disponibilité du livre lors d'un emprunt / retour

### Lancement avec Docker Compose

```bash
cd exercice-4-microservices

# Construire et démarrer tous les services
docker-compose up --build

# Démarrer en arrière-plan
docker-compose up -d --build

# Arrêter
docker-compose down
```

Attendre ~60s que tous les services s'enregistrent auprès d'Eureka.

### Lancement Manuel (développement)

```bash
# 1. Discovery Service (démarrer en premier)
cd discovery-service && mvn spring-boot:run &

# 2. Services métier (dans n'importe quel ordre)
cd book-service && mvn spring-boot:run &
cd user-service && mvn spring-boot:run &
cd review-service && mvn spring-boot:run &

# 3. Loan Service (après book et user)
cd loan-service && mvn spring-boot:run &

# 4. API Gateway (en dernier)
cd api-gateway && mvn spring-boot:run &
```

### Endpoints via API Gateway (port 8080)

#### Books
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `http://localhost:8080/api/books` | Liste des livres |
| GET | `http://localhost:8080/api/books/{id}` | Détail livre |
| POST | `http://localhost:8080/api/books` | Créer livre |
| PUT | `http://localhost:8080/api/books/{id}` | Modifier livre |
| DELETE | `http://localhost:8080/api/books/{id}` | Supprimer livre |
| GET | `http://localhost:8080/api/books/available` | Livres disponibles |
| GET | `http://localhost:8080/api/books/{id}/exists` | Vérifier existence |
| PUT | `http://localhost:8080/api/books/{id}/availability?available=true` | Mise à jour dispo |

#### Users
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `http://localhost:8080/api/users` | Liste des utilisateurs |
| GET | `http://localhost:8080/api/users/{id}` | Détail utilisateur |
| POST | `http://localhost:8080/api/users` | Créer utilisateur |
| PUT | `http://localhost:8080/api/users/{id}` | Modifier utilisateur |
| DELETE | `http://localhost:8080/api/users/{id}` | Supprimer utilisateur |
| GET | `http://localhost:8080/api/users/{id}/exists` | Vérifier existence |

#### Loans
| Méthode | URL | Corps | Description |
|---------|-----|-------|-------------|
| POST | `http://localhost:8080/api/loans` | `{"bookId":1,"userId":1}` | Créer emprunt |
| PUT | `http://localhost:8080/api/loans/{id}/return` | — | Retour livre |
| GET | `http://localhost:8080/api/loans` | — | Tous les emprunts |
| GET | `http://localhost:8080/api/loans/active` | — | Emprunts actifs |

#### Reviews
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `http://localhost:8080/api/reviews` | Tous les avis |
| GET | `http://localhost:8080/api/reviews/{id}` | Détail avis |
| GET | `http://localhost:8080/api/reviews/book/{bookId}` | Avis par livre |
| POST | `http://localhost:8080/api/reviews` | Créer avis |
| PUT | `http://localhost:8080/api/reviews/{id}` | Modifier avis |
| DELETE | `http://localhost:8080/api/reviews/{id}` | Supprimer avis |

### Interfaces de monitoring

- **Eureka Dashboard :** http://localhost:8761
- **H2 Console book-service :** http://localhost:8081/h2-console
- **H2 Console user-service :** http://localhost:8082/h2-console
- **H2 Console loan-service :** http://localhost:8083/h2-console
- **H2 Console review-service :** http://localhost:8084/h2-console

---

## Structure Complète des Fichiers

```
tp-archi-solutions/
├── README.md
├── exercice-1-patterns/
│   ├── pom.xml
│   └── src/main/java/com/ynov/patterns/
│       ├── PatternsDemo.java
│       ├── builder/   Book.java, BuilderDemo.java
│       ├── factory/   Notification.java, EmailNotification.java, SMSNotification.java,
│       │              PushNotification.java, NotificationFactory.java, FactoryDemo.java
│       ├── singleton/ DatabaseConnection.java, SingletonDemo.java
│       └── prototype/ BookPrototype.java, PhysicalBook.java, EBook.java, PrototypeDemo.java
├── exercice-2-solid/
│   ├── pom.xml
│   └── src/main/java/com/ynov/solid/
│       ├── SOLIDDemo.java
│       ├── srp/  problem/UserManager.java  solution/User.java, UserValidator.java,
│       │                                    UserRepository.java, EmailService.java,
│       │                                    UserReportService.java, UserService.java
│       ├── ocp/  problem/DiscountCalculator.java  solution/DiscountStrategy.java,
│       │                                           RegularDiscount.java, PremiumDiscount.java,
│       │                                           StudentDiscount.java, DiscountCalculator.java
│       ├── lsp/  problem/Rectangle.java, Square.java, LSPProblemDemo.java
│       │         solution/Shape.java, Rectangle.java, Square.java
│       ├── isp/  problem/LibraryItem.java
│       │         solution/Borrowable.java, Downloadable.java, Streamable.java, Printable.java,
│       │                  PhysicalBook.java, EBook.java, AudioBook.java
│       └── dip/  problem/OrderService.java
│                 solution/DatabaseRepository.java, EmailNotificationService.java,
│                          MySQLDatabase.java, PostgreSQLDatabase.java,
│                          SMTPEmailSender.java, OrderService.java
├── exercice-3-monolith/
│   ├── pom.xml
│   └── src/main/java/com/ynov/library/
│       ├── LibraryApplication.java
│       ├── model/      Book.java, AppUser.java, Loan.java, LoanStatus.java
│       ├── repository/ BookRepository.java, UserRepository.java, LoanRepository.java
│       ├── dto/        BookDTO, CreateBookDTO, UserDTO, CreateUserDTO, LoanDTO, CreateLoanDTO
│       ├── service/    BookService.java, UserService.java, LoanService.java
│       ├── controller/ BookController.java, UserController.java, LoanController.java
│       ├── exception/  ResourceNotFoundException.java, BusinessException.java,
│       │               GlobalExceptionHandler.java
│       └── config/     OpenApiConfig.java, DataInitializer.java
└── exercice-4-microservices/
    ├── pom.xml
    ├── docker-compose.yml
    ├── discovery-service/  pom.xml, Dockerfile, src/...
    ├── api-gateway/        pom.xml, Dockerfile, src/...
    ├── book-service/       pom.xml, Dockerfile, src/...
    ├── user-service/       pom.xml, Dockerfile, src/...
    ├── loan-service/       pom.xml, Dockerfile, src/...  (OpenFeign)
    └── review-service/     pom.xml, Dockerfile, src/...
```
