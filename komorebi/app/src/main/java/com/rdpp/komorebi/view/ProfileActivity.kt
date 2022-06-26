package com.rdpp.komorebi.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
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
import com.rdpp.komorebi.model.Contact


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var db: KomorebiDAO


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        db = KomorebiDAO(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.txtUser.text = GlobalVariables.uLogged.user
        binding.txtAlias.text = GlobalVariables.uLogged.alias
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
                                binding.txtAlias.text = input.text.toString()
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
                                updateProfilePicture(input.text.toString())
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
                    selectEmergencyContact()
                    true
                }

                else -> {
                    false
                }
            }
        }

    }

    private fun updateProfilePicture(url: String) {
        Glide.with(binding.root)
            .load(url)
            .circleCrop().diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(binding.imgProfile)
    }

    fun selectEmergencyContact() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, 1)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                val contactData: Uri? = data!!.data
                val cursor: Cursor = managedQuery(contactData, null, null, null, null)
                if (cursor.moveToFirst()) {
                    val id =
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                    val hasPhone =
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    var phoneNumber: String? = null
                    try {
                        if (hasPhone.equals("1")) {
                            val phones: Cursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                null,
                                null
                            )!!
                            phones.moveToFirst()
                            phoneNumber = phones.getString(phones.getColumnIndexOrThrow("data1"))
                        }
                        val name =
                            cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                        GlobalVariables.uLogged.emergencyContact = Contact(name, phoneNumber!!)
                    } catch (e: Exception) {
                    }
                }
            }
        }
    }
}

