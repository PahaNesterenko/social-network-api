package org.social_network_api.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.social_network_api.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Resource
@Transactional(propagation = Propagation.REQUIRED)
public class CityDao {

  @Autowired
  private SessionFactory sessionFactory;

  public void createCity(City city) {
    sessionFactory.getCurrentSession().saveOrUpdate(city);
  }

  public City getById(Long id) {
    return (City) sessionFactory.getCurrentSession().get(City.class, id);
  }

  public List<City> getAllCityes() {
    return sessionFactory.getCurrentSession().createCriteria(City.class).list();
  }

  public void updateCity(City city) {
    sessionFactory.getCurrentSession().update(city);
  }

  public void deleteCity(Long id) {
    Query query = sessionFactory.getCurrentSession().createQuery("delete City where id = :ID");
    query.setParameter("ID", id);

    int result = query.executeUpdate();
  }
}
