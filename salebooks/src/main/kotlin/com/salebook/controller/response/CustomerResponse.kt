package com.salebook.controller.response

import com.salebook.enums.CustomerStatus

data class CustomerResponse(
    var id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus
)