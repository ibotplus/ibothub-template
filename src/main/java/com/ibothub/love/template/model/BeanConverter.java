package com.ibothub.love.template.model;

import com.ibothub.love.template.model.dto.RolePermDTO;
import com.ibothub.love.template.model.dto.UserRoleDTO;
import com.ibothub.love.template.model.entity.*;
import com.ibothub.love.template.model.vo.BaseVO;
import com.ibothub.love.template.model.vo.req.*;
import com.ibothub.love.template.model.vo.resp.*;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.reflect.InvocationTargetException;
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


    @Mapping(target = "roleList", ignore = true)
    User forward(UserReq reqVO);

    @Mapping(target = "roleList", ignore = true)
    @Mapping(target = "deptList", ignore = true)
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "modifyTime", target = "modifyTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserResp backward(User entity);

    @Mapping(target = "roleList", ignore = true)
    @Mapping(target = "deptList", ignore = true)
    UserResp backward(UserRoleDTO dto);

    List<UserResp> backwardUserDTO(List<UserRoleDTO> dto);
    List<PermissionResp> backwardPermDTO(List<RolePermDTO> rolePermList);


    Role forward(RoleReq vo);
    RoleResp backward(Role entity);
    Dept forward(DeptReq vo);
    DeptResp backward(Dept entity);

    @Mapping(source = "path", target = "uri")
    Permission forward(PermissionReq vo);
    @Mapping(source = "uri", target = "path")
    PermissionResp backward(Permission entity);


    Conf forward(ConfReq vo);
    ConfResp backward(Conf entity);

    @SneakyThrows
    default <V extends BaseVO, T extends BaseEntity> V backward(T t) {
        String key = t.getClass().getSimpleName();
        Method method = CACHED_METHOD.get(key);
        if (method == null) {
            synchronized (CACHED_METHOD) {
                try {
                    method = this.getClass().getDeclaredMethod("backward", t.getClass());
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                CACHED_METHOD.put(key, method);
            }
        }

        try {
            return (V) method.invoke(this, t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    default <V extends BaseVO, T extends BaseEntity> T forward(V v) {
        String key = v.getClass().getSimpleName();
        Method method = CACHED_METHOD.get(key);
        if (method == null) {
            synchronized (CACHED_METHOD) {
                try {
                    method = this.getClass().getDeclaredMethod("forward", v.getClass());
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                CACHED_METHOD.put(key, method);
            }
        }

        try {
            return (T) method.invoke(this, v);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
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
