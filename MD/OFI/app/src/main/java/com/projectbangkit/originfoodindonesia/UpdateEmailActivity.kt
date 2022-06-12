package com.projectbangkit.originfoodindonesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.projectbangkit.originfoodindonesia.databinding.ActivityUpdateEmailBinding
import com.projectbangkit.originfoodindonesia.ui.user.AkunFragment

class UpdateEmailActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateEmailBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        binding.cdvInptPassword.visibility = View.VISIBLE
        binding.cdvUpdateEmail.visibility = View.GONE

        binding.btnKonfirmasi.setOnClickListener {
            val pass = binding.edtPassword.text.toString()
            if (pass.isEmpty()){
                binding.edtPassword.error = "Password Tidak Boleh Kosong !"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            //Buat Credential User
            user.let {
                val userCredential = EmailAuthProvider.getCredential(it?.email!!, pass)
                // ngecek apakah password ini punya si user login skrng
                it.reauthenticate(userCredential).addOnCompleteListener { Task ->
                    when{
                        Task.isSuccessful -> {
                            binding.cdvInptPassword.visibility = View.GONE
                            binding.cdvUpdateEmail.visibility = View.VISIBLE
                        }
                        Task.exception is FirebaseAuthInvalidCredentialsException -> {
                            binding.edtPassword.error = "Password Salah !"
                            binding.edtPassword.requestFocus()
                        }
                        else -> {
                            val toast =   Toast.makeText(this, "${Task.exception?.message}", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP, 0, 0)
                            toast.show()
                        }
                    }
                }
            }
            binding.btnUpdateEmail.setOnClickListener btnUpdateEmail@ {
                val email = binding.edtUpdateEmail.text.toString()
                if (email.isEmpty()){
                    binding.edtUpdateEmail.error = "Email Tidak Boleh Kosong !"
                    binding.edtUpdateEmail.requestFocus()
                    return@btnUpdateEmail
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.edtUpdateEmail.error = "Email Tidak Valid !"
                    binding.edtUpdateEmail.requestFocus()
                    return@btnUpdateEmail
                }

                user?.let {
                    user.updateEmail(email).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this, "Email Berhasil DI Update", Toast.LENGTH_SHORT).show()
                            val i = Intent(this, AkunFragment::class.java)
                            startActivity(i)

                        } else{

                        }
                    }
                }
            }
        }
    }
}