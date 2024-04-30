package com.abfonseca.mktcomparable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abfonseca.mktcomparable.model.Market;
import com.abfonseca.mktcomparable.repository.MarketRepository;

@Service
public class MarketService {

  @Autowired
  private MarketRepository marketRepository;

  public List<Market> getAllMarkets() {
    return marketRepository.findAll();
  }


  public Market insert(Market market) {
    return marketRepository.save(market);
  }

  public Optional<Market> findById(Long id) {
    Optional<Market> market = marketRepository.findById(id);
    return market;
  }

  public void delete(Long id) {
    marketRepository.deleteById(id);
  }

  public Market update(Long id, Market market) {
    Optional<Market> optionalEntity = marketRepository.findById(id);

    if (optionalEntity.isPresent()) {
      Market entity = optionalEntity.get();
      updateData(entity, market);
      return marketRepository.save(entity);
    } else {
      throw new RuntimeException("Produto não entontrado com o ID:" + id);
    }
  }

  private void updateData(Market entity, Market market) {
    entity.setName(market.getName());
    entity.setNeighborhood(market.getNeighborhood());
  }
}
