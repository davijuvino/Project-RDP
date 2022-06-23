package br.com.rdp.settings;
//
//package br.com.mvp.tests.settings;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import br.com.mvp.tests.dto.AuthProvider;
//import br.com.mvp.tests.model.entity.Permission;
//import br.com.mvp.tests.model.entity.Role;
//import br.com.mvp.tests.model.entity.User;
//import br.com.mvp.tests.model.repository.PermissionRepository;
//import br.com.mvp.tests.model.repository.RoleRepository;
//import br.com.mvp.tests.model.repository.UserRepository;
//
//@Component
//public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
//
//	private boolean alreadySetup = false;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private RoleRepository roleRepository;
//
//	@Autowired
//	private PermissionRepository permissionRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Override
//
//	@Transactional
//	public void onApplicationEvent(final ContextRefreshedEvent event) {
//		if (alreadySetup) {
//			return;
//		}
//
//		// Create initial permission Permission pCreate =
//		createPermissonIfNotFound(Permission.PERMISSION_CREATE);
//		Permission pRead = createPermissonIfNotFound(Permission.PERMISSION_READ);
//		Permission pWrite = createPermissonIfNotFound(Permission.PERMISSION_WRITE);
//		Permission pUpdate = createPermissonIfNotFound(Permission.PERMISSION_UPDATE);
//		Permission pDelete = createPermissonIfNotFound(Permission.PERMISSION_DELETE);
//
//		// Create initial role Role rUser = createRoleIfNotFound(Role.ROLE_USER );
//		Role rAdmin = createRoleIfNotFound(Role.ROLE_ADMIN);
//		Role rModerator = createRoleIfNotFound(Role.ROLE_MODERATOR);
//
//		createPermissionForRoleIfNotFound(rUser, Set.of(pRead));
//
//		createUserIfNotFound("admin@administrador.com", Set.of(rUser, rAdmin, rModerator));
//
//		alreadySetup = true;
//	}
//
//	@Transactional
//	private final Role createPermissionForRoleIfNotFound(Role role, Set<Permission> permissions) {
//		Optional<Role> roleId = roleRepository.findById(role.getId());
//		if (roleId != null) {
//			role.setPermissions(permissions);
//			role = roleRepository.save(role);
//		}
//		return role;
//	}
//
//	@Transactional
//	private final User createUserIfNotFound(final String email, Set<Role> roles) {
//		User user = userRepository.findByEmail(email);
//		if (user == null) {
//			user = new User();
//			user.setDisplayName("Admin");
//			user.setEmail(email);
//			user.setPassword(passwordEncoder.encode("admin@"));
//			user.setRoles(roles);
//			user.setProvider(AuthProvider.LOCAL.getProviderType());
//			user.setEnabled(true);
//			Date now = Calendar.getInstance().getTime();
//			user.setCreatedDate(now);
//			user.setModifiedDate(now);
//			user = userRepository.save(user);
//		}
//		return user;
//	}
//
//	@Transactional
//	private final Role createRoleIfNotFound(final String name) {
//		Role role = roleRepository.findByName(name);
//		if (role == null) {
//			role = roleRepository.save(new Role(name));
//		}
//		return role;
//	}
//
//	@Transactional
//	private final Permission createPermissonIfNotFound(final String name) {
//		Permission permission = permissionRepository.findByName(name);
//		if (permission == null) {
//			permission = permissionRepository.save(new Permission(name));
//		}
//		return permission;
//	}
//
//}
