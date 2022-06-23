package br.com.rdp.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "permission")
@Table
@Immutable
@Getter
@Setter
public class Permission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String CREATE = "CREATE";
	public static final String READ   = "READ";
	public static final String WRITE  = "WRITE";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public Permission(){}
		
	public Permission(String name) {
		this.name = name;
	}
	
    
}
