package kals.com.core.utility;

import kals.com.core.model.Page;

public class PageUtil {

    public static <T> Page convertRawPageToPageDomain(org.springframework.data.domain.Page<T> rawPageResponse) {
        return Page.builder()
                .number(rawPageResponse.getNumber() + 1)
                .size(rawPageResponse.getSize())
                .totalPages(rawPageResponse.getTotalPages())
                .contentSize(rawPageResponse.getNumberOfElements())
                .totalSize(rawPageResponse.getTotalElements())
                .build();
    }

}
