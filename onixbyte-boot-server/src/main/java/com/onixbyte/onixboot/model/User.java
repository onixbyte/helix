package com.onixbyte.onixboot.model;

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
}
