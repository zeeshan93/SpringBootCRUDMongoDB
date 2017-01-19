package com.test.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.test.model.User;

@Repository
public class UserDatabaseImpl implements UserDatabase {

	@Autowired
	private Environment environment;

	@Autowired
	private MongoClient mongoClient;

	public String saveUser(User user) {
		MongoDatabase database = mongoClient.getDatabase(environment.getProperty("mongo.dataBase"));
		MongoCollection<BasicDBObject> coll = database.getCollection(environment.getProperty("mongo.UserCollection"),
				BasicDBObject.class);
		Gson gson = new Gson();
		String dbContent = gson.toJson(user);
		BasicDBObject dbObj = (BasicDBObject) JSON.parse(dbContent);
		coll.insertOne(dbObj);
		return "Success";
	}

	public User readUser(String name) {
		MongoDatabase database = mongoClient.getDatabase(environment.getProperty("mongo.dataBase"));
		MongoCollection<BasicDBObject> coll = database.getCollection(environment.getProperty("mongo.UserCollection"),
				BasicDBObject.class);
		FindIterable<BasicDBObject> iter = coll.find(new BasicDBObject("name", name));
		if (iter.first() != null) {
			User user = new Gson().fromJson(iter.first().toString(), User.class);
			return user;
		}
		return null;
	}

	@Override
	public User updateUser(String name, String updatedName) {
		MongoDatabase database = mongoClient.getDatabase(environment.getProperty("mongo.dataBase"));
		MongoCollection<BasicDBObject> coll = database.getCollection(environment.getProperty("mongo.UserCollection"),
				BasicDBObject.class);
		FindIterable<BasicDBObject> iter = coll.find(new BasicDBObject("name", name));
		if (iter.first() != null) {
			BasicDBObject newQuery = new BasicDBObject("name",updatedName);
			coll.updateOne(new BasicDBObject("name", name), new BasicDBObject("$set",newQuery));
			User user = new Gson().fromJson(coll.find(new BasicDBObject("name", updatedName)).first().toString(), User.class);
			return user;
		}
		return null;
	}

	@Override
	public String deleteUser(String name) {
		MongoDatabase database = mongoClient.getDatabase(environment.getProperty("mongo.dataBase"));
		MongoCollection<BasicDBObject> coll = database.getCollection(environment.getProperty("mongo.UserCollection"),
				BasicDBObject.class);
		FindIterable<BasicDBObject> iter = coll.find(new BasicDBObject("name", name));
		if (iter.first() != null) {
			coll.deleteOne(new BasicDBObject("name", name));
			return "Delete Successful";
		}
		return "Delete Unsuccessful";
	}

}
