package com.sandbox.sandbox;

import java.util.List;

import com.sandbox.sandbox.models.repositories.StarbucksRepository;
import com.sandbox.sandbox.models.starbucks.Starbucks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarbucksController {
    @Autowired private StarbucksRepository repository;

    @GetMapping(value = "/starbucks")
    public List<Starbucks> getAllStarbucks() {
        return repository.findAll();
    }

    @GetMapping(value = "/starbucks/{id}")
    public Starbucks getOneById(@PathVariable("id") String id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException(id));
    }

    @GetMapping(value = "/starbucks/within")
    public List<Starbucks> withinPolygon() {
        GeoJsonPolygon polygon = new GeoJsonPolygon(
            new GeoJsonPoint(-73.992514, 40.758934),
            new GeoJsonPoint(-73.961138, 40.760348),
            new GeoJsonPoint(-73.991658, 40.730006),
            new GeoJsonPoint(-73.992514, 40.758934));
        return repository.findByLocationWithin(polygon);
    }

    @GetMapping(value = "/starbucks/{lat}/{lon}")
    public List<Starbucks> getWithinRange(
        @PathVariable("lat") Double lon,
        @PathVariable("lon") Double lat
    ) {
        MongoOperations mongoOps = AppConfig.mongoOps;
        Circle circle = new Circle(lon, lat, (double)1000/39632);
        Query query = new Query(Criteria.where("location").withinSphere(circle));
        return mongoOps.find(query, Starbucks.class);
    }
}