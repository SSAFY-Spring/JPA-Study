package com.jpa.basic.basic.hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        // 설정 조회 후 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 필요 코드 작성

        // 매니저, 팩토리 닫기
        em.close();
        emf.close();
    }
}
