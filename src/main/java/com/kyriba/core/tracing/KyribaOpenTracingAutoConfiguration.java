package com.kyriba.core.tracing;

import com.wavefront.opentracing.WavefrontTracer;
import com.wavefront.opentracing.reporting.Reporter;
import com.wavefront.opentracing.reporting.WavefrontSpanReporter;
import com.wavefront.sdk.common.WavefrontSender;
import com.wavefront.sdk.common.application.ApplicationTags;
import com.wavefront.sdk.common.clients.WavefrontClientFactory;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(WavefrontClientFactory.class)
@EnableConfigurationProperties(KyribaOpenTracingProperties.class)
public class KyribaOpenTracingAutoConfiguration {


	private final KyribaOpenTracingProperties properties;

	@Autowired
	public KyribaOpenTracingAutoConfiguration(KyribaOpenTracingProperties properties) {
		this.properties = properties;
	}

	@Bean
	public Tracer createWavefrontTracer() {
		ApplicationTags applicationTags = new ApplicationTags.Builder(properties.getApplication(), properties.getService()).build();
		WavefrontClientFactory wavefrontClientFactory = new WavefrontClientFactory();
		properties.getPortNumbers().forEach(portNumber -> wavefrontClientFactory.addClient(properties.getProxyUrl() + ":" + portNumber + "/"));
		WavefrontSender wavefrontSender = wavefrontClientFactory.getClient();
		Reporter wfSpanReporter = new WavefrontSpanReporter.Builder().withSource(properties.getSourceName()).build(wavefrontSender);
		return new WavefrontTracer.Builder(wfSpanReporter, applicationTags).build();
	}
}
