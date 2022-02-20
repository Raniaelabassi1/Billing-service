package org.sid.Billingservice;

import org.sid.Billingservice.entities.ProductItem;
import org.sid.Billingservice.feign.CustomerRestClient;
import org.sid.Billingservice.feign.ProductItemRestClient;
import org.sid.Billingservice.model.Costumer;
import org.sid.Billingservice.model.Product;
import org.sid.Billingservice.repositories.BillRepository;
import org.sid.Billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.sid.Billingservice.entities.Bill;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;
@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(BillingServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BillRepository billRepo,
							ProductItemRepository ProductItemRepository,
							CustomerRestClient CustomerRestClient,
							ProductItemRestClient ProductItemRestClient){
		return args -> {
			Costumer cu=CustomerRestClient.getCustomerById(1L);
             Bill bill1=billRepo.save(new Bill(null,new Date(),null,cu.getId(),null));

			//Bill bill=billRepo.save(new Bill(null,new Date(),null,cu.getId(),null));
			PagedModel<Product> productPagedModel=ProductItemRestClient.pageProduct();
			productPagedModel.forEach(p->{
				ProductItem pri=new ProductItem();
				pri.setPrice(p.getPrice());
				pri.setQuantity(1+new Random().nextInt(100));
				pri.setBill(bill1);
				pri.setProductID(p.getId());
				ProductItemRepository.save(pri);
			});
			//System.out.println("----------------------------------");
			//System.out.println(cu.getId());
			//System.out.println(cu.getName());
			//System.out.println(cu.getEmail());


		};

	}

}
