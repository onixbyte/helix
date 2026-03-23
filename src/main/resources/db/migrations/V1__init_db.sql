/*
 * Copyright (c) 2024-2026 OnixByte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * IMPORTANT NOTE ON DATABASE CREATION:
 * * If you intend to create the database using a user other than the one specified
 * for the application (e.g., 'system_admin' creating a database for 'app_user'),
 * please ensure this SQL file is executed by the **corresponding database user**.
 * * This is crucial for correctly setting up ownership and default privileges.
 * * Example: CREATE DATABASE my_database OWNER app_user;
 * * Ensure all necessary user role and permissions are in place **prior** to
 * running this initialisation script.
 */

--- Type Definitions ---
DROP TYPE IF EXISTS USER_STATUS CASCADE;
DROP TYPE IF EXISTS STATUS CASCADE;
DROP TYPE IF EXISTS CREDENTIAL_PROVIDER CASCADE;
DROP TYPE IF EXISTS SETTING_TYPE CASCADE;

CREATE TYPE USER_STATUS AS ENUM ('ACTIVE', 'INACTIVE', 'LOCKED');
CREATE TYPE STATUS AS ENUM ('ACTIVE', 'INACTIVE');
CREATE TYPE CREDENTIAL_PROVIDER AS ENUM ('LOCAL', 'OIDC', 'MICROSOFT_ENTRA_ID', 'GOOGLE_OIDC', 'SAML');
CREATE TYPE SETTING_TYPE AS ENUM ('STRING', 'BOOLEAN', 'INT');

--- Departments Table ---
DROP TABLE IF EXISTS department CASCADE;
CREATE TABLE department
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(128) NOT NULL UNIQUE,
    parent_id  BIGINT       NULL REFERENCES department (id),
    sort       INT          NOT NULL DEFAULT NULL,
    status     STATUS       NOT NULL DEFAULT 'ACTIVE'::STATUS,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX department_name_index ON department (name);
CREATE INDEX department_parent_id_index ON department (parent_id);

--- Departments Data Insertion ---
WITH company_hq AS (
    INSERT INTO department (name, parent_id, sort, status)
        VALUES ('Company HQ', NULL, 1, 'ACTIVE'::STATUS)
        RETURNING id),
     human_resources AS (
         INSERT INTO department (name, parent_id, sort, status)
             SELECT 'Human Resources', company_hq.id, 1, 'ACTIVE'::STATUS
             FROM company_hq
             RETURNING id),
     finance AS (
         INSERT INTO department (name, parent_id, sort, status)
             SELECT 'Finance', company_hq.id, 2, 'ACTIVE'::STATUS
             FROM company_hq
             RETURNING id),
     technology AS (
         INSERT INTO department (name, parent_id, sort, status)
             SELECT 'Technology', company_hq.id, 3, 'ACTIVE'::STATUS
             FROM company_hq
             RETURNING id),
     it_support AS (
         INSERT INTO department (name, parent_id, sort, status)
             SELECT 'IT Support', technology.id, 1, 'ACTIVE'::STATUS
             FROM technology
             RETURNING id),
     software_development AS (
         INSERT INTO department (name, parent_id, sort, status)
             SELECT 'Software Development', technology.id, 2, 'ACTIVE'::STATUS
             FROM technology
             RETURNING id),
     operations AS (
         INSERT INTO department (name, parent_id, sort, status)
             SELECT 'Operations', company_hq.id, 4, 'INACTIVE'::STATUS
             FROM company_hq
             RETURNING id)
SELECT NULL;

--- Positions Table ---
DROP TABLE IF EXISTS position CASCADE;
CREATE TABLE position
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128) NOT NULL UNIQUE,
    code        VARCHAR(64)  NULL UNIQUE,
    description TEXT,
    sort        INT          NOT NULL DEFAULT 0,
    status      STATUS       NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX position_name_index ON position (name);
CREATE INDEX position_code_index ON position (code);
CREATE INDEX position_name_code_index ON position (name, code);

