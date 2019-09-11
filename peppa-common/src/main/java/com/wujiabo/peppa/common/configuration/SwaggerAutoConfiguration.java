package com.wujiabo.peppa.common.configuration;

import com.netflix.hystrix.Hystrix;
import com.wujiabo.peppa.common.Constant.TokenConstants;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnClass({Hystrix.class})
@ConditionalOnProperty(value = "swagger.ui.enabled", matchIfMissing = false)
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableSwagger2
public class SwaggerAutoConfiguration extends WebMvcConfigurationSupport {
    @Value("${swagger.ui.title}")
    private String title;
    @Value("${swagger.ui.desc}")
    private String desc;
    @Value("${swagger.ui.version}")
    private String version;
    @Value("${swagger.ui.basePackage}")
    private String basePackage;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).description(desc).version(version).build();
    }

    @Bean
    public Docket swaggerApi() {
        //  ==================== 需要的参数 START ====================
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder token = new ParameterBuilder();
        token.name(TokenConstants.HEADER_TOKEN_KEY).description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(token.build());
        //  ==================== 需要的参数 END ====================
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .globalOperationParameters(pars) // 全局参数
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .forCodeGeneration(true).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
                .build();
    }

    private List<ResponseMessage> customerResponseMessage() {
        List<ResponseMessage> list = new ArrayList<>();
        list.add(new ResponseMessageBuilder().code(200).message("请求成功").build());
        list.add(new ResponseMessageBuilder().code(201).message("资源创建成功").build());
        list.add(new ResponseMessageBuilder().code(204).message("服务器成功处理了请求，但不需要返回任何实体内容").build());
        list.add(new ResponseMessageBuilder().code(400).message("请求失败,具体查看返回业务状态码与对应消息").build());
        list.add(new ResponseMessageBuilder().code(401).message("请求失败,未经过身份认证").build());
        list.add(new ResponseMessageBuilder().code(405).message("请求方法不支持").build());
        list.add(new ResponseMessageBuilder().code(415).message("请求媒体类型不支持").build());
        list.add(new ResponseMessageBuilder().code(500).message("服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理").build());
        list.add(new ResponseMessageBuilder().code(503).message("服务器当前无法处理请求,这个状况是临时的，并且将在一段时间以后恢复").build());
        return list;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
