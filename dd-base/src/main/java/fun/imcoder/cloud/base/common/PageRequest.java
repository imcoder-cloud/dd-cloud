package fun.imcoder.cloud.base.common;

import lombok.Data;

@Data
public class PageRequest<T> {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private T param;

    public PageRequest() {
    }

    public PageRequest(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

}
