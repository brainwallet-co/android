package com.brainwallet.tools.manager

import com.unstoppabledomains.exceptions.ns.NamingServiceException
import com.unstoppabledomains.resolution.DomainResolution
import com.unstoppabledomains.resolution.Resolution
import timber.log.Timber

data class ResolutionResult(val error: NamingServiceException?, val address: String?)

class UDResolution {
    private val tool: DomainResolution =
        Resolution.builder()
//            .infura(NamingServiceType.CNS, Network.MAINNET, BuildConfig.INFURA_KEY)
            .build()

    fun resolve(domain: String): ResolutionResult {
        return try {
            ResolutionResult(null, tool.getAddress(domain, "LTC"))
        } catch (err: NamingServiceException) {
            Timber.e(err)
            ResolutionResult(err, null)
        }
    }
}

// https://github.com/unstoppabledomains/resolution-java/blob/master/samples.md
// data class ResolutionResult(val error: NamingServiceException?, val address: String?) {}
//
// class AsyncResolution : AsyncTask<String, String, ResolutionResult>() {
//    private val tool: DomainResolution = Resolution()
//
//    override fun doInBackground(vararg params: String?): ResolutionResult {
//        val domain =  params[0]
//        val currency = params[1]
//        return try {
//            val address = this.tool.addr(domain, currency)
//            ResolutionResult(null, address)
//        } catch(err: NamingServiceException) {
//            err.printStackTrace();
//            ResolutionResult(err, null)
//        }
//    }
// }
