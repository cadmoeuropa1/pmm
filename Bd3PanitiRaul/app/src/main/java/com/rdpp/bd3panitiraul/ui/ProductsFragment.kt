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
import com.rdpp.bd3panitiraul.dataclass.ShoppingList
import com.rdpp.bd3panitiraul.listener.ProductEventListener

class ProductsFragment : Fragment(), ProductEventListener {
    private lateinit var mBinding: FragmentProductsBinding
    private var mActivity: MainActivity? = null
    private lateinit var database: ShoppingListDAO
    private lateinit var adapter2: ProductAdapter
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
        database = ShoppingListDAO(mActivity!!.applicationContext)
        adapter2 = ProductAdapter(mutableListOf(), this)
        layout = GridLayoutManager(requireContext(), 2)
        val productGeneral = database.getAllProducts()
        adapter2.setProducts(productGeneral)
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = adapter2
            layoutManager = layout
        }
        mBinding.btnCategories.setOnClickListener { setRecyclerViewCategories() }
        mBinding.btnAlphabet.setOnClickListener { setRecyclerViewAlphabetically() }
        mBinding.btnAddProduct.setOnClickListener {
            addNewProduct()
        }
        setAutoCompleteTextView()
    }

    private fun setAutoCompleteTextView() {
        val autoComplete = mBinding.autoCompleteTextView
        val categories = database.getAllCategories()
        val categoriesStrings = mutableListOf<String>()
        for (product in categories)
            categoriesStrings.add(product.name)
        val adapterACTV =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, categoriesStrings)
        autoComplete.setAdapter(adapterACTV)
    }

    private fun addNewProduct() {
        Snackbar.make(
            mBinding.root,
            getString(R.string.product_added_correct),
            Snackbar.LENGTH_SHORT
        ).show()
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
                val dato = spinner.selectedItem as Category
                val prodImage = input2.text.toString()
                if (database.addProduct(prodName, dato, prodImage) == -1L) {
                    Toast.makeText(
                        mActivity!!,
                        getString(R.string.product_add_error),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.product_added_correct),
                        Toast.LENGTH_SHORT
                    ).show()
                    setNormalRecycler()
                }

            }
            setNegativeButton(getString(R.string.alert_dialog_cancel), null)
            show()


        }
    }

    private fun categoriesProductAdapter(): SpinnerAdapter {
        val cats = database.getAllCategories()
        val categoriesSpinner =
            ArrayAdapter<Category>(requireContext(), android.R.layout.simple_spinner_dropdown_item)

        if (database.categoriesData()) {
            for (i in 0 until cats.size) {
                val cat = cats[i]
                categoriesSpinner.add(cat)
            }
        }
        return categoriesSpinner
    }

    private fun setNormalRecycler() {
        val products = database.getAllProducts()
        adapter2.setProducts(products)
    }

    private fun setRecyclerViewCategories() {
        val products = database.getProductsByCategories()
        adapter2.setProducts(products)
    }

    private fun setRecyclerViewAlphabetically() {
        val products = database.getProductsAlphabetically()
        adapter2.setProducts(products)
    }

    override fun deleteProduct(product: Product) {
        database.deleteProduct(product.name)
    }

    override fun addToList(product: Product) {
        val builder = MaterialAlertDialogBuilder(requireContext())
        val input = Spinner(requireContext())
        input.adapter = shoppingListsAdapter()
        with(builder) {
            setTitle("Add product to List")
            setMessage("Select a Shopping List where to add the product to")
            setView(input)
            setPositiveButton("Add") { _, _ ->
                val shList = input.selectedItem as ShoppingList
                database.addProductToList(product, shList, 1)
            }
            setNegativeButton("Cancel", null)
            show()
        }
        //database.addProductToList(product, lis)
    }

    private fun shoppingListsAdapter(): SpinnerAdapter? {
        val lists = database.getAllLists()
        val listsSpinner =
            ArrayAdapter<ShoppingList>(
                requireContext(), android.R.layout.simple_spinner_dropdown_item
            )
        if (database.shoppingListsData()) {
            for (i in 0 until lists.size) {
                val list = lists[i]
                listsSpinner.add(list)
            }
        }
        return listsSpinner
    }
}