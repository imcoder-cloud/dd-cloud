package fun.imcoder.cloud.base.handle;

import fun.imcoder.cloud.base.annotation.PageParam;
import fun.imcoder.cloud.base.annotation.PageParamClass;
import fun.imcoder.cloud.base.common.PageRequest;
import fun.imcoder.cloud.base.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class PageArgumentHandle implements HandlerMethodArgumentResolver, HandlerInterceptor {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, String[]> map = webRequest.getParameterMap();
        Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if (StringUtils.equals("pageNum", entry.getKey()) || StringUtils.equals("pageSize", entry.getKey())) {
                continue;
            }

            params.put(entry.getKey().replace("param.", ""), entry.getValue().length > 1 ? entry.getValue() :
                    entry.getValue()[0]);
        }

        PageRequest pageRequest = new PageRequest();
        String current = webRequest.getParameter("pageNum");
        String size = webRequest.getParameter("pageSize");

        if (StringUtils.isNotBlank(current)) {
            pageRequest.setPageNum(Integer.parseInt(current));
        }

        if (StringUtils.isNotBlank(size)) {
            pageRequest.setPageSize(Integer.parseInt(size));
        }

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String modelClass = (String) request.getAttribute("ModelClass");
        Class clz = Class.forName(modelClass);
        pageRequest.setParam(BeanUtil.mapToBean(params,clz));

        return pageRequest;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String modelClass = ((HandlerMethod) handler).getBeanType().getAnnotation(PageParamClass.class).value();
        request.setAttribute("ModelClass",modelClass);
        return true;
    }

}
