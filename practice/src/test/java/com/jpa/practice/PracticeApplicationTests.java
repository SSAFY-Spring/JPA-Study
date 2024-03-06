package com.jpa.practice;

import com.jpa.practice.entity.Member;
import com.jpa.practice.entity.Team;
import com.jpa.practice.repository.MemberJpaRepository;
import com.jpa.practice.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
// 데이터의 변경은 트랜젝션 내에서 가능
@Transactional
// 테스트에서는 작업 후 기본이 롤백이므로 처리 필요
@Rollback(value = false)
class PracticeApplicationTests {

	@PersistenceContext
	EntityManager em;

	// 회원 레포지터리 주입
	@Autowired
	MemberJpaRepository memberJpaRepository;

	@Autowired
	MemberRepository memberRepository;

	// 기본 JPA 회원 저장, 조회 테스트
	@Test
	public void testMember1() {
		
		// 회원 생성
		Member member = new Member("memberA");
		Member saveMember = memberJpaRepository.save(member);
		
		// 회원 조회
		Member findMember = memberJpaRepository.find(saveMember.getId());

		// 비교 검증
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

		// 동일성 검증
		assertThat(findMember).isEqualTo(member);
	}

	// 스프링 데이터 JPA 회원 저장, 조회 테스트
	@Test
	public void testMember2() {

		// 회원 생성
		Member member = new Member("memberA");
		Member saveMember = memberRepository.save(member);

		// 회원 조회
		Member findMember = memberRepository.findById(saveMember.getId()).get();

		// 비교 검증
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

		// 동일성 검증
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	public void testMember3() {

		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);
		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);

		//초기화
		em.flush();
		em.clear();

		//확인
		List<Member> members = em.createQuery("select m from Member m",
						Member.class)
				.getResultList();
		for (Member member : members) {
			System.out.println("member=" + member);
			System.out.println("-> member.team=" + member.getTeam());
		}
	}
}
