package fun.imcoder.cloud.base.support;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BaseModel {

    private String createTime;
    private String modifyTime;

    @TableField(exist = false)
    private String order = "create_time"; // 排序方式 默认按时间倒序
}
