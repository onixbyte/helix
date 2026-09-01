# Helix

Helix is a **quick-start boilerplate** for building custom identity and organisation
management systems. It ships with a working full-stack baseline — authentication,
role-based access control, and user/role/department/position/menu management — so
you can start from a runnable application and customise it to your own
requirements instead of starting from zero.

The boilerplate is organised as a monorepo:

- **`server/`** — Spring Boot 3 REST API (Java 21, PostgreSQL, Redis, Flyway)
- **`web/`** — React 19 single-page application (Vite, Ant Design, Tailwind CSS, Redux Toolkit)
- **`docker/`** — deployment support files used by Docker Compose

> Helix is a starting point, not a finished product. Every baseline module is
> meant to be extended or replaced as part of your custom development.

## Baseline capabilities

Out of the box the boilerplate provides:

- **Authentication & single sign-on** — username/password login with captcha,
  Microsoft Entra ID (MSAL) sign-in, WeCom (WeChat Work) login and MFA
- **User management** — create, edit and deactivate users; assign roles, departments and positions
- **Role-based access control** — roles with fine-grained authorities, enforced across all endpoints
- **Organisation management** — departments, positions and custom navigation menus
- **Asset storage** — optional S3-compatible object storage for uploaded files
- **Internationalisation** — English (`en-GB`) and Chinese (`zh-CN`) message bundles
- **Responsive admin UI** — Ant Design + Tailwind CSS front end

## Tech stack

| Layer       | Technologies |
| ----------- | ------------ |
| Backend     | Java 21 · Spring Boot 3.5 · Spring Security · Spring Data JPA · MyBatis · Flyway · PostgreSQL · Redis · Auth0 java-jwt · AWS SDK for Java (S3) |
| Frontend    | React 19 · TypeScript · Vite 7 · Ant Design 6 · Tailwind CSS 4 · Redux Toolkit · React Router 7 · Axios · MSAL |
| Infra       | Docker · Docker Compose · Caddy |

## Repository layout

```
.
├── server/                     # Spring Boot REST API
│   ├── src/main/java/com/onixbyte/helix/
│   │   ├── controller/         # HTTP entry points
│   │   ├── service/            # business logic
│   │   ├── manager/            # use-case orchestration
│   │   ├── repository/         # JPA repositories
│   │   ├── mapper/             # MyBatis mappers
│   │   ├── validation/         # custom validation framework
│   │   └── security/           # JWT filter, security configuration
│   ├── src/main/resources/
│   │   ├── db/migrations/      # Flyway SQL migrations
│   │   ├── i18n/               # message bundles (en-GB, zh-CN)
│   │   └── mapper/             # MyBatis XML mappers
│   ├── config/                 # runtime config (see application-prod.yml.example)
│   └── Dockerfile
├── web/                        # React SPA
│   ├── src/api/                # typed API clients (auth, user, role, …)
│   ├── src/page/               # page components
│   ├── src/store/              # Redux slices
│   ├── src/router/             # react-router routes
│   ├── Dockerfile              # builds the SPA and serves it with Caddy
│   └── Caddyfile               # static files + /api reverse proxy
├── docker/                     # Compose support files
│   └── application-prod.yml    # server config mounted into the container
├── docker-compose.yaml         # full-stack: postgres + redis + server + web
└── .env.example                # Compose environment template
```

## Customising the boilerplate

Helix is designed to be forked and extended. The recommended approach:

1. **Fork (or copy) the repository** and rename the project to your own.
2. **Change the branding** — application title (`VITE_APP_TITLE`), regions
   (`VITE_DEFAULT_REGION_ABBREVIATION`), JWT issuer, S3 buckets and so on.
3. **Build on the baseline modules.** The existing patterns — custom validation
   framework, `BizException` + `MessageName` error handling, JWT authentication,
   Redux slices and typed API clients — are the extension points for your own
   features. Follow the conventions in [AGENTS.md](AGENTS.md).
