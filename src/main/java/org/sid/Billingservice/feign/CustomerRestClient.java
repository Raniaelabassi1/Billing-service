package org.sid.Billingservice.feign;

import org.sid.Billingservice.model.Costumer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COSTMUER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/costumers/{id}")
     Costumer getCustomerById(@PathVariable(name="id") Long id);

}
