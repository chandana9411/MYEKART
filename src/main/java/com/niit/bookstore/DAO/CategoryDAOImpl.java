package com.niit.bookstore.DAO;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bookstore.modal.*;

@SuppressWarnings("deprecation")
@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	@Autowired
	private SessionFactory sessionFactory;


	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public Boolean saveOrUpdate(Category category){
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	} 

	@Transactional
	public Boolean delete(Category category)
	    {     
		try {
			sessionFactory.getCurrentSession().delete(category);
			return true;
		}
		 catch (Exception e){
			e.printStackTrace();
		
		return false;
		}
		}

	
	
	

@Transactional
public Category get(int category_id) {
	String hql = "from"+" Category"+" where category_id=" +category_id;
	@SuppressWarnings("rawtypes")
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
	@SuppressWarnings( { "unchecked" })
	List<Category> listCategory = (List<Category>) query.list();
	
	if (listCategory != null && !listCategory.isEmpty()) {
		return listCategory.get(0);
	}
	return null;
}


	//@SuppressWarnings("deprecation")

	@Transactional
  public List<Category> list(){
		@SuppressWarnings("unchecked")
		List<Category> listCategory = (List<Category>)
		sessionFactory.getCurrentSession()
		.createCriteria(Category.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listCategory;
	}

}