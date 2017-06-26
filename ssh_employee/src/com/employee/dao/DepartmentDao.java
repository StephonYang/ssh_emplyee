package com.employee.dao;

import java.util.List;

import com.employee.domain.Department;

/**
 * 部门管理的DAO层接口
 * @author Administrator
 *
 */
public interface DepartmentDao {

	List<Department> findByPage(int begin, int pageSize);

	int findCount();

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
