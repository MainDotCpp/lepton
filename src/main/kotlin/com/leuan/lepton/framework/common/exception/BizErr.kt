package com.leuan.lepton.framework.common.exception

import com.leuan.lepton.framework.common.constants.BizErrEnum

class BizErr : RuntimeException {
    var code: Int
    override var message: String

    constructor(code: Int, message: String) : super(message) {
        this.code = code
        this.message = message
    }

    constructor(message: String) : super(message) {
        this.code = 500
        this.message = message
    }

    constructor(err: BizErrEnum) {
        this.code = err.code
        this.message = err.message
    }

}
