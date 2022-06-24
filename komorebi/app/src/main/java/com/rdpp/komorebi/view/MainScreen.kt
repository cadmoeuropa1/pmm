package com.rdpp.komorebi.view

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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
}