/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.controller;


import com.avbravo.trace.model.cursos.Tutor;
import com.avbravo.trace.repository.TutorRepository;
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
@Path("/tutor")
@Tag(name = "Config Retrieval tutor", description = "Get the value for tutor")
public class TutorController {

 private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);
    @Inject
    TutorRepository tutorRepository;

    

    

    // <editor-fold defaultstate="collapsed" desc="@GET">
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Get all tutor", description = "Returns all available items at the tutor")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @APIResponse(responseCode = "200", description = "The tutors")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The tutors", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "the tutors", required = true, name = "tutors")))
    public List<Tutor> get() {
        List<Tutor> list = new ArrayList<>();
        try {
   
            
            list = tutorRepository.findAll();

        } catch (Exception e) {
            System.out.println("get() " + e.getLocalizedMessage());
        }

        return list;
    }


    
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }
    
     @GET
    @Path("{id}")
    @Operation(summary = "Find a tutor by id", description = "Find a tutor by id")
    @APIResponse(responseCode = "200", description = "The tutor")
    @APIResponse(responseCode = "404", description = "When the id does not exist")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The tutor", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Tutor.class)))
    public Tutor findById(
            @Parameter(description = "The item ID", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @PathParam("id") String id) {
        return tutorRepository.findById(id).orElseThrow(
                () -> new WebApplicationException("There is no tutor with the id " + id, Response.Status.NOT_FOUND));
    }
    
    
     @POST
    @Operation(summary = "Insert a tutor", description = "Insert a tutor")
    @APIResponse(responseCode = "201", description = "When creates an tutor")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public Response insert(
            @RequestBody(description = "Create a new tutor.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tutor.class))) Tutor tutor) {
        return Response.status(Response.Status.CREATED).entity(tutorRepository.save(tutor)).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete a tutor by ID", description = "Delete a tutor by ID")
    @APIResponse(responseCode = "200", description = "When deletes the tutor")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public Response delete(
       @Parameter(description = "The item ID", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @PathParam("id") String id) {
      tutorRepository.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
