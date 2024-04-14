package com.lms.domain.member.service;

import com.lms.domain.member.dto.MemberJoinDTO;
import com.lms.domain.member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface MemberService {

    public List<MemberJoinDTO> memberList();

    public PasswordEncoder passwordEncoder();
    public MemberJoinDTO getEmail(String email);
    public void memberInsert(MemberJoinDTO memberJoinDTO);
    public Member LoginEmail(String email);
    public void memberUpdate(MemberJoinDTO memberJoinDTO);
    public void stateUpdate(MemberJoinDTO memberJoinDTO);
    public void roleUpdate(MemberJoinDTO memberJoinDTO);
    public void memberDelete(Long id);
    public int loginPro(String email);
    public boolean idCheck(String email);
    public void memberChangePw(MemberJoinDTO memberJoinDTO);

    // 고정 아이디 생성
    void createAdminMember();
    boolean existByEmail(String email);







}