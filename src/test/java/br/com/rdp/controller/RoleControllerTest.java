package br.com.rdp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.rdp.controllers.RoleController;
import br.com.rdp.model.repository.RoleRepository;



@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RoleController.class)
public class RoleControllerTest {

	@MockBean
    RoleRepository repository;
  
    
}
