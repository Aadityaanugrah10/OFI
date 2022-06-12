package com.projectbangkit.originfoodindonesia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectbangkit.originfoodindonesia.model.ModelProduk
import kotlinx.android.synthetic.main.activity_detail_produk.*

class DetailProdukActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        val data = intent.getParcelableExtra<ModelProduk>("DTL")
        if (data != null) {
            tv_nama_produk.text = data.namaProduk
            tv_harga_produk.text = data.hargaProduk
            img_detail.setImageResource(data.gambarProduk)
        }
    }
}