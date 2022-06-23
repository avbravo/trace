package com.avbravo.trace.controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


/**
 *
 * @author
 */
@Path("hello")
public class HelloController {

//    @Inject
//    MongoClient mongoClient;

    @GET
    public Response ping() {
        try {

   

        
        } catch (Exception e) {
            System.out.println("ping() " + e.getLocalizedMessage());
        }
        return Response
                .ok("ping")
                .build();
    }
}
