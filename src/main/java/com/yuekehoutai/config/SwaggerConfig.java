package com.yuekehoutai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("X-Token").description("用户token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());

        //API构建器
        ApiInfoBuilder apiBuilder = new ApiInfoBuilder();
        //设置API的相关信息
        apiBuilder.title("悦客系统");
        apiBuilder.description("只为成就更好的你");
        apiBuilder.contact(new Contact("corazon", "", ""));
        apiBuilder.version("1.0");
        //构建API对象
        ApiInfo api = apiBuilder.build();
        //构建API清单 用于说明那些接口需要生成API文档
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(api);
        //定义接口（控制层）所在的包
        docket.select().apis(RequestHandlerSelectors
                .basePackage("com.yuekehoutai.controller"))
                .paths(PathSelectors.any()).build()
                .globalOperationParameters(pars);
        return docket;
    }
}

