package com.example.githubuserapp

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class User() : Parcelable{
    var username: String = ""
    var name: String = ""
    var avatar: String = ""
    var company: String = ""
    var location: String = ""
    var repository: Int = 0
    var follower: Int = 0
    var following: Int = 0

    constructor(parcel: Parcel) : this() {
        username = parcel.readString() ?: ""
        name = parcel.readString() ?: ""
        avatar = parcel.readString() ?: ""
        company = parcel.readString() ?: ""
        location = parcel.readString() ?: ""
        repository = parcel.readInt()
        follower = parcel.readInt()
        following = parcel.readInt()
    }

    constructor(_json: JSONObject): this(){
        username = _json.optString("username")
        name = _json.optString("name")
        val imgFull = _json.optString("avatar")
        val imgArr = imgFull.split("/")
        avatar = if(imgArr.size > 1) imgArr[1] else ""
        company = _json.optString("company")
        location = _json.optString("location")
        repository = _json.optInt("repository")
        follower = _json.optInt("follower")
        following = _json.optInt("following")
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(username)
        dest?.writeString(name)
        dest?.writeString(avatar)
        dest?.writeString(company)
        dest?.writeString(location)
        dest?.writeInt(repository)
        dest?.writeInt(follower)
        dest?.writeInt(following)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
