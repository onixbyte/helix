# Contributing to Helix

Thanks for your interest in contributing! Helix is a quick-start boilerplate for
custom development, open source under the [MIT licence](LICENCE). This guide
explains how to set up your environment, make changes and get them merged.

## What to contribute

Contributions should make the **boilerplate itself** more useful to everyone who
forks it:

- New or improved baseline capabilities (reusable, not business-specific)
- Bug fixes and hardening
- Documentation and tests

Changes that encode one particular organisation's custom rules are usually
better kept in your own fork. If you are not sure whether something belongs in
the boilerplate, open an issue first and ask.

## Getting started

1. Fork the repository and clone your fork.
2. Install the prerequisites:
   - Backend: JDK 21 and the Gradle wrapper (bundled in `server/`)
   - Frontend: Node.js 22+ and [pnpm](https://pnpm.io/)
   - Optional: Docker + Docker Compose for the full-stack run
3. Follow the [Quick start](README.md#quick-start) in the README to run the app
   locally.

## Development workflow

- Create a feature branch from `main`, using a short prefix for clarity:
  `feat/`, `fix/`, `refactor/`, `chore/` or `docs/` (e.g. `feat/role-audit-log`).
- Keep pull requests small and focused on a single concern.
- Write or update tests for the code you touch.
- Open a pull request against `main` and describe what changed and why.

## Code style

The full conventions are documented in [AGENTS.md](AGENTS.md). The essentials:

**Backend (`server/`)**

- Follow the `Controller → Service → Manager → Repository` layering.
- Use the custom validation framework for input validation.
- Throw `BizException` with a `MessageName` constant and add the message key to
  every bundle under `src/main/resources/i18n`.
- Add Flyway migrations for schema changes under `src/main/resources/db/migrations/`.
- Run `./gradlew build` before opening a PR.

**Frontend (`web/`)**

- Use ESLint and Prettier formatting; run `pnpm lint`.
- Keep API clients in `src/api` and state in Redux Toolkit slices.
- Never commit local `.env` files.

## Commit messages

Use [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: support TOTP multi-factor authentication
fix: resolve department name uniqueness check
refactor: extract build cookie
docs: update deployment guide
chore(deps): upgrade spring boot to 3.5.4
```

## Reporting issues

When opening an issue, include:

- What you expected to happen and what actually happened
- Steps to reproduce
- Environment details (OS, Java/Node versions, deployment mode)

Security issues: please report them privately to the maintainers rather than
opening a public issue.

## License

By contributing, you agree that your contributions are licensed under the
[MIT licence](LICENCE).
