package io.inkstand.examples.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * This is your actual rest service. Simply put a {@link Path} annotated class into one of your bean deployment archive
 * and it will be automatically started when starting inkstand.
 *
 * Created by <a href="mailto:gerald.muecke@gmail.com">Gerald M&uuml;cke</a> on 4/21/2015
 *
 * @author <a href="mailto:gerald.muecke@gmail.com">Gerald M&uuml;cke</a>
 */
@Path("/")
public class HelloWorldService {

    @GET
    @Path("/hello/world")
    public Response helloWorld() {
        return Response.ok("Hello world").build();
    }
}
