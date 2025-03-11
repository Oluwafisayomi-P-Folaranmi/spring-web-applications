package com.opf.restaurant.service;

import com.opf.restaurant.model.MenuItem;

import java.util.List;

public interface MenuItemService {

    void saveMenuItem(MenuItem menuItem);

    List<MenuItem> findAll();

    MenuItem findById(Integer id);
}
