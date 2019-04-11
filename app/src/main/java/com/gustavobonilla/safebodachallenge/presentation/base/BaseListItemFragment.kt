package com.gustavobonilla.safebodachallenge.presentation.base

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener
import com.gustavobonilla.safebodachallenge.presentation.adapter.recyclerviewadapter.ItemAdapter
import com.gustavobonilla.safebodachallenge.visible
import kotlinx.android.synthetic.main.fragment_listitem.*

/**
 * Defines the [BaseListItemFragment] used in app.
 *
 * This fragment handles any type of list elements.
 * Also handles the scrolling logic required for a list elements.
 */
abstract class BaseListItemFragment<T, Parameters>: BaseFragment<@JvmSuppressWildcards List<T>, Parameters>(), ClickListener<T> {
    lateinit var adapter: ItemAdapter<T>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showLoading()
        adapter = ItemAdapter.create(this)
        setupRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }
    //endregion

    //r8egion [BaseFragment] implementation
    override fun getLayoutResource(): Int = R.layout.fragment_listitem

    override fun subscribeListener(): (listCategory: List<T>) -> Unit {
        return {
            if (it.isNotEmpty() || adapter.itemCount > 0) {
                showContent()
                adapter.addItems(it, ItemAdapter.AdapterNotification.RANGE_INSERTED)
            } else {
                showContent(false)
            }
        }
    }

    override fun subscribeErrorListener(): ((Throwable) -> Unit)? {
        return {
            showContent(false)
        }
    }
    //endregion

    override fun onItemClickListener(item: T) {
        //No implementation, override here to avoid the strict implementation in subclasses.
    }
    //endregion

    //region Private Methods
    /**
     * Shows the content or shows the error message.
     *
     * @param show true if should show content, false otherwise.
     */
    private fun showContent(show: Boolean = true) {
        instructionsText.visible = show
        cardRecyclerContainer.visible = show
        recyclerView.visible = show
        loadingAnimation.visible = false
        errorMessage.visible = !show
    }

    /**
     * Shows the content or shows the error message.
     *
     * @param show true if should show content, false otherwise.
     */
    private fun showLoading(show: Boolean = true) {
        loadingAnimation.visible = show
        instructionsText.visible = !show
        cardRecyclerContainer.visible = !show
        recyclerView.visible = !show
        errorMessage.visible = !show
    }

    /**
     * Setups the [RecyclerView].
     *
     * Handles the scrolling logic and sets the adapter.
     */
    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
    //endregion
}