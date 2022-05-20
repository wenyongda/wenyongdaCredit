package com.wyd.wenyongdaCredit.commons.util;

public enum ResultEnum {
    SUCCESS(200, "请求成功"),
    PASSWORD_ERROR(3390, "密码错误"),
    USERNAME_ERROR_OR_UNKNOWN(3391, "用户名错误或不存在"),
    USERNAME_OR_NICKNAME_IS_EMPTY(3392,"用户名或昵称为空"),
    USER_ACCOUNT_IS_DISABLED(3393,"用户被禁用请联系系统管理员"),
    VERIFY_CODE_ERROR(3394,"验证码错误，请核对"),
    USER_ID_IS_EMPTY(3395,"用户id为空，请检查"),
    MENU_ERROR_OR_UNKNOWN(3490,"菜单数据不存在或错误"),
    DICT_ERROR_OR_UNKNOWN(3590,"字典数据不存在或错误"),
    DICT_ENTRY_ERROR_OR_UNKNOWN(3690,"字典入口数据不存在或错误"),
    ROLE_ERROR_OR_UNKNOWN(3790,"角色数据不存在或错误"),
    PAGE_NUMBER_OR_PAGE_SIZE_IS_EMPTY(99911,"分页数据为空"),
    UNKNOWN_ERROR(99999, "未知错误")
    ;
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
