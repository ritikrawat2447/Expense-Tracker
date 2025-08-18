package eu.tutorials.mywishlistapp.data

import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Callback
import okhttp3.Call
import org.json.JSONObject
import java.io.IOException

val client = OkHttpClient()
fun apiGateway(){
    val jsonBody = JSONObject()
    jsonBody.put("user_id","1234")
    jsonBody.put("timestamp", "2025-08-14T17:12:45Z");
    jsonBody.put("amount", 1299);
    jsonBody.put("category", "Food");
    jsonBody.put("payment_method", "card");
    jsonBody.put("merchant_name", "Cafe Aroma");
    jsonBody.put("tag","hangout");
    jsonBody.put("source", "manual");


    val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())
    val request = Request.Builder()
        .url("https://q28wyzai15.execute-api.us-east-1.amazonaws.com/prod/track")
        .post(requestBody)
        .build()
    Log.d("API_RESPONSE", "Reached here")
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("API_RESPONSE", e.toString())
        }
        override fun onResponse(call: Call, response: Response) {
            Log.d("API_RESPONSE", response.body?.string().orEmpty())
        }
    })
}