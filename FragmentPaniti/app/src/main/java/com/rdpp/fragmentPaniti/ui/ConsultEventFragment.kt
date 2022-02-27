package com.rdpp.fragmentPaniti.ui


import FragmentPaniti.databinding.FragmentConsultEventBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.fragmentPaniti.adapters.EventAdapter
import com.rdpp.fragmentPaniti.database.ProgrammersDAO
import com.rdpp.fragmentPaniti.dataclass.Event
import com.rdpp.fragmentPaniti.dataclass.User
import com.rdpp.fragmentPaniti.listeners.EventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class ConsultEventFragment : Fragment(), EventListener {
    private lateinit var mBinding: FragmentConsultEventBinding
    private var mActivity: MainScreen? = null
    private lateinit var database: ProgrammersDAO
    private lateinit var events: MutableList<Event>
    private lateinit var adapterM: EventAdapter
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        mBinding = FragmentConsultEventBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainScreen
        database = ProgrammersDAO(mActivity!!.applicationContext)
        user = database.getUser(arguments?.getInt("user_Id"))!!
        mBinding.fabAddEvent.setOnClickListener {
            mActivity?.addEvent()
        }
        if (user.type == "U") {
            mBinding.fabAddEvent.visibility = View.INVISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        configurarRecycler()
    }


    private fun configurarRecycler() {
        adapterM = EventAdapter(mutableListOf(), this, user)
        val gridLayout = GridLayoutManager(requireActivity(), 1)
        getEvents()
        with(mBinding.recyclerView) {
            setHasFixedSize(true)
            adapter = adapterM
            layoutManager = gridLayout
        }
    }


    private fun getEvents() {
        getEventsCoroutine()
    }


    private fun getEventsCoroutine() {
        GlobalScope.launch(Dispatchers.IO) {
            events = database.getEvents(getCurrentDateTime().toString("yyyy-MM-dd"))
            launch(Dispatchers.Main) {
                adapterM.setEvents(events)
            }
        }
    }

    override fun editEvent(idEvent: Int) {
        super.editEvent(idEvent)
        mActivity!!.editEvent(idEvent)
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }


}


