package br.com.rdp.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import lombok.Builder;
import lombok.Data;


/**
 * The persistent class for the role database table.
 * 
 */

@Entity(name = "role")
@Data
@Builder
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(unique = true)
	private String name;

	// bi-directional many-to-many association to permission and users
	@Builder.Default
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "roles_permissions",
			joinColumns = { @JoinColumn(name = "role_id")},
			inverseJoinColumns = { @JoinColumn (name = "permission_id")})
	private Set<Permission> permissions = new HashSet<>();
	
	@Builder.Default
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "roles_users",
			joinColumns = { @JoinColumn(name = "role_id")},
			inverseJoinColumns = { @JoinColumn (name = "user_id")})
	private Set<User> users = new HashSet<>();	

}