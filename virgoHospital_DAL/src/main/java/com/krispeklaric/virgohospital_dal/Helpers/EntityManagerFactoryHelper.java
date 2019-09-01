package com.krispeklaric.virgohospital_dal.Helpers;

import javax.persistence.*;

/**
 *
 * @author Kris
 */
public class EntityManagerFactoryHelper {

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("com.krispeklaric_virgoHospital_DAL_jar_1.0-DEVELOPMENTPU");
    }
}
