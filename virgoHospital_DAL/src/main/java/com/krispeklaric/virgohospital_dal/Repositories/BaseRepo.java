package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Helpers.EntityManagerFactoryHelper;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Kris
 */
public class BaseRepo {

    public EntityManagerFactory emf;

    public BaseRepo() {
        if (emf == null) {
            emf = EntityManagerFactoryHelper.getEntityManagerFactory();
        }
    }
}
