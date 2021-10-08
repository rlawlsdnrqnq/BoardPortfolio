package com.example.BoardProject.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationInfo {
    private Criteria criteria;
    private int totalRecordCount;
    private int totalPageCount;
    private int firstPage;
    private int lastPage;
    private int firstRecordIndex;
    private int lastRecordIndex;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;

    public PaginationInfo(Criteria criteria) {
        if(criteria.getCurrentPageNo() < 1) {
            this.criteria.setCurrentPageNo(1);
        }
        if(criteria.getRecordsPerPage() < 1 || criteria.getRecordsPerPage() > 100) {
            this.criteria.setRecordsPerPage(10);
        }
        if(criteria.getPageSize() < 5 || criteria.getPageSize() > 20) {
            this.criteria.setPageSize(10);
        }
        this.criteria = criteria;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;

        if(totalRecordCount > 0) {
            calculation();
        }
    }

    private void calculation() {
        totalPageCount = ((totalRecordCount - 1) / criteria.getRecordsPerPage()) + 1;
        if(criteria.getCurrentPageNo() > totalPageCount) {
            criteria.setCurrentPageNo(totalPageCount);
        }

        firstPage = ((criteria.getCurrentPageNo() - 1) / criteria.getPageSize()) * criteria.getPageSize() + 1;

        lastPage = firstPage + criteria.getPageSize() - 1;
        if(lastPage > totalPageCount) {
            lastPage = totalPageCount;
        }

        firstRecordIndex = (criteria.getCurrentPageNo() - 1) * criteria.getRecordsPerPage();
        lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordsPerPage();
        hasPreviousPage = firstPage != 1;
        hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount;
    }
}
