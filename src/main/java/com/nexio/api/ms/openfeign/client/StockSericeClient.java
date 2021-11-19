package com.nexio.api.ms.openfeign.client;

 
import com.nexio.api.ms.config.Constants;
import com.nexio.api.ms.openfeign.config.FeignClientConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "client-details",
        url = Constants.CLIENT_API_URL_BASE,
        configuration = FeignClientConfig.class,
        fallback = StockSericeFallback.class)

public interface StockSericeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/clients/")
    List<ClientFromOrderService> geClients();


    @RequestMapping(method = RequestMethod.GET, value = "/api/clients/first/", produces = "application/json")
    ClientFromOrderService getFisrtClient();
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/clients/{clientID}", produces = "application/json")
    ClientFromOrderService getClientById(@PathVariable("clientID") Long id);
}