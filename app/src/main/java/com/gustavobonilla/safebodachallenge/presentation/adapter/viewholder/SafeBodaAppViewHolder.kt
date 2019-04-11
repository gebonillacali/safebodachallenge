package com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder

import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener

interface SafeBodaAppViewHolder<T> {
    fun bindItem(item: T, clickListener: ClickListener<T>)
}