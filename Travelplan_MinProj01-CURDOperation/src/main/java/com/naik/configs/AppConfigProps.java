package com.naik.configs;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="plan.module")
@EnableConfigurationProperties
public class AppConfigProps {

	private Map<String,String> messeges;
}
