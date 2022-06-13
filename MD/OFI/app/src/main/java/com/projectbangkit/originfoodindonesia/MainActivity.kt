package com.projectbangkit.originfoodindonesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import com.projectbangkit.originfoodindonesia.databinding.ActivityMainBinding
import com.projectbangkit.originfoodindonesia.ui.user.AkunFragment
import com.projectbangkit.originfoodindonesia.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottonNavView: BottomNavigationView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        bottonNavView = binding.bottomBarMenu

        val homeFragment = HomeFragment()
        val akunFragment = AkunFragment()

        setThatFragment(homeFragment)

        bottonNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    setThatFragment(homeFragment)
                }
                R.id.akun ->{
                    setThatFragment(akunFragment)
                }
            }
            true
        }
        binding.fabCamera.setOnClickListener {
            val intent = Intent(this@MainActivity , CameraActivity::class.java)
            startActivity(intent)
        }
        //val conditions = CustomModelDownloadConditions.Builder()
           // .requireWifi()
          //  .build()
        //FirebaseModelDownloader.getInstance()
          //  .getModel("ofi", DownloadType.LOCAL_MODEL, conditions)
          //  .addOnCompleteListener {
                // Download complete. Depending on your app, you could enable the ML
                // feature, or switch from the local model to the remote model, etc.
          //  }

    }

    private fun setThatFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame,fragment)
            commit()
        }
}