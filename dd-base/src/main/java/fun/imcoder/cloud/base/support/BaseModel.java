package fun.imcoder.cloud.base.support;

import fun.imcoder.cloud.base.validate.PageValidate;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BaseModel {
    @NotNull(message="分页参数pageNum不能为空",groups={PageValidate.class})
    public Integer pageNum;

    @NotNull(message="分页参数pageSize不能为空",groups={PageValidate.class})
    public Integer pageSize;

    private Integer[] ids;
    private Integer id;
    private String name;
    private String createTime;
    private Integer createBy;
    private String modifyTime;
    private Integer modifyBy;
    private String createByName;
    private String modifyByName;
}
