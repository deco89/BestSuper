package com.abfonseca.mktcomparable.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abfonseca.mktcomparable.model.Product;
import com.abfonseca.mktcomparable.repository.ProductRepository;
import com.abfonseca.mktcomparable.service.ProductService;

import lombok.AllArgsConstructor;

@RestController //diz para a classe que aqui é um controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

  private ProductRepository productRepository;
  private ProductService productService;

  @GetMapping
  public List<Product> list() {
    return productService.getAllProducts();
  }

  @PostMapping
  @ResponseStatus
  public Product create(@RequestBody Product product)  {
    return productService.insert(product);
  }

  @GetMapping({"/{id}"})
  public Product findById(@PathVariable Long id) {
    return productRepository.findById(id).get();
  }

  @PutMapping({"/{id}"})
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    product = productService.update(id, product);
    return ResponseEntity.ok().body(product);
  }

  @DeleteMapping({"/{id}"})
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    Optional<Product> product = productRepository.findById(id);

    if(product.isPresent()) {
      productService.delete(id);
      return ResponseEntity.ok().body("Produto excluido com sucesso");
    }
    return ResponseEntity.noContent().build();
  }
}
