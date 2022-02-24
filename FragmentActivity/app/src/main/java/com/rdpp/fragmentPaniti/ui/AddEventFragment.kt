package com.rdpp.fragmentPaniti.ui

import FragmentPaniti.databinding.FragmentAddEventBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rdpp.fragmentPaniti.database.ProgrammersDAO
import com.rdpp.fragmentPaniti.dataclass.Event

class AddEventFragment : Fragment() {
    private lateinit var mBinding: FragmentAddEventBinding
    private var mActivity: MainScreen? = null
    private lateinit var database: ProgrammersDAO
    private lateinit var events: MutableList<Event>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        mBinding = FragmentAddEventBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainScreen
        database = ProgrammersDAO(mActivity!!.applicationContext)
        mBinding.tieDate.setOnClickListener { showDatePicker() }
        mBinding.btnAddEvent.setOnClickListener { addEvent() }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        parentFragmentManager.let { manager -> datePicker.show(manager, "datePicker") }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        mBinding.tieDate.setText("$year-" + month + 1 + "-$day")
    }

    private fun addEvent() {

    }
}