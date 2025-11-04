package com.onixbyte.helix.service;

import com.onixbyte.captcha.Producer;
import com.onixbyte.helix.constant.FileFormat;
import com.onixbyte.helix.domain.web.response.CaptchaResponse;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.CaptchaManager;
import com.onixbyte.helix.manager.CaptchaSettingManager;
import com.onixbyte.tuple.ImmutableBiTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class CaptchaService {

    private final CaptchaManager captchaManager;
    private final CaptchaSettingManager captchaSettingManager;

    @Autowired
    public CaptchaService(CaptchaManager captchaManager, CaptchaSettingManager captchaSettingManager) {
        this.captchaManager = captchaManager;
        this.captchaSettingManager = captchaSettingManager;
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
        if (!captchaSettingManager.isCaptchaEnabled()) {
            return null;
        }

        // 生成 UUID 及验证码
        var uuid = UUID.randomUUID().toString().replaceAll("-", "");
        var captchaCode = producer.createText();

        // 将验证码保存到缓存中
        captchaManager.setCaptcha(uuid, captchaCode);

        // 生成验证码图片
        var captchaImage = producer.createImage(captchaCode);
        try (var byteArrayOutputStream = new FastByteArrayOutputStream()) {
            ImageIO.write(captchaImage, FileFormat.IMAGE_JPEG, byteArrayOutputStream);
            var captchaDataUrl = "data:image/jpeg;base64," + Base64.getEncoder()
                    .encodeToString(byteArrayOutputStream.toByteArray());
            return new CaptchaResponse(captchaDataUrl, uuid);
        } catch (IOException e) {
            throw new BizException("无法生成验证码图片。");
        }
    }

    public String getCaptcha(String uuid) {
        return captchaManager.getCaptcha(uuid);
    }

    public boolean isCaptchaEnabled() {
        return captchaSettingManager.isCaptchaEnabled();
    }
}
