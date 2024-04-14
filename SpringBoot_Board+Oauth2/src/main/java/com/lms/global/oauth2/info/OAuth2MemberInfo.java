package com.lms.global.oauth2.info;

public interface OAuth2MemberInfo {
    String getProviderId();
    String getProvider();
    String getName();
    String getEmail();

    // 개인 정보 심사 완료 되면 사용할 예정
    // String getTel();
}
