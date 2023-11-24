package com.shop.service;

import com.shop.dto.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {}

    void join(MemberJoinDTO memberJoinDTO)throws MidExistException ;

    MemberJoinDTO myinfo(String mid);
}
