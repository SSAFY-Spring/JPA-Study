package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//스프링 빈으로 등록해줌
@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext //EntityManager 주입해줌 - @Autowired도 사용할 수 있음(SpringDataJPA가 지원해줌)

    private final EntityManager em;

//    @PersistenceUnit //EntityManagerFactory 직접 주입할 때
//    private EntityManagerFactory emf;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id){ //하나 조회
        return em.find(Member.class, id); //type, pk
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) //jpql - 엔티티 객체를 대상으로 쿼리..
                .getResultList();
    }

    public List<Member> findByName(String name){ //특정 이름의 회원을 찾는 것
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

