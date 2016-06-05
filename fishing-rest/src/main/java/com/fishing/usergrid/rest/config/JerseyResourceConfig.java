package com.fishing.usergrid.rest.config;

import javax.json.stream.JsonGenerator;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class JerseyResourceConfig extends ResourceConfig {
	public JerseyResourceConfig() {
		register(LoggingFilter.class);
        register(MultiPartFeature.class);
        register(JsonProcessingFeature.class);
        property(JsonGenerator.PRETTY_PRINTING, true);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        packages(true, "com.fishing.usergrid.rest");
	}
}
