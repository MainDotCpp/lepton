package com.leuan.lepton.customer.controller.customer

import com.leuan.lepton.customer.dal.customer.CustomerRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController {

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Resource
    private lateinit var customerRepository: CustomerRepository


}