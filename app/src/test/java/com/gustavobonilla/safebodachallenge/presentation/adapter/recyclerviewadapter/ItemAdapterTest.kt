package com.gustavobonilla.safebodachallenge.presentation.adapter.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.gustavobonilla.safebodachallenge.ModelCreator
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.FlightScheduleViewHolder
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.atLeastOnce
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(ItemAdapter::class, LayoutInflater::class)
class ItemAdapterTest {
    private lateinit var adapter: ItemAdapter<FlightSchedule>
    private lateinit var clickListener: ClickListener<FlightSchedule>

    @Before
    fun setUp() {
        clickListener = Mockito.mock(DummyClickListener::class.java)
        adapter = Mockito.spy(ItemAdapter.create(clickListener))
    }

    @Test
    fun addItems() {
        val items = createFlightSchedule()
        Mockito.doNothing().`when`(adapter).notifyItemRangeInserted(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
        adapter.addItems(items, ItemAdapter.AdapterNotification.RANGE_INSERTED)
        Assert.assertEquals(10, adapter.itemCount)
    }

    @Test
    fun noDuplicateItems() {
        val items = createFlightSchedule()
        Mockito.doNothing().`when`(adapter).notifyItemRangeInserted(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
        adapter.addItems(items, ItemAdapter.AdapterNotification.RANGE_INSERTED)
        Assert.assertEquals(10, adapter.itemCount)
        adapter.addItems(items, ItemAdapter.AdapterNotification.RANGE_INSERTED)
        Assert.assertEquals(10, adapter.itemCount)
    }

    @Test
    fun clear() {
        val items = createFlightSchedule()
        Mockito.doNothing().`when`(adapter).notifyItemRangeInserted(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
        adapter.addItems(items, ItemAdapter.AdapterNotification.RANGE_INSERTED)
        Assert.assertEquals(10, adapter.itemCount)
        adapter.clear()
        Assert.assertEquals(0, adapter.itemCount)
    }

    @Test
    fun checkNotifyItemRangeInserted() {
        val items = createFlightSchedule()
        Mockito.doNothing().`when`(adapter).notifyItemRangeInserted(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
        adapter.addItems(items, ItemAdapter.AdapterNotification.RANGE_INSERTED)
        Assert.assertEquals(10, adapter.itemCount)
        Mockito.verify(adapter, atLeastOnce()).notifyItemRangeInserted(anyInt(), anyInt())
    }

    @Test
    fun checkNotifyDataSetChange() {
        val items = createFlightSchedule()
        Mockito.doNothing().`when`(adapter).notifyDataSetChanged()
        adapter.addItems(items, ItemAdapter.AdapterNotification.ALL_DATA)
        Assert.assertEquals(10, adapter.itemCount)
        Mockito.verify(adapter, atLeastOnce()).notifyDataSetChanged()
    }

    @Test
    fun checkGetItemViewType() {
        val items = createFlightSchedule()
        Mockito.doNothing().`when`(adapter).notifyItemRangeInserted(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())
        adapter.addItems(items, ItemAdapter.AdapterNotification.RANGE_INSERTED)
        Assert.assertEquals(R.layout.flight_list_item, adapter.getItemViewType(0))
    }

    @Test
    fun checkOnCreateViewHolder() {
        val layoutInflater = PowerMockito.mock(LayoutInflater::class.java)
        val context = Mockito.mock(Context::class.java, Mockito.RETURNS_MOCKS)
        PowerMockito.mockStatic(LayoutInflater::class.java)
        PowerMockito.`when`(LayoutInflater.from(context)).thenReturn(layoutInflater)
        val viewGroup = Mockito.spy(LinearLayout(context))
        val viewGroupInflated = Mockito.spy(LinearLayout(context))
        with(viewGroup) {
            Mockito.`when`(this.context).thenReturn(context)
        }
        with(viewGroupInflated) {
            Mockito.`when`(this.context).thenReturn(context)
        }

        Mockito.`when`(viewGroupInflated.findViewById<TextView>(ArgumentMatchers.anyInt())).thenReturn(TextView(context))
        Mockito.`when`(layoutInflater.inflate(R.layout.flight_list_item, viewGroup, false)).thenReturn(viewGroupInflated)
        val viewHolder = adapter.onCreateViewHolder(viewGroup, R.layout.flight_list_item)
        Assert.assertTrue(viewHolder is FlightScheduleViewHolder)
    }

    private fun createFlightSchedule() = ModelCreator.flightSchedule
}

abstract class DummyClickListener: ClickListener<FlightSchedule>