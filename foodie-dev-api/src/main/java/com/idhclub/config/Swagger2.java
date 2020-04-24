package com.idhclub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    //    配置doket核心配置
//    http://localhost:8080/swagger-ui.html

    @Bean
    public Docket createRestAPi() {

        return new Docket(DocumentationType.SWAGGER_2)//指定api类型
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.idhclub.controller"))
                .paths(PathSelectors.any())
                .build();         //文档汇总信息
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("电商平台 API")
                .contact(new Contact("idhclub", "www.idhclub.cn", "123@qq.com"))
                .description("api文档")
                .version("1.0.1")
                .termsOfServiceUrl("www.idhclub.cn")
                .build();
    }

}
