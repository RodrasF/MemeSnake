package workspace.android.memesnake.repository.dataAccess

import android.os.AsyncTask
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import workspace.android.memesnake.MemeSnakeApp
import workspace.android.memesnake.util.RequestStatus
import workspace.android.memesnake.repository.dataAccess.memesApi.URL_BASE
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


object HttpRequests{

    val TAG = HttpRequests::class.java.simpleName

    private lateinit var reqQueue :RequestQueue

    fun init(application : MemeSnakeApp) {
        reqQueue = Volley.newRequestQueue(application)
    }

    fun get(url: String, headers: Map<String, String> = mapOf() , cb: (String, RequestStatus) -> Unit) {

        val req = object : StringRequest(
                Method.GET,
                URL_BASE +url,
                Response.Listener<String>{
                    response ->
                    Log.d(TAG, "/GET request OK! Response: $response")
                    cb(response, RequestStatus.SUCCESS)
                },
                Response.ErrorListener{
                    error ->
                    Log.e(TAG, "Request fail! Error: ${error.message}")
                    cb(error.message?:"Unknown error!", RequestStatus.ERROR)
                })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>  = headers


        }

        reqQueue.add(req)

    }



    fun getFuture(url: String, headers: Map<String, String> = mapOf(),cb: (String, RequestStatus) -> Unit){

        val future = RequestFuture.newFuture<String>()

        val request = object : StringRequest(
                Method.GET,
                URL_BASE +url,
                future,
                future)
        {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>  = headers
        }

        reqQueue.add(request)

        try {
            val response = future.get(10, TimeUnit.SECONDS)
            Log.i(TAG,"RESPONSE = $response")
            cb(response, RequestStatus.SUCCESS)
        } catch (e: InterruptedException) {
            return cb("", RequestStatus.ERROR)
        } catch (e: ExecutionException) {
            return cb("", RequestStatus.ERROR)
        } catch (e: TimeoutException) {
            return cb("", RequestStatus.ERROR)
        }
    }

    class GetFutureAsyncTask(val headers : MutableMap<String, String> = mapOf<String, String>().toMutableMap(), val cb: (String, RequestStatus)-> Unit): AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg params: String): Void?{
            getFuture(
                params[0],
                headers
            ) { response, status ->
                cb(response, status)
            }
            return null
        }
    }

    class GetAsyncTask(val headers : MutableMap<String, String> = mapOf<String, String>().toMutableMap(), val cb: (String, RequestStatus)-> Unit): AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg params: String): Void?{
            get(
                params[0],
                headers
            ) { response, status ->
                cb(response, status)
            }
            return null
        }
    }
}