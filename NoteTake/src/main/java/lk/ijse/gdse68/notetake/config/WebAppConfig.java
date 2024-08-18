package lk.ijse.gdse68.notetake.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse68.notetake")
@EnableWebMvc
public class WebAppConfig {
}

