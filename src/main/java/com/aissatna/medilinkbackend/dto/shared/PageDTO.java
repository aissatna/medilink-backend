package com.aissatna.medilinkbackend.dto.shared;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageDTO<T> {
    private List<T> content;
    private long totalItems;

    public PageDTO(Page<T> page) {
        this.content = page.getContent();
        this.totalItems = page.getTotalElements();

    }

}
