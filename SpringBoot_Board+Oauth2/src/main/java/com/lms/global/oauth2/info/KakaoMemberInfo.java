package com.lms.global.oauth2.info;

import java.util.Map;

public class KakaoMemberInfo implements OAuth2MemberInfo{

    private Map<String, Object> attributes;
    private Map<String, Object> kakaoAccountAttributes;
    private Map<String, Object> profileAttributes;

    public KakaoMemberInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.kakaoAccountAttributes = (Map<String, Object>) attributes.get("kakao_account");
        this.profileAttributes = (Map<String, Object>) kakaoAccountAttributes.get("profile");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getName() {
//        return profileAttributes.get("name").toString();
        Object name = attributes.get("nickname");
        return name != null ? name.toString() : "Unknown";
    }

    // 개인정보 심사 완료되면 사용할 예정
//    @Override
//    public String getTel() {
//        return profileAttributes.get("tel").toString();
//    }

    @Override
    public String getEmail() {
        return kakaoAccountAttributes.get("email").toString();
    }
}
