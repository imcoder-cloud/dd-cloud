package fun.imcoder.cloud.base.support;

import java.util.List;
import java.util.Map;

public interface BaseMapper<M extends BaseModel> {
    List<M> findAll();

    List<M> findAll(M m);

    List<M> findAll(Map<String, Object> map);

    M findById(Integer id);

    M findByName(String name);

    List<M> findByIds(List<Integer> ids);

    List<M> findByIds(Integer[] ids);

    List<M> findByNames(List<String> names);

    List<M> findByNames(String[] names);

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
