package com.leuan.lepton.framework.common.constants

enum class BizErrEnum(val code: Int, val message: String) {
    // --------------------- 业务异常 ---------------------
    ACCESS_DENIED(403, "无权限访问"),
    RESOURCE_NOT_FOUND(404, "资源不存在"),

    // --------------------- 系统模块 ---------------------
    BIZ(500, "业务异常"),
    NOT_LOGIN(501, "未登录"),
    LOGIN_EXPIRED(502, "登录过期"),
    PHONE_OR_PASSWORD_ERROR(503, "用户名或密码不正确 "),
    INVALID_PARAM(504, "参数错误"),
    TENANT_NOT_FOUND(1001, "租户不存在"),
    USER_NOT_FOUND(1002, "用户不存在"),
    MENU_NOT_FOUND(1003, "菜单不存在"),
    ROLE_NOT_FOUND(1004, "系统角色不存在 "),
    SYS_PACKAGE_NOT_FOUND(1005, "系统套餐不存在"),
    USER_PHONE_EXIST(1006, "手机号已存在"),
    DICT_NOT_FOUND(1007, "字典不存在"),
    CONFIG_NOT_FOUND(1008, "配置不存在"),

    // --------------------- 客资模块 ---------------------
    CUSTOMER_NOT_FOUND(3002, "客资不存在"),
    CHANNEL_NOT_FOUND(3003, "渠道不存在"),
    BRAND_NOT_FOUND(3004, "品牌不存在"),
    // --------------------- 订单模块 ---------------------
    ORDER_NOT_FOUND(4001, "订单不存在"),
    GOODS_NOT_FOUND(4002, "商品不存在"),

    // --------------------- 收集器模块 ---------------------
    XHS_NOTE_COMMENT_NOT_FOUND(5001, "小红书评论不存在"),
}
