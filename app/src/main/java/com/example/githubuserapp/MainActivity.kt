package com.example.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    companion object{
        var PACKAGE_NAME = ""
    }

    private lateinit var rvMain: RecyclerView
    private var userList: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.main_rv)
        rvMain.setHasFixedSize(true)

        PACKAGE_NAME = applicationContext.packageName

        val jsonObject = JSONObject(jsonFromAssets())
        val jsonArray = jsonObject.getJSONArray("users")
        for (i in 0 until jsonArray.length()) {
            val userJson = jsonArray.getJSONObject(i)
            val user = User(userJson)
            userList.add(user)
        }

        showRecyclerList()
    }

    private fun jsonFromAssets(): String {
        val json: String
        try {
            val inputStream = assets.open("githubuser.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        }catch (e: Exception){
            e.printStackTrace()
            return "{\"users\":[]}"
        }
        return json
    }

    private fun showRecyclerList() {
        rvMain.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(userList)
        rvMain.adapter = listUserAdapter

        listUserAdapter.setClickCallback(
            object : OnClickItemCallback {
                override fun onClickItem(user: User) {
                    onItemClick(user)
                }
            }
        )
    }

    private fun onItemClick(user: User) {
        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
        detailIntent.putExtra(DetailActivity.EXTRA_USER, user)
        startActivity(detailIntent)
    }
}