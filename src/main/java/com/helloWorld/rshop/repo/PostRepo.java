package com.helloWorld.rshop.repo;

import com.helloWorld.rshop.model.Schema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends MongoRepository<Schema,Integer>
{

}