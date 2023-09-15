package com.example.demo.auth.cotroller;

import com.example.demo.auth.model.Product;
import com.example.demo.auth.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @PostMapping
    public ResponseEntity<Product> cadastrar(@RequestBody Product product) {
        repository.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listar() {
        List<Product> produtos = repository.findAll();
        return ResponseEntity.ok().body(repository.findAll());
    }
}
