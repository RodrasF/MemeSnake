package workspace.android.memesnake.repository.dataAccess.memesApi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import workspace.android.memesnake.util.RequestStatus
import workspace.android.memesnake.repository.dataAccess.ApiErrorResponse
import workspace.android.memesnake.repository.dataAccess.ApiResponse
import workspace.android.memesnake.repository.dataAccess.ApiSuccessResponse
import workspace.android.memesnake.repository.dataAccess.HttpRequests
import workspace.android.memesnake.repository.domain.*
import java.nio.charset.Charset

object MemeAPIClient : MemeAPI {

    private val TAG = MemeAPIClient::class.java.simpleName

    private val mapper : ObjectMapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    override fun getMemes(count: Int): LiveData<ApiResponse<List<Meme>>> {
        val memes = MutableLiveData<ApiResponse<List<Meme>>>()

        HttpRequests.GetFutureAsyncTask { response, status ->

            if (status == RequestStatus.SUCCESS) {
                val dto = mapDto<MemesDto>(response)
                if (dto != null) memes.postValue(
                    ApiSuccessResponse(
                        dto.toMemesList()
                    )
                )
                else memes.postValue(
                    ApiErrorResponse(
                        emptyList()
                    )
                )
            } else memes.postValue(
                ApiErrorResponse(
                    emptyList()
                )
            )
        }.execute(count.toString())

        return memes
    }


    private inline fun <reified T> mapDto(response : String): T?{
        val utf8String = String(response.toByteArray(Charset.forName("ISO-8859-1")), Charsets.UTF_8)
        return mapper.readValue<T>(utf8String)
    }

}