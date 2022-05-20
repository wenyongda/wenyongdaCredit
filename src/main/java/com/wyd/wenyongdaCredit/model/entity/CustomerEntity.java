package com.wyd.wenyongdaCredit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
@TableName("t_customer")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = -4163556421253949551L;

    @Id
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    private String customerName;

    private Integer idType;

    private String idNumber;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)// 出参格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")// 入参格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Integer isDeleted;


    @Version
    @TableField(value = "version")
    private Integer version;
}
