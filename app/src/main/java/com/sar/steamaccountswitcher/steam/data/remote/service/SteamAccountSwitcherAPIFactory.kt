package com.sar.steamaccountswitcher.steam.data.remote.service

import com.sar.steamaccountswitcher.steam.data.local.storage.WebAPIAddressStorage
import com.sar.steamaccountswitcher.steam.data.local.storage.WebAPIAllowSelfSignedCertsStorage
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

class SteamAccountSwitcherAPIFactory(
    private val apiAddressStorage: WebAPIAddressStorage,
    private val allowSelfSignedCertsStorage: WebAPIAllowSelfSignedCertsStorage
) {

    fun getClient(): SteamAccountSwitcherAPI {
        return createClient()
    }

    private fun createClient(): SteamAccountSwitcherAPI {
        val baseUrl = apiAddressStorage.get(defaultValue = "")
        val allowSelfSigned = allowSelfSignedCertsStorage.get(defaultValue = false)

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())

        if (allowSelfSigned) {
            builder.client(getUnsafeOkHttpClient())
        }

        return builder.build()
            .create(SteamAccountSwitcherAPI::class.java)
    }

}

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
