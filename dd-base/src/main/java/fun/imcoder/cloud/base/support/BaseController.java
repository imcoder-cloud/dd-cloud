package fun.imcoder.cloud.base.support;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.imcoder.cloud.base.annotation.ModelParam;
import fun.imcoder.cloud.base.common.PageRequest;
import fun.imcoder.cloud.base.common.ResponseData;
import fun.imcoder.cloud.base.enums.ModelParamType;
import fun.imcoder.cloud.base.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseController<M extends BaseModel, S extends IService> {

    @Autowired
    public S service;

    @GetMapping
    public ResponseData<List<M>> list(@ModelParam M m) {
        QueryWrapper<M> queryWrapper = new QueryWrapper<>(m);
        return ResponseData.success(service.list(queryWrapper));
    }

    @GetMapping("/{id}")
    public ResponseData<M> getById(@PathVariable Integer id) {
        return ResponseData.success((M) service.getById(id));
    }

    @GetMapping("/page")
    public ResponseData<IPage<M>> page(@ModelParam(ModelParamType.PAGE) PageRequest<M> pageRequest) {
        Page<M> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<M> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(pageRequest.getParam());
        IPage rtn = service.page(page, queryWrapper);
        return ResponseData.success(rtn);
    }

//    @PostMapping
//    public ResponseData<Boolean> save(@RequestBody Object object) throws Exception {
//        Class<M> entityClass = this.getMClass();
//        if (object instanceof List) {
//            List<Map<String, Object>> mapList = (List<Map<String, Object>>) object;
//            List<M> list = new ArrayList<>();
//            for (Map<String, Object> map : mapList) {
//                list.add(BeanUtil.mapToBean(map, entityClass));
//            }
//            return ResponseData.success(service.saveOrUpdateBatch(list));
//        }
//        M m = BeanUtil.mapToBean((Map<String, Object>) object, entityClass);
//        return ResponseData.success(service.saveOrUpdate(m));
//    }

    @PostMapping
    public ResponseData<Boolean> saveBatch(@RequestBody List<M> list) {
        return ResponseData.success(service.saveBatch(list));
    }

    @PutMapping
    public ResponseData<Boolean> updateBatch(@RequestBody List<M> list) {
        return ResponseData.success(service.updateBatchById(list));
    }

    @DeleteMapping("/{id}")
    public ResponseData<Boolean> deleteById(@PathVariable Serializable id) {
        return ResponseData.success(service.removeById(id));
    }

    @DeleteMapping
    public ResponseData<Boolean> deleteByModel(@ModelParam M m) {
        QueryWrapper<M> queryWrapper = new QueryWrapper<>(m);
        return ResponseData.success(service.remove(queryWrapper));
    }

    @DeleteMapping("/batch")
    public ResponseData<Boolean> deleteByIds(@RequestParam String ids) {
        List<Integer> list = Arrays.stream(ids.split(",")).mapToInt(s->Integer.parseInt(s)).boxed().collect(Collectors.toList());
        return ResponseData.success(service.removeByIds(list));
    }

    public Class<M> getMClass() {
        return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
