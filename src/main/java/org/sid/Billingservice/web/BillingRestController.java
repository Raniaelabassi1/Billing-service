package org.sid.Billingservice.web;

import org.sid.Billingservice.entities.Bill;
import org.sid.Billingservice.feign.CustomerRestClient;
import org.sid.Billingservice.feign.ProductItemRestClient;
import org.sid.Billingservice.model.Costumer;
import org.sid.Billingservice.model.Product;
import org.sid.Billingservice.repositories.BillRepository;
import org.sid.Billingservice.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired private ProductItemRepository productItemRepository;
    private  CustomerRestClient CustomerRestClient;
    private ProductItemRestClient ProductItemRestClient;

    public BillingRestController(BillRepository billRepository,
                                 ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.CustomerRestClient = customerRestClient;
        this.ProductItemRestClient = productItemRestClient;
    }

    @GetMapping(path ="/fullBill/{id}")
    public Bill getBill(@PathVariable(name="id")Long id){
      Bill bill=billRepository.findById(id).get();
      Costumer costumer=CustomerRestClient.getCustomerById(bill.getCustomerID());
     bill.setCustomer(costumer);
     bill.getProductItems().forEach(pi->{
         Product product=ProductItemRestClient.getProductById(pi.getProductID());
         //pi.setProduct(product);
         pi.setProductName(product.getName());
     });
      return bill;
    }





}
