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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.imcoder.cloud.base.common.PageRequest;

import java.util.List;

/**
 * 基础 Service
 *
 * @author cdd
 * @since 2020-01-23
 */
public interface BaseService<M> extends IService<M> {


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
     * @param pageRequest
     * @return
     */
    IPage<M> customPage(PageRequest<M> pageRequest);

    /**
     * 自定义批量插入
     *
     * @param list
     * @return
     */
    Boolean insertBatch(List<M> list);

}
