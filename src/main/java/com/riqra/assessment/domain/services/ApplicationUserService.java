package com.riqra.assessment.domain.services;

import com.riqra.assessment.domain.entities.ApplicationUser;
import com.riqra.assessment.domain.entities.Product;
import com.riqra.assessment.domain.entities.Supplier;
import com.riqra.assessment.infrastructure.repositories.ApplicationUserRepository;
import com.riqra.assessment.infrastructure.repositories.ProductRepository;
import com.riqra.assessment.infrastructure.repositories.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ApplicationUserRepository applicationUserRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserService(ApplicationUserRepository applicationUserRepository,
                                  ProductRepository productRepository,
                                  SupplierRepository supplierRepository,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ApplicationUser signUp(String email, String password) {
        logger.info("Registering user...");

        String protectedPassword = bCryptPasswordEncoder.encode(password);
        Supplier randomSupplier = supplierRepository.getRandomSupplier();

        ApplicationUser userToSignUp = new ApplicationUser(email, protectedPassword, randomSupplier);

        return applicationUserRepository.save(userToSignUp);
    }

    public Boolean isUserRegistered(String email) {
        return applicationUserRepository.findByEmail(email) != null;
    }

    public List<Product> getUserCatalog(String loggedInUserEmail) {
        ApplicationUser loggedInUser = applicationUserRepository.findByEmail(loggedInUserEmail);

        return productRepository.findBySupplier(loggedInUser.getSupplier());
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        ApplicationUser applicationUser = applicationUserRepository.findByEmail(email);

        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(applicationUser.getEmail(), applicationUser.getPassword(), Collections.emptyList());
    }
}
