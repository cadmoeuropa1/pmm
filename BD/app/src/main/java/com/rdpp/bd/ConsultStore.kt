package com.rdpp.bd

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.bd.databinding.ActivityMainBinding
import com.rdpp.bd.databinding.FragmentConsultStoreBinding
import com.rdpp.bd.databinding.FragmentEditStoreBinding


class ConsultStore : Fragment() {
    private lateinit var mBinding: FragmentEditStoreBinding
    private lateinit var adapterR: StoreAdapter
    private lateinit var gridLayout: GridLayoutManager
    private lateinit var db: StoreDAO
    private var mActivity: MainActivity?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = FragmentEditStoreBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consult_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    private fun configurar(){
        mActivity = activity as? MainActivity
    }

    private fun save() {
        /*val store = Store(1, binding.etName.text.toString().trim(), 1)
        Thread {
            db.addStore(store)
            runOnUiThread { adapterR.add(store) }
        }.start()
*/
    }

    /*private fun startRecyclerView() {
        adapterR = StoreAdapter(mutableListOf(), this)
        gridLayout = GridLayoutManager(this, 2)
        getAllStores()
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = adapterR
            layoutManager = gridLayout
        }
    }

    private fun getAllStores() {
        val stores = db.getAllStores()
        adapterR.setStores(stores)
    }

    override fun edit(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onFavorite(store: Store) {
        if (store.isFavorite == 0) {
            store.isFavorite = 1
        } else {
            store.isFavorite = 0
        }
        db.updateStore(store)
        adapterR.update(store)
    }

    override fun deleteStore(id: Long) {
        Thread {
            db.deleteStore(id)
            runOnUiThread { adapterR.delete(id) }
        }
    }

    private inner class MyAsyncTask() : AsyncTask<Void, Void, MutableList<Store>>() {
        override fun doInBackground(vararg p0: Void?): MutableList<Store> {
            val stores = db.getAllStores()
            return stores
        }

        override fun onPostExecute(result: MutableList<Store>) {
            super.onPostExecute(result)
            adapterR.setStores(result)
        }
    }
*/
}