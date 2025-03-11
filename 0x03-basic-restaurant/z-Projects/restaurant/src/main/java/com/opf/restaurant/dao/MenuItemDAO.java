package com.opf.restaurant.dao;

import com.opf.restaurant.model.MenuItem;

import java.util.List;

public interface MenuItemDAO {

    void saveMenuItem(MenuItem menuItem);

    List<MenuItem> findAll();

    MenuItem findById(Integer id);

}
