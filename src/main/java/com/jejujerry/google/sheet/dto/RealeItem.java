package com.jejujerry.google.sheet.dto;

import lombok.*;

import java.time.LocalDateTime;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Getter
@Setter
public class RealeItem {

    private String regDate;
    private String addrName;
    private String addrName2;
    private String addrName3;
    private String title;
    private String py;
    private String amt;
    private String amt2;
    private String dang;
    private String dang2;
    private String category;
    private String zone;
    private String explain;
    private String evaluation;

    private String pnuCode;
    //private Long lat; //위도
    //private Long lng; //경도
}

