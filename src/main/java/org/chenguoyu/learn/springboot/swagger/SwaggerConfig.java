package org.chenguoyu.learn.springboot.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
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

/**
 * swagger-ui的配置文件
 * 可以使用
 * http://ip-address:port/doc.html
 * doc.html
 * http://ip-address:port/swagger-ui.html
 * 访问接口文档
 *
 * @author 陈国钰
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    /**
     * 创建一个Docket对象
     * 调用select()方法，生成ApiSelectorBuilder对象实例，该对象负责定义外漏的API入口
     * 通过使用RequestHandlerSelectors和PathSelectors来提供Predicate，在此我们使用any()方法，将所有API都通过Swagger进行文档管理
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                //简介
                .description("")
                //服务条款
                .termsOfServiceUrl("")
                //作者个人信息
                .contact(new Contact("陈国钰","","chenguoyusir@gmail.com"))
                //版本
                .version("1.0")
                .build();
    }
}
