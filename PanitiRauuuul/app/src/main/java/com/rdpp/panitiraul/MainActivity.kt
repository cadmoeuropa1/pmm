package com.rdpp.panitiraul

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.rdpp.panitiraul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide.with(binding.root)
            .load(Uri.parse("https://www.creativefabrica.com/wp-content/uploads/2018/11/Hospital-logo-by-meisuseno-5-580x446.jpg"))
            .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.imgHospital)

        binding.btnLlamada.setOnClickListener {
            llamada()
        }
        binding.btnRegistro.setOnClickListener{
            val intentRegistro = Intent(this, Registro::class.java)
            startActivity(intentRegistro)
        }
    }

    fun llamada() {
        val phoneNumber: Uri = Uri.parse("tel:947061326")

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
                        arrayOf(Manifest.permission.CALL_PHONE), 1)
                }
                builder.setNegativeButton(getString(R.string.builderDeny), null)
                builder.show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE), 1
                )
            }
        } else {
            llamadaBien()
        }
    }

    private fun llamadaBien() {
        val phoneNumber = Uri.parse("tel:947061326")
        val intent = Intent(Intent.ACTION_CALL, phoneNumber)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,

        ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(binding.root,
                        getString(R.string.permission_granted),
                        Snackbar.LENGTH_SHORT).show()
                    llamadaBien()
                }
        }
    }
}
