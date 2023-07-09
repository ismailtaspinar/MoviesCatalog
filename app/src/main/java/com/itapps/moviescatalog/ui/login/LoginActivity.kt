package com.itapps.moviescatalog.ui.login


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itapps.moviescatalog.MainActivity
import com.itapps.moviescatalog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            login.setOnClickListener {
                goToMain()
            }
            signInWithGoogle.setOnClickListener {
                goToMain()
            }
        }
    }

    private fun goToMain(){
        val intent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
