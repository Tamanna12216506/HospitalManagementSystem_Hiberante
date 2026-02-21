package org.hospitalManagementSystem.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("dev");

    public static EntityManagerFactory getEmf() {
        return EMF;
    }
}
