package com.rdpp.fragmentPaniti.adapters

import FragmentPaniti.R
import FragmentPaniti.databinding.ItemEventCardLayoutBinding
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.fragmentPaniti.dataclass.Event
import com.rdpp.fragmentPaniti.dataclass.User
import com.rdpp.fragmentPaniti.listeners.EventListener

class EventAdapter(
    private var events: MutableList<Event>,
    private var listener: EventListener,
    private var user: User
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemEventCardLayoutBinding.bind(view)
        fun setListener(event: Event) {
            binding.btnEditDate.setOnClickListener { listener.editEvent(event.idEvent) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_event_card_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        with(holder) {
            setListener(event)
            binding.txtIdentifier.text = "Codigo: " + event.idEvent
            binding.txtTitle.text = "Event: " + event.title
            binding.txtDate.text = "Date: " + event.date
            binding.txtDescription.text = event.description
            if (user.type == "U") {
                binding.btnEditDate.visibility = View.INVISIBLE
            }
        }

    }

    override fun getItemCount() = events.size

    fun setEvents(events: MutableList<Event>) {
        this.events = events
        notifyDataSetChanged()
    }
}
