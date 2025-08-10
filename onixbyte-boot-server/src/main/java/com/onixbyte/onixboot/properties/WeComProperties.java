package com.onixbyte.onixboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WeCom configuration properties.
 *
 * @author zihluwang
 */
@ConfigurationProperties(prefix = "app.authentication.we-com")
public class WeComProperties {

    /**
     * WeCom corporation id. Reference <a href="https://developer.work.weixin.qq.com/document/path/90665"
     * >WeCom Developers' Centre</a> for details.
     */
    private String corporationId;

    /**
     * WeCom application secret. Reference <a href="https://developer.work.weixin.qq.com/document/path/90665"
     * >WeCom Developers' Centre</a> for details.
     */
    private String secret;

    /**
     * Default constructor.
     */
    public WeComProperties() {
    }

    /**
     * Get corporation ID.
     *
     * @return corporation ID
     */
    public String getCorporationId() {
        return corporationId;
    }

    /**
     * Set corporation ID.
     *
     * @param corporationId corporation ID
     */
    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    /**
     * Get application secret.
     *
     * @return application secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Set application secret.
     *
     * @param secret application secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }
}
