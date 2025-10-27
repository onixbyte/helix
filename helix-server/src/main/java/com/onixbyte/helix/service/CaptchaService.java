package com.onixbyte.helix.service;

import com.onixbyte.captcha.Producer;
import com.onixbyte.helix.constant.FileFormat;
import com.onixbyte.helix.exception.BizException;
import com.onixbyte.helix.manager.CaptchaManager;
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

    @Autowired
    public CaptchaService(CaptchaManager captchaManager) {
        this.captchaManager = captchaManager;
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
    public ImmutableBiTuple<String, String> buildCaptcha() {
        if (isCaptchaEnabled()) {
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
                return ImmutableBiTuple.of(captchaDataUrl, uuid);
            } catch (IOException e) {
                throw new BizException("无法生成验证码图片。");
            }
        } else {
            return null;
        }
    }

    public boolean isCaptchaEnabled() {
        return captchaManager.isCaptchaEnabled();
    }

    public String getCaptcha(String uuid) {
        return captchaManager.getCaptcha(uuid);
    }
}
