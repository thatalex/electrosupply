package by.alex.web.site.configuration;

import by.alex.web.site.model.Util;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.IOException;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
	    File file = new File(Util.INSTANCE.MESSAGES_FILE);
        ReloadableResourceBundleMessageSource  messageSource = new ReloadableResourceBundleMessageSource();
        try {
            String path = file.getCanonicalPath();
            int index = path.lastIndexOf(".properties");
            path = path.substring(0, index);
            messageSource.setBasename("file:" + path);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        messageSource.setDefaultEncoding("cp1251");
		return messageSource;
	}

}