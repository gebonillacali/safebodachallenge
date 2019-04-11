package com.gustavobonilla.safebodachallenge.presentation.adapter

interface ClickListener<T> {

  /**
   * Defines the click listener for items.
   *
   * @param item the info for the item
   * @param code the code for the item.
   */
  fun onItemClickListener(item: T)
}