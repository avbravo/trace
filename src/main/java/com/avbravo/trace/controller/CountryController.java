/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.controller;


import com.avbravo.trace.model.Country;
import com.avbravo.trace.repository.CountryRepository;
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
@Path("/country")
@Tag(name = "Config Retrieval country", description = "Get the value for country")
public class CountryController {

 private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);
    @Inject
    CountryRepository countryRepository;

    

    

    // <editor-fold defaultstate="collapsed" desc="@GET">
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Get all country", description = "Returns all available items at the country")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @APIResponse(responseCode = "200", description = "The countrys")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The countrys", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "the countrys", required = true, name = "countrys")))
    public List<Country> get() {
        List<Country> list = new ArrayList<>();
        try {
   
            
            list = countryRepository.findAll();

        } catch (Exception e) {
            System.out.println("get() " + e.getLocalizedMessage());
        }

        return list;
    }


    
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Country> findAll() {
        return countryRepository.findAll();
    }
    
     @GET
    @Path("{id}")
    @Operation(summary = "Find a country by id", description = "Find a country by id")
    @APIResponse(responseCode = "200", description = "The country")
    @APIResponse(responseCode = "404", description = "When the id does not exist")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    @APIResponse(description = "The country", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Country.class)))
    public Country findById(
            @Parameter(description = "The item ID", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @PathParam("id") String id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new WebApplicationException("There is no country with the id " + id, Response.Status.NOT_FOUND));
    }
    
    
     @POST
    @Operation(summary = "Insert a country", description = "Insert a country")
    @APIResponse(responseCode = "201", description = "When creates an country")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public Response insert(
            @RequestBody(description = "Create a new country.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))) Country country) {
        return Response.status(Response.Status.CREATED).entity(countryRepository.save(country)).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete a country by ID", description = "Delete a country by ID")
    @APIResponse(responseCode = "200", description = "When deletes the country")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    @Tag(name = "BETA", description = "This API is currently in beta state")
    public Response delete(
       @Parameter(description = "The item ID", required = true, example = "1", schema = @Schema(type = SchemaType.STRING)) @PathParam("id") String id) {
      countryRepository.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
