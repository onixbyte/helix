package com.onixbyte.onixboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Wecom configuration properties.
 *
 * @author zihluwang
 */
@ConfigurationProperties(prefix = "app.authentication.wecom")
public class WecomProperties {

    /**
     * Wecom corporation id. Reference <a href="https://developer.work.weixin.qq.com/document/path/90665"
     * >Wecom Developers' Centre</a> for details.
     */
    private String corporationId;

    /**
     * Wecom application ID.
     */
    private Long agentId;

    /**
     * Wecom application secret. Reference <a href="https://developer.work.weixin.qq.com/document/path/90665"
     * >Wecom Developers' Centre</a> for details.
     */
    private String secret;

    /**
     * Default constructor.
     */
    public WecomProperties() {
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

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
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
