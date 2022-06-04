package com.example.board.vo;

import lombok.Data;

@Data
public class Criteria {
    // 현재 페이지 번호
    private int currentPageNum;
    // 페이지당 출력할 데이터 개수
    private int perPageNum;
    // 화면 하단에 출력할 페이지 사이즈
    private int pageSize;
    // 검색 키워드
    private String keyword;

    public Criteria() {
        this.currentPageNum = 1;
        this.perPageNum = 10;
        this.pageSize = 10;
    }

    public int getStartPage() {
        return (currentPageNum -1) * perPageNum;
    }

}
