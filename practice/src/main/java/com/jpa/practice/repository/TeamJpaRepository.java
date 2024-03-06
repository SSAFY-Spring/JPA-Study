package com.jpa.practice.repository;

import com.jpa.practice.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamJpaRepository {

    // 매니저 가져오기
    @PersistenceContext
    private EntityManager em;
    
    // 팀 저장
    public Team save(Team team) {
        em.persist(team);
        return team;
    }
    
    // 팀 삭제
    public void delete(Team team) {
        em.remove(team);
    }
    public List<Team> findAll() {
        return em.createQuery("select t from Team t", Team.class)
                .getResultList();
    }

    // 팀 찾기 
    public Optional<Team> findById(Long id) {
        Team team = em.find(Team.class, id);
        return Optional.ofNullable(team);
    }
    
    // 팀 수 조회
    public long count() {
        return em.createQuery("select count(t) from Team t", Long.class)
                .getSingleResult();
    }
}
