package org.dy.dto;

import java.util.List;

public class BusinessListDto {
    private boolean hasMore;
    private List<BusinessDto> data;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<BusinessDto> getData() {
        return data;
    }

    public void setData(List<BusinessDto> data) {
        this.data = data;
    }
}
