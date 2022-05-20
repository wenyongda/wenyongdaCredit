package com.wyd.wenyongdaCredit.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class CustomerDTO implements Serializable {

    private String id;

    private String customerName;

    private Integer idType;

    private String idNumber;

}
