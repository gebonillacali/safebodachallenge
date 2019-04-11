package com.gustavobonilla.safebodachallenge.presentation.adapter.adapteritem

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.BaseViewHolder

/**
 * Defines the function for creating a [RecyclerView.ViewHolder]
 */
typealias CreateViewHolder = (Int, ViewGroup) -> RecyclerView.ViewHolder

interface BaseAdapterItem<T> {

  val layoutRes: Int

  /**
   * Binds the data into the view holder.
   *
   * @param viewHolder the [RecyclerView.ViewHolder] which contains the views to be populated
   */
  fun bindData(viewHolder: BaseViewHolder<T>)

  /**
   * Gets the section in which it belongs.
   */
  fun getSection(): String
}