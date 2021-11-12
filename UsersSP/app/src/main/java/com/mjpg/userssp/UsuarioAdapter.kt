package com.mjpg.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mjpg.userssp.databinding.ItemUsersAltBinding

class UsuarioAdapter(private val usuarios: List<Usuario>, private val evt: EventosListener) :
    RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_users_alt, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = usuarios.get(position)
        with(holder) {
            setListener(usuario, (position + 1))
            binding.tvName.text = usuario.personalizado()
            binding.tvOrder.text = (position + 1).toString()
            Glide.with(context)
                .load(usuario.url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(binding.imgPhoto)
        }


    }

    override fun getItemCount(): Int = usuarios.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUsersAltBinding.bind(view)
        fun setListener(usuario: Usuario, position: Int) {
            binding.root.setOnClickListener {
                evt.onClickListener(usuario, position)
            }
        }
    }

}