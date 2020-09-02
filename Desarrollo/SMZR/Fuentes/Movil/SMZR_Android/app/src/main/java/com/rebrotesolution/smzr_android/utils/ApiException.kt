package com.rebrotesolution.smzr_android.utils

import java.io.IOException

class ApiException( message: String ): IOException(message)
class NoInternetException(message: String ) : IOException(message)
class UserAlreadyExistsException ( message: String) : IOException(message)
class ApiTimeOutException(message: String) : IOException(message)
