package kals.com.core.utility;

import kals.com.core.model.Page;

/**
 * Utility class for translating Spring Data domain pages into the framework's custom Page model.
 */
public class PageUtil {

    /**
     * Converts a Spring Data {@link org.springframework.data.domain.Page} to the custom {@link Page} model.
     * Note: Increments the page number by 1 to map from Spring's 0-indexed pages to 1-indexed responses.
     *
     * @param rawPageResponse the raw Spring Data page
     * @param <T> the type of content in the page
     * @return the mapped Page metadata
     */
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
