package com.shop.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Book {
    @Id
    private Long seq;
    @NotBlank(message="제목을 반드시 입력하시기 바랍니다.")
    private String title;
    @NotBlank(message="저자를 반드시 입력하시기 바랍니다.")
    private String creator;
    @NotNull
    @Size(min=2, message="출판사는 두 글자 이상 입력하여야 합니다.")
    private String publisher;
    private String pubDate;
}
