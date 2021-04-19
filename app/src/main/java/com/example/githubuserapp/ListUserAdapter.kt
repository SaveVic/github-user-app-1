package com.example.githubuserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class ListUserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onClickItemCallback: OnClickItemCallback

    fun setClickCallback(clickCallback: OnClickItemCallback) {
        onClickItemCallback = clickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]

        val imgID = holder.itemView.resources.getIdentifier(
            user.avatar,
            "drawable",
            MainActivity.PACKAGE_NAME
        )
        holder.itemImg.setImageResource(imgID)

        holder.itemName.text = user.name
        holder.itemUsername.text = user.username

        holder.itemButton.setOnClickListener {
            onClickItemCallback.onClickItem(user)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImg: CircleImageView = itemView.findViewById(R.id.item_img)
        var itemName: TextView = itemView.findViewById(R.id.item_name)
        var itemUsername: TextView = itemView.findViewById(R.id.item_username)
        var itemButton: Button = itemView.findViewById(R.id.item_button)
    }
}

interface OnClickItemCallback {
    fun onClickItem(user: User)
}