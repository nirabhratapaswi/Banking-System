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
import models.BranchPost;
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
	
	@RequestMapping(value = "/getwithaccountsandloans/{branchname}")
	@GetMapping
	public @ResponseBody BranchPost getBranchWithAccountsAndLoans(@PathVariable("branchname") String branchname) {
		Branch branch = this.branchService.getBranchByBranchName(branchname);
		if (branch == null) {
			return null;
		}
		BranchPost branchPost = new BranchPost();
		branchPost.setAssets(branch.getAssets());
		branchPost.setBranchcity(branch.getBranchcity());
		branchPost.setBranchname(branch.getBranchname());
		branchPost.setAccounts(branch.getAccounts());
		branchPost.setLoans(branch.getLoans());
		return branchPost;
	}
	
	/*@RequestMapping(value = "/getwithaccountsandloans/{branchname}")
	@GetMapping
	public @ResponseBody BranchPost getBranchWithAccountsAndLoans(@PathVariable("branchname") String branchname) {
		BranchPost branchPost = new BranchPost();
		return this.branchService.getBranchByBranchName(branchname);
	}*/
	
	@RequestMapping(value = "/update/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean addNewBranch(BranchPost branchPost) {
		System.out.println("Trying to save branch: " + branchPost.toCustomString());
		Branch branch = new Branch();
		branch.setAssets(branchPost.getAssets());
		branch.setBranchcity(branchPost.getBranchcity());
		branch.setBranchname(branchPost.getBranchname());
		return this.branchService.saveBranch(branch);
	}
	
	@RequestMapping(value = "/update/exsiting", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@PostMapping
	public @ResponseBody Boolean updateBranch(BranchPost branchPost) {
		System.out.println("Trying to save branch: " + branchPost.toCustomString());
		Branch branch = this.branchService.getBranchByBranchName(branchPost.getBranchname());
		branch.setAssets(branchPost.getAssets());
		branch.setBranchcity(branchPost.getBranchcity());
		branch.setBranchname(branchPost.getBranchname());
		return this.branchService.saveBranch(branch);
	}
	
	@RequestMapping(value = "/delete/{branchname}")
	@GetMapping
	public @ResponseBody Boolean deleteBranch(@PathVariable("branchame") String branchname) {
		if (this.branchService.getBranchByBranchName(branchname) == null) {
			return false;
		}
		return this.branchService.deleteBranch(branchname);
	}
	
}
