package lk.ijse.gdse68.notetakerspringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoteTakerSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteTakerSpringBootApplication.class, args);
    }


    @Bean
    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
