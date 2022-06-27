/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.repository;

import com.avbravo.trace.model.automovilismo.Conductor;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
//@Stateless
public class ConductorRepositoryImpl implements ConductorRepository {

    @Inject
    private Config config;

    @Inject
    @ConfigProperty(name = "mongodb.uri")
    private String mongodburi;

    @Inject
    MongoClient mongoClient;
 @Override
    public List<Conductor> findAll() {
     System.out.println("*************************************");
     System.out.println("findAllSimple");
     System.out.println("*************************************");
    printList("findAllSimple()", findAllSimple());
      System.out.println("*************************************");
     System.out.println("findAllLookup())");
     System.out.println("*************************************");
    printList("findAllLooku()",findAllLookup());
       System.out.println("*************************************");
     System.out.println("findAllBson())");
     System.out.println("*************************************");
   printList("findAllBson()", findAllBson());
        
       
        return new ArrayList<>();
    }

    public List<Conductor> findAllBson() {

        List<Conductor> list = new ArrayList<>();
        try {

            MongoDatabase database = mongoClient.getDatabase("automovilismo");

            MongoCollection<Document> collection = database.getCollection("conductor");

            Bson lookup = new Document("$lookup",
                    new Document("from", "auto")
                            .append("localField", "auto.idauto")
                            .append("foreignField", "idauto")
                            .append("as", "auto"));

            Bson match = new Document("$match",
                    new Document("idconductor", "7"));

            List<Bson> filters = new ArrayList<>();
            filters.add(lookup);
            filters.add(match);

        MongoCursor<Document> cursor = collection.aggregate(filters).iterator();
            Jsonb jsonb = JsonbBuilder.create();
            try {
                while (cursor.hasNext()) {
                    String json = cursor.next().toJson();
                    //  el problema es que lo devuelve como una lista de tipo Auto
                    System.out.println(">> JSon " + json);
                    Conductor conductor = jsonb.fromJson(json, Conductor.class);
                    list.add(conductor);
                }
            } finally {
                cursor.close();
            }
       


        } catch (Exception e) {
            System.out.println("findAll() " + e.getLocalizedMessage());
        }

        return list;
    }

   
    public List<Conductor> findAllLookup() {

        List<Conductor> list = new ArrayList<>();
        try {

            MongoDatabase database = mongoClient.getDatabase("automovilismo");

            MongoCollection<Document> collection = database.getCollection("conductor");

  
            MongoCursor<Document> cursor = collection.aggregate(
                    Arrays.asList(match(eq("idconductor", "7")),
                            Aggregates.unwind("$auto"),
                            lookup("auto", "auto.idauto", "idauto", "auto")
                    )).iterator();
 Jsonb jsonb = JsonbBuilder.create();
            try {
                while (cursor.hasNext()) {
                    Conductor conductor = jsonb.fromJson(cursor.next().toJson(), Conductor.class);
                    list.add(conductor);
                }
            } finally {
                cursor.close();
            }
        } catch (Exception e) {
            System.out.println("findAll() " + e.getLocalizedMessage());
        }

        return list;
    }

    public List<Conductor> findAllSimple() {

        List<Conductor> list = new ArrayList<>();
        try {
            MongoDatabase database = mongoClient.getDatabase("automovilismo");
            MongoCollection<Document> collection = database.getCollection("conductor");
            // Busca en la misma coleccion          
            MongoCursor<Document> cursor = collection.find().iterator();
            Jsonb jsonb = JsonbBuilder.create();
            try {
                while (cursor.hasNext()) {
                   String json = cursor.next().toJson();
                    System.out.println("-->jsnon " +json);
                    Conductor conductor = jsonb.fromJson(json, Conductor.class);
                    System.out.println("convertido "+conductor.toString());
                    list.add(conductor);
                }
            } finally {
                cursor.close();
            }

        } catch (Exception e) {
            System.out.println("findAllSimple() " + e.getLocalizedMessage());
        }

        return list;
    }

    @Override
    public Optional<Conductor> findById(String id) {

        try {
            MongoDatabase database = mongoClient.getDatabase("automovilismo");
            MongoCollection<Document> collection = database.getCollection("conductor");
            Document doc = collection.find(eq("idconductor", id)).first();
            Jsonb jsonb = JsonbBuilder.create();
            Conductor conductor = jsonb.fromJson(doc.toJson(), Conductor.class);
            return Optional.of(conductor);
        } catch (Exception e) {
            System.out.println("findById() " + e.getLocalizedMessage());
        }

        return Optional.empty();
    }

    @Override
    public Conductor save(Conductor conductor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Conductor> findByConductor(String contry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    private void printList(String title, List<Conductor>list){
        try {
             System.out.println("|---------------------------------------");
             System.out.println("|                                       |");
             System.out.println(" title "+title);
             System.out.println("|                                       |");
             System.out.println("-----------------------------------------");
        if(list.isEmpty()){
            System.out.println("----------------> "+title +" is empty");
        }else{
            System.out.println("-----------------> registros ");
            for(Conductor c:list){
                System.out.println(" "+c.toString());
            }
        }
        } catch (Exception e) {
            System.out.println("printList() "+e.getLocalizedMessage());
        }
    }
}
