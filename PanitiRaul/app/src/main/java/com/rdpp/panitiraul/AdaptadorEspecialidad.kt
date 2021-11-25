package com.rdpp.panitiraul

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rdpp.panitiraul.databinding.LayoutEspecialidadBinding

class AdaptadorEspecialidad(
    private val listaEspecialdad: List<Especialidad>,
    private val evt: EventosListener,
) : RecyclerView.Adapter<AdaptadorEspecialidad.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.layout_especialidad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val especialidad = listaEspecialdad.get(position)
        with(holder) {
            setListener(especialidad)
            binding.txtCodigoEspecialidad.text = especialidad.codigoEspecialidad.toString()
            binding.txtNombreEspecialidad.text = especialidad.nombre
            binding.txtAnnosMinimos.text = especialidad.annos.toString()

        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutEspecialidadBinding.bind(view)
        fun setListener(especialidad: Especialidad) {
            binding.root.setOnLongClickListener {
                evt.onLongClickListener(especialidad)
                true
            }
        }
    }

    override fun getItemCount() = listaEspecialdad.size
}
