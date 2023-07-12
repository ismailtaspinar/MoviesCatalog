package com.itapps.moviescatalog.ui.login


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.itapps.moviescatalog.MainActivity
import com.itapps.moviescatalog.R
import com.itapps.moviescatalog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToMain()
        binding.apply {
            login.setOnClickListener {
                if(isEmailValid(userEmailText.text.toString()) && userPasswordText.text?.isNotEmpty()!!){
                    goToMain()
                }
                if(!isEmailValid(userEmailText.text.toString())) {
                    userEmailText.error = resources.getString(
                        R.string.invalid_username
                    )
                }
                if(userPasswordText.text?.isEmpty()!!){
                    userPasswordText.error = resources.getString(
                        R.string.invalid_password
                    )
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun goToMain(){
        val intent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
