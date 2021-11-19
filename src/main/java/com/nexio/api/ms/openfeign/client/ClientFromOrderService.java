package com.nexio.api.ms.openfeign.client;
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


import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientFromOrderService implements Serializable {
	
	private static final long serialVersionUID = 1L;

 
    private Long id;

 
    private String code;

  
    private String nom;

 
    private String prenom;

   
    private LocalDate dateNaissance;

 
    private String adresse;

  
    private String ville;

  
    private Long codePostal;

  
    private String tel;

 
    private String fax;

   
    private String gsm;

  
    private String email;

	

}
