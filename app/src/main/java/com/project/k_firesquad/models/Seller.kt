package com.project.k_firesquad.models

import android.os.Parcel
import android.os.Parcelable

data class Seller(val image:Int ,val name:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Seller> {
        override fun createFromParcel(parcel: Parcel): Seller {
            return Seller(parcel)
        }

        override fun newArray(size: Int): Array<Seller?> {
            return arrayOfNulls(size)
        }
    }
}
