package fun.imcoder.cloud.base.support;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.imcoder.cloud.base.annotation.ModelParam;
import fun.imcoder.cloud.base.common.PageRequest;
import fun.imcoder.cloud.base.common.ResponseData;
import fun.imcoder.cloud.base.enums.ModelParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础 Controller
 *
 * @param <M>
 * @param <S>
 */
public class BaseController<M extends BaseModel, S extends BaseService> {

    @Autowired
    public S service;

    @GetMapping
    public ResponseData<List<M>> list(@ModelParam M m) {
        QueryWrapper<M> queryWrapper = new QueryWrapper<>(m);
        return ResponseData.success(service.list(queryWrapper));
    }

    @GetMapping("/list")
    public ResponseData<List<M>> customList(@ModelParam M m) {
        return ResponseData.success(service.customList(m));
    }

    @GetMapping("/{id}")
    public ResponseData<M> getById(@PathVariable Integer id) {
        return ResponseData.success((M) service.getById(id));
    }

    @GetMapping("/page")
    public ResponseData<IPage<M>> page(@ModelParam(ModelParamType.PAGE) PageRequest<M> pageRequest) {
        Page<M> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
        QueryWrapper<M> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(pageRequest.getParams());
        queryWrapper.orderByDesc("create_time");
        IPage rtn = service.page(page, queryWrapper);
        return ResponseData.success(rtn);
    }

    /**
     * 自定义分页
     *
     * @param pageRequest
     * @return
     */
    @GetMapping("/page-plus")
    public ResponseData<IPage<M>> pagePlus(@ModelParam(ModelParamType.PAGE) PageRequest<M> pageRequest) {
        IPage rtn = service.customPage(pageRequest);
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
    public ResponseData<List<M>> saveBatch(@RequestBody List<M> list) {
        service.saveBatch(list);
        return ResponseData.success(list);
    }

    @PutMapping
    public ResponseData<List<M>> updateBatch(@RequestBody List<M> list) {
        service.updateBatchById(list);
        return ResponseData.success(list);
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

    /**
     * 自定义批量插入
     *
     * @param list
     * @return
     */
    @PostMapping("/batch")
    public ResponseData<List<M>> insertBatch(@RequestBody List<M> list) {
        service.insertBatch(list);
        return ResponseData.success(list);
    }

    @DeleteMapping("/batch")
    public ResponseData<Boolean> deleteByIds(@RequestParam String ids) {
        List<Integer> list = Arrays.stream(ids.split(",")).mapToInt(s -> Integer.parseInt(s)).boxed().collect(Collectors.toList());
        return ResponseData.success(service.removeByIds(list));
    }

    public Class<M> getMClass() {
        return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
