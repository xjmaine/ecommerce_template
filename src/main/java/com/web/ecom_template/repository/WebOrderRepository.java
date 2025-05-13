package com.web.ecom_template.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import com.web.ecom_template.entity.WebOrder;
import com.web.ecom_template.entity.LocalUser;

import java.util.List;

@Repository
public interface WebOrderRepository extends ListCrudRepository<WebOrder, Long> {
    List<WebOrder> findByUser(LocalUser user);
}
