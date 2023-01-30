package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MarsApiService {
    @GET("photos")
   suspend fun getPhotos(): List<MarsPhotos>
}

// O padrão Singleton garante que uma instância de um objeto seja criada e tenha um ponto global
// de acesso a esse objeto. A inicialização da declaração do objeto é segura para linhas de execução
// e é feita no primeiro acesso.
object MarsApi {

    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

}


