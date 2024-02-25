package hmy.webapp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * MessageSource配置类。
 * MessageSource是Spring提供的用于国际化的类，它可以读取资源文件中的信息，用于国际化。
 * 备注：后续开发这个功能
 * @author Huang Miaoyan
 */
@Configuration
public class MessageSourceConfig {

    /**
     * 该方法是用于配置MessageSource的方法。
     * 它返回一个MessageSource对象，用于读取资源文件中的信息。
     * @return 一个MessageSource对象
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages"); // 读取resources/i18n下的messages的Resource Bundle资源文件
        messageSource.setDefaultEncoding("UTF-8"); // 设置编码方式
        return messageSource;
    }
}
