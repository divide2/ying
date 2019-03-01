package com.ying.core.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static java.util.Collections.singletonList;
import static springfox.documentation.builders.PathSelectors.ant;

/**
 * @author bvvy
 * swagger2 config
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/")
                .directModelSubstitute(Character.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, singletonList(
                        new ResponseMessageBuilder().code(409).message("{code: 错误问题}").build())
                )
                .ignoredParameterTypes(Pageable.class,OAuth2Authentication.class, Errors.class)
                .securitySchemes(singletonList(oauth()))
                .securityContexts(singletonList(securityContext()))
//                .enableUrlTemplating(true)
//               .additionalModels(typeResolver.resolve(AdditionalModel.class))
                ;
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .description("小影圈接口文档")
                .title("小影圈接口文档")
                .license("Apache License Version 2.0")
                .version("0.0.1")
                .termsOfServiceUrl("/")
                .build();
    }

    public SecurityContext securityContext() {
        AuthorizationScope readScope = new AuthorizationScope("webclient", "webclient");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = readScope;
        SecurityReference securityReference = SecurityReference.builder()
                .reference("OAuth2")
                .scopes(scopes)
                .build();

        return SecurityContext.builder()
                .securityReferences(singletonList(securityReference))
                .forPaths(ant("/v1/**"))
                .build();
    }


    @Bean
    public SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("OAuth2")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }

    private  List<GrantType> grantTypes() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("/oauth/token");
        return singletonList(grantType);
    }

    private List<AuthorizationScope> scopes() {
        return singletonList(
                new AuthorizationScope("webclient", "登录"));
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return SecurityConfigurationBuilder.builder()
                .clientId("aiNzsAXE8tkOFJN6")
                .clientSecret("12345678")
                .realm("realm")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .appName("xyq")
                .build();
    }

}
