package com.rdpp.bd

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.bd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), EventListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterR: StoreAdapter
    private lateinit var gridLayout: GridLayoutManager
    private lateinit var db: StoreDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = StoreDAO(this)
        startRecyclerView()
        binding.btnSave.setOnClickListener { save() }
    }

    private fun save() {
        val store = Store(1, binding.etName.text.toString().trim(), 1)
        Thread {
            db.addStore(store)
            runOnUiThread { adapterR.add(store) }
        }.start()

    }

    private fun startRecyclerView() {
        adapterR = StoreAdapter(mutableListOf(), this)
        gridLayout = GridLayoutManager(this, 2)
        getAllStores()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = adapterR
            layoutManager = gridLayout
        }
    }

    private fun getAllStores() {

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
}