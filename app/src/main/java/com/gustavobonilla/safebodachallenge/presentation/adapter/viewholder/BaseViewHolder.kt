package com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(view: View): RecyclerView.ViewHolder(view), SafeBodaAppViewHolder<T>