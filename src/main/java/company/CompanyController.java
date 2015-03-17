package company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@RequestMapping("company")
public class CompanyController {

	@Autowired
	CompanyRepository repository;
	
	@RequestMapping(method={RequestMethod.GET}, produces="application/json")
	@ResponseBody
	public Iterable<Company> listCompanies() {
		return repository.findAll();
	}
	
	@RequestMapping(value="/{id}", method={RequestMethod.GET}, produces="application/json")
	@ResponseBody
	public Company getCompany(@PathVariable Long id) {
		return repository.findOne(id);
	}
	
	@RequestMapping(method={RequestMethod.POST}, headers="Accept=application/json", produces="application/json", consumes="application/json")
	@ResponseBody
	public Company createCompany(@RequestBody Company company) {
		System.out.println("Saved: " + company.toString());
		company.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		company.setLastAccessed(new java.sql.Date(new java.util.Date().getTime()));
		company.setIsActive(true);
		return repository.save(company);
	}
	
}
