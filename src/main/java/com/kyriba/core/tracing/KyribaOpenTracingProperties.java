package com.kyriba.core.tracing;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "tracing")
public class KyribaOpenTracingProperties {
    private String service;
    private String application;
    private String proxyUrl;
    private String sourceName;
    private List<Integer> portNumbers = Arrays.asList(2878,30000);
}
