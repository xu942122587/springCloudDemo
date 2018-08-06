package com.ruixun.sincfin.monitor.config;

import com.alibaba.fastjson.JSON;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class DingTalkNotifier extends AbstractStatusChangeNotifier {

    private String webhook;

    private Boolean enabled;

    private Expression text;

    private RestTemplate restTemplate = new RestTemplate();

    private final SpelExpressionParser parser = new SpelExpressionParser();

    public DingTalkNotifier(InstanceRepository repositpry) {
        super(repositpry);
        text = parser.parseExpression("Server #{registration.name}/#{id} is #{statusInfo.status}! Please check the health url: #{registration.healthUrl}", ParserContext.TEMPLATE_EXPRESSION);
    }

    private HttpEntity<String> createMessage(String message) {
        Map<String, Object> context = new HashMap<>();
        context.put("content", message);

        Map<String, Object> messageJson = new HashMap<>();
        messageJson.put("text", JSON.toJSONString(context));
        messageJson.put("msgtype", "text");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return new HttpEntity(JSON.toJSONString(messageJson), headers);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent instanceEvent, Instance instance) {
        return Mono.fromRunnable(() -> {
            if (enabled) {
                EvaluationContext context = new StandardEvaluationContext(instance);
                String message = text.getValue(context, String.class);
                restTemplate.postForEntity(webhook, createMessage(message), Void.class);
            }
        });
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getWebhook() {
        return webhook;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
