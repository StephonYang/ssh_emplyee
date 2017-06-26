package com.employee.action;

import java.util.List;

import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.PageBean;
import com.employee.service.DepartmentService;
import com.employee.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 员工管理的Action类
 * @author Administrator
 *
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	//模型驱动使用的对象
	private Employee employee = new Employee();
	@Override
	public Employee getModel() {
		return employee;
	}
	//注入业务层类
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//接收当前页数
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	/**
	 * 登录执行的方法
	 * @return
	 */
	public String login(){
		System.out.println("login执行了");
		//调用业务层的类
		Employee exitEmployee = employeeService.login(employee);
		if(exitEmployee == null){
			//登录失败
			this.addActionError("用户名或密码错误");
			return INPUT;
		}else{
			//登录成功
			ActionContext.getContext().getSession().put("exitEmployee", exitEmployee);
			return SUCCESS;
		}
	}
	
	/**
	 * 分页查询员工的执行方法
	 * @return
	 */
	public String findAll(){
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 跳转到添加员工页面执行的方法
	 * @return
	 */
	public String saveUI(){
		//查询所有的部门
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	/**
	 * 保存员工的执行方法
	 * @return 
	 */
	public String save(){
		employeeService.save(employee);
		return "saveSuccess";
	}
	
	/**
	 * 编辑员工的执行的方法
	 * @return
	 */
	public String edit(){
		//根据员工ID查询员工
		employee = employeeService.findById(employee.getEid());
		//查询所有的部门
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
		
	}
	
	/**
	 * 修改员工的执行方法
	 * @return
	 */
	public String update(){
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	/**
	 * 删除员工执行的方法
	 * @return
	 */
	public String delete(){
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}

}
