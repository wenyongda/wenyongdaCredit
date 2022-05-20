package com.wyd.wenyongdaCredit.commons.handler;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    private static final Logger log = LoggerFactory.getLogger(MybatisPlusMetaObjectHandler.class);

    //使用mp实现添加操作,这个方法会执行,metaObject元数据(表中的名字,表中的字段)
    @Override
    public void insertFill(MetaObject metaObject) {
        //根据名称设置属性值
        this.setFieldValByName("createTime", DateTime.now(), metaObject);
        this.setFieldValByName("isEnabled",1,metaObject);
        this.setFieldValByName("isDeleted",0,metaObject);
        this.setFieldValByName("version", 0,metaObject);
        //this.fillStrategy(metaObject, "version", 1);
    }

    //使用mp实现修改操作,这个方法会执行
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime", DateTime.now(), metaObject);
    }
}
