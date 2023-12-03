package com.springbootstart.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {

    private String mid;
    private String mpw;
    private String mname;
    private String email;
    private boolean del;
    private boolean social;
    private Map<String, Object> props;
    public MemberSecurityDTO(String username, String password, String name, String email, boolean del, boolean social,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.mid = username;
        this.mpw = password;
        this.mname = name;
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
