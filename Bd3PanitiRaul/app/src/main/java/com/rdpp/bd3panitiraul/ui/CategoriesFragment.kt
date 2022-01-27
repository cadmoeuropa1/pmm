package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rdpp.bd3panitiraul.database.ShoppingListDAO
import com.rdpp.bd3panitiraul.databinding.FragmentCategoriesBinding
import com.rdpp.bd3panitiraul.dataclass.Category


class CategoriesFragment : Fragment() {
    private lateinit var mBinding: FragmentCategoriesBinding
    private lateinit var database: ShoppingListDAO
    private var mActivity: MainActivity? = null
    private var mCategories: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    companion object {

    }
}