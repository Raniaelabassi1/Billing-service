package org.sid.Billingservice.feign;

import org.sid.Billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.QueryParam;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductItemRestClient {
    @GetMapping(path="/products")
    PagedModel<Product> pageProduct();
    @GetMapping(path="/products/{id}")
    Product getProductById(@PathVariable Long id);




}
