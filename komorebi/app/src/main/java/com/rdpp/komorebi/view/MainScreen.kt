package com.rdpp.komorebi.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.rdpp.komorebi.GlobalVariables
import com.rdpp.komorebi.R
import com.rdpp.komorebi.databinding.ActivityMainScreenBinding
import com.rdpp.komorebi.model.Contact

class MainScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(binding.root)
        selectEmergencyContact()

        if (GlobalVariables.uType == 0) {
            binding.navigationView.menu.removeItem(R.id.btn_Chat)
            binding.navigationView.menu.removeItem(R.id.btn_Forum)

        }

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.imgProfile.setOnLongClickListener {
            //Comprobar si se tiene permiso
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                Snackbar.make(
                    binding.rootLayout, "Permiso No Concedido",
                    Snackbar.LENGTH_LONG
                ).show()
                //conceder permiso
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CALL_PHONE
                    )
                ) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Permiso para realizar llamadas")
                    builder.setMessage("Este permiso es indispensable para que la aplicación se ejecute y funcione correctamente")
                    builder.setPositiveButton("Aceptar") { _, _ ->
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.CALL_PHONE), 123
                        )
                    }
                    builder.setNegativeButton("Cancelar", null)
                    builder.show()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CALL_PHONE), 123
                    )
                }
            } else {
                Snackbar.make(
                    binding.rootLayout, "Llamando a al servicio de emergencias...",
                    Snackbar.LENGTH_LONG
                ).show()
                makeCall()
            }
            false
        }


        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.btn_News -> {
                    startActivity(Intent(this, NewsActivity::class.java))
                    true
                }
                R.id.btn_Chat -> {
                    startActivity(Intent(this, ChatsActivity::class.java))
                    true
                }
                R.id.btn_Forum -> {
                    startActivity(Intent(this, ForumActivity::class.java))
                    true
                }
                R.id.btn_Workshop -> {
                    startActivity(Intent(this, WorkshopActivity::class.java))
                    true
                }
                R.id.menu_Information -> {
                    startActivity(Intent(this, InformationActivity::class.java))
                    true
                }
                R.id.menu_Profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> {
                    false
                }
            }
        }
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

    fun makeCall() {
        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:0034112"))
        startActivity(intentCall)
    }
}