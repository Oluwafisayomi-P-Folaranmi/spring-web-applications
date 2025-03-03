package com.opf.cruddemo.dao;

import com.opf.cruddemo.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDAO {

    void save(Instructor theInstructor);

}