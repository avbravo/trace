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

//AggregateIterable<Document> it =collection.aggregate(filters);
//
//for (Document row : it) {
//    System.out.println(row.toJson());
//}

            MongoCursor<Document> cursor = collection.aggregate(filters).iterator();
            Jsonb jsonb = JsonbBuilder.create();
            try {
                while (cursor.hasNext()) {
                    String json =cursor.next().toJson();
                    el problema es que lo devuelve como una lista de tipo Auto
                    System.out.println(">> JSon "+json);
                    Conductor conductor = jsonb.fromJson(json, Conductor.class);
                    list.add(conductor);
                }
            } finally {
                cursor.close();
            }
/**
 * Funciona sin cargar los datos de auto
 */
//            collection.aggregate(
//Arrays.asList(match(eq("idconductor", "7")),    
//lookup("auto", "auto.idauto", "idauto", "auto")
//))

/**
 * Search 3
 */
//Bson lookup = new Document("$lookup",
//        new Document("from", "auto")
//                .append("localField", "auto.idauto")
//                .append("foreignField", "idauto")
//                .append("as", "auto"));
//
//Bson match = new Document("$match",
//        new Document("idconductor", "7"));
//
//List<Bson> filters = new ArrayList<>();
//filters.add(lookup);
//filters.add(match);
// MongoCursor<Document> cursor = collection.aggregate(filters).iterator();
// 

//AggregateIterable<Document> it = db.getCollection("coll_one").aggregate(filters);
//
//for (Document row : it) {
//    System.out.println(row.toJson());
//}


// Search 1
//  no trae los demas datos de Auto
//            MongoCursor<Document> cursor = collection.aggregate(
//                    Arrays.asList(match(eq("idconductor", "7")),
//                            lookup("auto", "auto.idauto", "idauto", "auto")
//                    )).iterator();
//
//  


// Search 0
  // Busca en la misma coleccion          
//            MongoCursor<Document> cursor = collection.find().iterator();
//            Jsonb jsonb = JsonbBuilder.create();
//            try {
//                while (cursor.hasNext()) {
//                    Conductor conductor = jsonb.fromJson(cursor.next().toJson(), Conductor.class);
//                    list.add(conductor);
//                }
//            } finally {
//                cursor.close();
//            }

        } catch (Exception e) {
            System.out.println("findAll() " + e.getLocalizedMessage());
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

}
