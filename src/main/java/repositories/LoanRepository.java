package repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {

}
