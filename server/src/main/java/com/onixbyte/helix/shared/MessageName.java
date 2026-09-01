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

package com.onixbyte.helix.shared;

public class MessageName {

    public static final String ASSET_NOT_EMPTY = "asset.not-empty";
    public static final String ASSET_INVALID_PREFIX = "asset.invalid-prefix";
    public static final String ASSET_DELETE_FORBIDDEN = "asset.delete-forbidden";
    public static final String ASSET_UPLOAD_FAILED = "asset.upload-failed";

    public static final String AUTHORITY_DELETED = "authority.deleted";
    public static final String AUTHORITY_CODE_USED = "authority.code-used";
    public static final String AUTHORITY_NOT_FOUND = "authority.not-found";

    public static final String AUTH_LOGIN_CAPTCHA_NOT_FOUND = "auth.login.captcha-not-found";
    public static final String AUTH_LOGIN_CAPTCHA_INCORRECT = "auth.login.captcha-incorrect";
    public static final String AUTH_LOGIN_FAILED = "auth.login.failed";

    public static final String AUTH_PROVIDER_FAILED = "auth.provider.failed";
    public static final String AUTH_PROVIDER_BAD_CREDENTIALS = "auth.provider.bad-credentials";
    public static final String AUTH_PROVIDER_PASSWORD_NOT_CONFIGURED = "auth.provider.password-not-configured";

    public static final String CAPTCHA_GENERATE_FAILED = "captcha.generate-failed";

    public static final String ROLE_NOT_EXISTS = "role.not-exists";

    public static final String USER_PASSWORD_RESET_SUCCESS = "user.password-reset-success";
    public static final String USER_DELETED = "user.deleted";
    public static final String USER_NOT_FOUND = "user.not-found";

    public static final String SECURITY_CONTEXT_USER_NOT_FOUND = "security.context-user-not-found";
    public static final String TREE_MULTIPLE_ROOTS = "tree.multiple-roots";

    public static final String ROLE_NOT_FOUND = "role.not-found";
    public static final String ROLE_DELETED = "role.deleted";

    public static final String REQUEST_ADD_USER_USERNAME_NOT_EMPTY = "request.add-user.username.not-empty";
    public static final String REQUEST_ADD_USER_PASSWORD_NOT_EMPTY = "request.add-user.password.not-empty";
    public static final String REQUEST_ADD_USER_FULL_NAME_NOT_EMPTY = "request.add-user.full-name.not-empty";

    public static final String REQUEST_AUTHORITY_CODE_NOT_EDITABLE = "request.authority.code.not-editable";
    public static final String REQUEST_AUTHORITY_CODE_NOT_NULL = "request.authority.code.not-null";
    public static final String REQUEST_AUTHORITY_NAME_NOT_NULL = "request.authority.name.not-null";

    public static final String REQUEST_DEPARTMENT_NAME_NOT_NULL = "request.department.name.not-null";

    public static final String REQUEST_EDIT_ROLE_ID_NOT_NULL = "request.edit-role.id.not-null";
    public static final String REQUEST_EDIT_ROLE_STATUS_INVALID = "request.edit-role.status.invalid";

    public static final String REQUEST_EDIT_USER_ID_NOT_NULL = "request.edit-user.id.not-null";
    public static final String REQUEST_EDIT_USER_ID_POSITIVE = "request.edit-user.id.positive";

    public static final String REQUEST_RESET_PASSWORD_NOT_EMPTY = "request.reset-password.password.not-empty";

    public static final String REQUEST_ROLE_NAME_NOT_EMPTY = "request.role.name.not-empty";
    public static final String REQUEST_ROLE_CODE_NOT_EMPTY = "request.role.code.not-empty";
    public static final String REQUEST_ROLE_SORT_NOT_NULL = "request.role.sort.not-null";

    public static final String REQUEST_QUERY_ROLE_STATUS_INVALID = "request.query-role.status.invalid";
    public static final String REQUEST_QUERY_USER_STATUS_INVALID = "request.query-user.status.invalid";

    public static final String REQUEST_CREATE_DEPARTMENT_NAME_DUPLICATED = "request.create-department.name-duplicated";
}
