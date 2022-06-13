package com.projectbangkit.originfoodindonesia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectbangkit.originfoodindonesia.model.ModelTempat
import kotlinx.android.synthetic.main.activity_detail_makanan.*

class DetailMakananActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_makanan)

        val data = intent.getParcelableExtra<ModelTempat>("DTL")
        if (data != null) {
            tv_nama_buku.text = data.namaBuku
            tv_harga_buku.text = data.hargaBuku
            img_detail.setImageResource(data.gambarBuku)
        }
    }
}