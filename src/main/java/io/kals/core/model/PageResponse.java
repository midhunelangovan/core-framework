package io.kals.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Generic response wrapper for paginated endpoints.
 * Contains both the metadata (Page) and the actual payload (content).
 *
 * @param <T> the type of elements in the content list
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private Page page;
    private List<T> content;

}
