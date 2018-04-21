package au.com.tabcorp.configs;

import au.com.tabcorp.core.services.BetReportService;
import au.com.tabcorp.core.services.BetStoreService;
import au.com.tabcorp.utils.AppSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This class is the contains the spring configurations
 */
@Configuration
@EnableWebMvc
@ComponentScan({"au.com.tabcorp.controllers"})
public class AppConfigs {
    @Bean
    public BetStoreService betStoreService() {
        return new BetStoreService();
    }

    @Bean
    public BetReportService betReportService() {
        return new BetReportService();
    }

    @Bean
    public AppSecurity security() {
        return new AppSecurity();
    }
}
