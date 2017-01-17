package com.test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@SpringBootApplication
@PropertySource({ "classpath:persistence-mongo.properties"})
public class Application {
	
	@Autowired
	private Environment env;
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	
	@Bean(name = "dataSourceClient")
	public MongoClient dataSource() {
		/*return new MongoClient(env.getProperty("mongo.url"),
				Integer.valueOf(env.getProperty("mongo.port")));*/
		String mongoUserName =  env
				.getProperty("mongo.userName");
		
		String mongoPassword = env
				.getProperty("mongo.password");
		
		String host = env
				.getProperty("mongo.url").toString();
		
		Object portOb = env
				.getProperty("mongo.port");
		Integer port = Integer.parseInt(portOb.toString());
		
		String db = env
				.getProperty("mongo.dataBase").toString();
		
		if(mongoPassword == null){
			return new MongoClient(env.getProperty("mongo.url"),
					Integer.valueOf(env.getProperty("mongo.port")));
		}
		MongoCredential credential = MongoCredential.createCredential(mongoUserName, db , mongoPassword.toCharArray());
		MongoClient client = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
		return client;
	}
}
