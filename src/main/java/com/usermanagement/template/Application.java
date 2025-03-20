package com.usermanagement.template;

import com.usermanagement.template.domain.RequestContext;
import com.usermanagement.template.entity.RoleEntity;
import com.usermanagement.template.enumeration.Authority;
import com.usermanagement.template.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
// 		    RequestContext.setUserId(0L);
//			var userRole = new RoleEntity();
//			userRole.setName(Authority.USER.name());
//			userRole.setAuthorities(Authority.USER);
//			roleRepository.save(userRole);
//
//			var adminRole = new RoleEntity();
//			adminRole.setName(Authority.ADMIN.name());
//			adminRole.setAuthorities(Authority.ADMIN);
//			roleRepository.save(adminRole);
//
//            var superAdminRole = new RoleEntity();
//            superAdminRole.setName(Authority.SUPER_ADMIN.name());
//            superAdminRole.setAuthorities(Authority.SUPER_ADMIN);
//            roleRepository.save(superAdminRole);
//
//            var managerRole = new RoleEntity();
//            managerRole.setName(Authority.MANAGER.name());
//            managerRole.setAuthorities(Authority.MANAGER);
//            roleRepository.save(managerRole);
//
//            RequestContext.start();
        };
    }
}
