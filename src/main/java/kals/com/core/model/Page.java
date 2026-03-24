package kals.com.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
