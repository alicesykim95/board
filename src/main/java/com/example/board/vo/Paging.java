package com.example.board.vo;

import lombok.Data;

@Data
public class Paging {
    // 페이징 계산에 필요한 파라미터들이 담긴 클래스
    private Criteria criteria;
    // 전체 데이터 개수
    private int totalRecordCount;
    // 전체 페이지 개수
    private int totalPageCount;
    // 페이지 리스트의 첫 페이지 번호
    private int firstPage;
    // 페이지 리스트의 마지막 페이지 번호
    private int lastPage;
    // 이전 페이지 존재 여부
    private boolean hasPreviousPage;
    // 다음 페이지 존재 여부
    private boolean hasNextPage;

    public Paging(Criteria criteria, int totalRecordCount){
        if (criteria.getCurrentPageNum() < 1){
            criteria.setCurrentPageNum(1);
        }
        if (criteria.getPerPageNum() < 1 || criteria.getPerPageNum() > 100){
            criteria.setPerPageNum(10);
        }
        if(criteria.getPageSize() < 5 || criteria.getPageSize() > 20) {
            criteria.setPageSize(10);
        }
        this.criteria = criteria;

        this.totalRecordCount = totalRecordCount;

        if(totalRecordCount > 0){
            calculation();
        }

    }

    private void calculation() {
        //전체 페이지 수=((전체 데이터 개수 -1)/페이지당 출력할 데이터 개수)+1
        // 현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장
        totalPageCount = ((totalRecordCount - 1) / criteria.getPerPageNum()) + 1;
        if (criteria.getCurrentPageNum() > totalPageCount){
            criteria.setCurrentPageNum(totalPageCount);
        }

        // 페이지 리스트의 첫 페이지 번호
        // 첫번째 페이지=((현재 페이지 번호-1)/화면 하단의 페이지 개수)*화면하단의 페이지 개수+1
        firstPage = ((criteria.getCurrentPageNum() - 1) / criteria.getPageSize()) * criteria.getPageSize() + 1;

        // 페이지 리스트의 마지막 페이지 번호
        // 라스트 페이지=(첫 페이지 번호 + 화면 하단의 페이지 개수)-1;
        // 마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장
        lastPage = firstPage + criteria.getPageSize() -1;
        if (lastPage > totalPageCount) {
            lastPage = totalPageCount;
        }

        // 이전 페이지 존재 여부
        hasPreviousPage = firstPage != 1;

        // 다음 페이지 존재 여부
        hasNextPage = (lastPage * criteria.getPerPageNum()) < totalRecordCount;

    }


}
