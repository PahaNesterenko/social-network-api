package org.social_network_api.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.social_network_api.domain.UserPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Resource
@Transactional(propagation = Propagation.REQUIRED)
public class UserPhotoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void createUserPhoto(UserPhoto userPhoto) {
        sessionFactory.getCurrentSession().saveOrUpdate(userPhoto);
    }

    public UserPhoto getById(Long id) {
        return (UserPhoto) sessionFactory.getCurrentSession().get(UserPhoto.class, id);
    }

    public List<UserPhoto> getAllPhotos() {
        return sessionFactory.getCurrentSession().createCriteria(UserPhoto.class).list();
    }

    public void updateUserPhoto(UserPhoto userPhoto) {
        sessionFactory.getCurrentSession().update(userPhoto);
    }

    public void deleteUserPhoto(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete UserPhoto where id = :ID");
        query.setParameter("ID", id);

        int result = query.executeUpdate();
    }

}
