package workspace.android.memesnake.repository.dataAccess.memesApi

import androidx.lifecycle.LiveData
import workspace.android.memesnake.repository.dataAccess.ApiResponse
import workspace.android.memesnake.repository.domain.Meme


const val URL_BASE = "https://meme-api.herokuapp.com/gimme/"

interface MemeAPI{

    fun getMemes(count: Int): LiveData<ApiResponse<List<Meme>>>
}