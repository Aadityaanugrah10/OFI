package com.projectbangkit.originfoodindonesia.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ModelTempat (

    var namaBuku: String? = null,
    var hargaBuku: String? = null,
    var gambarBuku: Int = 0
) : Parcelable