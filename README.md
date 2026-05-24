# 🌍 Nomad Trip Planner API

A Spring Boot REST API that auto-generates day-by-day itineraries for digital nomads. Users input a budget, destination, dates, and travel vibe — the API returns a complete trip plan with accommodation, coworking spaces, and activities.

Built as a university project covering: dependency injection, JPA relationships, REST APIs, validation, JWT authentication, and DTO mapping.

---

## Features

- **JWT Authentication** — Register and login with secure token-based auth (USER and ADMIN roles)
- **Trip Generation** — Submit a budget, dates, origin city, and vibe (CHILL, BALANCED, FAST) to get a full itinerary
- **Budget Calculator** — Intelligently allocates budget across accommodation and coworking based on vibe preference
- **City & Amenity Data** — Pre-loaded data for 6 nomad-friendly cities with accommodations, coworking spaces, and flights
- **RESTful API** — Full CRUD for cities, accommodations, coworking spaces, flights, trips, and bundles
- **Validation** — Input validation with descriptive error messages
- **H2 Database** — In-memory database with seed data, ready to run out of the box

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| **Language** | Java 17+ (compiled with JDK 25) |
| **Framework** | Spring Boot 3.2 |
| **Database** | H2 (in-memory), JPA / Hibernate |
| **Auth** | JWT (jjwt 0.12.3) |
| **Build** | Maven |
| **Port** | 8083 (configurable) |

---

## Quick Start

### Prerequisites

- Java 17+ installed
- Maven installed (or use the included `mvnw` wrapper)

### Clone & Run

```bash
git clone https://github.com/Amin8382/nomad-trip-planner.git
cd nomad-trip-planner
mvn package -DskipTests
java -jar target/nomad-0.0.1-SNAPSHOT.jar --server.port=8083
```

The app starts at `http://localhost:8083`. H2 console available at `/h2-console` (JDBC URL: `jdbc:h2:mem:nomaddb`).

---

## API Overview

### Authentication

All endpoints except `/api/auth/**` require a Bearer token.

```
POST /api/auth/register   — Create a new account
POST /api/auth/login      — Login, returns JWT token
```

**Test credentials:**
| Email | Password | Role |
|-------|----------|------|
| `alice@nomad.io` | `pass123` | USER |
| `admin@nomad.io` | `admin123` | ADMIN |

### Cities

```
GET    /api/cities        — List all cities
GET    /api/cities/{id}   — Get city by ID
```

### Accommodations

```
GET    /api/accommodations               — List all
GET    /api/accommodations/{id}          — Get by ID
GET    /api/accommodations/city/{cityId} — Filter by city
POST   /api/accommodations               — Create (ADMIN)
```

### Coworking Spaces

```
GET    /api/coworking                 — List all
GET    /api/coworking/{id}            — Get by ID
GET    /api/coworking/city/{cityId}   — Filter by city
POST   /api/coworking                 — Create (ADMIN)
```

### Flights

```
GET    /api/flights?originId=X&destinationId=Y        — Find flights between cities
GET    /api/flights?originId=X&destinationId=Y&maxPrice=Z   — With max price filter
GET    /api/flights/to/{cityId}                         — Flights to a specific city
POST   /api/flights                                      — Create (ADMIN)
```

### Trips

```
POST   /api/trips              — Create trip with auto-generated itinerary
GET    /api/trips              — List my trips
GET    /api/trips/{id}         — Get trip details + itinerary
PATCH  /api/trips/{id}/status?status=BOOKED  — Update status
DELETE /api/trips/{id}         — Delete trip
```

**Create Trip Request Body:**
```json
{
  "name": "Chiang Mai Adventure",
  "vibe": "CHILL",
  "totalBudget": 2000,
  "currency": "USD",
  "startDate": "2026-06-01",
  "endDate": "2026-06-05",
  "originCityId": 5
}
```

### Bundles

```
GET    /api/bundles                  — List all bundles
GET    /api/bundles/{id}             — Get by ID
GET    /api/bundles/city/{cityId}    — Filter by city
POST   /api/bundles                  — Create (ADMIN)
```

---

## Vibe System

The "vibe" determines how the daily budget is split:

| Vibe | Accommodation % | Description |
|------|----------------|-------------|
| **CHILL** | 60% | Relaxed pace, nicer stays |
| **BALANCED** | 40% | Even split between comfort and work |
| **FAST** | 25% | Budget-focused, more coworking days |

- **CHILL**: 1 coworking day per trip, higher accommodation budget
- **BALANCED**: 2 coworking days per trip, moderate split
- **FAST**: 3+ coworking days per trip, minimal accommodation spend

---

## Seed Data

On startup, the app loads:
- **6 cities**: Chiang Mai, Ubud, Lisbon, Medellin, Bangkok, Tenerife
- **10 accommodations**: Mix of hostels, Airbnbs, and hotels
- **6 coworking spaces**: Rating 4.0–4.7, with WiFi and amenities
- **7 flights**: Routes between cities with real-world pricing

---

## Architecture

```
src/main/java/com/nomad/
├── config/          — DataInitializer (seed data)
├── controller/      — 7 REST controllers
├── dto/             — 11 DTOs for request/response
├── entity/          — 8 JPA entities with relationships
├── exception/       — GlobalExceptionHandler
├── mapper/          — 7 manual DTO mappers
├── repository/      — 7 JPA repositories
├── security/        — JWT auth, filter, security config
└── service/         — Business logic (7 services)
```

### Entity Relationships

```
User ──1:N──> Trip ──1:N──> ItineraryDay
City ──1:N──> Accommodation
City ──1:N──> CoworkingSpace
City ──1:N──> Flight (origin/destination)
Bundle ──N:1──> City, Accommodation, CoworkingSpace, Flight
```

---

## Environment Notes

- **Port 8083** is used by default (port 8080 is often occupied by Oracle TNS Listener on Windows)
- **H2 in-memory** database — data resets on restart. Swap to PostgreSQL/MySQL by changing `application.yml`
- **MapStruct/Lombok not used** — JDK 25 removed a compiler API these libraries depend on; manual mappers and explicit getters/setters are used instead

---
