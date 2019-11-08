package fun.imcoder.cloud.base.support;

import com.github.pagehelper.PageInfo;
import fun.imcoder.cloud.base.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class BaseServiceImpl<M extends BaseModel,D extends BaseMapper> implements BaseService<M> {
    @Autowired
    private D mapper;

    @Override
    public List<M> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<M> findAll(M m) {
        return mapper.findAll(m);
    }

    @Override
    public List<M> findAll(Map<String,Object> map) {
        return mapper.findAll(map);
    }

    @Override
    public PageInfo<M> listPage(M params){
        return PageUtil.listPage(params, ()->mapper.findAll(params));
    }

    @Override
    public PageInfo<M> listPage(Map<String,Object> params){
        return PageUtil.listPage(params, ()->mapper.findAll(params));
    }

    @Override
    public M findById(Integer id) {
        return (M) mapper.findById(id);
    }

    @Override
    public M findByName(String name) {
        return (M) mapper.findByName(name);
    }

    @Override
    public List<M> findByIds(List<Integer> primaryIds) {
        return mapper.findByIds(primaryIds);
    }

    @Override
    public List<M> findByIds(Integer[] primaryIds) {
        return mapper.findByIds(primaryIds);
    }

    @Override
    public void delete(M m) {
        mapper.delete(m);
    }

    @Override
    public void delete(Map<String, Object> map) {
        mapper.delete(map);
    }

    @Override
    public void deleteById(Integer primaryId) {
        mapper.deleteById(primaryId);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public void update(Map<String, Object> map) {
        mapper.update(map);
    }

    @Override
    public void update(M m) {
        mapper.update(m);
    }

    @Override
    public void save(Map<String, Object> map) {
        mapper.save(map);
    }

    @Override
    public void save(M m) {
        mapper.save(m);
    }

    @Override
    public void save(List<M> list) {
        mapper.save(list);
    }
}
