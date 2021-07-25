package com.sandbox.sandbox;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.repository.init.AbstractRepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class AppConfig {

  private static final String DATABASENAME = "test";
  private static final String DBADDRESS = "mongodb://localhost:27017";
  public static final MongoOperations mongoOps = new MongoTemplate(MongoClients.create(DBADDRESS), DATABASENAME);

  public @Bean MongoClient mongoClient() {
    return MongoClients.create(DBADDRESS);
  }

  public @Bean MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), DATABASENAME);
  }
  
  /**
   * Adds data to database
   * 
   * @return
   */
  public @Bean AbstractRepositoryPopulatorFactoryBean repositoryPopulator() {

    ObjectMapper mapper = new ObjectMapper();
    mapper.addMixIn(GeoJsonPoint.class, GeoJsonPointMixin.class);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
    factoryBean.setResources(new Resource[] { new ClassPathResource("starbucks-in-nyc.json") });
    factoryBean.setMapper(mapper);

    return factoryBean;
  }

  abstract static class GeoJsonPointMixin {
    GeoJsonPointMixin(@JsonProperty("longitude") double x, @JsonProperty("latitude") double y) {
    }
  }

}