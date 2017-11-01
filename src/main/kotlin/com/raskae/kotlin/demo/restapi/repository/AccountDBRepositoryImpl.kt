package com.raskae.kotlin.demo.restapi.repository

import org.springframework.data.repository.CrudRepository
import com.raskae.kotlin.demo.restapi.domain.AccountEntity

interface AccountDBRepositoryImpl : CrudRepository<AccountEntity, Long>