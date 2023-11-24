package com.chunjae.test07.persis;

import com.chunjae.test07.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserRoleMapper {
    void setUserRoleInfo(@Param("param") UserRole param);
}
