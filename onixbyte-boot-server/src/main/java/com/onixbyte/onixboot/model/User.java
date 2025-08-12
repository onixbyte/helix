package com.onixbyte.onixboot.model;

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
    private Long id;

    /**
     * Username.
     */
    private String username;

    /**
     * Real name.
     */
    private String name;

    /**
     * Password.
     */
    private String password;

    /**
     * Microsoft Entra ID Open ID.
     */
    private String msalOpenId;

    /**
     * DingTalk Open ID.
     */
    private String dingTalkOpenId;

    /**
     * WeCom Open ID.
     */
    private String weComOpenId;

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

    public String getDingTalkOpenId() {
        return dingTalkOpenId;
    }

    public void setDingTalkOpenId(String dingTalkOpenId) {
        this.dingTalkOpenId = dingTalkOpenId;
    }

    public String getWeComOpenId() {
        return weComOpenId;
    }

    public void setWeComOpenId(String weComOpenId) {
        this.weComOpenId = weComOpenId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getName(), user.getName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getMsalOpenId(), user.getMsalOpenId()) && Objects.equals(getDingTalkOpenId(), user.getDingTalkOpenId()) && Objects.equals(getWeComOpenId(), user.getWeComOpenId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getName(), getPassword(), getMsalOpenId(), getDingTalkOpenId(), getWeComOpenId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", msalOpenId='" + msalOpenId + '\'' +
                ", dingTalkOpenId='" + dingTalkOpenId + '\'' +
                ", weComOpenId='" + weComOpenId + '\'' +
                '}';
    }
}
