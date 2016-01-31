package org.social_network_api.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.social_network_api.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Resource
@Transactional(propagation = Propagation.REQUIRED)
public class CountryDao {

  @Autowired
  private SessionFactory sessionFactory;

  public void createCountry(Country country) {
    sessionFactory.getCurrentSession().saveOrUpdate(country);
  }

  public Country getById(Long id) {
    return (Country) sessionFactory.getCurrentSession().get(Country.class, id);
  }

  public List<Country> getAllCountryes() {
    return sessionFactory.getCurrentSession().createCriteria(Country.class).list();
  }

  public void updateCountry(Country country) {
    sessionFactory.getCurrentSession().update(country);
  }

  public void deleteCountry(Long id) {
    Query query = sessionFactory.getCurrentSession().createQuery("delete Country where id = :ID");
    query.setParameter("ID", id);

    int result = query.executeUpdate();
  }

}
