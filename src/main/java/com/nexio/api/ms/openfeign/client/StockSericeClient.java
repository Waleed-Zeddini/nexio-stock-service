package com.nexio.api.ms.openfeign.client;

 
import com.nexio.api.ms.config.Constants;
import com.nexio.api.ms.domain.Produit;
import com.nexio.api.ms.openfeign.config.FeignClientConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "produit-details",
        url = Constants.PRODUIT_API_URL_BASE,
        configuration = FeignClientConfig.class,
        fallback = StockSericeFallback.class)

public interface StockSericeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/produits/")
    List<Produit> geProduits();


    @RequestMapping(method = RequestMethod.GET, value = "/api/produits/{produitId}", produces = "application/json")
    Produit getProduitById(@PathVariable("produitId") Long produitId);
}