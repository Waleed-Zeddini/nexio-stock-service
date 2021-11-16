package com.nexio.api.ms.openfeign.client;

 
import com.nexio.api.ms.domain.Produit;


import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StockSericeFallback implements  StockSericeClient{

   @Override
    public Produit getProduitById(Long produitId) {
//        return null;
        return null;

    }
   
   @Override
   public List<Produit> geProduits(){
       return Collections.emptyList();
   }
}