package com.abfonseca.mktcomparable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abfonseca.mktcomparable.model.Product;
import com.abfonseca.mktcomparable.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product insert(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> findById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product;
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  public Product update(Long id, Product product) {
    Optional<Product> optionalEntity = productRepository.findById(id);

    if (optionalEntity.isPresent()) {
      Product entity = optionalEntity.get();
      updateData(entity, product);
      return productRepository.save(entity);
    } else {
      throw new RuntimeException("Produto não encontrado com o ID: " + id);
    }
  }


  private void updateData(Product entity, Product product) {
    entity.setName(product.getName());
    entity.setPrice(product.getPrice());
    entity.setSize(product.getSize());
  }
}
