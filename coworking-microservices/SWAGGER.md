# Documentation Swagger - SpringDoc OpenAPI

Chaque microservice metier expose une documentation Swagger interactive via **springdoc-openapi-starter-webmvc-ui 2.3.0**.

## Acces

| Service | Swagger UI | OpenAPI JSON |
|---------|-----------|-------------|
| Room Service | http://localhost:8081/swagger-ui.html | http://localhost:8081/v3/api-docs |
| Member Service | http://localhost:8082/swagger-ui.html | http://localhost:8082/v3/api-docs |
| Reservation Service | http://localhost:8083/swagger-ui.html | http://localhost:8083/v3/api-docs |

## Dependance utilisee

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

Cette dependance est presente dans le `pom.xml` de chaque service metier (room-service, member-service, reservation-service). Aucune configuration supplementaire n'est necessaire : SpringDoc detecte automatiquement les controllers annotes `@RestController` et genere la documentation.

## Endpoints documentes

### Room Service (8081)

| Methode | Endpoint | Description |
|---------|----------|-------------|
| GET | /api/rooms | Liste toutes les salles |
| GET | /api/rooms/{id} | Recupere une salle par ID |
| POST | /api/rooms | Cree une nouvelle salle |
| PUT | /api/rooms/{id} | Met a jour une salle |
| DELETE | /api/rooms/{id} | Supprime une salle (+ event Kafka) |
| GET | /api/rooms/available | Liste les salles disponibles |
| GET | /api/rooms/{id}/exists | Verifie si une salle existe |
| GET | /api/rooms/{id}/available | Verifie la disponibilite d'une salle |
| PUT | /api/rooms/{id}/availability | Modifie la disponibilite |

### Member Service (8082)

| Methode | Endpoint | Description |
|---------|----------|-------------|
| GET | /api/members | Liste tous les membres |
| GET | /api/members/{id} | Recupere un membre par ID |
| POST | /api/members | Cree un nouveau membre |
| PUT | /api/members/{id} | Met a jour un membre |
| DELETE | /api/members/{id} | Supprime un membre (+ event Kafka) |
| GET | /api/members/{id}/exists | Verifie si un membre existe |
| GET | /api/members/{id}/suspended | Verifie si un membre est suspendu |
| GET | /api/members/{id}/max-bookings | Recupere le quota max de reservations |

### Reservation Service (8083)

| Methode | Endpoint | Description |
|---------|----------|-------------|
| POST | /api/reservations | Cree une reservation (validations cross-service) |
| GET | /api/reservations | Liste toutes les reservations |
| GET | /api/reservations/{id} | Recupere une reservation par ID |
| PUT | /api/reservations/{id}/cancel | Annule une reservation (State Pattern) |
| PUT | /api/reservations/{id}/complete | Complete une reservation (State Pattern) |

## Modeles de donnees

Les schemas des entites (Room, Member, Reservation) et des enums (RoomType, SubscriptionType, ReservationStatus) sont automatiquement generes et visibles dans la section "Schemas" de Swagger UI.
