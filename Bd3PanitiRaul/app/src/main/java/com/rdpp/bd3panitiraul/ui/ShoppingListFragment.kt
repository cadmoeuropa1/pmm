package com.rdpp.bd3panitiraul.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.adapter.ListAdapter
import com.rdpp.bd3panitiraul.database.ShoppingListDAO
import com.rdpp.bd3panitiraul.databinding.FragmentShoppingListBinding
import com.rdpp.bd3panitiraul.dataclass.ShoppingList
import com.rdpp.bd3panitiraul.listener.ListEventListener


class ShoppingListFragment : Fragment(), ListEventListener {
    private lateinit var mBinding: FragmentShoppingListBinding
    private var mActivity: MainActivity? = null
    private lateinit var database: ShoppingListDAO
    private lateinit var adapterR: ListAdapter
    private lateinit var layout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentShoppingListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        database = ShoppingListDAO(requireContext())
        adapterR = ListAdapter(mutableListOf(), this)
        layout = GridLayoutManager(requireContext(), 1)
        getAllLists()
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = adapterR
            layoutManager = layout
        }
        mBinding.fabAddList.setOnClickListener { addNewList() }
    }

    private fun addNewList() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        val input = EditText(requireContext())
        input.hint = "Shopping list name"
        with(builder) {
            setTitle("Add new Shopping List")
            setMessage("Please enter a name for the new Shopping List")
            setView(input)
            setNegativeButton(getString(R.string.alert_dialog_cancel), null)
            setPositiveButton("Add list") { _, _ ->
                val listName = input.text.toString()
                if (database.addList(listName) == -1L) {
                    Snackbar.make(
                        requireView(),
                        "Shopping list not created, try again",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        requireView(),
                        "Shopping list created correctly",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            show()
        }
    }

    private fun getAllLists() {
        val lists = database.getAllLists()
        adapterR.setLists(lists)
    }

    override fun deleteList(shoppingList: ShoppingList) {
        TODO("Not yet implemented")
    }

    override fun editList(shoppingList: ShoppingList) {
        val intent = Intent(mActivity, EditShoppingList::class.java)
        startActivity(intent)
    }

}