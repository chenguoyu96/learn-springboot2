package org.chenguoyu.learn.springboot.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义资源映射
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 配置静态访问资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 解决 swagger-ui.html 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决 doc.html 404 报错
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");

    }
}