package com.hellodiffa.googlemaps_recylerview_interaction.common

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min

/*
* CREATED By Diffa
* linkedin : www.linkedin.com/in/diffadwi
* github : www.github.com/ddiffa
* email : ddiffa2@gmail.com
*/

class HorizontalRecyclerViewScrollListener(private val onScrolled: OnScrolled) :
    RecyclerView.OnScrollListener() {
    private var OFFSET_RANGE = 270
    private var itemBounds = arrayListOf<Int>()


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (itemBounds.isNullOrEmpty()) recyclerView.adapter?.itemCount?.let {
            fillItemBounds(
                it,
                recyclerView
            )
        }

        for (i in 0 until itemBounds.size) {
            if (isInChildItemsRange(
                    recyclerView.computeHorizontalScrollOffset(),
                    itemBounds[i],
                    OFFSET_RANGE
                )
            ) {
                onScrolled(i)
            }
        }
    }


    private fun fillItemBounds(itemsCount: Int, recyclerView: RecyclerView) {
        val childWidth =
            (recyclerView.computeHorizontalScrollRange() - recyclerView.computeHorizontalScrollExtent()) / itemsCount

        for (i in 0 until itemsCount) {
            itemBounds.add((((childWidth * i + childWidth * (i + 1)) / 2)))
        }
    }

    private fun isInChildItemsRange(offset: Int, itemBound: Int, range: Int): Boolean {
        val rangeMin = itemBound - range
        val rangeMax = itemBound + range
        return (min(rangeMin, rangeMax) <= offset) && (max(rangeMin, rangeMax) >= offset)
    }
}


typealias OnScrolled = (position: Int) -> Unit
