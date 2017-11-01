package com.raskae.kotlin.demo.restapi

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import com.raskae.kotlin.demo.restapi.Utils.UpdateField
import com.raskae.kotlin.demo.restapi.domain.CreateAccountDTO
import com.raskae.kotlin.demo.restapi.domain.UpdateAccountDTO
import com.raskae.kotlin.demo.restapi.service.AccountService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Application::class), webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IntegrationTest {

    @Autowired
    private lateinit var endpoint: AccountService

    @Test
    fun testCreation() {
        val jane = endpoint.createCustomer(CreateAccountDTO(username = "Jane", password = "123", email = "ptenni"))
        assertThat(endpoint.getCustomerById(jane.id).id).isEqualTo(jane.id)
    }

    @Test
    fun testEmail() {
        val john = endpoint.createCustomer(CreateAccountDTO(username = "John", password = "123", email = "ptenni"))
        println(john)
        assertThat(endpoint.getCustomerById(john.id).email).isEqualToIgnoringCase("ptenni")
    }

    @Test
    fun testUpdateMandatoryField() {
        val john = endpoint.createCustomer(CreateAccountDTO(username = "John", password = "123", email = "ptenni"))
        endpoint.updateCustomer(UpdateAccountDTO(id = john.id, username = UpdateField.of("Jan")))
        val updated = endpoint.getCustomerById(john.id)
        assertThat(updated.username).isEqualTo("Jan")
    }

    @Test
    fun testUpdateMandatoryFieldToNullThrows() {
        val john = endpoint.createCustomer(CreateAccountDTO(username = "John", password = "doe", email = "jdoe"))
        val updateRequest = UpdateAccountDTO(id = john.id, email = UpdateField.setNull())
        assertThatThrownBy { endpoint.updateCustomer(updateRequest) }.hasMessage("value cannot be null.")
    }

    @Test
    fun testDeleteEntity() {
        val alberto = endpoint.createCustomer(CreateAccountDTO(username = "Alberto", password = "999", email = "atenni"))
        endpoint.deleteCustomerById(alberto.id)
        assertThatThrownBy { endpoint.getCustomerById(alberto.id) }.hasMessage("No customer with id ${alberto.id}")
    }

}
