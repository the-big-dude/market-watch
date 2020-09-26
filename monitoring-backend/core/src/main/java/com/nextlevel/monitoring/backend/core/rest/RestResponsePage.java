package com.nextlevel.monitoring.backend.core.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class RestResponsePage<T> extends PageImpl<T> {

    private static final long serialVersionUID = 977174191196543959L;
    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    private boolean previousPage;
    private boolean first;
    private boolean nextPage;
    private boolean last;
    private List<T> content;
    private Sort sort;

    public RestResponsePage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    /**
     * @return the sort
     */
    public Sort getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(Sort sort) {
        this.sort = sort;
    }

    /**
     * @return the content
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * @return the last
     */
    public boolean isLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(boolean last) {
        this.last = last;
    }

    /**
     * @return the nextPage
     */
    public boolean isNextPage() {
        return nextPage;
    }

    /**
     * @param nextPage the nextPage to set
     */
    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * @return the first
     */
    public boolean isFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(boolean first) {
        this.first = first;
    }

    /**
     * @return the previousPage
     */
    public boolean isPreviousPage() {
        return previousPage;
    }

    /**
     * @param previousPage the previousPage to set
     */
    public void setPreviousPage(boolean previousPage) {
        this.previousPage = previousPage;
    }

    /**
     * @return the totalElements
     */
    public long getTotalElements() {
        return totalElements;
    }

    /**
     * @param totalElements the totalElements to set
     */
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * @return the numberOfElements
     */
    public int getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * @param numberOfElements the numberOfElements to set
     */
    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    /**
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    public RestResponsePage(List<T> content) {
        super(content);
    }

    public RestResponsePage() {
        super(new ArrayList<T>());
    }

    public PageImpl<T> pageImpl() {
        return new PageImpl<T>(getContent(), PageRequest.of(getNumber(),
                getSize(), getSort()), getTotalElements());
    }
}