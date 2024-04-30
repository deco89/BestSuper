package com.abfonseca.mktcomparable.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abfonseca.mktcomparable.model.Market;
import com.abfonseca.mktcomparable.service.MarketService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/market")
@AllArgsConstructor
public class MarketController {

  private MarketService marketService;

  @GetMapping
  public List<Market> list() {
    return marketService.getAllMarkets();
  }

  @PostMapping
  @ResponseStatus
  public Market create(@RequestBody Market market) {
      return marketService.insert(market);
  }

  @GetMapping({"/{id}"})
  public Market findById(@PathVariable Long id) {
      return marketService.findById(id).get();
  }

  @DeleteMapping({"/{id}"})
  public ResponseEntity<String> deleteMarket(@PathVariable Long id) {
    Optional<Market> market = marketService.findById(id);

    if(market.isPresent()) {
      marketService.delete(id);
      return ResponseEntity.ok().body(market.get().getName() + " deletado com sucesso");
    }
    return ResponseEntity.noContent().build();
  }

  @PutMapping({"/{id}"})
  public ResponseEntity<Market> updateMarket(@PathVariable Long id, @RequestBody Market market) {
      market = marketService.update(id, market);
      return ResponseEntity.ok().body(market);
  }
}