--- Positions Data Insertion ---
INSERT INTO position (name, code, description, sort, status)
VALUES ('HR Manager', 'HR-MGR',
        'Responsible for overseeing recruitment, employee relations, and staff wellbeing.', 1,
        'ACTIVE'),
       ('Finance Officer', 'FIN-OFC',
        'Handles accounts, prepares financial statements, and ensures compliance with regulations.',
        2, 'ACTIVE'),
       ('IT Support Specialist', 'IT-SPT',
        'Provides technical assistance, manages helpdesk queries, and maintains computer systems.',
        3, 'ACTIVE'),
       ('Software Engineer', 'SWE-ENG',
        'Develops and maintains in-house applications, ensuring code quality and system reliability.',
        4, 'ACTIVE'),
       ('Operations Coordinator', 'OPS-CRD',
        'Assists with day-to-day logistics, procurement, and office organisation.', 5, 'INACTIVE');

--- Users Table ---
DROP TABLE IF EXISTS "user" CASCADE;
DROP SEQUENCE IF EXISTS user_id_seq CASCADE;
CREATE SEQUENCE user_id_seq START WITH 1;

CREATE TABLE "user"
(
    id                  BIGSERIAL PRIMARY KEY,
    username            VARCHAR(64) UNIQUE NOT NULL,
    full_name           VARCHAR(128)       NOT NULL,
    email               VARCHAR(128)       NULL UNIQUE,
    region_abbreviation VARCHAR(10)        NULL,
    phone_number        VARCHAR(32)        NULL,
    avatar_url          TEXT,
    status              USER_STATUS        NOT NULL DEFAULT 'ACTIVE',
    department_id       BIGINT REFERENCES department (id),
    position_id         BIGINT REFERENCES position (id),
    created_at          TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (region_abbreviation, phone_number)
);

CREATE INDEX user_username_index ON "user" (username);

--- Users Data Insertion ---
-- NOTE: All phone numbers are generated by ChatGPT, they should not be connected any real person.
INSERT INTO "user"(username, full_name, email, region_abbreviation, phone_number,
                   avatar_url, department_id, position_id, created_at, updated_at)
SELECT 'helix',
       'Helix Admin',
       'admin@helix.onixbyte.dev',
       'GB',
       '7000000000',
       'https://gravatar.com/avatar/6ef4c4033f6aa8e43d06bd5e462a6173cc2a960633473721a6f1289cd1b5146f',
       (SELECT id FROM department WHERE name = 'Company HQ'),
       (SELECT id FROM position WHERE code = 'HR-MGR'),
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP;

INSERT INTO "user"(username, full_name, email, region_abbreviation, phone_number,
                   avatar_url, department_id, position_id, created_at, updated_at)
SELECT 'johndoe',
       'John Doe',
       'johndoe@helix.onixbyte.dev',
       'GB',
       '7000000001',
       'https://gravatar.com/avatar/41bcebddd573747d1bd35ef7fae72ebefd6b47f077d42442a2510d35b0c2db92',
       (SELECT id FROM department WHERE name = 'Software Development'),
       (SELECT id FROM position WHERE code = 'SWE-ENG'),
       CURRENT_TIMESTAMP,
       CURRENT_TIMESTAMP;

--- User Identities Table ---
DROP TABLE IF EXISTS user_credential CASCADE;
CREATE TABLE user_credential
(
    user_id    BIGINT              NOT NULL REFERENCES "user" (id),
    provider   CREDENTIAL_PROVIDER NOT NULL,
    credential VARCHAR(255)        NOT NULL,
    created_at TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, provider, credential)
);

--- Roles Table ---
DROP TABLE IF EXISTS role CASCADE;
CREATE TABLE role
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(128) NOT NULL UNIQUE,
    code          VARCHAR(64)  NOT NULL UNIQUE,
    sort          INTEGER      NOT NULL,
    default_value BOOLEAN      NOT NULL DEFAULT FALSE,
    description   TEXT,
    status        STATUS       NOT NULL DEFAULT 'ACTIVE',
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX role_name_index ON role (name);
CREATE INDEX role_code_index ON role (code);
CREATE INDEX role_name_code_index ON role (name, code);

