package fun.imcoder.cloud.base.common;

import lombok.Data;

@Data
public class PageRequest<T> {
    private Integer pageNum;
    private Integer pageSize;
    private T param;
}
