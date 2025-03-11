package com.opf.restaurant.service;

import com.opf.restaurant.dao.MenuItemDAOImpl;
import com.opf.restaurant.model.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    /*
     * Fields
     */
    MenuItemDAOImpl menuItemDAOImpl;

    /*
     * Constructors
     */
    public MenuItemServiceImpl(MenuItemDAOImpl menuItemDAOImpl) {
        this.menuItemDAOImpl = menuItemDAOImpl;
    }

    /*
     * Methods
     */
    @Override
    public void saveMenuItem(MenuItem menuItem) {
        menuItemDAOImpl.saveMenuItem(menuItem);
    }

    @Override
    public List<MenuItem> findAll() {
        List<MenuItem> menuItems = menuItemDAOImpl.findAll();
        return menuItems;
    }

    @Override
    public MenuItem findById(Integer id) {
        MenuItem menuItem = menuItemDAOImpl.findById(id);
        return menuItem;
    }
}
