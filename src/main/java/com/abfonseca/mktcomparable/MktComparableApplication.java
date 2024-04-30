package com.abfonseca.mktcomparable;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abfonseca.mktcomparable.model.Market;
import com.abfonseca.mktcomparable.model.Product;
import com.abfonseca.mktcomparable.repository.MarketRepository;
import com.abfonseca.mktcomparable.repository.ProductRepository;

@SpringBootApplication
public class MktComparableApplication {

	public static void main(String[] args) {
		SpringApplication.run(MktComparableApplication.class, args);

	}

  @Bean
  CommandLineRunner initDataBase(ProductRepository productRepository, MarketRepository marketRepository) {
    return args -> {
      productRepository.deleteAll();
      marketRepository.deleteAll();

      Product p1 = new Product();
      p1.setName("Coca-cola");
      p1.setPrice(4.50);
      p1.setSize(350);

      Product p2 = new Product();
      p2.setName("Papel Higiênico");
      p2.setPrice(9.80);
      p2.setSize(12);



      Market m1 = new Market();
      m1.setName("Pão de Açucar");
      m1.setNeighborhood("Copacabana");

      Market m2 = new Market();
      m2.setName("Supermarket");
      m2.setNeighborhood("Barra da Tijuca");

      productRepository.save(p1);
      productRepository.save(p2);
      marketRepository.save(m1);
      marketRepository.save(m2);
    };
  }

}
