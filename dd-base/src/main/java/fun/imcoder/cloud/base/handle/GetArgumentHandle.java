package fun.imcoder.cloud.base.handle;

import fun.imcoder.cloud.base.annotation.ModelParam;
import fun.imcoder.cloud.base.common.PageRequest;
import fun.imcoder.cloud.base.enums.ModelParamType;
import fun.imcoder.cloud.base.utils.BeanUtil;
import fun.imcoder.cloud.base.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Component
public class GetArgumentHandle implements HandlerMethodArgumentResolver, HandlerInterceptor {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ModelParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ModelParamType type = parameter.getParameterAnnotation(ModelParam.class).value();
        // 分页请求
        if(type.equals(ModelParamType.PAGE)){
            Map<String, String[]> map = webRequest.getParameterMap();
            Map<String, Object> params = new HashMap<>();
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                if (StringUtils.equals("pageNum", entry.getKey()) || StringUtils.equals("pageSize", entry.getKey())) {
                    continue;
                }

                params.put(entry.getKey().replace("param.", ""), entry.getValue().length > 1 ? entry.getValue() :
                        entry.getValue()[0]);
            }

            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            Class<?> modelClass = (Class) request.getAttribute("ModelClass");

            PageRequest pageRequest = new PageRequest();
            String current = webRequest.getParameter("pageNum");
            String size = webRequest.getParameter("pageSize");

            if (StringUtils.isNotBlank(current)) {
                pageRequest.setPageNum(Integer.parseInt(current));
            }

            if (StringUtils.isNotBlank(size)) {
                pageRequest.setPageSize(Integer.parseInt(size));
            }

            pageRequest.setParam(BeanUtil.mapToBean(params,modelClass));

            return pageRequest;

        }

        // 普通 get 请求
        String name = ModelFactory.getNameForParameter(parameter);
        Object attribute = (mavContainer.containsAttribute(name) ? mavContainer.getModel().get(name) :
                BeanUtils.instantiateClass(parameter.getParameterType()));

        WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
        if (binder.getTarget() != null) {
            if (!mavContainer.isBindingDisabled(name)) {
                MutablePropertyValues mpv = new MutablePropertyValues(webRequest.getParameterMap());
                binder.bind(mpv);
            }
        }

        return binder.convertIfNecessary(binder.getTarget(), parameter.getParameterType(), parameter);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Class controllerClass = Class.forName(((HandlerMethod) handler).getBeanType().getName());
        Type genericSuperclass = controllerClass.getGenericSuperclass();
        if(genericSuperclass.getTypeName()!="java.lang.Object"){
            ParameterizedType pType = (ParameterizedType)genericSuperclass;
            Type[] arguments = pType.getActualTypeArguments();
            Class modelClass = (Class)arguments[0];
            request.setAttribute("ModelClass",modelClass);
        }
        return true;
    }

}
