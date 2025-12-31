package com.onixbyte.helix.domain.database.query.wrapper;

import com.onixbyte.helix.enumeration.Status;

public class QueryRoleWrapper {
    private String name;
    private String code;
    private Status status;

    public QueryRoleWrapper() {
    }

    public QueryRoleWrapper(String name, String code, Status status) {
        this.name = name;
        this.code = code;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
