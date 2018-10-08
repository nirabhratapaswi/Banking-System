package repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	Customer findByUsername(String username);
}