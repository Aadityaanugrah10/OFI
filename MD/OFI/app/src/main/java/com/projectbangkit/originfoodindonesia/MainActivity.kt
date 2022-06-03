package com.projectbangkit.originfoodindonesia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.projectbangkit.originfoodindonesia.databinding.ActivityMainBinding
import com.projectbangkit.originfoodindonesia.fragment.AkunFragment
import com.projectbangkit.originfoodindonesia.fragment.HomeFragment
import com.projectbangkit.originfoodindonesia.fragment.KeranjangFragment
import com.projectbangkit.originfoodindonesia.fragment.RiwayatFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottonNavView: BottomNavigationView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        bottonNavView = binding.bottonNav

        val homeFragment = HomeFragment()
        val keranjangFragment = KeranjangFragment()
        val riwayatFragment = RiwayatFragment()
        val akunFragment = AkunFragment()

        setThatFragment(homeFragment)

        bottonNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    setThatFragment(homeFragment)
                }
                R.id.keranjang ->{
                    setThatFragment(keranjangFragment)
                }
                R.id.riwayat ->{
                    setThatFragment(riwayatFragment)
                }
                R.id.akun ->{
                    setThatFragment(akunFragment)
                }
            }
            true
        }
    }

    private fun setThatFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame,fragment)
            commit()
        }
}