package com.example.interviewtask.config;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    @Operation(description = "mapper to transfer all the data between dto's and User")
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }


}
