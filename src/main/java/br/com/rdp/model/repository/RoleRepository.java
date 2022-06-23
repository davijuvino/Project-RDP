package br.com.rdp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rdp.model.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
