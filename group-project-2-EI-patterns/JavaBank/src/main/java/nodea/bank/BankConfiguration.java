package nodea.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * BankConfiguration
 */
@Configuration
public class BankConfiguration implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(BankConfiguration.class);
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        logger.info("IT WORKS");
        configurer.defaultContentType(MediaType.APPLICATION_XML);
    }

}