4. **Add schema changes as Flyway migrations** under
   `server/src/main/resources/db/migrations/` and add new message keys to
   **all** i18n bundles.

## Quick start

### Full stack with Docker Compose

Requires [Docker](https://www.docker.com/) with the Compose plugin.

```bash
cp .env.example .env      # optional: adjust ports, secrets and integrations
docker compose up -d --build
```

Once the containers are healthy:

| Service            | URL                                |
| ------------------ | ---------------------------------- |
| Web UI             | <http://localhost>                  |
| REST API           | <http://localhost:8080>             |
| Health check       | <http://localhost:8080/actuator/health> |

Login works out of the box with username/password + captcha. Microsoft Entra ID
and WeCom sign-in require configuring their credentials (see below).

### Manual development

#### Server (JDK 21)

```bash
cd server
cp config/application-prod.yml.example config/application-prod.yml   # edit to match your environment
SPRING_PROFILES_ACTIVE=prod ./gradlew bootRun
```

The API starts on <http://localhost:8080>.

#### Web (Node 22+ with pnpm)

```bash
cd web
pnpm install
cp .env.example .env.development.local   # point the API base URL at your dev server
pnpm dev
```

Set `VITE_API_BASE_URL=http://localhost:8080` in `.env.development.local` to call
the locally running API directly. The Vite dev server listens on
<http://localhost:5173>.

## Configuration

Runtime configuration is externalised through environment variables (see
`server/config/application-prod.yml.example` for the complete reference).

### Docker Compose (`.env`)

| Variable                   | Default                   | Description                                    |
| -------------------------- | ------------------------- | ---------------------------------------------- |
| `PG_DATABASE`              | `helix`                   | PostgreSQL database name                       |
| `PG_USER`                  | `helix`                   | PostgreSQL user                                |
| `PG_PASSWORD`              | `helix`                   | PostgreSQL password                            |
| `REDIS_PASSWORD`           | *(empty)*                 | Redis password (leave empty for local Redis)   |
| `TOKEN_SECRET`             | *(see example)*           | JWT signing secret — change in production      |
| `MSAL_CLIENT_ID`           | *(empty)*                 | Microsoft Entra ID application (client) ID     |
| `MSAL_TENANT_ID`           | *(empty)*                 | Microsoft Entra ID tenant ID                   |
| `S3_ACCESS_KEY_ID`         | *(empty)*                 | S3 access key (requires enabling asset storage)|
| `S3_SECRET_ACCESS_KEY`     | *(empty)*                 | S3 secret key                                  |
| `SERVER_PORT`              | `8080`                    | Host port mapped to the server container       |
| `WEB_PORT`                 | `80`                      | Host port mapped to the Caddy container        |

Asset storage is disabled in `docker/application-prod.yml` by default. To enable
it, set `app.asset.enabled: true` there and provide the `S3_*` variables.

### Web (`.env`)

| Variable                        | Default                            | Description                        |
| ------------------------------- | ---------------------------------- | ---------------------------------- |
| `VITE_API_BASE_URL`             | `/api`                             | Backend base URL                   |
| `VITE_MSAL_CLIENT_ID`           | *(empty)*                          | Microsoft Entra ID client ID       |
| `VITE_MSAL_TENANT_ID`           | *(empty)*                          | Microsoft Entra ID tenant ID       |
| `VITE_DEFAULT_REGION_ABBREVIATION` | `GB`                            | Default user region (ISO 3166-1 alpha-2) |
| `VITE_APP_TITLE`                | `Onixbyte Technology Co., Ltd`     | Application title shown in the UI  |

## Development

- Architecture and conventions are documented in [AGENTS.md](AGENTS.md).
- See [CONTRIBUTING.md](CONTRIBUTING.md) for how to contribute.
- Database schema is managed with Flyway — add migrations under
  `server/src/main/resources/db/migrations/` rather than relying on JPA `ddl-auto`.

## Licence

[MIT](LICENCE) © OnixByte
