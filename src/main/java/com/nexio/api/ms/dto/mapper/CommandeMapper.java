package com.nexio.api.ms.dto.mapper;

import com.nexio.api.ms.domain.*;
import com.nexio.api.ms.dto.*;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link Commande} and its DTO called {@link CommandeDTO}.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * requires a manual step with an IDE.
 */
@Service
public class CommandeMapper {

    public List<CommandeDTO> ordersToCommandeDTOs(List<Commande> orders) {
        return orders.stream()
            .filter(Objects::nonNull)
            .map(this::orderToCommandeDTO)
            .collect(Collectors.toList());
    }

    public CommandeDTO orderToCommandeDTO(Commande order) {
        return new CommandeDTO(order);
    }

    public List<Commande> orderDTOsToCommandes(List<CommandeDTO> orderDTOs) {
        return orderDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::orderDTOToCommande)
            .collect(Collectors.toList());
    }

    public Commande orderDTOToCommande(CommandeDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        } else {
        	Commande order = new Commande();
            
            order.setId(orderDTO.getId());
            order.setNumero(orderDTO.getNumero());
            order.setDate(orderDTO.getDate());
            order.setPrixTotal(orderDTO.getPrixTotal());
            order.setEtat(orderDTO.getEtat());
            order.setClient(orderDTO.getClient());
            
            Set<LigneCommande> lignesCde = this.carnetCommandesFromLons(orderDTO.getCarnets());
            order.setLigneCommandes(lignesCde);
            return order;
        }
    }


    private Set<LigneCommande> carnetCommandesFromLons(Set<Long> carnetCommandesAsLong) {
        Set<LigneCommande> carnetCommandes = new HashSet<>();

        if (carnetCommandesAsLong != null) {
        	carnetCommandes = carnetCommandesAsLong.stream().map(id -> {
            	LigneCommande auth = new LigneCommande();
                auth.setId(id);
                return auth;
            }).collect(Collectors.toSet());
        }

        return carnetCommandes;
    }

    public Commande orderFromId(Long id) {
        if (id == null) {
            return null;
        }
        Commande order = new Commande();
        order.setId(id);
        return order;
    }
}
