package com.mjpg.tiendasfragment.vista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mjpg.tiendasfragment.adaptadores.AdaptadorTienda
import com.mjpg.tiendasfragment.adaptadores.EventosListener
import com.mjpg.tiendasfragment.modelo.Tienda
import com.mjpg.tiendasfragment.bd.TiendasDAO
import com.mjpg.tiendasfragment.databinding.ConsultaBinding
import com.mjpg.tiendasfragment.vistamodelo.VistaModelo
import com.mjpg.tiendasfragment.vistamodelo.VistaModeloFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ConsultaFragment : Fragment(), EventosListener {

    private lateinit var mBinding: ConsultaBinding
    private var mActivity: MainActivity? = null
    private lateinit var tiendas: MutableList<Tienda>
    private lateinit var db: TiendasDAO;
    private lateinit var adaptador: AdaptadorTienda
    private lateinit var viewModelo: VistaModelo

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
        val viewModeloFactory= VistaModeloFactory(0)
        viewModelo = ViewModelProvider(this.requireActivity(),viewModeloFactory).get(VistaModelo::class.java)
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
        viewModelo.setIdentificador(id)
        //mActivity?.editar(id)
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



