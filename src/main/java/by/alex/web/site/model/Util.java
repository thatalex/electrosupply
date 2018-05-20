package by.alex.web.site.model;

import by.alex.web.site.controller.FileUploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;
import java.util.Set;

public enum Util {
    INSTANCE;
    public final String MESSAGES_FILE = "." + File.separator + "messages.properties";
    private final Properties properties;
    private ReloadableResourceBundleMessageSource messageSource;
    private byte[] mainImage = new byte[0];

    Util() {
        properties = createProperties();
    }


    @Component
    public static class Initiator {

        @Autowired
        private ReloadableResourceBundleMessageSource messageSource;

        @PostConstruct
        public void init() {
            INSTANCE.messageSource = messageSource;
            INSTANCE.reloadMainImage();
        }
    }

    public void reloadMainImage(){
        try {
            INSTANCE.mainImage = Files.readAllBytes(Paths.get(FileUploadController.HOME_IMAGE_FILENAME));
        } catch (IOException e) {
        }
    }

    public String getMainImage(){
        return Base64.getEncoder().encodeToString(mainImage);
    }

    private Properties createProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(MESSAGES_FILE);
             Reader reader = new InputStreamReader(inputStream, Charset.forName("utf-8"))) {
            properties.load(reader);
        } catch (IOException e) {
        }
        return properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public static String toString(Set<?> objects) {
        if (objects != null && !objects.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Object o : objects) {
                builder.append(o).append(", ");
            }

            builder.setLength(builder.length() - 2);
            return builder.toString();
        }
        return "";
    }

    public void storeProperties() {
        try (OutputStream outputStream = new FileOutputStream(MESSAGES_FILE);
             Writer writer = new OutputStreamWriter(outputStream, Charset.forName("utf-8"))) {
            properties.store(writer, "");
            messageSource.clearCache();
        } catch (IOException e) {
        }
    }
}
