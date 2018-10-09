package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {
	Branch findByBranchname(String branch_name);
}
