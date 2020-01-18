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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.imcoder.cloud.base.common.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 基础service实现类
 *
 * @param <T>
 * @param <M>
 */
@SuppressWarnings("unchecked")
public class BaseServiceImpl<T extends BaseDdMapper<M>, M> extends ServiceImpl<T, M> implements BaseService<M> {

    @Override
    public List<M> customList(M m) {
        return this.baseMapper.customList(m);
    }

    @Override
    public List<M> customList(Map<String, Object> param) {
        return this.baseMapper.customList(param);
    }

    @Override
    public IPage<M> customPage(PageRequest<M> pageRequest) {
        Page page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<M> list = this.baseMapper.customPage(page, pageRequest.getParam());
        return page.setRecords(list);
    }

    @Override
    public Boolean insertBatch(List<M> list) {
        return this.baseMapper.insertBatch(list);
    }
}
