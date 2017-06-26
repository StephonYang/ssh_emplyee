package com.employee.service;

import java.util.List;

import com.employee.domain.Department;
import com.employee.domain.PageBean;

/**
 * 部门管理的Service层接口
 * @author Administrator
 *
 */
public interface DepartmentService {

	PageBean<Department> findByPage(Integer currPage);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
