package com.raskae.kotlin.demo.restapi.domain

data class ResponseErrorModel(val statusCode: String, val statusErrorMessage: String, val errorCode: String, val errorMessage: String)