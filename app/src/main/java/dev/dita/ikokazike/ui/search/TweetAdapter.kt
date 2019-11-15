package dev.dita.ikokazike.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.dita.ikokazike.R
import dev.dita.ikokazike.data.Tweet
import kotlinx.android.synthetic.main.list_item_small.view.*

class TweetAdapter : PagedListAdapter<Tweet, TweetViewHolder>(
    TweetAdapter
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_small, parent, false)

        return TweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = getItem(position)

        if (tweet != null) holder.bind(tweet)
    }

    companion object : DiffUtil.ItemCallback<Tweet>() {
        override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
            return oldItem::class == newItem::class
        }

        override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
            return oldItem.text == newItem.text
        }

    }

}

class TweetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(tweet: Tweet) {
        view.itemName.text = tweet.text
    }
}