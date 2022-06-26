/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.repository;



import com.avbravo.trace.model.automovilismo.Auto;
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
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
//@Stateless
public class AutoRepositoryImpl implements AutoRepository {

    @Inject
    private Config config;

    @Inject
    @ConfigProperty(name = "mongodb.uri")
    private String mongodburi;
 
    @Inject
    MongoClient mongoClient;

    @Override
    public List<Auto> findAll() {

        List<Auto> list = new ArrayList<>();
        try {

            MongoDatabase database = mongoClient.getDatabase("automovilismo");
     
            MongoCollection<Document> collection = database.getCollection("auto");

            MongoCursor<Document> cursor = collection.find().iterator();
            Jsonb jsonb = JsonbBuilder.create();
            try {
                while (cursor.hasNext()) {
                    Auto auto = jsonb.fromJson(cursor.next().toJson(), Auto.class);
                    list.add(auto);
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
    public Optional<Auto> findById(String id) {

        try {
            MongoDatabase database = mongoClient.getDatabase("automovilismo");
            MongoCollection<Document> collection = database.getCollection("auto");
            Document doc = collection.find(eq("idauto", id)).first();
            Jsonb jsonb = JsonbBuilder.create();
            Auto auto = jsonb.fromJson(doc.toJson(), Auto.class);
            return Optional.of(auto);
        } catch (Exception e) {
            System.out.println("findById() " + e.getLocalizedMessage());
        }

        return Optional.empty();
    }

    @Override
    public Auto save(Auto auto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Auto> findByAuto(String contry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
