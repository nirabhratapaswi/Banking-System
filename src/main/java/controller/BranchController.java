package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import models.Account;
import models.Branch;
import service.BranchService;

@Controller
@RestController
@RequestMapping(value = "/branch")
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	protected BranchController() {}
	
	@RequestMapping(value = "/list/all")
	@GetMapping
	public List<Branch> getAllBranches() {
		return this.branchService.getAllBranches();
	}
	
	@RequestMapping(value = "/get/{branchname}")
	@GetMapping
	public @ResponseBody Branch getBranch(@PathVariable("branchname") String branchname) {
		return this.branchService.getBranchByBranchName(branchname);
	}
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewBranch(Branch branch) {
		System.out.println("Trying to save branch: " + branch.toCustomString());
		return this.branchService.saveBranch(branch);
	}
	
	@RequestMapping(value = "/delete/{branchname}")
	@GetMapping
	public @ResponseBody Boolean deleteBranch(@PathVariable("branchame") String branchname) {
		return this.branchService.deleteBranch(branchname);
	}
	
}
