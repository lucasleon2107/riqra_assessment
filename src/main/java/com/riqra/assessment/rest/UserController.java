package com.riqra.assessment.rest;

import com.riqra.assessment.domain.dtos.response.ProductResponse;
import com.riqra.assessment.domain.entities.Product;
import com.riqra.assessment.domain.services.ApplicationUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ApplicationUserService applicationUserService;

    public UserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @GetMapping("/me/catalog")
    public ResponseEntity<List<ProductResponse>> getUserCatalog(@AuthenticationPrincipal String loggedInUserEmail) {
        List<Product> userCatalog = applicationUserService.getUserCatalog(loggedInUserEmail);

        List<ProductResponse> catalogResponse = userCatalog.stream().map(ProductResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(catalogResponse);
    }
}
