package com.rdpp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rdpp.fragments.databinding.FragmentConsultStoreBinding
import com.rdpp.fragments.databinding.FragmentEditStoreBinding

class ConsultFragment : Fragment() {

    private var mActivity: MainActivity? = null
    private lateinit var mBinding: FragmentConsultStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentConsultStoreBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        mBinding.fab.setOnClickListener {
            mActivity?.add()
        }

    }
    private fun consultaCorrutinas(){}


}