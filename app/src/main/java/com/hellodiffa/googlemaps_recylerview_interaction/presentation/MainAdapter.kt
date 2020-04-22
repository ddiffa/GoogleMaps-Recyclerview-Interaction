package com.hellodiffa.googlemaps_recylerview_interaction.presentation

import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hellodiffa.googlemaps_recylerview_interaction.R
import com.hellodiffa.googlemaps_recylerview_interaction.model.Hotel
import com.hellodiffa.googlemaps_recylerview_interaction.utils.getFormat
import com.hellodiffa.googlemaps_recylerview_interaction.utils.getLocation
import kotlinx.android.synthetic.main.item_maps.view.*
import java.util.*

/*
* CREATED By Diffa
* linkedin : www.linkedin.com/in/diffadwi
* github : www.github.com/ddiffa
* email : ddiffa2@gmail.com
*/
class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var list = arrayListOf<Hotel>()

    fun setData(data: List<Hotel>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_maps, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }


    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(data: Hotel) = view.apply {
            textView.text = data.name
            textView2.text = "${getFormat().format(data.price).toString()}/room/night"
            Glide.with(context)
                .load(data.image)
                .into(imageView)
            textView3.text = data.getLocation(
                Geocoder(context, Locale.getDefault())
            )
        }
    }

}