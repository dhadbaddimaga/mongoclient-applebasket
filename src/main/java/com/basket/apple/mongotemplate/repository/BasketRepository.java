package com.basket.apple.mongotemplate.repository;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class BasketRepository {

	MongoClient mongoClient;
	MongoClient getMongoClient() {
		if(mongoClient == null) {
			mongoClient = new MongoClient("localhost", 27017);
		}
		return mongoClient; 
	}
	

	public List<Object> getAllBaskets() {
		MongoClient mongoClient = getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("applebasket");
		MongoCollection<Document> collection = database.getCollection("baskets");
		
		FindIterable<Document> findIterable = collection.find();
		
		List<Object> basketsResponse = new ArrayList<Object>();
		
		for(Document doc : findIterable) {
			basketsResponse.add(doc);
		}
		
		return basketsResponse;
	}


	public List<Object> getBasketbyId(String id) {
		MongoClient mongoClient = getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("applebasket");
		MongoCollection<Document> collection = database.getCollection("baskets");
		
		BasicDBObject lkup = new BasicDBObject();
		lkup.append("from", "apples");
		lkup.append("localField", "_id");
		lkup.append("foreignField", "basketid");
		lkup.append("as", "apples" );
	    
		BasicDBObject lookup = new BasicDBObject("$lookup",lkup);
		
		System.out.println(lookup.toJson());
		
		BasicDBObject byid = new BasicDBObject();
		byid.append("_id", id);
		BasicDBObject match = new BasicDBObject("$match",byid);
		
		System.out.println(match.toJson());
		
		List<BasicDBObject> pipeline = new ArrayList<BasicDBObject>();
		pipeline.add(lookup);
		pipeline.add(match);
		AggregateIterable<Document> result = collection.aggregate(pipeline);
		
		List<Object> basketsResponse = new ArrayList<Object>();
		
		for(Document doc : result) {
			basketsResponse.add(doc);
		}
		
		return basketsResponse;
	}
	
	
//	 {
//	     $lookup:
//	       {
//	         from: "apples",
//	         localField: "_id",
//	         foreignField: "basketid" ,
//	         as: "apples"
//	       }	
//	  },
//
//	  { $match : { _id : objId1 } }

	
}
