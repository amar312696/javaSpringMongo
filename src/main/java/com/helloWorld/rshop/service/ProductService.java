package com.helloWorld.rshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helloWorld.rshop.model.Schema;
import com.helloWorld.rshop.repo.PostRepo;

@Service
public class ProductService {
    @Autowired
    private PostRepo repositories;

    public String addProduct(Schema schema){
        repositories.save(schema);
        return "Product with id:"+ schema.getId() +" is Saved.";
    }

    public List<Schema> getAllProduct(){
        return repositories.findAll();
    }

    public Schema getProductById(int id){
        return repositories.findById(id).orElse(null);
    }

    public String deleteProduct(int id){
        repositories.deleteById(id);
        return "Product with Id:" + id + " is Deleted";
    }

    public String updateProduct(int id, Schema updatedSchema) {
        if (!repositories.existsById(id)) {
            return "Not Found";
        }
        Schema existingSchema = repositories.findById(id).get();

        existingSchema.setName(updatedSchema.getName());
        existingSchema.setImage(updatedSchema.getImage());
        existingSchema.setPrice(updatedSchema.getPrice());

        repositories.save(existingSchema);
        
        return "Updated";
    }

}
