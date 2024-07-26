package com.leuan.lepton.constants

enum class BizErrEnum(val code: Int, val message: String) {
    BIZ(500, "业务异常"),
    TENANT_NOT_FOUND(1001, "租户不存在"),
    USER_NOT_FOUND(1002, "用户不存在"),
    MENU_NOT_FOUND(1003, "菜单不存在"), ;
}
