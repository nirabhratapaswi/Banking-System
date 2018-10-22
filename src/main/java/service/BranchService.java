package service;

import org.springframework.stereotype.Service;

import models.Branch;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import repositories.BranchRepository;

@Service
public class BranchService {
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch getBranchByBranchName(String branch_name) {
		return branchRepository.findByBranchname(branch_name);
	}
	
	public List<Branch> getAllBranches() {
		return (List<Branch>) branchRepository.findAll();
	}
	
	public Optional<Branch> getBranch(String branch_name) {
		return this.branchRepository.findById(branch_name);
	}
	
	public Boolean saveBranch(Branch branch) {
		branchRepository.save(branch);
		return true;
	}
	
	public Boolean deleteBranch(String branchname) {
		try {
			this.branchRepository.deleteById(branchname);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}
