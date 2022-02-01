package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.adapter.ListAdapter
import com.rdpp.bd3panitiraul.database.ShoppingListDAO
import com.rdpp.bd3panitiraul.databinding.FragmentShoppingListBinding
import com.rdpp.bd3panitiraul.listener.ListEventListener
import java.util.*


class ShoppingListFragment : Fragment(), ListEventListener {
    private lateinit var mBinding: FragmentShoppingListBinding
    private var mActivity: MainActivity? = null
    private lateinit var database : ShoppingListDAO
    private lateinit var adapter : ListAdapter
    private lateinit var layout : GridLayoutManager

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
        adapter = ListAdapter(mutableListOf(), this)
    }

}