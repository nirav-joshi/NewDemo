package com.example.minimoneybox.login.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class LoginResponseDto() :Parcelable{
    @SerializedName("Session")
    var session:Session?=null

    constructor(parcel: Parcel) : this() {
        session = parcel.readParcelable(Session::class.java.classLoader)
    }

    class Session() :Parcelable {

        @SerializedName("BearerToken")
        var bearerToken: String? = null
        @SerializedName("ExternalSessionId")
        var externalSessionId: String? = null
        @SerializedName("SessionExternalId")
        var sessionExternalId: String? = null
        @SerializedName("ExpiryInSeconds")
        var expiryInSeconds: Int = 0

        constructor(parcel: Parcel) : this() {
            bearerToken = parcel.readString()
            externalSessionId = parcel.readString()
            sessionExternalId = parcel.readString()
            expiryInSeconds = parcel.readInt()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(bearerToken)
            parcel.writeString(externalSessionId)
            parcel.writeString(sessionExternalId)
            parcel.writeInt(expiryInSeconds)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Session> {
            override fun createFromParcel(parcel: Parcel): Session {
                return Session(parcel)
            }

            override fun newArray(size: Int): Array<Session?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(session, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginResponseDto> {
        override fun createFromParcel(parcel: Parcel): LoginResponseDto {
            return LoginResponseDto(parcel)
        }

        override fun newArray(size: Int): Array<LoginResponseDto?> {
            return arrayOfNulls(size)
        }
    }
}
