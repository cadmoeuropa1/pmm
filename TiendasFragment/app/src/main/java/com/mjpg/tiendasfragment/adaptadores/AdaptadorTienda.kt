package com.mjpg.tiendasfragment.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mjpg.tiendasfragment.R
import com.mjpg.tiendasfragment.databinding.ItemStoreBinding
import com.mjpg.tiendasfragment.modelo.Tienda


class AdaptadorTienda(
    private var tiendas: MutableList<Tienda>,
    private var listener: EventosListener
) : RecyclerView.Adapter<AdaptadorTienda.ViewHolder>() {

    private lateinit var contexto: Context


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemStoreBinding.bind(view)
        fun setListener(tienda: Tienda) {

            with(binding.root) {
                setOnClickListener { listener.editar(tienda.id) }
                setOnLongClickListener {
                    listener.borrarTienda(tienda.id)
                    true
                }

            }
            binding.cbFavorite.setOnClickListener {
                listener.onFavorito(tienda)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contexto = parent.context
        val view = LayoutInflater.from(contexto).inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tienda = tiendas.get(position)
        with(holder) {
            setListener(tienda)
            binding.tvName.text = tienda.name
            binding.cbFavorite.isChecked = tienda.esFavorito.equals(0)
        }

    }

    override fun getItemCount(): Int = tiendas.size
    fun add(tienda: Tienda) {
        tiendas.add(tienda)
        notifyDataSetChanged()

    }

    fun setTiendas(tiendas: MutableList<Tienda>) {
        this.tiendas = tiendas
        notifyDataSetChanged()
    }

    fun update(tienda: Tienda) {
        val index = tiendas.indexOf(tienda)
        if (index != -1) {
            tiendas.set(index, tienda)
            notifyItemChanged(index)
        }
    }

    fun borrar(id: Long) {
        val tienda= Tienda(id,"","","","")
        val index = tiendas.indexOf(tienda)
        if (index != -1) {
            tiendas.removeAt(index)
            notifyItemRemoved(index)
        }
    }


}