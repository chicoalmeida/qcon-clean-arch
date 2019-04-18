package com.chico.cleanarch.app.dataprovider.mongo.repository;

import com.chico.cleanarch.app.dataprovider.mongo.model.FilmModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmModelRepository extends MongoRepository<FilmModel, Integer> {

}
