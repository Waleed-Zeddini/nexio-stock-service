package com.nexio.api.ms.service;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nexio.api.ms.dto.OrderDTO;

public interface IOrderService {

	OrderDTO save(OrderDTO commande);

	Page<OrderDTO> findAll(Pageable pageable);
	
	List<OrderDTO> findAll();

	Optional<OrderDTO> findOne(Long id);

	void delete(Long id);

}