--- Roles Data Insertion ---
INSERT INTO role (name, code, sort, default_value, description, status, created_at, updated_at)
VALUES ('Admin', 'admin', 1, FALSE, 'Administrator of this system.', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Normal User', 'user', 2, TRUE, 'Normal user of this system.', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--- User Roles Table ---
DROP TABLE IF EXISTS user_role CASCADE;
CREATE TABLE user_role
(
    user_id    BIGINT                              NOT NULL
        CONSTRAINT user_role_user_id_fk REFERENCES "user",
    role_id    BIGINT                              NOT NULL
        CONSTRAINT user_role_role_id_fk REFERENCES role,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (user_id, role_id)
);

--- User Roles Data Insertion ---
INSERT INTO user_role (user_id, role_id)
SELECT u.id, r.id
FROM "user" u
         CROSS JOIN role r
WHERE (u.username = 'helix' AND r.code = 'admin')
   OR (u.username = 'johndoe' AND r.code = 'user');

--- Authorities Table ---
DROP TABLE IF EXISTS authority CASCADE;
CREATE TABLE authority
(
    id          BIGSERIAL PRIMARY KEY,
    code        VARCHAR(128) NOT NULL UNIQUE,
    name        VARCHAR(128) NOT NULL,
    description TEXT,
    status      STATUS       NOT NULL DEFAULT 'ACTIVE'::STATUS,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX authority_code_index ON authority (code);

--- Authorities Data Insertion ---
INSERT INTO authority(code, name, description, status, created_at, updated_at)
VALUES ('system:dashboard:read', 'Read Dashboard', 'Read dashboard.', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:user:read', 'Read User', 'Read user.', 'ACTIVE'::STATUS, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('system:user_detail:read', 'Read User', 'Read user detail.', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:user:write', 'Write User', 'Write user, such as add, edit or delete.',
        'ACTIVE'::STATUS, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:department:read', 'Read Department', 'Read department', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:department:write', 'Write Department', 'Write department.', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:role:read', 'Read Roles', 'Read role.', 'ACTIVE'::STATUS, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('system:role:write', 'Write Roles', 'Write role.', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:authority:read', 'Read Authorities', 'Read authority.', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:authority:write', 'Write Authorities', 'Write authority.',
        'ACTIVE'::STATUS, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:audit_log:read', 'Read Audit Logs', 'Read audit logs.', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:sso:write', 'Manage SSO',
        'Manage SSO configurations (such as Microsoft Entra ID, etc.).', 'ACTIVE'::STATUS,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('system:setting:write', 'Write System Settings', 'Write system setting.',
        'ACTIVE'::STATUS, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--- Role Authorities Table ---
DROP TABLE IF EXISTS role_authority CASCADE;
CREATE TABLE role_authority
(
    role_id      BIGINT    NOT NULL REFERENCES role (id),
    authority_id BIGINT    NOT NULL REFERENCES authority (id),
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (role_id, authority_id)
);

--- Role Authorities Data Insertion ---
-- Admin role gets all authority
INSERT INTO role_authority (role_id, authority_id, created_at)
SELECT r.id, a.id, CURRENT_TIMESTAMP
FROM role r
         CROSS JOIN authority a
WHERE r.code = 'admin';

-- Normal user role gets specific authority
INSERT INTO role_authority (role_id, authority_id, created_at)
SELECT r.id, a.id, CURRENT_TIMESTAMP
FROM role r
         CROSS JOIN authority a
WHERE r.code = 'user'
  AND a.code IN ('system:dashboard:read',
                 'system:user:read',
                 'system:user_detail:read',
                 'system:department:read',
                 'system:role:read',
                 'system:authority:read',
                 'system:audit_log:read');

DROP TABLE IF EXISTS asset;
CREATE TABLE asset
(
    id          BIGSERIAL    NOT NULL PRIMARY KEY,
    key         VARCHAR(255) NOT NULL UNIQUE,
    upload_by   BIGINT       NOT NULL REFERENCES "user" (id),
    upload_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX asset_key_index ON asset (key);

COMMENT ON TABLE asset IS 'Stores metadata for files or other digital asset within the system.';
COMMENT ON COLUMN asset.id IS 'The unique identifier for the asset, automatically generated by the database.';
COMMENT ON COLUMN asset.key IS 'The unique key or path of the asset within the storage system.';
COMMENT ON COLUMN asset.upload_by IS 'The unique ID of the user who uploaded this asset, referencing the ID in the user table.';
COMMENT ON COLUMN asset.upload_time IS 'The timestamp indicating when the asset was uploaded to the system.';

DROP TABLE IF EXISTS setting;
CREATE TABLE setting
(
    id            BIGSERIAL    NOT NULL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NULL,
    type          SETTING_TYPE NOT NULL,
    value         VARCHAR(255) NULL,
    default_value VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX setting_name_index ON setting (name);

COMMENT ON TABLE setting IS 'Hot-deployable application setting.';
COMMENT ON COLUMN setting.id IS 'Setting unique identifier.';
COMMENT ON COLUMN setting.name IS 'Setting name.';
COMMENT ON COLUMN setting.description IS 'Setting description.';
COMMENT ON COLUMN setting.type IS 'The type of the value.';
COMMENT ON COLUMN setting.value IS 'Setting current value.';
COMMENT ON COLUMN setting.default_value IS 'Setting default value.';

INSERT INTO setting(name, description, type, value, default_value)
VALUES ('captcha-enabled', 'Whether captcha is enabled.', 'BOOLEAN'::SETTING_TYPE, 'true',
        'false'),
       ('register-enabled', 'Whether register is enabled', 'BOOLEAN'::SETTING_TYPE,
        'true', 'false');

DROP TABLE IF EXISTS menu;
CREATE TABLE menu
(
    id               BIGSERIAL    NOT NULL PRIMARY KEY,
    name             VARCHAR(50)  NOT NULL,
    parent_id        BIGINT       NULL     DEFAULT NULL,
    code             VARCHAR(255) NOT NULL,
    sort             INTEGER      NOT NULL DEFAULT 0,
    path             VARCHAR(255) NULL     DEFAULT NULL,
    is_external_link BOOLEAN      NOT NULL DEFAULT FALSE,
    is_visible       BOOLEAN      NOT NULL DEFAULT TRUE,
    status           STATUS       NOT NULL DEFAULT 'ACTIVE'::STATUS,
    authority_code   VARCHAR(128) NULL     DEFAULT NULL,
    icon             VARCHAR(128) NULL     DEFAULT NULL,
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX menu_code_uindex ON menu (code);

WITH system_menu AS (
    INSERT INTO menu (name, parent_id, code, sort, path, is_external_link, is_visible, status,
                      authority_code, icon, created_at, updated_at)
        VALUES ('系统管理', NULL, 'system-mgmt', 99, NULL, FALSE, TRUE, 'ACTIVE'::STATUS, NULL,
                NULL, NOW(), NOW())
        RETURNING id)
INSERT
INTO menu(name, parent_id, code, sort, path, is_external_link, is_visible, status,
          authority_code, icon, created_at, updated_at)
SELECT '用户管理',
       system_menu.id,
       'user-mgmt',
       1,
       '/user',
       FALSE,
       TRUE,
       'ACTIVE'::STATUS,
       'system:user:write',
       NULL,
       NOW(),
       NOW()
FROM system_menu;

INSERT INTO menu (name, parent_id, code, sort, path, is_external_link, is_visible, status,
                  authority_code, icon, created_at, updated_at)
VALUES ('Helix 官网', null, 'helix-official-site', 100, 'https://helix.onixbyte.com', true, true,
        'ACTIVE'::STATUS, null, null, NOW(), NOW());

DO
$$
    DECLARE
        v_user_id BIGINT;
        v_now     TIMESTAMP;
    BEGIN
        SELECT id INTO v_user_id FROM "user" WHERE username = 'helix';
        v_now := CURRENT_TIMESTAMP;

        RAISE NOTICE 'User ID: %, Timestamp: %', v_user_id, v_now;

        -- Default password is '123456'
        INSERT INTO "user_credential"
        VALUES (v_user_id, 'LOCAL'::credential_provider, '$2a$10$1LoatLVvHL3LFK0pnNXcM.eiPK6.UdA9cl9IDwanWHBAAILn1xe0K', v_now, v_now);
    END;
$$;