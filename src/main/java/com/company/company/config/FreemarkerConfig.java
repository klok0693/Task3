package com.company.company.config;

import com.company.company.NotNullByDefault;
import com.company.company.entity.Mail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
@NotNullByDefault

@Configuration
public class FreemarkerConfig {

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");
        return bean;
    }

    @Bean
    public Mail getMail() {
        return new Mail();
    }
}
