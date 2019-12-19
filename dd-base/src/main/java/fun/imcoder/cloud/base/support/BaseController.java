package fun.imcoder.cloud.base.support;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.imcoder.cloud.base.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

public class BaseController<M extends BaseModel,S extends IService> {

    @Autowired
    public S service;

    @GetMapping("/all")
    public ResponseData findAll() {
        return ResponseData.success(service.list());
    }

    @PostMapping("/list")
    public ResponseData list(@RequestBody M m) {
        QueryWrapper<M> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(m);
        return ResponseData.success(service.list(queryWrapper));
    }

    @GetMapping("/{id}")
    public ResponseData findById(@PathVariable Integer id) {
        return ResponseData.success(service.getById(id));
    }

    @PostMapping("/page")
    public ResponseData page(@RequestParam long current, @RequestParam long size, @RequestBody M m) {
        Page<M> page = new Page<>(current, size);
        QueryWrapper<M> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(m);
        IPage rtn = service.page(page, queryWrapper);
        return ResponseData.success(rtn);
    }

    @PostMapping("/save")
    public ResponseData save(@RequestBody M m) {
        return ResponseData.success(service.saveOrUpdate(m));
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
}
