# CinemaBooking

Sistema simples para reserva de assentos de cinema.

## Objetivo

Permitir que clientes consultem filmes, sessões e assentos disponíveis, além de realizar reservas com expiração automática após 10 minutos.

## Stack

### Backend

* Java 21
* Spring Boot
* MariaDB

### Frontend

* Next.js
* TypeScript
* TailwindCSS

### Infra

* Docker
* Docker Compose

## Regras de Negócio

* Um assento pode ser reservado apenas uma vez por sessão.
* Reservas são criadas com status `PENDING`.
* Reservas expiram após 10 minutos.
* Reservas expiradas liberam o assento automaticamente.

## Entidades

* Movie
* Room
* Seat
* Session
* Customer
* Ticket

## Endpoints

### Filmes

```http
GET /api/movies
```

### Sessões

```http
GET /api/movies/{movieId}/sessions
```

### Assentos

```http
GET /api/sessions/{sessionId}/seats
```

### Reserva

```http
POST /api/tickets
```

### Cancelamento

```http
DELETE /api/tickets/{ticketId}
```

## Concorrência

Garantida através de:

```sql
UNIQUE(session_id, seat_id)
```

e transações no backend.

## TODO

### Backend

* [x] Configurar Spring Boot
* [x] Configurar MariaDB
* [ ] Criar entidades
* [ ] Criar repositories
* [ ] Criar DTOs
* [ ] Criar mappers
* [ ] Implementar services
* [ ] Implementar controllers
* [ ] Implementar validações
* [ ] Implementar tratamento global de erros
* [ ] Implementar reserva de assentos
* [ ] Implementar cancelamento de reservas
* [ ] Implementar scheduler de expiração
* [ ] Criar seed inicial

### Frontend

* [x] Configurar Next.js
* [x] Configurar Tailwind
* [x] Configurar shadcn/ui
* [ ] Criar página de filmes
* [ ] Criar página de sessões
* [ ] Criar página de assentos
* [ ] Criar formulário de reserva
* [ ] Integrar APIs
* [ ] Exibir status dos assentos
* [ ] Exibir confirmação da reserva
