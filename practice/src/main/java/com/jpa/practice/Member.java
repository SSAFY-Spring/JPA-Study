package com.jpa.practice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    // 기본 키 생성을 DB에 위임하는 전략
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이름
    private String username;
}
