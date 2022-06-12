package com.projectbangkit.originfoodindonesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.projectbangkit.originfoodindonesia.databinding.ActivityUpdatePasswordBinding

class UpdatePasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdatePasswordBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        binding.cdvInptPassword.visibility = View.VISIBLE
        binding.cdvUpdatePassword.visibility = View.GONE

        binding.btnOldKonfirmasi.setOnClickListener {
            val password = binding.edtOldPassword.text.toString()

            if (password.isEmpty()){
                binding.edtOldPassword.error = "Password Tidak Bolrh Kosong"
                binding.edtOldPassword.requestFocus()
                return@setOnClickListener
            }

            //Buat Credential User (untuk password) untuk pemberitahuan error yang lainnya
            user.let {
                val userCredential = EmailAuthProvider.getCredential(it?.email!!, password)
                // ngecek apakah password ini punya si user login skrng
                it.reauthenticate(userCredential).addOnCompleteListener { Task ->
                    when{
                        Task.isSuccessful -> {
                            binding.cdvInptPassword.visibility = View.GONE
                            binding.cdvUpdatePassword.visibility = View.VISIBLE
                        }
                        Task.exception is FirebaseAuthInvalidCredentialsException -> {
                            binding.edtOldPassword.error = "Password Salah !"
                            binding.edtOldPassword.requestFocus()
                        }
                        else -> {
                            val toast =   Toast.makeText(this, "${Task.exception?.message}", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP, 0, 0)
                            toast.show()
                        }
                    }
                }
            }

            binding.btnUpdatePassword.setOnClickListener updatePassword@{
                val newPassword = binding.edtNewPassword.text.toString()
                val newPasswordConf = binding.edtNewPasswordConfirmation.text.toString()

                if (newPassword.isEmpty()){
                    binding.edtNewPassword.error = "password Tidak Boleh Kosong !"
                    binding.edtNewPassword.requestFocus()
                    return@updatePassword
                }

                if (newPasswordConf.isEmpty()){
                    binding.edtNewPasswordConfirmation.error = "Password Konfirmasi Tidak Boleh Kosong !"
                    binding.edtNewPasswordConfirmation.requestFocus()
                    return@updatePassword
                }

                if (newPassword.length < 6){
                    binding.edtNewPassword.error = "Password Minimal 6 Karakter !"
                    binding.edtNewPassword.requestFocus()
                    return@updatePassword
                }

                if (newPasswordConf.length < 6){
                    binding.edtNewPasswordConfirmation.error = "Password Konfirmasi Minimal 6 Karakter !"
                    binding.edtNewPasswordConfirmation.requestFocus()
                    return@updatePassword
                }

                if (newPassword != newPasswordConf){
                    binding.edtNewPasswordConfirmation.error = "Password Tidak Sama !"
                    binding.edtNewPasswordConfirmation.requestFocus()
                    return@updatePassword
                }

                user?.let {
                    user.updatePassword(newPassword).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this, "Password Berhasil DI Update silahkan login kembali", Toast.LENGTH_SHORT).show()
                            logout()
                        }
                    }
                }


            }
        }
    }

    private fun logout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
}