package com.helloWorld.rshop;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.helloWorld.rshop.model.Schema;
import com.helloWorld.rshop.repo.PostRepo;
// import com.helloWorld.rshop.repo.SearchRepo;
import com.helloWorld.rshop.service.ProductService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import springfox.documentation.annotations.ApiIgnore;

@RestController
public class PostController {

    // @Autowired //sting will create obj and map it
    // private PostRepo repo;

    @Autowired
    private ProductService service;
    
    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException{

        response.sendRedirect("./swagger-uo.html");
    }



    @GetMapping(value="/posts")

    public ResponseEntity<List<Schema>> getAllPosts(){

        return new ResponseEntity<List<Schema>>(service.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping(value="/posts/{id}")
    // public ResponseEntity<Schema> getPostById(@PathVariable("id") String id) {
    //     System.out.println(id);
    //     Optional<Schema> optionalSchema = repo.findById(id);
    //     System.out.println(id);
    //     if (optionalSchema.isPresent()) {
    //         System.out.println(id);
    //         return new ResponseEntity<>(optionalSchema.get(), HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
    public ResponseEntity<Schema> getProductById(@PathVariable int id) {
        return new ResponseEntity<Schema>(service.getProductById(id), HttpStatus.OK);
    }
    

    @PostMapping(value="/post")
    public ResponseEntity<String> addPost(@RequestBody Schema schema){
        
        return new ResponseEntity<String>(service.addProduct(schema),HttpStatus.OK);
    }

    @DeleteMapping(value="/del/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return new ResponseEntity<String>(service.deleteProduct(id), HttpStatus.OK);
    }

    @PutMapping(value="/posts/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Schema updatedSchema) {
        String updateResult = service.updateProduct(id, updatedSchema);
        
        if (updateResult.equals("Updated")) {
            return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);
        } else if (updateResult.equals("Not Found")) {
            return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>("Failed to update product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
