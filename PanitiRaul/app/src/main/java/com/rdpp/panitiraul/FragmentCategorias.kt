package com.rdpp.panitiraul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rdpp.panitiraul.databinding.FragmentCategoriasBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragmentCategorias : Fragment(), CategoriaEventListener {
    private lateinit var mBinding: FragmentCategoriasBinding
    private var mActivity: ContainerActivity? = null
    private lateinit var categorias: MutableList<Categoria>
    private lateinit var db: EmpresaDAO
    private lateinit var adaptador: CategoriaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCategoriasBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as ContainerActivity
        db = EmpresaDAO(mActivity!!.applicationContext)
        configurarRecycler()
    }

    override fun onResume() {
        super.onResume()
        configurarRecycler()
    }

    private fun configurarRecycler() {
        adaptador = CategoriaAdapter(mutableListOf(), this)
        val layout = LinearLayoutManager(mActivity!!)
        getAllCategorias()
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = layout
            adapter = adaptador
        }

    }

    private fun getAllCategorias() {
        categorias = db.getAllCategorias()
        adaptador.setCategorias(categorias)
    }

    private fun consultaCorrutinas() {
        GlobalScope.launch(Dispatchers.IO) {
            categorias = db.getAllCategorias()
            launch(Dispatchers.Main) {

            }
        }
    }

    override fun onClick(categoria: Categoria) {
        TODO("Not yet implemented")
    }
}