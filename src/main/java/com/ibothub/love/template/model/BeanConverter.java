package com.ibothub.love.template.model;

import com.ibothub.love.template.model.entity.*;
import com.ibothub.love.template.model.vo.BaseVO;
import com.ibothub.love.template.model.vo.req.DeptReq;
import com.ibothub.love.template.model.vo.req.PermissionReq;
import com.ibothub.love.template.model.vo.req.RoleReq;
import com.ibothub.love.template.model.vo.req.UserReq;
import com.ibothub.love.template.model.vo.resp.DeptResp;
import com.ibothub.love.template.model.vo.resp.PermissionResp;
import com.ibothub.love.template.model.vo.resp.RoleResp;
import com.ibothub.love.template.model.vo.resp.UserResp;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <pre>
 * easy to convert A to B
 *
 * forward: request viewObject to data entity
 * backward: data entity to response viewObject
 * </pre>
 *
 * @author yogurt_lei
 * @date 2019-08-06 15:15
 */
@Mapper(componentModel = "spring")
public interface BeanConverter {

    /**
     * cache the method to invoked
     */
    Map<String, Method> CACHED_METHOD = new ConcurrentHashMap<>(BeanConverter.class.getDeclaredMethods().length);


    User forward(UserReq reqVO);

    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "modifyTime", target = "modifyTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserResp backward(User entity);

    Role forward(RoleReq vo);
    RoleResp backward(Role entity);
    Dept forward(DeptReq vo);
    DeptResp backward(Dept entity);

    @Mapping(source = "path", target = "uri")
    Permission forward(PermissionReq vo);
    @Mapping(source = "uri", target = "path")
    PermissionResp backward(Permission entity);



    @SneakyThrows
    default <V extends BaseVO, T extends BaseEntity> V backward(T t) {
        String key = t.getClass().getSimpleName();
        Method method = CACHED_METHOD.get(key);
        if (method == null) {
            synchronized (CACHED_METHOD) {
                method = this.getClass().getDeclaredMethod("backward", t.getClass());
                CACHED_METHOD.put(key, method);
            }
        }

        return (V) method.invoke(this, t);
    }

    @SneakyThrows
    default <V extends BaseVO, T extends BaseEntity> T forward(V v) {
        String key = v.getClass().getSimpleName();
        Method method = CACHED_METHOD.get(key);
        if (method == null) {
            synchronized (CACHED_METHOD) {
                method = this.getClass().getDeclaredMethod("forward", v.getClass());
                CACHED_METHOD.put(key, method);
            }
        }

        return (T) method.invoke(this, v);
    }

    default <V extends BaseVO, T extends BaseEntity> List<T> forward(List<V> voList) {
        if (voList==null || voList.size()==0) return null;
        return voList.stream().map(v -> (T) this.forward(v)).collect(Collectors.toList());
    }

    default <V extends BaseVO, T extends BaseEntity> List<V> backward(List<T> entityList) {
        if (entityList==null || entityList.size()==0) return null;
        return entityList.stream().map(t -> (V) this.backward(t)).collect(Collectors.toList());
    }

}
