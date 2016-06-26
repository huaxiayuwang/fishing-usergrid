package com.fishing.usergrid.rest.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/")
@Component
@Scope("singleton")
@Produces({MediaType.APPLICATION_JSON, "application/javascript", "application/x-javascript", "text/ecmascript", "application/ecmascript", "text/jscript"})
public class RootResource {
	
}
