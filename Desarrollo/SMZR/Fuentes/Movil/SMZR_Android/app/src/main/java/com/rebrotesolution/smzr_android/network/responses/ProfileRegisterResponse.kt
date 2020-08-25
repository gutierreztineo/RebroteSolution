package com.rebrotesolution.smzr_android.network.responses

import com.rebrotesolution.smzr_android.models.models_api.Profile

data class ProfileRegisterResponse(
    var data: Profile,
    var error: String?
)