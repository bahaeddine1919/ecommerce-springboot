package tn.formalab.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.ecommerce.models.Category;
import tn.formalab.ecommerce.models.Product;
import tn.formalab.ecommerce.repositories.CategoryRepository;
import tn.formalab.ecommerce.repositories.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("products")

public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostMapping(path = "add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){

        System.out.println(product.name);

        Product savedProduct = this.productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }


    @GetMapping(path = "all")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products=this.productRepository.findAll();

        return  ResponseEntity.status(HttpStatus.OK).body(products);

    }


    @GetMapping(path = "one/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        try {
            Product product =productRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Product());
        }


    }



    @PatchMapping(path = "update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){

        Product updatedProduct =this.productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);

    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String,String>> deleteById(@PathVariable Integer id) {
        this.productRepository.deleteById(id);
        HashMap<String,String> obj =new HashMap<>();
        obj.put("message","product deleted Succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(obj) ;
    }


}
