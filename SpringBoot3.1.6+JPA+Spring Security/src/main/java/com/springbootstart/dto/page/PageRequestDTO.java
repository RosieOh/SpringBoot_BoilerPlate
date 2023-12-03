package com.springbootstart.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type;

    private String keyword;

    // type라는 문자열을 배열로 반환해 주는 기능
    public String[] getTypes() {
        if(type == null || type.isEmpty()) {
            return null;
        }

        return type.split("");
    }

    // 페이징 처리를 위해 Pageable 타입을 반환하는 기능
    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    }

    // 검색 조건과 페이징 조건 등을 문자열로 구성하는 getLink() 설정
    private String link;

    private String getLink() {
        if(link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if(type != null && type.length() > 0) {
                builder.append("&type=" + type);
            }

            if(keyword != null) {
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            }
            link = builder().toString();
        }

        return link;
    }
}
