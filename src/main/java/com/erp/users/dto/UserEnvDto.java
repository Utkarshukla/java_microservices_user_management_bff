package com.erp.users.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "env")
public record UserEnvDto(String appName, String author) {

}
