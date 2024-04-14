package com.lms.domain.member.service;

import com.lms.domain.member.dto.MemberJoinDTO;
import com.lms.domain.member.entity.Member;
import com.lms.domain.member.repository.MemberRepository;
import com.lms.global.cosntant.Role;
import com.lms.global.cosntant.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createAdminMember() {
        // 이미 존재하는 회원인지 확인
        if (!memberRepository.existsByEmail("admin@chunjaeIT.co.kr")) {
            // 관리자 계정 생성 및 초기 설정
            Member admin = Member.builder()
                    .pw(passwordEncoder.encode("1234"))
                    .name("관리자")
                    .email("admin@chunjaeIT.co.kr")
                    .role(Role.ADMIN)
                    .status(Status.ACTIVE)
                    .tel("02-3282-8589")
                    .build();

            memberRepository.save(admin);

            log.info("Admin 계정이 생성되었습니다.");
        } else {
            log.info("Admin 계정이 이미 존재합니다.");
        }
    }

    @Override
    public boolean existByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }

    @Override
    public List<MemberJoinDTO> memberList() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberJoinDTO> memberJoinDTOList = memberList.stream().map(
                member -> modelMapper.map(member,MemberJoinDTO.class))
                .collect(Collectors.toList());
        return memberJoinDTOList;
    }

    @Override
    public MemberJoinDTO getEmail(String email) {
        Member member = memberRepository.getEmail(email);
        MemberJoinDTO memberJoinDTO = modelMapper.map(member, MemberJoinDTO.class);
        return memberJoinDTO;
    }


    @Override
    public void memberInsert(MemberJoinDTO memberJoinDTO) {
        String password = passwordEncoder.encode(memberJoinDTO.getPw());
        memberJoinDTO.setPw(password);
        memberJoinDTO.setRole(Role.STUDENT);
        memberJoinDTO.setStatus(Status.ACTIVE);
        Member member = modelMapper.map(memberJoinDTO, Member.class);
        memberRepository.save(member);
    }

    @Override
    public Member LoginEmail(String email) {
        Member member = memberRepository.getEmail(email);
        return member;
    }

    @Override
    public void memberUpdate(MemberJoinDTO memberJoinDTO) {
        Optional<Member> member = memberRepository.getMember(memberJoinDTO.getEmail());
        Member member1 = member.orElseThrow();
        member1.change(memberJoinDTO);
        memberRepository.save(member1);
    }

    @Override
    public void stateUpdate(MemberJoinDTO memberJoinDTO) {
        Optional<Member> member = memberRepository.getMember(memberJoinDTO.getEmail());
        Member member1 = member.orElseThrow();
        member1.stateUpdate(memberJoinDTO);
        memberRepository.save(member1);
    }

    @Override
    public void roleUpdate(MemberJoinDTO memberJoinDTO) {
        Optional<Member> member = memberRepository.getMember(memberJoinDTO.getEmail());
        Member member1 = member.orElseThrow();
        member1.roleUpdate(memberJoinDTO);
        memberRepository.save(member1);
    }

    @Override
    public void memberDelete(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public int loginPro(String email) {
        int pass = 0;
        Member member = memberRepository.getEmail(email);
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(30); // 현재 시점에서 30일 동안 반응이 없으면 휴면
        if (localDateTime.isAfter(member.getLoginAt())) {
            member.setStatus(Status.REST);
            memberRepository.save(member);
            pass = 2;
        } else {
            if (member.getStatus().equals(Status.ACTIVE)) {
                member.setLoginAt(LocalDateTime.now());
                memberRepository.save(member);
                pass = 2;
            } else if (member.getStatus().equals(Status.REST)) {
                pass = 2;
            } else if (member.getStatus().equals(Status.OUT)) {
                pass = 3;
            }
        }
        return pass;
    }

    @Override
    public boolean idCheck(String email) {
        boolean pass = true;
        int cnt = memberRepository.countByEmail(email);
        if(cnt > 0) pass = false;
        return pass;
    }

    @Override
    public void memberChangePw(MemberJoinDTO memberJoinDTO) {
        String password = passwordEncoder.encode(memberJoinDTO.getPw());
        memberJoinDTO.setPw(password);
        Member member = modelMapper.map(memberJoinDTO, Member.class);
        memberRepository.save(member);
    }

}
