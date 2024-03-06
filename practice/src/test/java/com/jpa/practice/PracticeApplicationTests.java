package com.jpa.practice;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
// 데이터의 변경은 트랜젝션 내에서 가능
@Transactional
// 테스트에서는 작업 후 기본이 롤백이므로 처리 필요
@Rollback(value = false)
class PracticeApplicationTests {

	// 회원 레포지터리 주입
	@Autowired
	MemberJpaRepository memberJpaRepository;

	@Test
	public void testMember() {
		
		// 회원 생성
		Member member = new Member();
		member.setUsername("memberA");
		Member saveMember = memberJpaRepository.save(member);
		
		// 회원 조회
		Member findMember = memberJpaRepository.find(saveMember.getId());

		// 비교 검증
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

		// 동일성 검증
		assertThat(findMember).isEqualTo(member);
	}
}
