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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="Model - Client",description="Client gère une commande et ses lignes commandes")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "nom", length = 30, nullable = false)
    private String nom;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "prenom", length = 30, nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "adresse", length = 50, nullable = false)
    private String adresse;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "ville", length = 30, nullable = false)
    private String ville;

    @NotNull
    @Column(name = "code_postal", nullable = false)
    private Long codePostal;

    @Size(min = 6, max = 30)
    @Column(name = "tel", length = 30)
    private String tel;

    @Size(min = 6, max = 30)
    @Column(name = "fax", length = 30)
    private String fax;

    @NotNull
    @Size(min = 6, max = 30)
    @Column(name = "gsm", length = 30, nullable = false)
    private String gsm;

    @NotNull
    @Size(min = 6, max = 30)
    @Column(name = "email", length = 30, nullable = false)
    private String email;

	@Transient
    @JsonIgnore
    private Set<Commande> commandes = new HashSet<>();
	
    /**
     * In the case of Monolithic Application with 
     * a relational DB containing entities of the Application in the same DB , 
     * we can define OneToMany and  ManyToMany relations between 
     * persistent entities.
     * 
     * In Microservices, it's recommended to discuss and implement
     * in Service Classes
     */       
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", orphanRemoval = true)

}
