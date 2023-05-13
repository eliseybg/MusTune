package com.breaktime.mustune.common.exceptions

import java.lang.Exception

class ServiceException(val code: Int, override val message: String?) : Exception(message)