package com.mjpg.tiendasfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.mjpg.tiendasfragment.databinding.ConsultaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ConsultaFragment : Fragment(), EventosListener {

    private lateinit var mBinding: ConsultaBinding
    private var mActivity: MainActivity? = null
    private lateinit var tiendas: MutableList<Tienda>
    private lateinit var db: TiendasDAO;
    private lateinit var adaptador: AdaptadorTienda

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = ConsultaBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as MainActivity
        db = TiendasDAO(mActivity!!.applicationContext)
        mBinding.fab.setOnClickListener {
            mActivity?.anadir()

        }
    }

    override fun onResume() {
        super.onResume()
        configurarRecycler()
    }

    private fun configurarRecycler() {
        adaptador = AdaptadorTienda(mutableListOf(), this)
        var gridLayout = GridLayoutManager(requireActivity(), 2)
        getAllTiendas()
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = gridLayout
            adapter = adaptador
        }
    }

    private fun getAllTiendas() {
        consultaCorrutinas()

    }

    private fun consultaCorrutinas() {
        GlobalScope.launch(Dispatchers.IO) {
            tiendas = db.getAllTiendas();
            launch(Dispatchers.Main) {
                adaptador.setTiendas(tiendas)
            }
        }
    }


    override fun editar(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onFavorito(tienda: Tienda) {
        if (tienda.esFavorito == 0)
            tienda.esFavorito = 1
        else
            tienda.esFavorito = 0
        db.updateTienda(tienda)
        adaptador.update(tienda)


    }

    override fun borrarTienda(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            db.deleteTienda(id)
            launch(Dispatchers.Main)
            {
                adaptador.borrar(id)
            }

        }


    }


}



