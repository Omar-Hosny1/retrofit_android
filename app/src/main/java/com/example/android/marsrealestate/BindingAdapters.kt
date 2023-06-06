package com.example.android.marsrealestate

import android.view.View
import android.widget.Adapter
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.overview.MarsApiStatus
import com.example.android.marsrealestate.overview.PhotoGridAdapter

@BindingAdapter("listData")
fun RecyclerView.bind(data: List<MarsProperty>?) {
    val adapter = this.adapter as PhotoGridAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context).load(imgUri).apply(
            RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(this)
    }
}

@BindingAdapter("marsApiStatus")
fun ImageView.bindStatus(status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.DONE -> {
            this.visibility = View.GONE
        }
        MarsApiStatus.ERROR -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.ic_connection_error)
        }
    }
}