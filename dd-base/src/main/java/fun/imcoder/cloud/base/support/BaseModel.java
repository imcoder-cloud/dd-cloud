package fun.imcoder.cloud.base.support;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BaseModel {

    private String createTime;
    @TableField(exist = false)
    private Integer createBy;
    private String modifyTime;
    @TableField(exist = false)
    private Integer modifyBy;

    @TableField(exist = false)
    private String createByName;

    @TableField(exist = false)
    private String modifyByName;
}
