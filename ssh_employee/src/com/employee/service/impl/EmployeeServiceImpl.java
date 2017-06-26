package com.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.employee.dao.EmployeeDao;
import com.employee.domain.Employee;
import com.employee.domain.PageBean;
import com.employee.service.EmployeeService;

/**
 * 员工管理的业务层实现类
 * @author Administrator
 *
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public Employee login(Employee employee) {
		Employee exitEmployee = employeeDao.findByUsernameAndPassword(employee);
		return exitEmployee;
	}

	@Override
	/**
	 * 业务层的分页查询员工的方法
	 */
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		//封装当前的页面
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总的页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	/**
	 * 业务层的保存员工的方法
	 */
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	/**
	 * 业务层根据员工的ID查询员工的方法
	 */
	public Employee findById(Integer eid) {
		// TODO Auto-generated method stub
		return employeeDao.findById(eid);
	}

	/**
	 * 业务层修改员工的方法
	 */
	public void update(Employee employee) {
		employeeDao.update(employee);
		
	}

	/**
	 * 业务层删除员工的方法
	 */
	public void delete(Employee employee) {
		employeeDao.delete(employee);
		
	}
	
	
}
