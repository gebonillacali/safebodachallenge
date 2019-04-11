package com.gustavobonilla.safebodachallenge.presentation.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener
import com.gustavobonilla.safebodachallenge.presentation.adapter.recyclerviewadapter.ItemAdapter
import com.gustavobonilla.safebodachallenge.presentation.base.BaseView
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SearchCitiesViewModel
import com.gustavobonilla.safebodachallenge.visible
import kotlinx.android.synthetic.main.dialog_listitem.*

class DialogSearchCity(context: Context?, private val searchCity: SearchCitiesViewModel, private val currentCity: String?, private val action: (City)
->Unit) : Dialog
(context),
        BaseView<List<City>>,
        ClickListener<City> {

    lateinit var adapter: ItemAdapter<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        setViews()
        searchCity.subscribe(subscribeListener(), subscribeErrorListener())
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onItemClickListener(item: City) {
        dismiss()
        action(item)
    }

    override fun subscribeErrorListener(): ((Throwable) -> Unit)? {
        return {
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun subscribeListener(): (data: List<City>) -> Unit {
        return {
            if (it.isNotEmpty() || adapter.itemCount > 0) {
                showContent()
                adapter.clear()
                adapter.addItems(it, ItemAdapter.AdapterNotification.ALL_DATA)
            } else {
                showContent(false)
            }
        }
    }

    override fun getLayoutResource(): Int = R.layout.dialog_listitem

    override fun inject() {
        //Not required
    }

    private fun setViews() {
        adapter = ItemAdapter.create(this)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //Not required
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Not required
            }

            override fun onTextChanged(searchTerm: CharSequence?, start: Int, before: Int, count: Int) {
                if (!searchTerm.isNullOrEmpty()) {
                    showLoading()
                    searchCity.getData(searchTerm.toString())
                }
            }
        })

        currentCity?.let {
            searchText.setText(it)
        }
    }

    /**
     * Shows the content or shows the error message.
     *
     * @param show true if should show content, false otherwise.
     */
    private fun showContent(show: Boolean = true) {
        instructionsText.visible = show
        searchText.visible = show
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
        searchText.visible = show
        instructionsText.visible = !show
        cardRecyclerContainer.visible = !show
        recyclerView.visible = !show
        errorMessage.visible = !show
    }
}