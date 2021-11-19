package com.nexio.api.ms.openfeign.client;

 


import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StockSericeFallback implements  StockSericeClient{


@Override
public List<ClientFromOrderService> geClients() {
	 return Collections.emptyList();
}

@Override
public ClientFromOrderService getFisrtClient() {
	return null;
}

@Override
public ClientFromOrderService getClientById(Long id) {
	return null;
}
}