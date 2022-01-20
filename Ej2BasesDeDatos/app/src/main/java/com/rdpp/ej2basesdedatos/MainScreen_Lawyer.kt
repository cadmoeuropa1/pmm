package com.rdpp.ej2basesdedatos

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rdpp.ej2basesdedatos.database.LawFirmDAO
import com.rdpp.ej2basesdedatos.databinding.ActivityMainScreenLawyerBinding
import com.rdpp.ej2basesdedatos.dataclasses.Case
import com.rdpp.ej2basesdedatos.dataclasses.User
import com.rdpp.ej2basesdedatos.interfaces.EventListener

class MainScreen_Lawyer : AppCompatActivity(), EventListener {
    lateinit var binding: ActivityMainScreenLawyerBinding
    lateinit var db: LawFirmDAO
    lateinit var adapter: CaseAdapter
    lateinit var gridLayout: GridLayoutManager
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenLawyerBinding.inflate(layoutInflater)
        db = LawFirmDAO(this)
        adapter = CaseAdapter(mutableListOf(), this)
        user = intent.getParcelableExtra<User>("user")!!
        setContentView(binding.root)
        startRecycler()
    }

    private fun startRecycler() {
        adapter = CaseAdapter(mutableListOf(), this)
        gridLayout = GridLayoutManager(this, 1)
        getAllCases().execute()

    }

    inner class getAllCases : AsyncTask<Void, Void, MutableList<Case>>() {
        override fun doInBackground(vararg p0: Void?): MutableList<Case> {
            val portales = db.getAllCases()
            return portales
        }

        override fun onPostExecute(result: MutableList<Case>?) {
            super.onPostExecute(result)
            adapter.setCases(result)
        }
    }

    override fun showDetails(case: Case) {
        TODO("Not yet implemented")
    }
}