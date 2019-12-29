package fun.imcoder.cloud.base.support;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.imcoder.cloud.base.annotation.ModelParam;
import fun.imcoder.cloud.base.annotation.PageParam;
import fun.imcoder.cloud.base.common.PageRequest;
import fun.imcoder.cloud.base.common.ResponseData;
import fun.imcoder.cloud.base.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseController<M extends BaseModel,S extends IService> {

    @Autowired
    public S service;

    @GetMapping
    public ResponseData list(@ModelParam M m) {
        QueryWrapper<M> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(m);
        return ResponseData.success(service.list(queryWrapper));
    }

    @GetMapping("/{id}")
    public ResponseData findById(@PathVariable Integer id) {
        return ResponseData.success(service.getById(id));
    }

    @GetMapping("/page")
    public ResponseData page(@PageParam() PageRequest<M> pageRequest) {
        Page<M> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<M> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(pageRequest.getParam());
        IPage rtn = service.page(page, queryWrapper);
        return ResponseData.success(rtn);
    }

    @PostMapping("/save")
    public ResponseData save(@RequestBody Object object) throws Exception {
        Class<M> entityClass = this.getMClass();
        if(object instanceof List){
            List<Map<String,Object>> mapList = (List<Map<String, Object>>) object;
            List<M> list = new ArrayList<>();
            for(Map<String,Object> map : mapList){
                list.add(BeanUtil.mapToBean(map,entityClass));
            }
            service.saveOrUpdateBatch(list);
            return ResponseData.success(list);
        }
        M m = BeanUtil.mapToBean((Map<String, Object>) object,entityClass);
        service.saveOrUpdate(m);
        return ResponseData.success(m);
    }

    @PostMapping("/saveBatch")
    public ResponseData saveBatch(@RequestBody List<M> list) {
        return ResponseData.success(service.saveOrUpdateBatch(list));
    }

    @DeleteMapping("/{id}")
    public ResponseData deleteById(@PathVariable Serializable id) {
        return ResponseData.success(service.removeById(id));
    }

    @DeleteMapping("/deleteBatch")
    public ResponseData deleteByIds(@RequestBody List<Integer> ids) {
        return ResponseData.success(service.removeByIds(ids));
    }

    public Class <M> getMClass(){
        return (Class <M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
