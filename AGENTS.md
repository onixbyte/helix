# AGENTS.md

Guidance for AI coding agents and contributors working in this repository.
It complements [README.md](README.md); read that first for the project overview.

> **Scope — this is a quick-start boilerplate.** Helix is a starting point for
> custom development, not a finished product. The baseline modules
> (authentication, RBAC, user/role/department/position/menu management) are
> intentionally kept minimal and exist to be extended. When adding features,
> build on the existing patterns below; do not introduce parallel
> conventions.

## Repository overview

Helix is a monorepo containing a backend API and a frontend SPA:

- **`server/`** — Spring Boot 3.5 REST API. Java 21, Gradle, Spring Security,
  Spring Data JPA + MyBatis, Flyway, PostgreSQL, Redis, Auth0 java-jwt,
  AWS SDK (S3). Package root: `com.onixbyte.helix`.
- **`web/`** — React 19 SPA. TypeScript, Vite 7, Ant Design 6, Tailwind CSS 4,
  Redux Toolkit, React Router 7, Axios, MSAL.

## Build and test

Server (requires JDK 21):

```bash
cd server
./gradlew build      # compiles and runs the JUnit 5 test suite
./gradlew bootRun    # starts the API on port 8080
```

Web (requires Node 22+ and pnpm):

```bash
cd web
pnpm install
pnpm dev             # Vite dev server
pnpm build           # tsc -b && vite build
pnpm lint            # ESLint
```

## Server conventions

- **Layering.** Follow the strict call chain
  `Controller → Service → Manager → Repository` (MyBatis Mappers sit alongside
  JPA repositories for query-heavy reads). Controllers stay thin: parse and
  validate input, delegate to a service, return a response DTO. Business rules
  live in services; transactional use-case orchestration lives in managers.
- **Validation.** Use the custom validation framework in
  `com.onixbyte.helix.validation` (`annotation` / `validator` / `executor` /
  `group`) instead of ad-hoc checks where possible.
- **Errors and i18n.** Throw `BizException(HttpStatus, MessageName.X, args...)`.
  Message keys are constants declared in `com.onixbyte.helix.shared.MessageName`
  and resolved from the bundles in `src/main/resources/i18n`
  (`messages.properties`, `messages_en_GB.properties`, `messages_zh_CN.properties`).
  Always add a new key to **all** bundles, in the default language and in Chinese.
- **Authentication.** Spring Security with a JWT stored in a cookie.
  `TokenAuthenticationFilter` (under `com.onixbyte.helix.filter`) validates the
  token; the `X-Authorisation` header carries the exchanged token. Sign-in
  providers (Entra ID/MSAL, WeCom) live under `service/auth`. MFA is supported.
- **Persistence.** The schema is owned by Flyway — add migrations under
  `src/main/resources/db/migrations/`; `ddl-auto` is disabled. MyBatis XML
  mappers live in `src/main/resources/mapper/`. JPA entities go in
  `com.onixbyte.helix.domain.entity`.
- **Shared utilities.** Prefer the Onixbyte libraries already on the classpath
  (`identity-generator`, `tuple`, `common-toolbox`, `math-toolbox`) over
  reimplementing them.
- **Style.** UTF-8 source, Javadoc on public APIs, constructor injection with
  `final` fields, builder/record patterns for DTOs. Match the surrounding code.
- **Configuration.** Runtime config is externalised via environment variables
  (reference: `config/application-prod.yml.example`). Never hardcode secrets.
  Local `config/*.yml` files are gitignored.

## Web conventions

- **State.** Redux Toolkit slices in `src/store` (e.g. `auth-slice`);
  consume via `react-redux` hooks.
- **API calls.** Typed per-domain clients in `src/api` (`auth`, `user`, `role`,
  `department`, `position`, `menu`). They use the shared Axios instance in
  `src/client/web-client/index.ts`, whose `baseURL` comes from
  `VITE_API_BASE_URL` and which sends credentials (`withCredentials`).
- **Types.** Request/response types live in `src/types`.
- **Routing.** `react-router` 7 routes are declared in `src/router/index.tsx`.
- **UI.** Ant Design 6 components styled with Tailwind utility classes.
- **Environment.** `VITE_*` variables (see `.env.example`). The Vite dev server
  has no proxy — for local development point `VITE_API_BASE_URL` at the backend
  directly (e.g. `http://localhost:8080`).

## General guidelines

- **Custom development.** When extending the boilerplate, use the existing
  extension points rather than parallel patterns: add Flyway migrations for
  schema changes, register message keys in every i18n bundle, implement features
  through the standard `Controller → Service → Manager → Repository` chain, and
  expose typed API clients in `web/src/api`. Customise the baseline — do not
  re-architect it.
- **Commits.** Use Conventional Commits (`feat:`, `fix:`, `refactor:`, `chore:`,
  `docs:`, `perf:`, `style:`, `test:`). Match the language of the surrounding
  history (the log is a mix of English and Chinese).
- **Secrets.** Never commit `.env` files, local config, or credentials.
  `web/.dockerignore` excludes local env files from the build context — keep it
  that way.
