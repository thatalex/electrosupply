package by.alex.web.site.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Set;

public enum Util {
    INSTANCE;
    public final String MESSAGES_FILE = "." + File.separator + "messages.properties";
    private final Properties properties;
    private ReloadableResourceBundleMessageSource messageSource;

    Util() {
        properties = createProperties();
    }


    @Component
    public static class Initiator{

        @Autowired
        private ReloadableResourceBundleMessageSource messageSource;

        @PostConstruct
        public void init(){
            INSTANCE.messageSource = messageSource;
        }
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
