/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.repository;



import com.avbravo.trace.model.cursos.Tutor;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
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
public class TutorRepositoryImpl implements TutorRepository {

    @Inject
    private Config config;

    @Inject
    @ConfigProperty(name = "mongodb.uri")
    private String mongodburi;
 
    @Inject
    MongoClient mongoClient;

    @Override
    public List<Tutor> findAll() {

        List<Tutor> list = new ArrayList<>();
        try {
//findAllSimple();
findAllBson();
        } catch (Exception e) {
            System.out.println("findAll() " + e.getLocalizedMessage());
        }

        return list;
    }
    
    public List<Tutor> findAllSimple() {

        List<Tutor> list = new ArrayList<>();
        try {

            MongoDatabase database = mongoClient.getDatabase("automovilismo");
     
            MongoCollection<Document> collection = database.getCollection("tutor");

            MongoCursor<Document> cursor = collection.find().iterator();
            Jsonb jsonb = JsonbBuilder.create();
            try {
                while (cursor.hasNext()) {
                    Tutor tutor = jsonb.fromJson(cursor.next().toJson(), Tutor.class);
                    list.add(tutor);
                }
            } finally {
                cursor.close();
            }

        } catch (Exception e) {
            System.out.println("findAll() " + e.getLocalizedMessage());
        }

        return list;
    }

    
    public List<Tutor> findAllBson() {

        List<Tutor> list = new ArrayList<>();
        try {

            MongoDatabase database = mongoClient.getDatabase("automovilismo");

            MongoCollection<Document> collection = database.getCollection("tutor");

            Bson lookup = new Document("$lookup",
                    new Document("from", "curso")
                            .append("localField", "idtutor")
                            .append("foreignField", "tutor.idtutor")
                            .append("as", "curso"));
            
             
                           

            Bson match = new Document("$match",
                    new Document("idtutor", "1"));

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
                    Tutor tutor = jsonb.fromJson(json, Tutor.class);
                    list.add(tutor);
                }
            } finally {
                cursor.close();
            }
       


        } catch (Exception e) {
            System.out.println("findAll() " + e.getLocalizedMessage());
        }

        return list;
    }

    @Override
    public Optional<Tutor> findById(String id) {

        try {
            MongoDatabase database = mongoClient.getDatabase("automovilismo");
            MongoCollection<Document> collection = database.getCollection("tutor");
            Document doc = collection.find(eq("idtutor", id)).first();
            Jsonb jsonb = JsonbBuilder.create();
            Tutor tutor = jsonb.fromJson(doc.toJson(), Tutor.class);
            return Optional.of(tutor);
        } catch (Exception e) {
            System.out.println("findById() " + e.getLocalizedMessage());
        }

        return Optional.empty();
    }

    @Override
    public Tutor save(Tutor tutor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Tutor> findByTutor(String contry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
