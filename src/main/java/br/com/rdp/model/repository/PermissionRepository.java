package br.com.rdp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rdp.model.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	Permission findByName(String name);
}
