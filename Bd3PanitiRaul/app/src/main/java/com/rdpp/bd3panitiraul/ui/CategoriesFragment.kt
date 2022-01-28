package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.database.ShoppingListDAO
import com.rdpp.bd3panitiraul.databinding.FragmentCategoriesBinding
import com.rdpp.bd3panitiraul.dataclass.Category


class CategoriesFragment : Fragment() {
    private lateinit var mBinding: FragmentCategoriesBinding
    private lateinit var database: ShoppingListDAO
    private var mActivity: MainActivity? = null
    private var mCategories: MutableList<Category>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        database = ShoppingListDAO(mActivity!!.applicationContext)
        setSpinner()
        mBinding.btnAdd.setOnClickListener {
            addCategory()
        }
        mBinding.btnDelete.setOnClickListener {
            deleteCategory()
        }
    }

    private fun deleteCategory() {
        val cat = mBinding.spnCategories.selectedItem.toString()
        val result = database.deleteCategory(cat)
        if (result == 0) {
            Snackbar.make(requireView(), "Category couldn't be deleted", Snackbar.LENGTH_SHORT)
                .show()

        } else {
            Snackbar.make(requireView(), "Category deleted", Snackbar.LENGTH_SHORT).show()
            setSpinner()
        }
    }

    private fun setSpinner() {
        mCategories = database.getAllCategories()
        val lstCategories =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item)

        mBinding.spnCategories.adapter = lstCategories

        if (database.CategoriesData()) {
            for (i in 0 until mCategories!!.size) {
                val cat = mCategories!![i]
                lstCategories.add(cat.toString())
            }
        } else {
            lstCategories.add("No category created yet")
        }
    }

    private fun addCategory() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        val input = EditText(requireContext())
        with(input) {
            hint = context.getString(R.string.edit_text_add_category_hint)
            maxLines = 0
        }
        with(builder) {
            setTitle(getString(R.string.alert_dialog_create_category_title))
            setMessage(getString(R.string.alert_dialog_create_category_message))
            setView(input)
            setPositiveButton(
                getString(R.string.alert_dialog_add_category)
            ) { _, _ ->
                val cat = input.text.toString()
                if (database.addCategory(cat) == -1L) {
                    Snackbar.make(
                        requireView(),
                        getString(R.string.category_add_error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        requireView(),
                        getString(R.string.category_added_correct),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                setSpinner()
            }
            setNegativeButton(getString(R.string.alert_dialog_cancel), null)
            show()
        }

    }


}