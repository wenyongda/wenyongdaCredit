package com.wyd.wenyongdaCredit.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class UserAccountVO implements Serializable {
    private static final long serialVersionUID = -5299639519247904930L;

    private Long id;

    private String username;

    private String nickName;

    private String avatar;

    private String password;

    private Integer isEnabled;

    private Long roleId;
}
