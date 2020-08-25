package com.rebrotesolution.smzr_android.models.models_api

data class Profile(
    var id: Int?,
    var firstname: String,
    var lastnamep: String,
    var lastnamem: String,
    var dni: String,
    var gender: String,
    var birthdate: String,
    var email: String
) {
    override fun toString(): String {
        return "id: " + id + "\nfirstname: " + firstname + "\nlastnamep: " + lastnamep + "\nlastnamem: " + lastnamem + "\ndni: " + dni + "\ngender: " + gender + "\nbirthday: " + birthdate + "\nemail: " + email

    }
}