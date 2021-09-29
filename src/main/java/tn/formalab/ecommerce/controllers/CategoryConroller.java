package tn.formalab.ecommerce.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.ecommerce.models.Category;
import tn.formalab.ecommerce.repositories.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("categories")


public class CategoryConroller {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryConroller(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(path = "add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {

        System.out.println(category.name); // smartphones

        Category savedCategory = this.categoryRepository.save(category);
        // temchi tverifi fel database 3al unicité mta3 name
        // ken name riguel tsajlou w traja3 objet
        // ken name mch mriguel traja3 automatiquement HTTP status 500

        //ken name mriguel
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(categories);

    }

    @CrossOrigin()
    @GetMapping(path = "one/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        try {
            Category category = categoryRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new Category());
        }


    }

    @PatchMapping(path = "update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {

        Category updatedCategory = this.categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCategory);

    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Integer id) {
        this.categoryRepository.deleteById(id);
        HashMap<String, String> obj = new HashMap<>();
        obj.put("message", "category deleted Succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }


    /*
    FRONT => API
    FORM : name => submit : { name : "smartphones" }
    => POST => http://localhost:8080/categories/add
    => BODY : { name : "smartphones" }
    INTO API
    => save(catgeory) : save lel objet fel database table "categories"
    => save traja3 un objet de type Category m3abi b les données eli tsajlou fel base ( name , id )
    => savedCategory = { id : 1 , name : "smartphones" }
    API => FRONT ( return )
    HTTP => STATUS : y3awen el front bch yefhm est ce que l'API 5demt khdemtou comme il faut walla lé
    CREATED : 201
    OK : 200
    NOT_FOUND : 404

     */
    //REST
    /*
    http://localhost:8080/pathController/pathAPI
    TRANSPORT | BODY | PARAMS | HEADERS
    GET       |  X   |   OP   |   OP
    DELETE    |  X   |   OP   |   OP
    POST      |  OB  |   OP   |   OP
    PATCH     |  OB  |   OP   |   OP
     */


}
