package com.rdpp.bd3panitiraul.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.rdpp.bd3panitiraul.R
import com.rdpp.bd3panitiraul.adapter.ProductAdapter
import com.rdpp.bd3panitiraul.database.ShoppingListDAO
import com.rdpp.bd3panitiraul.databinding.FragmentProductsBinding
import com.rdpp.bd3panitiraul.dataclass.Category
import com.rdpp.bd3panitiraul.dataclass.Product
import com.rdpp.bd3panitiraul.listener.EventListener

class ProductsFragment : Fragment(), EventListener {
    private lateinit var mBinding: FragmentProductsBinding
    private var mActivity: MainActivity? = null
    private lateinit var database: ShoppingListDAO
    private lateinit var adapter: ProductAdapter
    private lateinit var layout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentProductsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        database = ShoppingListDAO(requireContext())
        adapter = ProductAdapter(mutableListOf(), this)
        layout = GridLayoutManager(requireContext(), 2)
        val productGeneral = database.getAllProducts()
        adapter.setProducts(productGeneral)
        mBinding.recyclerView.adapter = adapter
        mBinding.toggleButton.addOnButtonCheckedListener { toggleButton, _, _ ->
            when (toggleButton.checkedButtonId) {
                R.id.btnCategories -> {
                    setRecyclerViewCategories()
                }
                R.id.btnAlphabet -> {
                    setRecyclerViewAlphabetically()
                }
            }
        }
        mBinding.btnAddProduct.setOnClickListener {
            addNewProduct()
        }
    }

    private fun addNewProduct() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        val input = EditText(requireContext())
        val input2 = EditText(requireContext())
        val spinner = Spinner(requireContext())
        spinner.adapter = categoriesProductAdapter()
        with(input) {
            hint = getString(R.string.product_name_add)
        }
        input2.hint = getString(R.string.product_image_add)
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(input)
        layout.addView(spinner)
        layout.addView(input2)
        with(builder) {
            setTitle("Add new product")
            setMessage("Complete the following fields to add a new product.")
            setView(layout)
            setPositiveButton(
                getString(R.string.alert_dialog_add_product)
            ) { _, _ ->
                val prodName = input.text.toString()
                val cat: Category = database.selectedCat(spinner.selectedItemId)!!
                val prodImage = input2.text.toString()
                if (database.addProduct(prodName, cat, prodImage) == -1L) {
                    Snackbar.make(
                        requireView(),
                        getString(R.string.product_add_error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        requireView(),
                        getString(R.string.product_added_correct),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            setNegativeButton(getString(R.string.alert_dialog_cancel), null)
            show()
        }

    }

    private fun categoriesProductAdapter(): SpinnerAdapter {
        val cats = database.getAllCategories()
        val categoriesSpinner =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item)

        if (database.categoriesData()) {
            for (i in 0 until cats.size) {
                val cat = cats[i]
                categoriesSpinner.add(cat.toString())
            }
        } else {
            categoriesSpinner.add("No category created yet")
        }
        return categoriesSpinner
    }

    private fun setRecyclerViewCategories() {
        val products = database.getProductsByCategories()
        adapter.setProducts(products)
    }

    private fun setRecyclerViewAlphabetically() {
        val products = database.getProductsAlphabetically()
        adapter.setProducts(products)
    }

    override fun deleteProduct(product: Product) {
        database.deleteProduct(product.name)
    }

}