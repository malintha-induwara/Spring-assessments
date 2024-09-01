package lk.ijse.gdse68.notetake.config;

import jakarta.servlet.annotation.MultipartConfig;
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
@MultipartConfig(
        location = "/tmp",
        fileSizeThreshold = 1024 * 1024 * 2, //After 2MB Data will be written to disk
        maxFileSize = 1024 * 1024 * 10,  //maximum file size that can upload
        maxRequestSize = 1024 * 1024 * 50 //maximum file size with file and other data
)
public class WebAppConfig {
}

