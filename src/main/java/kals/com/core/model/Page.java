package kals.com.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Metadata model representing pagination details.
 * Used internally and within PageResponse to convey pagination state to clients.
 */
@Getter
@Setter
@Builder
public class Page {

    private long number;
    private long size;
    private long totalPages;
    private long contentSize;
    private long totalSize;

}
