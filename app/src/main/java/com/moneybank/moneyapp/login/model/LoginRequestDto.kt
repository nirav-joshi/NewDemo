package com.example.minimoneybox.login.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("Email")
    var email:String?=null,
    @SerializedName("Password")
    var password:String?=null,
    @SerializedName("Idfa")
    var idfa:String?="ANYTHING"
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(idfa)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginRequestDto> {
        override fun createFromParcel(parcel: Parcel): LoginRequestDto {
            return LoginRequestDto(parcel)
        }

        override fun newArray(size: Int): Array<LoginRequestDto?> {
            return arrayOfNulls(size)
        }
    }
}