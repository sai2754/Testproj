package com.nt.controller;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.Employee;
import com.nt.service.IEMployeeMgmtService;

@Controller
public class EmployeeOperationsController { 
	@Autowired
	private IEMployeeMgmtService empservice;
@GetMapping("/")
	public String showHome(){
		return "home";
	}
	@GetMapping("/emp_report")
public String showEmployeeReport(Map<String,Object> map){
		System.out.println("EmployeeOperationsController.showEmployeeReport()");
		Iterable<Employee> itEmps=empservice.getAllEmployees();
		map.put("empsList",itEmps);
	return "show_employee_report";
}
	@GetMapping("/emp_add")    //for from launching
	public String showFormForsaveEmployee(@ModelAttribute("emp") Employee emp){
		return "register_employee";
	}
/*@PostMapping("/emp_add")    //from submission related to add employee operation
public String saveEmployee(@ModelAttribute("emp") Employee emp,Map<String,Object> map){
	String msg=empservice.registerEmployee(emp);
	Iterable<Employee> itEmps=empservice.getAllEmployees();
	map.put("resultMsg",msg);
	map.put("empList",itEmps);
	return "show_employee_report";
}*/
	/*@PostMapping("/emp_add")    //from submission related to add employee operation
public String saveEmployee(@ModelAttribute("emp") Employee emp,Map<String,Object> map){
	System.out.println("EmployeeOperationsController.saveEmployee()");
	String msg=empservice.registerEmployee(emp);
	map.put("resultMsg",msg);
	return "redirect:emp_report";
}*/
	/*@PostMapping("/emp_add")    //from submission related to add employee operation
	public String saveEmployee(@ModelAttribute("emp") Employee emp,RedirectAttributes attrs){
		System.out.println("EmployeeOperationsController.saveEmployee()");
		String msg=empservice.registerEmployee(emp);
		attrs.addFlashAttribute("resultMsg",msg);
		return "redirect:emp_report";
	}*/
	@PostMapping("/emp_add")    //from submission related to add employee operation
	public String saveEmployee(@ModelAttribute("emp") Employee emp,HttpSession ses){
		System.out.println("EmployeeOperationsController.saveEmployee()");
		String msg=empservice.registerEmployee(emp);
		ses.setAttribute("resultMsg", msg);
		return "redirect:emp_report";
	}
	@GetMapping("/emp_edit")
	public String showEditEmployeeFormPage(@RequestParam("no") int no,@ModelAttribute("emp") Employee emp){
		Employee emp1=empservice.getEmployeeByNo(no);
		BeanUtils.copyProperties(emp1, emp);
		return "update_employee";
	}
	@PostMapping("/emp_edit")
	public String editEmployee(RedirectAttributes attrs,@ModelAttribute("emp") Employee emp) {
		String msg=empservice.updateEmployee(emp);
		attrs.addFlashAttribute("resultMsg",msg);
		return "redirect:emp_report";
	}
	@GetMapping("/emp_delete")
	public String deleteEmployee(RedirectAttributes attrs,@RequestParam int no){
		String msg=empservice.deleteEmployeeById(no);
		attrs.addFlashAttribute("resultMsg",msg);
		return "redirect:emp_report";
	}
}

