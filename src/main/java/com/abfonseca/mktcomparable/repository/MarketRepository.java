package com.abfonseca.mktcomparable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abfonseca.mktcomparable.model.Market;

@Repository
public interface MarketRepository extends JpaRepository <Market, Long> {

}
