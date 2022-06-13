package com.projectbangkit.originfoodindonesia.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projectbangkit.originfoodindonesia.DetailProdukActivity
import com.projectbangkit.originfoodindonesia.R
import com.projectbangkit.originfoodindonesia.model.ModelProduk

class AdapterProduk(var dataProduk : ArrayList<ModelProduk>, var context : Activity?) : RecyclerView.Adapter<AdapterProduk.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNamaProduk = view.findViewById<TextView>(R.id.tv_nama)
        val tvHargaProduk = view.findViewById<TextView>(R.id.tv_harga)
        val imgGambarProduk = view.findViewById<ImageView>(R.id.img_produk)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(tampil: MyViewHolder, position: Int) {
        tampil.tvNamaProduk.text = dataProduk[position].namaProduk
        tampil.tvHargaProduk.text = dataProduk[position].hargaProduk
        tampil.imgGambarProduk.setImageResource(dataProduk[position].gambarProduk)

        val cont = tampil.itemView.context
        tampil.itemView.setOnClickListener {
            val i = Intent(cont, DetailProdukActivity::class.java)
            i.putExtra("DTL", dataProduk?.get(position))
            cont.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return dataProduk.size
    }


}