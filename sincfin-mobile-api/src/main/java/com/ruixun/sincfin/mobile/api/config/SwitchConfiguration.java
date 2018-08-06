package com.ruixun.sincfin.mobile.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "switch")
public class SwitchConfiguration {

	private boolean coexist;

	public boolean isCoexist() {
		return coexist;
	}

	public void setCoexist(boolean coexist) {
		this.coexist = coexist;
	}
	

}
