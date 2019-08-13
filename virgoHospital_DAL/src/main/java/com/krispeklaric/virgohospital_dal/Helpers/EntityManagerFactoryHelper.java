package com.krispeklaric.virgohospital_dal.Helpers;

import javax.persistence.*;

/**
 *
 * @author Kris
 */
public class EntityManagerFactoryHelper {

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("");
    }
}
