package com.sandbox.sandbox.models.repositories;

import java.util.List;

import com.sandbox.sandbox.models.starbucks.Starbucks;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StarbucksRepository extends MongoRepository<Starbucks, String> {
    public List<Starbucks> findByLocationWithin(GeoJsonPolygon polygon);
    
    @Query("{ location: { $geoWithin: { $centerSphere: [ [ ?0, ?1 ], 100 / 3963.2 ] } } }")
    public List<Starbucks> findByLatitudeAndLongitude(Double lon, Double lat);
}