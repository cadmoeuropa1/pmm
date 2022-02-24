package com.rdpp.fragmentPaniti.ui

import FragmentPaniti.databinding.FragmentEditEventBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rdpp.fragmentPaniti.database.ProgrammersDAO
import com.rdpp.fragmentPaniti.dataclass.Event
import java.text.SimpleDateFormat
import java.util.*


class EditEventFragment : Fragment() {

    private lateinit var mBinding: FragmentEditEventBinding
    private var mActivity: MainScreen? = null
    private lateinit var database: ProgrammersDAO
    private var event: Event? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        mBinding = FragmentEditEventBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainScreen
        database = ProgrammersDAO(mActivity!!.applicationContext)
        event = database.getSingleEvent(requireArguments().getInt("event_Id"))
        mBinding.txtDate.setOnClickListener {
            editEventDate()
        }
    }

    private fun editEventDate() {
        showDatePicker()
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        parentFragmentManager.let { manager -> datePicker.show(manager, "datePicker") }
    }

    @SuppressLint("SetTextI18n")
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
            mBinding.txtDate.text = "$year-" + month + 1 + "-$day"
        }

    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}