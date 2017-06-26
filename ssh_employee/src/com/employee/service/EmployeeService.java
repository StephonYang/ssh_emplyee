package com.employee.service;

import com.employee.domain.Employee;
import com.employee.domain.PageBean;

/**
 * 员工管理的Service接口
 * @author Administrator
 *
 */
public interface EmployeeService {

	Employee login(Employee employee);

	PageBean<Employee> findByPage(Integer currPage);

	void save(Employee employee);

	Employee findById(Integer eid);

	void update(Employee employee);

	void delete(Employee employee);

}
