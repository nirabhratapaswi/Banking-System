package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountnumber(Long account_number);
}
