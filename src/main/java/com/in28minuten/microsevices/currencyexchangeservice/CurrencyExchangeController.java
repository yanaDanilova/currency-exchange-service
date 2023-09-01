package com.in28minuten.microsevices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Logger loger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private CurrencyExchangeRepository repository;
    @Autowired
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to){
        /*CurrencyExchange currencyExchange = new CurrencyExchange(1000l, from, to, BigDecimal.valueOf(50));
        return currencyExchange;*/

        //2023-09-01T12:41:20.676+02:00  INFO [currency-exchange,4c4643f06be602455e10dceab1f6cf1a,58ddbd0cb6186fd5] 2532 --- [nio-8000-exec-1] c.i.m.c.CurrencyExchangeController       : getExchange value called with USD to INR
        loger.info("getExchange value called with {} to {}",from,to);
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from,to);
        if(currencyExchange==null){
            throw new RuntimeException("Unable to find data for "+ from+ " to "+to);
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;

    }

}
