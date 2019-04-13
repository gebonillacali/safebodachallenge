package com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Represent any kind of [RecyclerView.ViewHolder].
 */
abstract class BaseViewHolder<T>(view: View): RecyclerView.ViewHolder(view), SafeBodaAppViewHolder<T>