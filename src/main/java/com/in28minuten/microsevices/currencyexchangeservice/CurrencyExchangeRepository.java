package com.in28minuten.microsevices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

   CurrencyExchange findByFromAndTo(String from,String to);
}
