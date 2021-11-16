package com.nexio.api.ms.dto;
/*
 * Copyright 2021 Zeddini, as indicated by the @author tags.
 *
 * Licensed under the zeddini License; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.zeddini.com/licenses/LICENSE-2.0
 *
 * @author  Zeddini Walid
 * @version 1.0.0
 * @since   2021-11-05 
 */

import java.io.Serializable;
import java.util.List;

import com.nexio.api.ms.domain.Client;
import com.nexio.api.ms.domain.Commande;
import com.nexio.api.ms.domain.LigneCommande;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="DTO Order",description="Order est un DTO non persistant, il inclut les données du Client, de la Commande et des lignes commande de cette dernière")
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private Client client;
    
    private Commande commande;

    private List<LigneCommande> ligneCommande;

    /**
     * a DTO, for easy binding with Front-end ( In my case, I use Angular )
     */    
}
