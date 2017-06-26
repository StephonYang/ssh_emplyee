package com.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.employee.dao.EmployeeDao;
import com.employee.domain.Employee;

/**
 * 员工管理的DAO的实现类
 * @author Administrator
 *
 */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

	
	/**
	 * dao中根据用户名和密码查询用户的方法
	 */
	public Employee findByUsernameAndPassword(Employee employee) {
		String hql = "from Employee where username = ? and password = ?";
		List<Employee> list = this.getHibernateTemplate().find(hql, employee.getUsername(),employee.getPassword());
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public int findCount() {
		String hql = "select count(*) from Employee";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Employee> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		List<Employee> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	/**
	 * DAO保存员工的方法
	 */
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);
		
	}

	
	/**
	 * DAO层根据员工ID查询员工的方法
	 */
	public Employee findById(Integer eid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Employee.class, eid);
	}

	/**
	 * dao层更新员工的方法
	 */
	public void update(Employee employee) {
		this.getHibernateTemplate().update(employee);
		
	}

	/**
	 * dao层删除员工的方法
	 */
	public void delete(Employee employee) {
		this.getHibernateTemplate().delete(employee);
		
	}

}
