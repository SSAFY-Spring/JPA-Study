package com.jpa.practice.repository;

import com.jpa.practice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이름, 기준 조회
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
}
