package com.web.ecom_template.repository;
import com.web.ecom_template.entity.Address;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends ListCrudRepository<Address, Long> {
    List<Address> findByUser_Id(Long userId);

}
