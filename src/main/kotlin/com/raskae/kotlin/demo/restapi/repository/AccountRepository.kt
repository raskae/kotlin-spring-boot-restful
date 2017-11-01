package com.raskae.kotlin.demo.restapi.repository

import com.raskae.kotlin.demo.restapi.domain.AccountDTO
import com.raskae.kotlin.demo.restapi.domain.CreateAccountDTO
import com.raskae.kotlin.demo.restapi.domain.UpdateAccountDTO

interface AccountRepository{

    fun getAll(): List<AccountDTO>
    fun getById(id: Long): AccountDTO
    fun save(create: CreateAccountDTO): AccountDTO
    fun save(customerUpdate: UpdateAccountDTO): AccountDTO
    fun removeById(id: Long): AccountDTO
}