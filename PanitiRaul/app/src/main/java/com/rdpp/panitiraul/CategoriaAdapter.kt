package com.rdpp.panitiraul

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.panitiraul.databinding.CategoriaTarjetaBinding

class CategoriaAdapter(

    private var categorias: MutableList<Categoria>,
    private var listener: CategoriaEventListener
) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    private lateinit var database: EmpresaDAO
    private lateinit var contexto: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CategoriaTarjetaBinding.bind(view)
        fun setListener(categoria: Categoria) {
            with(binding.root) {
                setOnClickListener { listener.onClick(categoria) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contexto = parent.context
        database = EmpresaDAO(contexto)
        val view = LayoutInflater.from(contexto).inflate(R.layout.categoria_tarjeta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categorias[position]
        with(holder) {
            setListener(categoria)
            binding.txtNombre.text = categoria.nombre as Editable
        }

    }

    fun setCategorias(categorias: MutableList<Categoria>) {
        this.categorias = categorias
        notifyDataSetChanged()
    }

    override fun getItemCount() = categorias.size
}