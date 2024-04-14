package com.lms.domain.member.entity;

import com.lms.domain.member.dto.MemberJoinDTO;
import com.lms.global.cosntant.BaseEntity;
import com.lms.global.cosntant.Role;
import com.lms.global.cosntant.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;     // 기본키

    private String oauth2Id;

    private String provider;

    private String providerId;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = true)
    private String name;      // 유저 이름

    @Column(unique = true, nullable = true)
    private String email;       // 유저가 사용하는 이메일

    private String tel;

    @Enumerated(EnumType.STRING)
    private Status status;      //회원 활동상태

    @CreatedDate
    private LocalDateTime loginAt;  //최종 로그인시간

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Role> roleSet = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.STUDENT; // 디폴트로 USER 권한을 갖도록 초기화

    public void change(MemberJoinDTO memberJoinDTO) {
        this.tel = memberJoinDTO.getTel();
    }



    public void stateUpdate(MemberJoinDTO memberJoinDTO) {
        this.status = memberJoinDTO.getStatus();
    }

    public void roleUpdate(MemberJoinDTO memberJoinDTO) {
        this.role = memberJoinDTO.getRole();
    }

    @Builder
    public Member(String oauth2Id, String email, String pw, String name, String provider, String providerId) {
        this.oauth2Id = oauth2Id;
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
    }
}