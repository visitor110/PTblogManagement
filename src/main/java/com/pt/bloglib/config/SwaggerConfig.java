package com.pt.bloglib.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
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

    private final String name = "PT";
    private final String url = "https://github.com/visitor110/PTblogManagement";
    private final String email = "1187038691@qq.com";
    // 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    private Boolean swaggerEnabled;

    @Bean
    public Docket restApiDocket() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("token")
                .description("认证token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());

        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo()) // 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
                .globalOperationParameters(parameters)
                .enable(swaggerEnabled) // 是否开启
                .genericModelSubstitutes(DeferredResult.class) //
                .useDefaultResponseMessages(false) //
                .forCodeGeneration(false) //
                .pathMapping("/").select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.pt.bloglib.controller")) // 指定扫描的包路径
                .paths(PathSelectors.any())// 指定路径处理PathSelectors.any()代表所有的路径
                .build().pathMapping("/");// 创建
    }

    /**
     * @return
     * @方法描述:构建 api文档的详细信息函数
     */
    private ApiInfo apiInfo() {
        // 联系人信息：联系人名字、联系人URL、联系人email
        Contact contact = new Contact(name, url, email);
        return new ApiInfoBuilder().title("SpringBoot-Swagger2集成和使用-demo示例") // 标题
                .description("Spring Boot test") // 描述
                .contact(contact)// 作者信息
                .version("0.0.1")// 版本
                // .extensions(null) //在basePath 基础上需要排除的url规则
                // .termsOfServiceUrl("") // 服务条款url
                // .license("") //许可证
                // .licenseUrl("") //许可证url
                .build();
    }
}
