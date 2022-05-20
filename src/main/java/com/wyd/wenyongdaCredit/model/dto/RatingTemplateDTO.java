package com.wyd.wenyongdaCredit.model.dto;

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
public class RatingTemplateDTO implements Serializable {

    private static final long serialVersionUID = -4163556421253949551L;

    private Long id;

    private String templateName;

    private Integer templateType;

    private String templateTermType;

    private Integer financialAccounting;

    private Integer nonFinancialAccounting;

    private Integer financialNonFinancialScoreGap;

    private Integer isEnabled;
}
