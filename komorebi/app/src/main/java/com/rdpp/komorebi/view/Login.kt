package com.rdpp.komorebi.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.rdpp.komorebi.GlobalVariables
import com.rdpp.komorebi.database.KomorebiDAO
import com.rdpp.komorebi.databinding.ActivityLoginBinding
import java.util.*

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: KomorebiDAO
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onResume() {
        super.onResume()
        if (database.deleteInvited("invitada") != -1) {
            Snackbar.make(
                binding.root,
                "Perfil de invitada eliminado con éxito",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        database = KomorebiDAO(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database.insertExamples()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)


        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ), 123
        )

        binding.btnInvited.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            database.insertUser(
                UUID.randomUUID().toString(),
                "invitada",
                "invitada",
                "https://us.123rf.com/450wm/tanyadanuta/tanyadanuta1910/tanyadanuta191000037/148019346-dibujado-a-mano-moderno-icono-de-perfil-de-avatar-de-mujer-o-icono-de-retrato-icono-de-avatar-plano-.jpg?ver=6",
                1,
                "invitada"
            )
            GlobalVariables.uType = 0; GlobalVariables.uLogged = database.getInvitedUser()
            startActivity(intent)
        }

        binding.btnRegistered.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            GlobalVariables.uType = 1
            startActivity(intent)
        }

        binding.imgEmergency.setOnLongClickListener {
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
                    Snackbar.make(
                        binding.rootLayout,
                        "Acabas de conceder el permiso",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        binding.rootLayout,
                        "No has concedido el permiso",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            else -> {
                Snackbar.make(
                    binding.rootLayout,
                    "NADA",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun makeCall() {
        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:0034112"))
        startActivity(intentCall)
        val location = getLastKnownLocation()
        val smsManager = SmsManager.getDefault()
        //smsManager.sendTextMessage()
    }

    fun getLastKnownLocation(): MutableList<Double> {
        val coordinates: MutableList<Double> = arrayListOf()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        coordinates.add(location.latitude)
                        coordinates.add(location.longitude)
                    }
                }
        }
        return coordinates
    }
}