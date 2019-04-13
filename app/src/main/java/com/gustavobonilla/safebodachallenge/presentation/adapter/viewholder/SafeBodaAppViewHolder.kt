package com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder

import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener

interface SafeBodaAppViewHolder<T> {
    /**
     * Binds the information of the item in the view.
     *
     * @param item The item model which contains the info to be bind in the view.
     * @param clickListener the listener when the viewholder is clicked.
     */
    fun bindItem(item: T, clickListener: ClickListener<T>)
}