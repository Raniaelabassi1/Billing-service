package org.sid.Billingservice.repositories;

import org.sid.Billingservice.entities.Bill;
import org.sid.Billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
public Collection<ProductItem> findByBillId(long id);

}
