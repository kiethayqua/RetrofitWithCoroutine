package vn.kietnguyendev.retrofit

import retrofit2.http.GET

interface ApiService { // NOTE: require for Retrofit
    @GET("users")
    suspend fun getUsers(): List<ApiUser>

    @GET("more-users")
    suspend fun getMoreUsers(): List<ApiUser>

    @GET("error")
    suspend fun getUsersWithError(): List<ApiUser>
}