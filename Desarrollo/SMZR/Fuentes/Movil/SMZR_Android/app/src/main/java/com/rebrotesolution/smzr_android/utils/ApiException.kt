package com.rebrotesolution.smzr_android.utils

import java.io.IOException

class ApiException( message: String ): IOException(message)
class NoInternetException(message: String ) : IOException(message)
class UserAlreadyExistsException ( errors: UserError) : IOException(errors.username.toString())
class ApiTimeOutException(message: String) : IOException(message)

data class UserError( var username: ArrayList<String>)