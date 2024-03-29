package com.jejujerry.google.sheet.dto;

import lombok.*;

import java.time.LocalDateTime;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Getter
@Setter
public class RealeItem {

    private String regDate;//접수일0
    private String addrName;//소재지1
    private String addrName2;//소재지2
    private String addrName3;//소재지3(추가소재지)
    private String title;//TITLE4
    private String pnupre;//PNUPRE5
    private String ads; //광고6
    private String link1; //DISCO7
    private String smeter;//사이즈(square meter)8
    private String py;//사이즈(평)9
    private String amt;//금액1(백만)10
    private String amt2;//금액2(백만)11
    private String dang;//당1(백만)12
    private String dang2;//당2(백만)13
    private String category;//지목14
    private String zone;//지역15
    private String explain;//설명16
    private String evaluation;//나의평가17
    private String question;//나의질문18
    private String owner;//소유자19

    //처리시 만들어담을 변수
    private String rowIdx;
    private String pnuCode;
    private String pageName;
    //private Long lat; //위도
    //private Long lng; //경도
}

