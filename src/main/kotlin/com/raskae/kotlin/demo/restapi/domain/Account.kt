package com.raskae.kotlin.demo.restapi.domain

import com.raskae.kotlin.demo.restapi.Utils.UpdateField

/**
 * This class is always created from stored entities, so their ID will never be null.
 * Since date of birth is optional, the age property can be null
 */
data class AccountDTO(val id: Long, val username: String, val password: String, val email: String)

/**
 * IDs are assigned by the persistence mechanism, so they have no place in a creation request
 */
data class CreateAccountDTO(val username: String, val password: String, val email: String)

/**
 * In an update request we need the ID to look up the stored entity
 * All UpdateField properties are mandatory, but they have default values for convenience
 */
data class UpdateAccountDTO(val id: Long,
                            val username: UpdateField<String> = UpdateField.ignore(),
                            val password: UpdateField<String> = UpdateField.ignore(),
                            val email: UpdateField<String> = UpdateField.ignore())