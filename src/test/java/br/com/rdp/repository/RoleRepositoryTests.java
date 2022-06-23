package br.com.rdp.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.rdp.model.entity.Role;
import br.com.rdp.model.repository.RoleRepository;



@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Tests for Role Repository")
class RoleRepositoryTests {

	  @Autowired private DataSource dataSource;
	  @Autowired private JdbcTemplate jdbcTemplate;
	  @Autowired private EntityManager entityManager;
	  @Autowired private RoleRepository roleRepository;
	
	  
	  
	  @Test
	  @DisplayName("Tests for injected components repository")
	  void injectedComponentsAreNotNull(){
		    assertThat(dataSource).isNotNull();
		    assertThat(jdbcTemplate).isNotNull();
		    assertThat(entityManager).isNotNull();
		    assertThat(roleRepository).isNotNull();
	  }
	  
	  
	  @Test
	  @DisplayName("Tests for when saved the find by name")
	  void whenSaved_thenFindsByName() {
			Role role = roleRepository.save(Role.builder().name("Admin").build());
		    assertThat(roleRepository.findByName("Admin")).isNotNull();
		    assertThat(role).hasFieldOrPropertyWithValue("name", "Admin");
	  }
	  
	  
      @DisplayName("Tests for get all roles operation")
      @Test
      void gellRolesList_whenFindAll_thenRolesList(){

	    	Role role = roleRepository.save(Role.builder().name("Admin").build());
	    	Role role1 = roleRepository.save(Role.builder().name("User").build());
	    	Role role2 = roleRepository.save(Role.builder().name("Moderator").build());
	
	    	roleRepository.save(role);
	    	roleRepository.save(role1);
	    	roleRepository.save(role2);

	        List<Role> roleList = roleRepository.findAll();
	
	        assertThat(roleList).isNotNull();
	        assertThat(roleList.size()).isEqualTo(3);

      }
      
      
      @DisplayName("Test for get role by id operation")
      @Test
      void getRoleObject_whenFindById_thenReturnObject(){

    	  	Role role = roleRepository.save(Role.builder().name("Admin").build());
    	  	Role roleDB = roleRepository.findById(role.getId()).get();
    	  	assertThat(roleDB).isNotNull();
      }
      
      
      @DisplayName("Test for update role operation")
      @Test
      void whenUpdateRole_thenReturnUpdatedRole(){

    	  	Role role = roleRepository.save(Role.builder().name("Admin").build());
    	  	Role savedRole = roleRepository.findById(role.getId()).get();
    	  	savedRole.setName("Administrador");
    	  	Role updatedRole =  roleRepository.save(savedRole);
    	  	assertThat(updatedRole.getName()).isEqualTo("Administrador");

      }
      
      
      @DisplayName("Test for delete role operation")
      @Test
      void whenDelete_thenRemoveRole(){

    	  	Role role = roleRepository.save(Role.builder().name("Admin").build());
    	  	roleRepository.deleteById(role.getId());
    	  	Optional<Role> roleOptional = roleRepository.findById(role.getId());
    	  	assertThat(roleOptional).isEmpty();
      }
      
      
      @DisplayName("Test for unique constraint role duplicate")
      @Transactional(propagation = Propagation.NOT_SUPPORTED)
      @Test()
      public void checkRole_UniqueConstraint() {
    	  
          Role role = Role.builder().name("Repeat").build();
          Role duplicate = Role.builder().name("Repeat").build();
          roleRepository.save(role);
          
          Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
        	  roleRepository.save(duplicate);
      	});
         
      }
}
