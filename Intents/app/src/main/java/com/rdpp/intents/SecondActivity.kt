package com.rdpp.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rdpp.intents.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //var textReceived = intent.getStringExtra(MainActivity.NAME)
        val user = intent.getParcelableExtra<User>(MainActivity.USER)
        binding.tvNameReceived.text =
            getString(R.string.msgAccept, user?.name + " " + user?.surname)
        //binding.tvNameReceived.text = getString(R.string.msgAccept, textReceived)


        binding.btCancel.setOnClickListener(cancelButton)
        binding.btAccept.setOnClickListener(acceptButton)
    }

    val cancelButton: View.OnClickListener = View.OnClickListener {
        /*val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)*/
        setResult(RESULT_CANCELED)
        finish()
    }
    val acceptButton: View.OnClickListener = View.OnClickListener {
        /*var rating = ""
        if (!binding.ratingBar.rating.toString().contains(".5")) {
            rating = binding.ratingBar.rating.toString().filterNot { c -> ".0".contains(c) }

        } else {
            rating = binding.ratingBar.rating.toString()
        }
        Toast.makeText(this,
            "Rating is " + rating + " stars", Toast.LENGTH_LONG).show()
    }*/
        val intentResult: Intent = Intent()
        intentResult.putExtra("valores", binding.ratingBar.rating)
        setResult(RESULT_OK, intentResult)
        finish()
    }
}