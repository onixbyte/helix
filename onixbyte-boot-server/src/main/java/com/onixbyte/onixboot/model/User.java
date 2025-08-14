package com.onixbyte.onixboot.model;

import com.onixbyte.onixboot.validation.group.OnCreate;
import com.onixbyte.onixboot.validation.group.OnUpdate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * User entity.
 *
 * @author zihluwang
 */
public class User {

    /**
     * User ID.
     */
    @NotNull(groups = {OnUpdate.class}, message = "User ID cannot be located without an ID.")
    private Long id;

    /**
     * Username.
     */
    @NotEmpty(groups = {OnCreate.class}, message = "Username cannot be empty.")
    private String username;

    /**
     * Real name.
     */
    @NotEmpty(groups = {OnCreate.class}, message = "Username cannot be empty.")
    private String name;

    /**
     * Email address.
     */
    @NotEmpty(groups = {OnCreate.class}, message = "Email cannot be empty.")
    private String email;

    /**
     * Password.
     */
    private String password;

    /**
     * Microsoft Entra ID Open ID.
     */
    private String msalOpenId;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsalOpenId() {
        return msalOpenId;
    }

    public void setMsalOpenId(String msalOpenId) {
        this.msalOpenId = msalOpenId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getMsalOpenId(), user.getMsalOpenId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getName(), getEmail(), getPassword(),
                getMsalOpenId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", msalOpenId='" + msalOpenId + '\'' +
                '}';
    }
}
