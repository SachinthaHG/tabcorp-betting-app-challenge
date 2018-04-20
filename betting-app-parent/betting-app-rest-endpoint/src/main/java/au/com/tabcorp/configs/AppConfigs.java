package au.com.tabcorp.configs;

import au.com.tabcorp.core.services.BetStoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan({"au.com.tabcorp.controllers"})
public class AppConfigs {
    @Bean
    public BetStoreService betStoreService() {
        return new BetStoreService();
    }
}
