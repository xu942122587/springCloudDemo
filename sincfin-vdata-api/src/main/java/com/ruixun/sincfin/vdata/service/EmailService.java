package com.ruixun.sincfin.vdata.service;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

@Service
public class EmailService {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer; // 自动注入

	@Autowired
	private JavaMailSender mailSender; // 自动注入的Bean
	//
	// @Value("${daily-tos}")
	private String tos;
	// @Value("${kf-daily-tos}")
	private String kfDailyTos;

	// @Value("${cw-daily-tos}")
	private String cWDailytos;

	private static String fromName = "1@qq.com";

	public void sendDailyMail(String ftl, Map<String, Object> map) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromName);
			helper.setTo(tos.split(","));
			helper.setSubject(map.get("subject") == null ? " " : map.get("subject") + "");

			// 修改 application.properties 文件中的读取路径
			FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
			configurer.setTemplateLoaderPath("classpath:templates");
			// 读取 html 模板
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(ftl);
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			helper.setText(html, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
