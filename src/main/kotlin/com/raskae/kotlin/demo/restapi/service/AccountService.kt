package com.raskae.kotlin.demo.restapi.service

import com.raskae.kotlin.demo.restapi.domain.AccountDTO
import com.raskae.kotlin.demo.restapi.domain.CreateAccountDTO
import com.raskae.kotlin.demo.restapi.domain.UpdateAccountDTO
import com.raskae.kotlin.demo.restapi.repository.AccountCacheRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RequestMapping("/accounts")
@RestController
class AccountService @Autowired constructor(private val cacheRepository: AccountCacheRepository) {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun getAllCustomers(): List<AccountDTO> {
        return cacheRepository.getAll()
    }

    @RequestMapping(value = "{id}", method = arrayOf(RequestMethod.GET))
    fun getCustomerById(@PathVariable("id") id: Long): AccountDTO {
        return cacheRepository.getById(id)
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun createCustomer(@RequestBody createRequest: CreateAccountDTO): AccountDTO {
        return cacheRepository.save(createRequest)
    }

    @RequestMapping(value = "{id}", method = arrayOf(RequestMethod.DELETE))
    fun deleteCustomerById(@PathVariable("id") id: Long): AccountDTO {
        return cacheRepository.removeById(id)
    }

    @RequestMapping(method = arrayOf(RequestMethod.PUT))
    fun updateCustomer(@RequestBody updateRequest: UpdateAccountDTO): AccountDTO {
        return cacheRepository.save(updateRequest)
    }
}