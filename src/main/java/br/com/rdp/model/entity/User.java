
package br.com.rdp.model.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 65981149772133526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;

	private String password;

	private String provider;

	@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "PROVIDER_USER_ID")
	private String providerUserId;

	@Column(name = "created_date", nullable = false, updatable = false)

	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;

	@Column(name = "ACCOUNT_EXPIRED")
	private boolean accountExpired;

	@Column(name = "ACCOUNT_LOCKED")
	private boolean accountLocked;

	@Column(name = "CREDENTIALS_EXPIRED")
	private boolean credentialsExpired;

	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    @JsonIgnore
	private Set<Role> roles = new HashSet<Role>();

	@Transient
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired();
	}
}
