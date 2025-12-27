package com.onixbyte.helix.service;

import com.onixbyte.captcha.Producer;
import com.onixbyte.helix.constant.FileType;
import com.onixbyte.helix.constant.SettingName;
import com.onixbyte.helix.domain.entity.Setting;
import com.onixbyte.helix.domain.web.response.CaptchaResponse;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.CaptchaManager;
import com.onixbyte.helix.manager.SettingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class CaptchaService {

    private final CaptchaManager captchaManager;
    private final SettingManager settingManager;

    @Autowired
    public CaptchaService(CaptchaManager captchaManager, SettingManager settingManager) {
        this.captchaManager = captchaManager;
        this.settingManager = settingManager;
    }

    private Producer producer;

    @Autowired(required = false)
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**
     * Build captcha information.
     *
     * @return left value is data URL of captcha image, and right value is the identifier of
     * the captcha code
     */
    public CaptchaResponse buildCaptcha() {
        var captchaEnabled = Optional.ofNullable(settingManager.getSettingByName(SettingName.CAPTCHA_ENABLED))
                .map(Setting::asBoolean)
                .orElse(false);
        if (!captchaEnabled) {
            return null;
        }

        // Generate UUID and captcha
        var uuid = UUID.randomUUID().toString().replaceAll("-", "");
        var captchaCode = producer.createText();

        // Store UUID and captcha to cache
        captchaManager.setCaptcha(uuid, captchaCode);

        // Generate captcha image
        var captchaImage = producer.createImage(captchaCode);
        try (var byteArrayOutputStream = new FastByteArrayOutputStream()) {
            ImageIO.write(captchaImage, FileType.Image.JPEG.getExtension(), byteArrayOutputStream);
            var captchaDataUrl = "data:image/jpeg;base64," +
                    Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            return new CaptchaResponse(captchaDataUrl, uuid);
        } catch (IOException e) {
            throw new BizException("无法生成验证码图片。");
        }
    }
}
