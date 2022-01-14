package com.rdpp.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.rdpp.fragments.databinding.FragmentEditStoreBinding


class EditFragment : Fragment() {

    private lateinit var mBinding: FragmentEditStoreBinding
    private var mActivity : MainActivity? = null
    private var mTienda: Tienda? = null
    private lateinit var db: TiendasDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentEditStoreBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        db = TiendasDAO(mActivity!!.applicationContext)
        mTienda = Tienda(name="", phone="", photoUrl = "")
        setActionBar()
        setModifiedField()
    }

    private fun setModifiedField() {
        with(mBinding){
            etName.addTextChangedListener {
                validateFields(tilName)
            }
            etPhone.addTextChangedListener{
                validateFields(tilPhone)
            }
            etPhotoUrl.addTextChangedListener {
                validateFields(tilPhotoUrl)
                loadImage(it.toString().trim())
            }
        }
    }

    private fun loadImage(imageURL: String) {
        Glide.with(this).load(imageURL).diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(mBinding.imgPhoto)
    }

    private fun setActionBar() {
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity?.supportActionBar?.title=getString(R.string.edit)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                mActivity?.onBackPressed()
                true
            }
            R.id.action_save ->{
                if (mTienda != null && validateFields(mBinding.tilPhotoUrl, mBinding.tilPhone, mBinding.tilName)){
                    with(mTienda!!){
                        name = mBinding.etName.text.toString().trim()
                        phone = mBinding.etPhone.text.toString().trim()
                        webSite = mBinding.etWebSite.text.toString().trim()
                        photoUrl = mBinding.etPhotoUrl.text.toString().trim()
                    }
                    mTienda!!.id = db.addTienda(mTienda!!)
                }
                return true
            }
            else ->{return true}
        }
    }
    private fun validateFields(vararg textFields: TextInputLayout): Boolean {
        var isValid = true
        for (textField in textFields) {
            if (textField.editText?.getText().toString().isEmpty()) {
                textField.error = getString(R.string.helper_requiered)
                isValid = false
            } else {
                textField.error = null
            }
        }
        if (!isValid)
            Snackbar.make(
                mBinding.root, R.string.edit_store_message_valid,
                Snackbar.LENGTH_LONG
            ).show()
        return isValid
    }
    private fun hideKeyboard() {
        val imm = mActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        if (view != null)
            imm.hideSoftInputFromWindow(requireView().windowToken,0)
    }
}