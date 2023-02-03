package com.projects.retrofit.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.projects.retrofit.R

//@BindingAdapter("bindInt")
//fun bindId(textView: TextView, int: Int) {
//    val context = textView.context
//    textView.text = String.format(context.getString(int), int)
//}

@BindingAdapter("statusImage")
fun bindImageOfDay(imageView: ImageView,status:Boolean){
    if (status) {
        imageView.contentDescription = imageView.context.getString(R.string.dog)
        imageView.setImageResource(R.drawable.img_cat)
    } else {
        imageView.contentDescription = imageView.context.getString(R.string.cat)
        imageView.setImageResource(R.drawable.img_dog)
    }
}
