package com.projectbangkit.originfoodindonesia.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

import com.projectbangkit.originfoodindonesia.R
import com.projectbangkit.originfoodindonesia.adapter.AdapterBuku
import com.projectbangkit.originfoodindonesia.adapter.AdapterProduk
import com.projectbangkit.originfoodindonesia.adapter.AdapterSlider
import com.projectbangkit.originfoodindonesia.databinding.FragmentHomeBinding
import com.projectbangkit.originfoodindonesia.model.ModelTempat
import com.projectbangkit.originfoodindonesia.model.ModelProduk


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    lateinit var vpSlider : ViewPager
    lateinit var rvMk: RecyclerView
    lateinit var rvMakanan:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        (requireActivity() as AppCompatActivity).window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)

        val arraySlider = ArrayList<Int>()
        arraySlider.add(R.drawable.food)
        arraySlider.add(R.drawable.food)
        arraySlider.add(R.drawable.food)
        arraySlider.add(R.drawable.food)

        val adapterSlider = AdapterSlider(arraySlider, activity)
        vpSlider.adapter = adapterSlider


        // manggil item produk
        rvMk = view.findViewById(R.id.rv_baju)
        val adapterBaju = AdapterProduk(arrayBaju, activity)
        val lm = LinearLayoutManager(activity)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        rvMk.layoutManager = lm
        rvMk.setHasFixedSize(true)
        rvMk.adapter = adapterBaju

        // manggil item buku
        rvMakanan = view.findViewById(R.id.rv_buku)
        val adapterBuku = AdapterBuku(arrayBuku, activity)
        val lmB = LinearLayoutManager(activity)
        lmB.orientation = LinearLayoutManager.HORIZONTAL
        rvMakanan .layoutManager = lmB
        rvMakanan .setHasFixedSize(true)
        rvMakanan .adapter = adapterBuku


        return view
    }

    // array profuk
    val arrayBaju: ArrayList<ModelProduk>get() {
        val arrMk = ArrayList<ModelProduk>()
        val produkMakanan1 = ModelProduk()
        produkMakanan1.namaProduk = "Serabi"
        produkMakanan1.hargaProduk = "Rp. 100.000,-"
        produkMakanan1.gambarProduk = R.drawable.serabi

        val produkMakanan2 = ModelProduk()
        produkMakanan2.namaProduk = "Bika Ambon"
        produkMakanan2.hargaProduk = "Rp. 99.000,-"
        produkMakanan2.gambarProduk = R.drawable.bika_ambon

        val produkMakanan3 = ModelProduk()
        produkMakanan3.namaProduk = "Lumpia"
        produkMakanan3.hargaProduk = "Rp. 100.000,-"
        produkMakanan3.gambarProduk = R.drawable.lumpia

        val produkMakanan4 = ModelProduk()
        produkMakanan4.namaProduk = "Pempek"
        produkMakanan4.hargaProduk = "Rp. 100.000,-"
        produkMakanan4.gambarProduk = R.drawable.pempek

        val produkMakanan5 = ModelProduk()
        produkMakanan5.namaProduk = "Rendang"
        produkMakanan5.hargaProduk = "Rp. 100.000,-"
        produkMakanan5.gambarProduk = R.drawable.rendang

        val produkMakanan6 = ModelProduk()
        produkMakanan6.namaProduk = "Ayam Betutu"
        produkMakanan6.hargaProduk = "Rp. 100.000,-"
        produkMakanan6.gambarProduk = R.drawable.ayam_betutu

        arrMk.add(produkMakanan1)
        arrMk.add(produkMakanan2)
        arrMk.add(produkMakanan3)
        arrMk.add(produkMakanan4)
        arrMk.add(produkMakanan5)
        arrMk.add(produkMakanan6)

        return arrMk
    }

    // array profuk
    val arrayBuku: ArrayList<ModelTempat>get() {
        val arrBuk = ArrayList<ModelTempat>()
        val produkTokoh4 = ModelTempat()
        produkTokoh4.namaBuku = "Soto Banjar"
        produkTokoh4.hargaBuku = "Rp. 100.000,-"
        produkTokoh4.gambarBuku = R.drawable.soto_banjar

        val produkTokoh3 = ModelTempat()
        produkTokoh3.namaBuku = "Ayam Betutu"
        produkTokoh3.hargaBuku = "Rp. 100.000,-"
        produkTokoh3.gambarBuku = R.drawable.ayam_betutu

        val produkTokoh2 = ModelTempat()
        produkTokoh2.namaBuku = "Pempek"
        produkTokoh2.hargaBuku = "Rp. 100.000,-"
        produkTokoh2.gambarBuku = R.drawable.pempek




        arrBuk.add(produkTokoh4)
        arrBuk.add(produkTokoh3)
        arrBuk.add(produkTokoh2)


        return arrBuk
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}