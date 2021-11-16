package com.rdpp.ej2panitiraul

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rdpp.ej2panitiraul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), EventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var monumentAdapter: MonumentAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        monumentAdapter = MonumentAdapter(getMonuments(), this)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = monumentAdapter
        binding.recyclerview.layoutManager = linearLayoutManager
    }

    private fun getMonuments(): MutableList<Monument> {
        val monuments = mutableListOf<Monument>()
        val monument1 = Monument(
            getString(R.string.image_1),
            getString(R.string.name_1),
            getString(R.string.location_1),
            getString(R.string.mail_1),
            Uri.parse(getString(R.string.phone_1)),
            getString(R.string.description_1)
        )
        val monument2 =
            Monument(
                getString(R.string.image_2),
                getString(R.string.name_2),
                getString(R.string.location_2),
                getString(R.string.mail_2),
                Uri.parse(getString(R.string.phone_2)),
                getString(R.string.description_2)
            )
        val monument3 =
            Monument(
                getString(R.string.image_3),
                getString(R.string.name_3),
                getString(R.string.location_3),
                getString(R.string.mail_3),
                Uri.parse(""),
                getString(R.string.description_3)
            )
        val monument4 =
            Monument(
                getString(R.string.image_4),
                getString(R.string.name_4),
                getString(R.string.location_4),
                getString(R.string.mail_4),
                Uri.parse(getString(R.string.phone_4)),
                getString(R.string.description_4)
            )
        val monument5 =
            Monument(
                getString(R.string.image_5),
                getString(R.string.name_5),
                getString(R.string.location_5),
                getString(R.string.mail_5),
                Uri.parse(getString(R.string.phone_5)),
                getString(R.string.description_5)
            )

        monuments.add(monument1)
        monuments.add(monument2)
        monuments.add(monument3)
        monuments.add(monument4)
        monuments.add(monument5)

        return monuments
    }

    override fun onLongClick(monument: Monument, position: Int) {
        val intent = Intent(this, MonumentInfo::class.java)
        intent.putExtra("monument", monument)
        startActivity(intent)
    }

    override fun call(monument: Monument, position: Int) {
        val phoneNumber = monument.phoneNumber

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            Snackbar.make(
                binding.root, getString(R.string.permit_error),
                Snackbar.LENGTH_LONG
            ).show()
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)
            ) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.builderTitle))
                builder.setMessage(getString(R.string.builderMessage))
                builder.setPositiveButton(getString(R.string.builderAccept)) { _, _ ->
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE), 123)
                }
                builder.setNegativeButton(getString(R.string.builderDeny), null)
                builder.show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE), 123
                )
            }
        } else {
            if (phoneNumber.toString() == "") {
                Snackbar.make(binding.root,
                    getString(R.string.no_phone_number_error),
                    Snackbar.LENGTH_SHORT).show()
            } else {
                val intentCall = Intent(Intent.ACTION_CALL, phoneNumber)
                startActivity(intentCall)
            }
        }
    }


    @SuppressLint("IntentReset")
    override fun sendMail(monument: Monument, position: Int) {
        val TO = arrayOf(monument.mail)
        val CC = arrayOf("")
        val intentEmail = Intent(Intent.ACTION_SEND)
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.setType("text/plain");
        intentEmail.putExtra(Intent.EXTRA_EMAIL, TO);
        intentEmail.putExtra(Intent.EXTRA_CC, CC);
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "CONSULT");
        intentEmail.putExtra(Intent.EXTRA_TEXT, getString(R.string.mail_content));
        startActivity(Intent.createChooser(intentEmail, "Send Mail"))
    }
}