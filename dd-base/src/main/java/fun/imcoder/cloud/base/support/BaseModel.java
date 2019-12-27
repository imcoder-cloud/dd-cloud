package fun.imcoder.cloud.base.support;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BaseModel {

    private String createTime;
    private String modifyTime;
}
