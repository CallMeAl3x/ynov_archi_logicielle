# Plateforme de Reservation Coworking - Microservices

Plateforme de reservation de salles de coworking construite en architecture microservices avec Spring Boot 3.2.0, Spring Cloud 2023.0.0, Java 17, Apache Kafka et H2.

## Architecture

| Service | Port | Description |
|---------|------|-------------|
| config-server | 8888 | Serveur de configuration centralise (Spring Cloud Config) |
| discovery-server | 8761 | Service de decouverte (Eureka Server) |
| api-gateway | 8080 | Passerelle API (Spring Cloud Gateway) |
| room-service | 8081 | Gestion des salles de coworking |
| member-service | 8082 | Gestion des membres |
| reservation-service | 8083 | Gestion des reservations |
| Zookeeper | 2181 | Coordination Kafka |
| Kafka | 9092 | Bus de messages asynchrone |

## Lancement

### Option 1 : Lancement local (sans Docker)

1. Demarrer Kafka et Zookeeper :
```bash
docker-compose up -d zookeeper kafka
```

2. Lancer les services dans l'ordre :
```bash
cd config-server && mvn spring-boot:run &
cd discovery-server && mvn spring-boot:run &
# Attendre que discovery-server soit pret (http://localhost:8761)
cd api-gateway && mvn spring-boot:run &
cd room-service && mvn spring-boot:run &
cd member-service && mvn spring-boot:run &
cd reservation-service && mvn spring-boot:run &
```

### Option 2 : Docker Compose

```bash
docker-compose up --build
```

## API Endpoints

### Room Service (port 8081)

| Methode | URL | Description |
|---------|-----|-------------|
| GET | /api/rooms | Lister toutes les salles |
| GET | /api/rooms/{id} | Obtenir une salle |
| POST | /api/rooms | Creer une salle |
| PUT | /api/rooms/{id} | Modifier une salle |
| DELETE | /api/rooms/{id} | Supprimer une salle |
| GET | /api/rooms/available | Salles disponibles |
| GET | /api/rooms/{id}/exists | Verifier existence |
| GET | /api/rooms/{id}/available | Verifier disponibilite |
| PUT | /api/rooms/{id}/availability?available= | Modifier disponibilite |

### Member Service (port 8082)

| Methode | URL | Description |
|---------|-----|-------------|
| GET | /api/members | Lister tous les membres |
| GET | /api/members/{id} | Obtenir un membre |
| POST | /api/members | Creer un membre |
| PUT | /api/members/{id} | Modifier un membre |
| DELETE | /api/members/{id} | Supprimer un membre |
| GET | /api/members/{id}/exists | Verifier existence |
| GET | /api/members/{id}/suspended | Verifier suspension |

### Reservation Service (port 8083)

| Methode | URL | Description |
|---------|-----|-------------|
| POST | /api/reservations | Creer une reservation |
| GET | /api/reservations | Lister les reservations |
| GET | /api/reservations/{id} | Obtenir une reservation |
| PUT | /api/reservations/{id}/cancel | Annuler une reservation |
| PUT | /api/reservations/{id}/complete | Completer une reservation |

## Exemples de requetes

### Creer une salle
```bash
curl -X POST http://localhost:8081/api/rooms \
  -H "Content-Type: application/json" \
  -d '{"name":"Salle Alpha","city":"Paris","capacity":10,"type":"MEETING_ROOM","hourlyRate":25.00}'
```

### Creer un membre
```bash
curl -X POST http://localhost:8082/api/members \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Jean Dupont","email":"jean@example.com","subscriptionType":"PRO"}'
```

### Creer une reservation
```bash
curl -X POST http://localhost:8083/api/reservations \
  -H "Content-Type: application/json" \
  -d '{"roomId":1,"memberId":1,"startDateTime":"2026-03-20T09:00:00","endDateTime":"2026-03-20T12:00:00"}'
```

## Swagger UI

- Room Service : http://localhost:8081/swagger-ui.html
- Member Service : http://localhost:8082/swagger-ui.html
- Reservation Service : http://localhost:8083/swagger-ui.html

## Communication inter-services

### Synchrone (Feign)
- reservation-service -> room-service : verification existence et disponibilite salle
- reservation-service -> member-service : verification existence et suspension membre

### Asynchrone (Kafka)

| Topic | Producteur | Consommateur | Action |
|-------|-----------|-------------|--------|
| room-deleted | room-service | reservation-service | Annuler reservations CONFIRMED de la salle |
| member-deleted | member-service | reservation-service | Supprimer toutes reservations du membre |
| member-suspend | reservation-service | member-service | Suspendre le membre |
| member-unsuspend | reservation-service | member-service | Desuspendre le membre |
