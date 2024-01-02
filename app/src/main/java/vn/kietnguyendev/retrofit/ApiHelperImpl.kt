package vn.kietnguyendev.retrofit

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getUsers() = apiService.getUsers()
    override suspend fun getMoreUsers() = apiService.getMoreUsers()
    override suspend fun getUsersWihError() = apiService.getUsersWithError()
}