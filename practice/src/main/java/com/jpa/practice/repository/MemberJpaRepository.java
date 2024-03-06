package com.jpa.practice.repository;

import com.jpa.practice.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    // 회원 삭제
    public void delete(Member member) {
        em.remove(member);
    }
    
    // 회원 전체 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 특정 회원 찾기 (Optional Type)
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    
    // 회원 수 조회
    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    // 회원 조회
    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    // 이름과 나이 기준으로 조회
    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }
}
