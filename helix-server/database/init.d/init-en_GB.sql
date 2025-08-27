DROP TYPE IF EXISTS UserStatus CASCADE;
DROP TYPE IF EXISTS NormalStatus CASCADE;
DROP TYPE IF EXISTS IdentityProvider CASCADE;

CREATE TYPE UserStatus AS ENUM ('ACTIVE', 'INACTIVE', 'LOCKED');
CREATE TYPE NormalStatus AS ENUM ('ACTIVE', 'INACTIVE');
CREATE TYPE IdentityProvider AS ENUM ('LOCAL', 'OIDC', 'MICROSOFT_ENTRA_ID', 'GOOGLE_OIDC', 'SAML');

DROP TABLE IF EXISTS departments CASCADE;
CREATE TABLE departments
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(128) NOT NULL UNIQUE,
    parent_id  BIGINT       NULL REFERENCES departments (id),
    tree_path  TEXT,
    sort_order INT          NOT NULL DEFAULT 0,
    status     NormalStatus NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO departments (id, name, parent_id, tree_path, sort_order, status)
VALUES (1, 'Company HQ', NULL, '/1/', 1, 'ACTIVE'::NormalStatus),
       (2, 'Human Resources', 1, '/1/2/', 1, 'ACTIVE'::NormalStatus),
       (3, 'Finance', 1, '/1/3/', 2, 'ACTIVE'::NormalStatus),
       (4, 'Technology', 1, '/1/4/', 3, 'ACTIVE'::NormalStatus),
       (5, 'IT Support', 4, '/1/4/5/', 1, 'ACTIVE'::NormalStatus),
       (6, 'Software Development', 4, '/1/4/6/', 2, 'ACTIVE'::NormalStatus),
       (7, 'Operations', 1, '/1/7/', 4, 'INACTIVE'::NormalStatus);


DROP TABLE IF EXISTS positions CASCADE;
CREATE TABLE positions
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128) NOT NULL UNIQUE,
    code        VARCHAR(64)  NULL UNIQUE,
    description TEXT,
    sort_order  INT          NOT NULL DEFAULT 0,
    status      NormalStatus NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO positions (id, name, code, description, sort_order, status)
VALUES (1, 'HR Manager', 'HR-MGR',
        'Responsible for overseeing recruitment, employee relations, and staff wellbeing.', 1,
        'ACTIVE'),
       (2, 'Finance Officer', 'FIN-OFC',
        'Handles accounts, prepares financial statements, and ensures compliance with regulations.',
        2, 'ACTIVE'),
       (3, 'IT Support Specialist', 'IT-SPT',
        'Provides technical assistance, manages helpdesk queries, and maintains computer systems.',
        3, 'ACTIVE'),
       (4, 'Software Engineer', 'SWE-ENG',
        'Develops and maintains in-house applications, ensuring code quality and system reliability.',
        4, 'ACTIVE'),
       (5, 'Operations Coordinator', 'OPS-CRD',
        'Assists with day-to-day logistics, procurement, and office organisation.', 5, 'INACTIVE');


DROP TABLE IF EXISTS users CASCADE;
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

DROP TABLE IF EXISTS user_identities CASCADE;
CREATE TABLE user_identities
(
    user_id     BIGINT           NOT NULL REFERENCES users (id),
    provider    IdentityProvider NOT NULL,
    external_id VARCHAR(255)     NOT NULL,
    created_at  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, provider, external_id)
);

DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128) NOT NULL UNIQUE,
    code        VARCHAR(64)  NOT NULL UNIQUE,
    description TEXT,
    status      NormalStatus NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS permissions CASCADE;
CREATE TABLE permissions
(
    id          BIGSERIAL PRIMARY KEY,
    code        VARCHAR(128) NOT NULL UNIQUE,
    name        VARCHAR(128) NOT NULL,
    description TEXT,
    status      NormalStatus NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO permissions(code, name, description, status, created_at, updated_at)
VALUES ('system:dashboard:read', 'Read Dashboard', 'Read dashboard.', 'ACTIVE'::NormalStatus,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:user:read', 'Read User', 'Read user.', 'ACTIVE'::NormalStatus, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('system:user_detail:read', 'Read User', 'Read user detail.', 'ACTIVE'::NormalStatus,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:user:write', 'Write User', 'Write user, such as add, edit or delete.',
        'ACTIVE'::NormalStatus, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:department:read', 'Read Department', 'Read departments', 'ACTIVE'::NormalStatus,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:department:write', 'Write Department', 'Write departments.', 'ACTIVE'::NormalStatus,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:role:read', 'Read Roles', 'Read roles.', 'ACTIVE'::NormalStatus, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('system:role:write', 'Write Roles', 'Write roles.', 'ACTIVE'::NormalStatus,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:permission:read', 'Read Permissions', 'Read permissions.', 'ACTIVE'::NormalStatus,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:permission:write', 'Write Permissions', 'Write permissions.',
        'ACTIVE'::NormalStatus, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:audit_log:read', 'Read Audit Logs', 'Read audit logs.', 'ACTIVE'::NormalStatus,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:sso:write', 'Manage SSO',
        'Manage SSO configurations (such as Microsoft Entra ID, etc.).', 'ACTIVE'::NormalStatus,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:setting:write', 'Write System Settings', 'Write system settings.',
        'ACTIVE'::NormalStatus, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

DROP TABLE IF EXISTS role_permissions CASCADE;
CREATE TABLE role_permissions
(
    role_id       BIGINT    NOT NULL REFERENCES roles (id),
    permission_id BIGSERIAL NOT NULL REFERENCES permissions (id),
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (role_id, permission_id)
);

