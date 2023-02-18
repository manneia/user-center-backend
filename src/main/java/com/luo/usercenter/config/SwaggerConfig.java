package com.luo.usercenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 自定义swagger接口文档的配置
 * @author lkx
 */
@Configuration
@Profile("dev")
public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 分组名称
                .groupName("用户中心")
                .select()
                //这里一定要标注你控制器的位置
                .apis(RequestHandlerSelectors.basePackage("com.luo.usercenter.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api信息
     * @return api对象信息
     */
    private ApiInfo apiInfo()   {
        return new ApiInfoBuilder()
                .title("用户中心")
                .description("用户中心接口文档")
                .termsOfServiceUrl("https://github.com/manneia/")
                .contact(new Contact("lkx","https://github.com/manneia/","luokaixuan2001@163.com"))
                .version("1.0")
                .build();
    }
}