# Build the React SPA, then serve it with Caddy (static files + /api reverse proxy).
FROM node:22-alpine AS build

WORKDIR /app

ARG VITE_API_BASE_URL=/api
ARG VITE_MSAL_CLIENT_ID=
ARG VITE_MSAL_TENANT_ID=
ARG VITE_APP_TITLE=Helix
ARG VITE_DEFAULT_REGION_ABBREVIATION=GB

ENV VITE_API_BASE_URL=$VITE_API_BASE_URL \
    VITE_MSAL_CLIENT_ID=$VITE_MSAL_CLIENT_ID \
    VITE_MSAL_TENANT_ID=$VITE_MSAL_TENANT_ID \
    VITE_APP_TITLE=$VITE_APP_TITLE \
    VITE_DEFAULT_REGION_ABBREVIATION=$VITE_DEFAULT_REGION_ABBREVIATION

COPY package.json pnpm-lock.yaml ./
RUN corepack enable && pnpm install --frozen-lockfile

COPY . .
RUN pnpm build

FROM caddy:2-alpine

COPY --from=build /app/dist /srv
COPY Caddyfile /etc/caddy/Caddyfile

EXPOSE 80
