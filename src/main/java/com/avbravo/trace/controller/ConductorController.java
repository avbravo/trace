/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.controller;


import com.avbravo.trace.model.automovilismo.Conductor;
import com.avbravo.trace.repository.ConductorRepository;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
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
@Path("/conductor")
@Tag(name = "Config Retrieval conductor", description = "Get the value for conductor")
public class ConductorController {

 private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);
    @Inject
    ConductorRepository conductorRepository;

    

    

    // <editor-fold defaultstate="collapsed" desc="@GET">
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Get all conductor", description = "Returns all available items at the conductor")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @APIResponse(responseCode = "200", description = "The conductors")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The conductors", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "the conductors", required = true, name = "conductors")))
    public List<Conductor> get() {
        List<Conductor> list = new ArrayList<>();
        try {
   
            
            list = conductorRepository.findAll();

        } catch (Exception e) {
            System.out.println("get() " + e.getLocalizedMessage());
        }

        return list;
    }


    
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Conductor> findAll() {
        return conductorRepository.findAll();
    }
    
     @GET
    @Path("{id}")
    @Operation(summary = "Find a conductor by id", description = "Find a conductor by id")
    @APIResponse(responseCode = "200", description = "The conductor")
    @APIResponse(responseCode = "404", description = "When the id does not exist")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The conductor", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Conductor.class)))
    public Conductor findById(
            @Parameter(description = "The item ID", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @PathParam("id") String id) {
        return conductorRepository.findById(id).orElseThrow(
                () -> new WebApplicationException("There is no conductor with the id " + id, Response.Status.NOT_FOUND));
    }
    
    
     @POST
    @Operation(summary = "Insert a conductor", description = "Insert a conductor")
    @APIResponse(responseCode = "201", description = "When creates an conductor")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public Response insert(
            @RequestBody(description = "Create a new conductor.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Conductor.class))) Conductor conductor) {
        return Response.status(Response.Status.CREATED).entity(conductorRepository.save(conductor)).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete a conductor by ID", description = "Delete a conductor by ID")
    @APIResponse(responseCode = "200", description = "When deletes the conductor")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public Response delete(
       @Parameter(description = "The item ID", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @PathParam("id") String id) {
      conductorRepository.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
