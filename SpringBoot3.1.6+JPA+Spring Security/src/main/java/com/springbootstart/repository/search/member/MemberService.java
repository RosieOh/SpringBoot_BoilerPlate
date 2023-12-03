package com.springbootstart.repository.search.member;

import com.springbootstart.domain.Member;
import com.springbootstart.dto.member.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {}
    Member existByEmail(String email);
    void join(MemberJoinDTO memberJoinDTO)throws MidExistException ;

}
