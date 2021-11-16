package com.nexio.api.ms.dto.mapper;

import com.nexio.api.ms.config.Constants;

import com.nexio.api.ms.domain.LigneCommande;
import com.nexio.api.ms.domain.Client;
import com.nexio.api.ms.domain.Commande;


import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a Commande, with its LigneCommandes.
 */
public class CommandeDTO {

	@NotBlank
    private Long id;

    
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 2, max = 30)
    private String numero;

    
    private LocalDate date;

    
    private BigDecimal prixTotal;

   
    private Long etat;

    private Set<Long> carnets = new HashSet<>();

    private Client client;

    public CommandeDTO() {
        // Empty constructor needed for Jackson.
    }

    public CommandeDTO(Commande commande) {
    	
        this.id = commande.getId();
        this.numero = commande.getNumero();
        this.date = commande.getDate();
        this.prixTotal = commande.getPrixTotal();
        this.etat = commande.getEtat();
        this.client = commande.getClient();
 
        this.carnets = commande.getLigneCommandes().stream()
            .map(LigneCommande::getId)
            .collect(Collectors.toSet());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(BigDecimal prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Long getEtat() {
		return etat;
	}

	public void setEtat(Long etat) {
		this.etat = etat;
	}

 

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	
	public Set<Long> getCarnets() {
		return carnets;
	}

	public void setCarnets(Set<Long> carnets) {
		this.carnets = carnets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carnets == null) ? 0 : carnets.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((prixTotal == null) ? 0 : prixTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandeDTO other = (CommandeDTO) obj;
		if (carnets == null) {
			if (other.carnets != null)
				return false;
		} else if (!carnets.equals(other.carnets))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (etat == null) {
			if (other.etat != null)
				return false;
		} else if (!etat.equals(other.etat))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (prixTotal == null) {
			if (other.prixTotal != null)
				return false;
		} else if (!prixTotal.equals(other.prixTotal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommandeDTO [id=" + id + ", numero=" + numero + ", date=" + date + ", prixTotal=" + prixTotal
				+ ", etat=" + etat + ", carnets=" + carnets + ", client=" + client + "]";
	}

 

    
}
