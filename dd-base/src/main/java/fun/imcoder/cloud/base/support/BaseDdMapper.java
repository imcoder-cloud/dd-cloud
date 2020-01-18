/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fun.imcoder.cloud.base.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDdMapper<M> extends BaseMapper<M> {

    /**
     * 自定义查询
     * 需要自己写sql
     *
     * @param m
     * @return
     */
    List<M> customList(M m);

    /**
     * 自定义分页查询
     * 需要自己写sql
     *
     * @param m
     * @return
     */
    List<M> customPage(Page page, @Param(value = "params") M m);

    /**
     * 自定义批量插入
     *
     * @param list
     * @return
     */
    Boolean insertBatch(List<M> list);
}
