package com.raskae.kotlin.demo.restapi.repository

import com.raskae.kotlin.demo.restapi.domain.AccountDTO
import com.raskae.kotlin.demo.restapi.domain.AccountEntity
import com.raskae.kotlin.demo.restapi.domain.CreateAccountDTO
import com.raskae.kotlin.demo.restapi.domain.UpdateAccountDTO
import org.springframework.stereotype.Service

/**
 * Simple in-memory storage
 */
@Service
class AccountCacheRepository : AccountRepository {

    private var sequence = 1L
    private val cache = mutableMapOf<Long, AccountEntity>()

    override fun getAll(): List<AccountDTO> = cache.values.map { createDTO(it) }

    override fun getById(id: Long): AccountDTO = createDTO(cache[id] ?: throw IllegalArgumentException("No customer with id $id"))

    override fun save(create: CreateAccountDTO): AccountDTO {
        val entity = AccountEntity(username = create.username, password = create.password, email = create.email)
        //in a JPA setting, a unique would be assigned after construction, so the ID is initially null and must be a Long?
        val id = nextId()
        entity.id = id
        cache.put(id, entity)
        return createDTO(entity)
    }

    override fun save(customerUpdate: UpdateAccountDTO): AccountDTO {
        val entity: AccountEntity = cache[customerUpdate.id] ?: throw IllegalArgumentException("No customer with id ${customerUpdate.id}")
        if (!customerUpdate.username.ignored)
            entity.username = customerUpdate.username.get()

        if (!customerUpdate.password.ignored)
            entity.password = customerUpdate.password.get()

        if (!customerUpdate.email.ignored)
            entity.email = customerUpdate.email.get()

        cache.put(customerUpdate.id, entity)
        return createDTO(entity)
    }

    private fun createDTO(entity: AccountEntity): AccountDTO {

        return AccountDTO(id = entity.id!!, username = entity.username, password = entity.password, email = entity.email)
    }

    override fun removeById(id: Long): AccountDTO {
        val toDelete = getById(id)
        cache.remove(id)
        return toDelete
    }

    private fun nextId() = ++sequence

}