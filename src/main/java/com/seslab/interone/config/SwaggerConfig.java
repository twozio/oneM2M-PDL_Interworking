package com.seslab.interone.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .info(apiInfo());
  }

  private Info apiInfo() {
    return new Info()
      .title("oneM2M-blockchain intterworking proxy")
      .description("This is a project for test")
      .version("1.0");
  }
}

