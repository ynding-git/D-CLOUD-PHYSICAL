package com.ynding.cloud.person.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.collection.CollUtil.newHashSet;

/**
 * @author ynding
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * 是否开启swagger配置，生产环境需关闭
     */
    @Value("${swagger.enabled}")
    private boolean enable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(enable)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(newHashSet("https", "http"))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger3构建RESTful APIs")
                .description("springboot 接口文档")
                .termsOfServiceUrl("termsOfServiceUrl")
                .license("http://springfox.github.io/springfox/docs/current/")
                .licenseUrl("http://springfox.github.io/springfox/docs/current/")
                .version("1.0").build();
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> r = new ArrayList<>();
        r.add(new ApiKey("Authorization", "Authorization", "header"));
        return r;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> r = new ArrayList<>();
        r.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build());
        return r;
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> r = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        r.add(new SecurityReference("Authorization", authorizationScopes));
        return r;
    }
}
