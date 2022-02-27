package com.rdpp.fragmentPaniti.ui

import FragmentPaniti.databinding.FragmentAddEventBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rdpp.fragmentPaniti.database.ProgrammersDAO
import com.rdpp.fragmentPaniti.dataclass.Event
import java.util.*

class AddEventFragment : Fragment() {
    private lateinit var mBinding: FragmentAddEventBinding
    private var mActivity: MainScreen? = null
    private lateinit var database: ProgrammersDAO


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
        if (year < getCurrentDateTime().year || (year == getCurrentDateTime().year && (month + 1) < getCurrentDateTime().month + 1)
            || ((month + 1) == getCurrentDateTime().month + 1 && day < getCurrentDateTime().day)
        ) {
            Toast.makeText(
                mActivity!!.applicationContext,
                "Incorrect date. Date must be superior to today's date",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            mBinding.tieDate.setText("$year-" + month + 1 + "-$day")
        }
    }

    private fun addEvent() {
        val title = mBinding.tieTitle.text.toString()
        val description = mBinding.tieDescription.text.toString()
        val date = mBinding.tieDate.text.toString()
        database.addEvent(Event(date = date, title = title, description = description))
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}