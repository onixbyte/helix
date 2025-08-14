-- Table structure
CREATE TABLE users
(
    id           BIGINT       NOT NULL,
    username     VARCHAR(255) NOT NULL,
    name         VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    msal_open_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk__users PRIMARY KEY (id)
);

-- Table comment
COMMENT ON TABLE users IS 'User information.';
COMMENT ON COLUMN users.id IS 'User ID.';
COMMENT ON COLUMN users.username IS 'Username.';
COMMENT ON COLUMN users.name IS 'User real name.';
COMMENT ON COLUMN users.email IS 'User email.';
COMMENT ON COLUMN users.password IS 'User login credential.';
COMMENT ON COLUMN users.msal_open_id IS 'User Microsoft Entra ID account ID.';

-- =================================================================================
-- Indexes
-- =================================================================================

-- 1. For Username-based Authentication
-- Ensures usernames are unique and lookups are fast.
CREATE UNIQUE INDEX idx_uq_users_username ON users (username);
COMMENT ON INDEX idx_uq_users_username IS 'Ensures uniqueness and fast lookups on the username column, critical for user login.';

-- 2. For Email-based Authentication and Recovery
-- Ensures email addresses are unique and lookups are fast.
CREATE UNIQUE INDEX idx_uq_users_email ON users (email);
COMMENT ON INDEX idx_uq_users_email IS 'Ensures uniqueness and fast lookups on the email column, critical for login and account recovery.';

-- 3. For Microsoft Entra ID (MSAL) Authentication
-- Ensures unique mapping to an MSAL account and provides fast lookups.
CREATE UNIQUE INDEX idx_uq_users_msal_open_id ON users (msal_open_id);
COMMENT ON INDEX idx_uq_users_msal_open_id IS 'Ensures unique mapping and fast lookups for users authenticating via Microsoft Entra ID.';

-- 4. For Searching by Real Name (Optional)
-- Accelerates searches in user directories or admin panels.
CREATE INDEX idx_users_name ON users (name);
COMMENT ON INDEX idx_users_name IS 'Improves performance of searches based on user real names (e.g., for admin panels).';
