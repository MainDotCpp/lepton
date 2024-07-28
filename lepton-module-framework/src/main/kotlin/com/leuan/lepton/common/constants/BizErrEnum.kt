package com.leuan.lepton.common.constants

enum class BizErrEnum(val code: Int, val message: String) {
    BIZ(500, "业务异常"),
    NOT_LOGIN(501, "未登录"),
    LOGIN_EXPIRED(502, "登录过期"),
    PHONE_OR_PASSWORD_ERROR(502, "用户名或密码不正确 "),
    TENANT_NOT_FOUND(1001, "租户不存在"),
    USER_NOT_FOUND(1002, "用户不存在"),
    MENU_NOT_FOUND(1003, "菜单不存在"),
    ROLE_NOT_FOUND(1004, "系统角色不存在 "),

    // 客资模块
    CUSTOMER_NOT_FOUND(3002, "客资不存在"),
}
