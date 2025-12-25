package com.onixbyte.helix.constant;

public interface FileType {

    String getExtension();

    enum Image implements FileType {
        JPEG("jpeg"),
        PNG("png")
        ;

        private final String extension;

        Image(String extension) {
            this.extension = extension;
        }

        @Override
        public String getExtension() {
            return extension;
        }
    }
}
