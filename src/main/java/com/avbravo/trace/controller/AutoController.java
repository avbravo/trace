/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.controller;


import com.avbravo.trace.model.automovilismo.Auto;
import com.avbravo.trace.repository.AutoRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


/**
 *
 * @author avbravo
 */
@Path("/auto")
@Tag(name = "Config Retrieval auto", description = "Get the value for auto")
public class AutoController {

 private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);
    @Inject
    AutoRepository autoRepository;

    

    

    // <editor-fold defaultstate="collapsed" desc="@GET">
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Get all auto", description = "Returns all available items at the auto")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @APIResponse(responseCode = "200", description = "The autos")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The autos", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "the autos", required = true, name = "autos")))
    public List<Auto> get() {
        List<Auto> list = new ArrayList<>();
        try {
   
            
            list = autoRepository.findAll();

        } catch (Exception e) {
            System.out.println("get() " + e.getLocalizedMessage());
        }

        return list;
    }


    
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Auto> findAll() {
        return autoRepository.findAll();
    }
    
     @GET
    @Path("{id}")
    @Operation(summary = "Find a auto by id", description = "Find a auto by id")
    @APIResponse(responseCode = "200", description = "The auto")
    @APIResponse(responseCode = "404", description = "When the id does not exist")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The auto", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Auto.class)))
    public Auto findById(
            @Parameter(description = "The item ID", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @PathParam("id") String id) {
        return autoRepository.findById(id).orElseThrow(
                () -> new WebApplicationException("There is no auto with the id " + id, Response.Status.NOT_FOUND));
    }
    
    
     @POST
    @Operation(summary = "Insert a auto", description = "Insert a auto")
    @APIResponse(responseCode = "201", description = "When creates an auto")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public Response insert(
            @RequestBody(description = "Create a new auto.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Auto.class))) Auto auto) {
        return Response.status(Response.Status.CREATED).entity(autoRepository.save(auto)).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete a auto by ID", description = "Delete a auto by ID")
    @APIResponse(responseCode = "200", description = "When deletes the auto")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public Response delete(
       @Parameter(description = "The item ID", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @PathParam("id") String id) {
      autoRepository.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
