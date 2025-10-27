package com.onixbyte.helix.config;

import com.onixbyte.captcha.Producer;
import com.onixbyte.captcha.impl.DefaultCaptchaProducer;
import com.onixbyte.captcha.text.impl.DefaultTextCreator;
import com.onixbyte.helix.properties.CaptchaProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({CaptchaProperties.class})
public class CaptchaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "app.captcha", name = "enabled", havingValue = "true")
    public Producer producer(CaptchaProperties captchaProperties) {
        var textProducer = DefaultTextCreator.builder()
                .length(captchaProperties.length())
                .build();
        return DefaultCaptchaProducer.builder()
                .textProducer(textProducer)
                .build();
    }
}
