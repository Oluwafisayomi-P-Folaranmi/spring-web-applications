package com.opf.restaurant.dao;

import com.opf.restaurant.model.MenuItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.management.MemoryUsage;
import java.util.List;

@Repository
public class MenuItemDAOImpl implements MenuItemDAO {

    /*
     * Fields
     */
    private EntityManager entityManager;

    /*
     * Constructors
     */
    @Autowired
    public MenuItemDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /*
     * Methods
     */
    @Override
    @Transactional
    public void saveMenuItem(MenuItem menuItem) {
        entityManager.persist(menuItem);
    }

    @Override
    public List<MenuItem> findAll() {
        TypedQuery<MenuItem> typedQuery = entityManager.createQuery("FROM MenuItem", MenuItem.class);
        List<MenuItem> menuItems = typedQuery.getResultList();
        return menuItems;
    }

    @Override
    public MenuItem findById(Integer id) {
        TypedQuery<MenuItem> typedQuery = entityManager.createQuery(
                "FROM MenuItem" + " WHERE id=:id", MenuItem.class);
        typedQuery.setParameter("id", id);
        MenuItem menuItem = typedQuery.getSingleResult();
        return menuItem;
    }
}
