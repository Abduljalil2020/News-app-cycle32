package com.route.newsapplicationc32

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("android:imageUrl")
fun bindImageWithGlide(imageView: ImageView, urlToImage: String) {

    Glide.with(imageView)
        .load(urlToImage)
        .into(imageView)

}