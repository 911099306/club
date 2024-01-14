package com.club.subject.domain.handler.subject;

import com.club.subject.common.enums.SubjectInfoTypeEnum;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/13
 **/
@Component
@RequiredArgsConstructor
public class SubjectTypeHandlerFactory implements InitializingBean {
    private final List<SubjectTypeHandler> subjectTypeHandlerList;


    private final Map<SubjectInfoTypeEnum, SubjectTypeHandler> handlerMap = new HashMap<>();

    public SubjectTypeHandler getHandler(int subjectType) {
        SubjectInfoTypeEnum typeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        return handlerMap.get(typeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlerList) {
            handlerMap.put(subjectTypeHandler.getHandlerType(), subjectTypeHandler);
        }
    }
}
