package com.example.coroutineflow.util


import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import java.lang.NumberFormatException
import javax.inject.Inject

@ActivityScoped
class StringToIntAdapter @Inject constructor() {
    @ToJson
    fun toJson(@StringToInt value: Int): String = value.toString()

    @FromJson
    @StringToInt
    fun fromJson(value: String): Int = value.toInt()
}

@ActivityScoped
class StringToNullIntAdapter @Inject constructor() {
    @ToJson
    fun toJson(@StringToNullInt value: Int?): String = try {
        value.toString()
    } catch (e: Exception) {
        ""
    }

    @FromJson
    @StringToNullInt
    fun fromJson(value: String): Int? = try {
        value.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}



@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class StringToInt
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class IntToCommodityType

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class StringToNullInt
