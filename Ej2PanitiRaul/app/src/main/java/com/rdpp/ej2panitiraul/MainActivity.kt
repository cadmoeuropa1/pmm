package com.rdpp.ej2panitiraul

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            "https://www.hotelciudaddeburgos.com/files/catedral-de-burgos.jpg",
            "Catedral de Burgos",
            "Plaza de Santa María, s/n",
            "info@catedraldeburgos.es",
            Uri.parse("tel:649785940"),
            "Catedral de Burgos. Ha sido destruida 2 veces"
        )
        val monument2 =
            Monument(
                "https://www.terranostrum.es/images/content/full/arco-santa-maria-burgos-1.jpg",
                "Arco de Santa María",
                "Plaza Rey San Fernando, 9",
                "",
                Uri.parse("tel:947288868"),
                "El Arco de Santa María es uno de los monumentos más emblemáticos de la ciudad de Burgos"
            )
        val monument3 =
            Monument(
                "https://www.laguiago.com/wp-content/uploads/2015/09/paseo-del-espolon-min.jpg",
                "Paso del Espolón",
                "Paseo del Espolón",
                "",
                Uri.parse(""),
                "Este paseo empieza desde el Teatro Principal, justo en frente de la estatua del Cid y llega hasta el Arco de Santamaría. Los árboles a cada lado del paseo dan un toque especial en cada estación a este paseo, por lo que siempre es muy agradable"
            )
        val monument4 =
            Monument(
                "https://www.guiasturisticosburgos.com/media/galerias/monasterio-de-las-huelgas-id-603.jpg",
                "Monasterio de las Huelgas",
                "Plaza Compás, s/n",
                "info@patrimonionacional.es",
                Uri.parse("tel:947288868"),
                "El monasterio de Santa María la Real de las Huelgas, conocido popularmente como monasterio de las Huelgas es un monasterio de la congregación de monasterios de monjas cistercienses de San Bernardo"
            )
        val monument5 =
            Monument(
                "https://www.terranostrum.es/images/content/full/Castillo-burgos-1.jpg",
                "Castillo de Burgos",
                "Cerro de San Miguel, s/n",
                "turismo@aytoburgos.es",
                Uri.parse("tel:947288874"),
                "El castillo de Burgos es una fortaleza situada en la cumbre del cerro del Castillo, elevado 75 m sobre el nivel de la ciudad. La primera torre fue levantada por el conde Diego Porcelos en los tiempos de la Reconquista, en el año 884"
            )

        monuments.add(monument1)
        monuments.add(monument2)
        monuments.add(monument3)
        monuments.add(monument4)
        monuments.add(monument5)

        return monuments
    }

    override fun onLongClickListener(monument: Monument, position: Int) {
        val intent = Intent(this, MonumentInfo::class.java)
        intent.putExtra("monument", monument)
    }

    override fun call(monument: Monument, position: Int) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            Snackbar.make(
                binding.root, "Call permit is not conceded",
                Snackbar.LENGTH_LONG
            ).show()
            //conceder permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CALL_PHONE
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE), 123
                )
            }
        } else {
            val intentCall = Intent(Intent.ACTION_CALL, monument.phoneNumber)
            startActivity(intentCall)
        }

    }

    @SuppressLint("IntentReset")
    override fun sendMail(monument: Monument, position: Int) {
        val mail = monument.mail
        val intentMail = Intent(Intent.ACTION_SEND)
        intentMail.data = Uri.parse("mailto:")
        intentMail.type = "text/plain"
        intentMail.putExtra(Intent.EXTRA_EMAIL, mail)

        startActivity(Intent.createChooser(intentMail, "Send Mail"))
    }
}