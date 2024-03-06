package com.jpa.practice.repository;

import com.jpa.practice.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberJpaRepository {

    // 매니저 가져오기
    @PersistenceContext
    private EntityManager em;

    // 회원 저장
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    // 회원 조회
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
