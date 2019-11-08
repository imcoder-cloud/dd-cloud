package fun.imcoder.cloud.base.support;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import fun.imcoder.cloud.base.utils.Util;
import fun.imcoder.cloud.base.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BaseController<M extends BaseModel,S extends BaseService> {

    @Resource
    public S service;

    @GetMapping("/list")
    private ResponseVO list(@RequestBody M m){
        List<M> list = service.findAll(m);
        return ResponseVO.success(list);
    }

    @GetMapping("/page")
    private ResponseVO listPage(@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request){
        String s = request.getParameter("params");
        s = Util.isEmpty(s) ? "{}" : s;
        Class <M> entityClass = (Class <M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        M m = JSON.parseObject(s,entityClass);
        m.setPageNum(pageNum);
        m.setPageSize(pageSize);
        return buildPageResult(service.listPage(m));
    }

    @GetMapping("/{id}")
    private ResponseVO findById(@PathVariable Integer id){
        M m = (M) service.findById(id);
        return ResponseVO.success(m);
    }

    @DeleteMapping("/{id}")
    private ResponseVO deleteById(@PathVariable Integer id, HttpServletRequest request){
        service.deleteById(id);
        return ResponseVO.success();
    }

    @DeleteMapping("/deleteByIds")
    public ResponseVO deleteByIds(@RequestParam String ids, HttpServletRequest request){
        List<Integer> list = Arrays.asList(Util.toIntegerArr(ids,","));
        service.deleteByIds(list);
        return ResponseVO.success();
    }

    @DeleteMapping("/deleteByMap")
    public ResponseVO deleteByMap(@RequestBody Map<String,Object> map, HttpServletRequest request){
        service.delete(map);
        return ResponseVO.success();
    }

    @PutMapping("/{id}")
    public ResponseVO updateById(@PathVariable Integer id,@RequestBody M m, HttpServletRequest request){
        service.update(m);
        return ResponseVO.success();
    }

    @PostMapping("/save")
    public ResponseVO save(@RequestBody M m, HttpServletRequest request){
        service.save(m);
        return ResponseVO.success(m);
    }

    @PostMapping("/saveList")
    private ResponseVO saveList(@RequestBody List<M> list, HttpServletRequest request){
        service.save(list);
        return ResponseVO.success(list);
    }

    protected ResponseVO buildPageResult(PageInfo pageInfo){
//        return ResponseVO.success(pageInfo.getList(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(),pageInfo.getPageSize());
        return null;
    }
}
