package com.projectbangkit.originfoodindonesia.ui.user

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.projectbangkit.originfoodindonesia.LoginActivity
import com.projectbangkit.originfoodindonesia.UpdateEmailActivity
import com.projectbangkit.originfoodindonesia.UpdatePasswordActivity
import com.projectbangkit.originfoodindonesia.databinding.FragmentAkunBinding
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class AkunFragment : Fragment() {

    private var _binding: FragmentAkunBinding? = null
    lateinit var auth: FirebaseAuth
    lateinit var imgUri: Uri

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        ViewModelProvider(this).get(UserViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        binding.imgProfile.setOnClickListener {
            goToCamera()
        }

        binding.btnLogout.setOnClickListener {
            tombolkeluar()
        }



        if (user != null) {
            binding.edtEmail.setText(user.email)
            binding.edtName.setText(user.displayName)

            if (user.photoUrl != null) {
                Picasso.get().load(user.photoUrl).into(binding.imgProfile)
            } else {
                Picasso.get().load("https://i.ibb.co/HXRkydR/Whats-App-Image-2022-04-04-at-11-18-00.jpg").into(binding.imgProfile)
            }

            if (user.isEmailVerified) {
                binding.imgSudahVerifikasi.visibility = View.VISIBLE
                binding.imgBelumVerifikasi.visibility = View.GONE
            } else {
                binding.imgBelumVerifikasi.visibility = View.VISIBLE
                binding.imgSudahVerifikasi.visibility = View.GONE

            }
        }

        binding.btnSave.setOnClickListener saveProfile@{

            val image = when {
                ::imgUri.isInitialized -> imgUri
                user?.photoUrl == null -> Uri.parse("https://bit.ly/3wc3Lcn")
                else -> user.photoUrl
            }

            val name = binding.edtName.text.toString()

            if (name.isEmpty()) {
                binding.edtName.error = "Nama Belum Di isi"
                binding.edtName.requestFocus()
                return@saveProfile
            }

            //update disini
            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val toast = Toast.makeText(
                                activity,
                                "Data Profile Berhasil Disimpan !",
                                Toast.LENGTH_SHORT
                            )
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                            toast.show()
                        } else {
                            Toast.makeText(
                                activity,
                                "${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                }

        }

        binding.btnVerifikasi.setOnClickListener {
            user?.sendEmailVerification()?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Email Verifikasi Berhasil Dikirim!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnUpdateEmail.setOnClickListener {
            val i = Intent(context, UpdateEmailActivity::class.java)
            startActivity(i)
        }

        binding.btnUpdatePasword.setOnClickListener {
            val i = Intent(context,     UpdatePasswordActivity::class.java)
            startActivity(i)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CAM && resultCode == RESULT_OK) {
            val imgBitmap = data?.extras?.get("data") as Bitmap

            uploadImgToFirebase(imgBitmap)
        }
    }

    private fun uploadImgToFirebase(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref =
            FirebaseStorage.getInstance().reference.child("img_user/${FirebaseAuth.getInstance().currentUser?.email}")
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val img = baos.toByteArray()
        ref.putBytes(img)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    ref.downloadUrl.addOnCompleteListener { Task ->
                        Task.result?.let { Uri ->
                            imgUri = Uri
                            binding.imgProfile.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }

    }

    private fun goToCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->

            activity?.packageManager?.let {
                intent?.resolveActivity(it).also {
                    startActivityForResult(intent, REQ_CAM)
                }
            }
        }
    }

    private fun tombolkeluar() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val i = Intent(context, LoginActivity::class.java)
        startActivity(i)
        activity?.finish()
    }

    companion object {
        const val REQ_CAM = 100
    }
}