package com.rdpp.komorebi.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.rdpp.komorebi.GlobalVariables
import com.rdpp.komorebi.R
import com.rdpp.komorebi.database.KomorebiDAO
import com.rdpp.komorebi.databinding.ActivityProfileBinding


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var db: KomorebiDAO


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        db = KomorebiDAO(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Glide.with(binding.root)
            .load(GlobalVariables.uLogged.profilePicture)
            .circleCrop().diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(binding.imgProfile)

        binding.nvProfileSettings.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mnu_ChangeAlias -> {
                    val builder = AlertDialog.Builder(this)
                    val input = EditText(this)
                    with(builder) {
                        setTitle("Cambiar alias")
                        setMessage("Para cambiar su alias introduzca un nuevo alias a continuación")
                        setView(input)
                        setNegativeButton("CANCELAR", null)
                        setPositiveButton("ACEPTAR") { _, _ ->
                            if (input.text.toString() != "") {
                                db.updateAlias(GlobalVariables.uLogged, input.text.toString())
                                Snackbar.make(
                                    binding.root,
                                    "Alias actualizado con éxito",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "El campo no puede estar en blanco.",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                        show()
                    }
                    true
                }

                R.id.mnu_ChangeProfilePicture -> {
                    val builder = AlertDialog.Builder(this)
                    val input = EditText(this)
                    with(builder) {
                        setTitle("Cambiar foto de perfil")
                        setMessage("Para cambiar su foto de perfil debe introducir la dirección web de la imagen a seleccionar")
                        setView(input)
                        setNegativeButton("CANCELAR", null)
                        setPositiveButton("ACEPTAR") { _, _ ->
                            if (input.text.toString() != "") {
                                db.updateProfilePicture(
                                    GlobalVariables.uLogged,
                                    input.text.toString()
                                )
                                Snackbar.make(
                                    binding.root,
                                    "Foto de perfil actualizada con éxito",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "El campo no puede estar en blanco.",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                        show()
                    }
                    true
                }

                R.id.mnu_ContactSettings -> {
                    true
                }

                else -> {
                    false
                }
            }
        }

    }
}