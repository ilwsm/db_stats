package org.kinocat.db_stats.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImportUtils {

    public static String textFromResource(String resource) throws IOException {
        Resource input = new ClassPathResource(resource);
        Path path = input.getFile().toPath();
        return Files.readString(path);
    }

    public static TextAndHash textFromResourceWithMD5(String resource) throws IOException, NoSuchAlgorithmException {
        Resource input = new ClassPathResource(resource);
        Path path = input.getFile().toPath();

        MessageDigest md = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(path);
             DigestInputStream dis = new DigestInputStream(is, md);
             ByteArrayOutputStream ous = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];

            int read;
            while ((read = dis.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }

            TextAndHash textAndHash = new TextAndHash();
            textAndHash.text = ous.toString();
            textAndHash.md5Digest = md.digest();
            return textAndHash;
        }
    }

    public static class TextAndHash {
        public String text;
        public byte[] md5Digest;
    }

}
