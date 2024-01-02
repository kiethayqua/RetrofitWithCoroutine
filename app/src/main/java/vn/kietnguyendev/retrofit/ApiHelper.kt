package vn.kietnguyendev.retrofit

interface ApiHelper {
    suspend fun getUsers(): List<ApiUser>
    suspend fun getMoreUsers(): List<ApiUser>
    suspend fun getUsersWihError(): List<ApiUser>
}