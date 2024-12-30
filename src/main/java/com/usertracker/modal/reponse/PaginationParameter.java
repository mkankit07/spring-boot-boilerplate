package com.usertracker.modal.reponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class PaginationParameter {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
}
