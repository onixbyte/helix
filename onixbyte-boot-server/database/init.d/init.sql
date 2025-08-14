DROP TYPE IF EXISTS UserStatus CASCADE;
DROP TYPE IF EXISTS NormalStatus CASCADE;
DROP TYPE IF EXISTS IdentityProvider CASCADE;

CREATE TYPE UserStatus AS ENUM ('ACTIVE', 'INACTIVE', 'LOCKED');
CREATE TYPE NormalStatus AS ENUM ('ACTIVE', 'INACTIVE');
CREATE TYPE IdentityProvider AS ENUM ('LOCAL', 'OIDC', 'MICROSOFT_ENTRA_ID', 'GOOGLE_OIDC', 'SAML');

DROP TABLE IF EXISTS departments;
CREATE TABLE departments
(
    id         BIGINT PRIMARY KEY,
    name       VARCHAR(128) NOT NULL UNIQUE,
    parent_id  BIGINT       NULL REFERENCES departments (id),
    tree_path  TEXT,
    sort_order INT          NOT NULL DEFAULT 0,
    status     NormalStatus NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS positions;
CREATE TABLE positions
(
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(128) NOT NULL UNIQUE,
    code        VARCHAR(64)  NULL UNIQUE,
    description TEXT,
    sort_order  INT          NOT NULL DEFAULT 0,
    status      NormalStatus NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id            BIGINT PRIMARY KEY,
    username      VARCHAR(64) UNIQUE NOT NULL,
    password      VARCHAR(255),
    full_name     VARCHAR(128)       NOT NULL,
    email         VARCHAR(128) UNIQUE,
    country_code  VARCHAR(10),
    phone_number  VARCHAR(32),
    avatar_url    TEXT,
    status        UserStatus         NOT NULL DEFAULT 'ACTIVE',
    department_id BIGINT REFERENCES departments (id),
    position_id   BIGINT REFERENCES positions (id),
    created_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX uidx_users_country_code_phone_number
    ON users (country_code, phone_number);

DROP TABLE IF EXISTS user_identities;
CREATE TABLE user_identities
(
    user_id     BIGINT           NOT NULL REFERENCES users (id),
    provider    IdentityProvider NOT NULL,
    external_id VARCHAR(255)     NOT NULL,
    created_at  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, provider, external_id)
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(128) NOT NULL UNIQUE,
    code        VARCHAR(64)  NOT NULL UNIQUE,
    description TEXT,
    status      NormalStatus NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS position_roles;
CREATE TABLE position_roles
(
    position_id BIGINT REFERENCES positions (id),
    role_id     BIGINT REFERENCES roles (id),
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (position_id, role_id)
);

DROP TABLE IF EXISTS permissions;
CREATE TABLE permissions
(
    id          BIGINT PRIMARY KEY,
    code        VARCHAR(128) NOT NULL UNIQUE,
    name        VARCHAR(128) NOT NULL,
    description TEXT,
    status      NormalStatus NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS role_permissions;
CREATE TABLE role_permissions
(
    role_id       BIGINT    NOT NULL REFERENCES roles (id),
    permission_id BIGINT    NOT NULL REFERENCES permissions (id),
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (role_id, permission_id)
);

