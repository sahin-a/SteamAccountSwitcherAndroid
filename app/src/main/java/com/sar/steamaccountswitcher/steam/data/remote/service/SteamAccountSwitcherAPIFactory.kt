package com.sar.steamaccountswitcher.steam.data.remote.service

import com.sar.steamaccountswitcher.steam.data.local.storage.WebAPIAddressStorage
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

// TODO: garbage solution, refactor this garbage
class SteamAccountSwitcherAPIFactory(private val apiAddressStorage: WebAPIAddressStorage) {
    private var _previousAddress: String = ""
    private var _instance: SteamAccountSwitcherAPI? = null

    fun getClient(): SteamAccountSwitcherAPI {
        val baseUrl = apiAddressStorage.get(defaultValue = "")

        if (_instance == null) {
            _instance = createClient(baseUrl)
        } else if (_previousAddress != baseUrl) {
            _instance = createClient(baseUrl)
        }

        return _instance!!
    }

    private fun createClient(baseUrl: String): SteamAccountSwitcherAPI {
        val gsonConverterFactory = GsonConverterFactory.create()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(SteamAccountSwitcherAPI::class.java)
    }


    // TODO: unsafe replace this l8r | https://stackoverflow.com/a/63399149 | temporary solution for testing purposes
    private fun getUnsafeOkHttpClient(): OkHttpClient {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                        return arrayOf()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val trustManagerFactory: TrustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            val trustManagers: Array<TrustManager> =
                trustManagerFactory.trustManagers
            check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                "Unexpected default trust managers:" + trustManagers.contentToString()
            }

            val trustManager =
                trustManagers[0] as X509TrustManager


            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustManager)
            builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
