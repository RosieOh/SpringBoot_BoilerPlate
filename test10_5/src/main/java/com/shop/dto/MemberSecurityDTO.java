package com.shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;
//MemberSecurityDTO : 사용자 Member 객체와 Spring Security 에서 제공하는 User 객체를 매핑
@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {
    private String mid;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;
    private Map<String, Object> props; //소셜 로그인 정보
    public MemberSecurityDTO(String username, String password, String email, boolean del, boolean social,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.mid = username;
        this.mpw = password;
        this.email = email;
        this.del = del;
        this.social = social;
    }

    public Map<String, Object> getAttributes() {
        return this.getProps();
    }
    public String getName() {
        return this.mid;
    }
}
