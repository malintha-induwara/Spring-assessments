package lk.ijse.gdse68.notetake.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse68.notetake")
@EnableWebMvc
@EnableJpaRepositories(basePackages = "lk.ijse.gdse68.notetake")
@EnableTransactionManagement
public class WebAppConfig {
}

