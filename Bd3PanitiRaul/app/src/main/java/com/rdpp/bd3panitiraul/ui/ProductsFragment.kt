package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.bd3panitiraul.listener.EventListener
import com.rdpp.bd3panitiraul.adapter.ProductAdapter
import com.rdpp.bd3panitiraul.database.ShoppingListDAO
import com.rdpp.bd3panitiraul.databinding.FragmentProductsBinding

class ProductsFragment : Fragment(), EventListener {
    private lateinit var mBinding: FragmentProductsBinding
    private var mActivity: MainActivity? = null
    private lateinit var database: ShoppingListDAO
    private lateinit var adapter: ProductAdapter
    private lateinit var layout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentProductsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        database = ShoppingListDAO(requireContext())
        setRecyclerView()
    }

    private fun setRecyclerView() {
        adapter = ProductAdapter(mutableListOf(), this)
        layout = GridLayoutManager(requireContext(), 0)

    }

}