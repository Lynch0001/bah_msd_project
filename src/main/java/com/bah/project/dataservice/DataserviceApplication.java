package com.bah.project.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;


@SpringBootApplication
public class DataserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataserviceApplication.class, args);
	}

    @Bean
    public static JaegerTracer getTracer(){

	Configuration.SamplerConfiguration sampleConfiguration = 

	Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);

	Configuration.ReporterConfiguration reporterConfiguration = 

	Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);

	Configuration config = new Configuration("MSD Project-Api Tracing").
	
	withSampler(sampleConfiguration).withReporter(reporterConfiguration);

	return config.getTracer();

    }
	
}
