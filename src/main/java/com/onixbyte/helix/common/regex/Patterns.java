package com.onixbyte.helix.common.regex;

import java.util.regex.Pattern;

public class Patterns {

    public static final Pattern EMAIL = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public static final Pattern IMAGE_URL = Pattern.compile("^https?://.*\\.(?:png|jpg|jpeg|gif|webp|svg|avif)(?:\\?.*)?$", Pattern.CASE_INSENSITIVE);

    public static final Pattern GRAVATAR_IMAGE_URL = Pattern.compile("^https?://(?:[a-z0-9-]+\\.)?gravatar\\.com/avatar/([a-f0-9]{32})(?:\\?.*)?$", Pattern.CASE_INSENSITIVE);
}
