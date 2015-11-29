package org.social_network_api.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.social_network_api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Resource
public class UserDaoImpl implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  @Transactional
  public void createUser(User user) {
    sessionFactory.getCurrentSession().persist(user);
  }

  @Override
  @Transactional
  public User getById(Long id) {
    return (User) sessionFactory.getCurrentSession().get(User.class, id);
  }

  @Override
  @Transactional
  public List<User> getAllUsers() {
    return sessionFactory.getCurrentSession().createCriteria(User.class).list();
  }

  @Override
  @Transactional
  public void updateUser(User user) {
    sessionFactory.getCurrentSession().update(user);
  }

  @Override
  @Transactional
  public void deleteUser(Long id) {
    Query query = sessionFactory.getCurrentSession().createQuery("delete User where id = :ID");
    query.setParameter("ID", id);

    int result = query.executeUpdate();
  }

}
