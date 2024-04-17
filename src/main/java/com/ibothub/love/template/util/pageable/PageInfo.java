package com.ibothub.love.template.util.pageable;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * JPA Page Wrapper
 * <p/>
 */
@Data
@Schema
public class PageInfo<T> implements Serializable {

    @Schema(description = "是否第一页")
    private boolean firstPage;

    @Schema(description = "是否最后页")
    private boolean lastPage;

    @Schema(description = "是否有前一页")
    private boolean hasPreviousPage;

    @Schema(description = "是否有下一页")
    private boolean hasNextPage;

    @Schema(description = "总页数")
    private int pages;

    @Schema(description = "总记录数")
    private long total;

    @Schema(description = "当前页码")
    private int pageNum;

    @Schema(description = "当前页大小")
    private int pageSize;

    @Schema(description = "分页结果集")
    private List<T> list;

    public PageInfo(){}

    public PageInfo(Page<T> page) {
        this.pageNum = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.pages = page.getTotalPages();
        this.list = page.getContent();
        this.total = page.getTotalElements();
        // 判断页边界
        firstPage = pageNum == 1;
        lastPage = pageNum == pages || pages == 0;
        hasPreviousPage = pageNum > 1;
        hasNextPage = pageNum < pages;
    }

    public static <T> PageInfo<T> of(Page<T> page) {
        return new PageInfo<>(page);
    }
}
