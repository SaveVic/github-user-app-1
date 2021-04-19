package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity(), View.OnClickListener{
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var detailBack: ImageButton
    private lateinit var detailName: TextView
    private lateinit var detailImg: CircleImageView
    private lateinit var detailUsername: TextView
    private lateinit var detailCompany: TextView
    private lateinit var detailLocation: TextView
    private lateinit var detailRepository: TextView
    private lateinit var detailFollower: TextView
    private lateinit var detailFollowing: TextView

    private lateinit var dataUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dataUser = intent.getParcelableExtra<User>(EXTRA_USER) as User

        detailBack = findViewById(R.id.detail_button_back)
        detailName = findViewById(R.id.detail_name)
        detailImg = findViewById(R.id.detail_image)
        detailUsername = findViewById(R.id.detail_username)
        detailCompany = findViewById(R.id.detail_company)
        detailLocation = findViewById(R.id.detail_location)
        detailRepository = findViewById(R.id.detail_repository)
        detailFollower = findViewById(R.id.detail_follower)
        detailFollowing = findViewById(R.id.detail_following)

        initiateData()
    }

    private fun initiateData(){
        detailBack.setOnClickListener(this)
        val imgID = resources.getIdentifier(
            dataUser.avatar,
            "drawable",
            MainActivity.PACKAGE_NAME
        )
        detailImg.setImageResource(imgID)

        detailName.text = dataUser.name
        detailUsername.text = dataUser.username
        detailCompany.text = dataUser.company
        detailLocation.text = dataUser.location
        detailRepository.text = dataUser.repository.toString()
        detailFollower.text = dataUser.follower.toString()
        detailFollowing.text = dataUser.following.toString()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.detail_button_back -> finish()
        }
    }
}