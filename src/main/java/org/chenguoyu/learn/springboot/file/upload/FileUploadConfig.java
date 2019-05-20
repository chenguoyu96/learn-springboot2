package org.chenguoyu.learn.springboot.file.upload;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 文件上传配置类
 *
 * @author 陈国钰
 */
@Configuration
public class FileUploadConfig {

    @Value("${file.upload.path}")
    private String path;
    @Value("${file.upload.tmp}")
    private String tmp;

    /**
     * 防止文件大于10M时Tomcat连接重置
     *
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded() {
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }
}
