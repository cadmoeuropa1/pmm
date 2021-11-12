package com.rdpp.solicitud

import android.Manifest

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.rdpp.solicitud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWebpage.setOnClickListener {
            val intentWeb = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.santamaria-artesano.org/"))
            //if (intentWeb.resolveActivity(packageManager) != null) {
            startActivity(intentWeb)
            /*  } else {
                  Snackbar.make(binding.rootlayout,
                      "No se ha encontrado navegador",
                      Snackbar.LENGTH_SHORT).show()
              }*/
        }
        binding.btnCallphone.setOnClickListener {
            //Comprobar si se tiene permiso
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(binding.rootlayout, "Permiso No Concedido",
                    Snackbar.LENGTH_LONG).show()
                //conceder permiso
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PHONE)
                ) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(getString(R.string.titulopermiso))
                    builder.setMessage(getString(R.string.explicacionpermiso))
                    builder.setPositiveButton(getString(R.string.aceptar)) {
                        _,_ -> ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE), 123)
                    }
                    builder.setNegativeButton(getString(R.string.cancelar), null)
                    builder.show()
                } else {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE), 123)
                }
            } else {
                Snackbar.make(binding.rootlayout, "Permiso Concedido",
                    Snackbar.LENGTH_LONG).show()
                llamada()
            }
        }
    }
            override fun onRequestPermissionsResult(
                requestCode: Int,
                permissions: Array<out String>,
                grantResults: IntArray,
            ) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
                when (requestCode) {
                    123 -> {
                        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            Snackbar.make(binding.rootlayout,
                                "Acabas de conceder el permiso",
                                Snackbar.LENGTH_SHORT).show()
                            llamada()
                        } else {
                            Snackbar.make(binding.rootlayout,
                                "No has concedido el permiso",
                                Snackbar.LENGTH_SHORT).show()
                        }
                    }
                    else -> {
                        Snackbar.make(binding.rootlayout,
                            "NADA",
                            Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

            fun llamada() {
                val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:0034611686108"))
                startActivity(intentCall)
            }
        }