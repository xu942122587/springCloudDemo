package com.ruixun.sincfin.monitor.config;

import de.codecentric.boot.admin.server.config.AdminServerNotifierAutoConfiguration;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({MailSenderAutoConfiguration.class})
public class DingTalkNotifierAutoConfiguration {

    @Configuration
    @ConditionalOnProperty(prefix = "spring.boot.admin.notify.dingtalk", name = "enabled")
    @AutoConfigureBefore({AdminServerNotifierAutoConfiguration.NotifierTriggerConfiguration.class, AdminServerNotifierAutoConfiguration.CompositeNotifierConfiguration.class})
    public static class DingTalkNotifierConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @ConfigurationProperties("spring.boot.admin.notify.dingtalk")
        @RefreshScope
        public DingTalkNotifier dingTalkNotifier(InstanceRepository repository) {
            return new DingTalkNotifier(repository);
        }
    }
}
