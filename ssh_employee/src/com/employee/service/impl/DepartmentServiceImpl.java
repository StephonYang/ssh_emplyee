package com.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.employee.dao.DepartmentDao;
import com.employee.domain.Department;
import com.employee.domain.PageBean;
import com.employee.service.DepartmentService;

/**
 * 部门管理的Service层实现类
 * @author Administrator
 *
 */
/**
 * @Transactional注解中的属性
 * propagation	：事务的传播行为
 * isolation	：事务的隔离级别
 * readOnly		：只读
 * noRollbackFor	:发生哪些异常不回滚
 * RollbackFor	：发生哪些异常回滚
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	//注入部门管理的DAO
	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	//分页查询部门的方法
	public PageBean<Department> findByPage(Integer currPage) {
		PageBean<Department> pageBean = new PageBean<Department>();
		//封装当前页数
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//封装总的记录数
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据
		int begin = (currPage - 1)* pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		//封装
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	//业务层保存部门的方法
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	//业务层根据部门ID查询部门的方法
	public Department findById(Integer did) {
		return departmentDao.findById(did);
		
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
		
	}

	@Override
	//业务层删除部门的方法
	public void delete(Department department) {
		departmentDao.delete(department);
		
	}

	@Override
	//查询所有部门的方法
	public List<Department> findAll() {
		return departmentDao.findAll();
	}
	
}
