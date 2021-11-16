package com.nexio.api.ms.domain;
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "commande")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="Model - Commande",description="Commande est gérée par un Client. Elle possède des lignes commandes")
public class Commande implements Serializable {
	
	private static final long serialVersionUID = 1L;

	  @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	    @SequenceGenerator(name = "sequenceGenerator")
	    private Long id;

	    @NotNull
	    @Size(min = 2, max = 30)
	    @Column(name = "numero", length = 30, nullable = false)
	    private String numero;

	    @NotNull
	    @Column(name = "date", nullable = false)
	    private LocalDate date;

	    @NotNull
	    @Column(name = "prix_total", precision = 21, scale = 2, nullable = false)
	    private BigDecimal prixTotal;

	    @NotNull
	    @Column(name = "etat", nullable = false)
	    private Long etat;

	    @NotNull
		@Column(name = "client_id",  nullable = false)
	    private Long clientId;

		
	    @JsonIgnore
	    @Transient
	    private Client client;
		
		
	    @JsonIgnore
	    @Transient
	    private Set<LigneCommande> ligneCommandes = new HashSet<>();
		
	    
	    /**
	     * In the case of Monolithic Application with 
	     * a relational DB containing entities of the Application in the same DB , 
	     * we can define OneToMany and  ManyToMany relations between 
	     * persistent entities.
	     * 
	     * In Microservices, it's recommended to discuss and implement
	     * in Service Classes
	     */    
//		@ManyToOne(fetch = FetchType.LAZY)
//		@JoinColumn(name = "client_id",  nullable = false)
	    
//		@OneToMany(fetch = FetchType.LAZY, mappedBy = "commande", orphanRemoval = true)

}