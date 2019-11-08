package fun.imcoder.cloud.base.support;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BaseService<M extends BaseModel> {
    List<M> findAll();

    List<M> findAll(M m);

    List<M> findAll(Map<String, Object> map);

    PageInfo<M> listPage(M params);

    PageInfo<M> listPage(Map<String, Object> params);

    M findById(Integer id);

    M findByName(String name);

    List<M> findByIds(List<Integer> primaryIds);

    List<M> findByIds(Integer[] primaryIds);

    void delete(M m);

    void delete(Map<String, Object> map);

    void deleteById(Integer id);

    void deleteByIds(List<Integer> ids);

    void deleteByIds(Integer[] ids);

    void update(Map<String, Object> map);

    void update(M m);

    void save(Map<String, Object> map);

    void save(M m);

    void save(List<M> list);
}
