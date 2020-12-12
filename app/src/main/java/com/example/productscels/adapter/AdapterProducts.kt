package com.example.productscels.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.productscels.R
import com.example.productscels.pojos.ProductsItem
import kotlinx.android.synthetic.main.celulares.view.*

class AdapterProducts( var mProductsId: ProductsId ) : RecyclerView.Adapter<AdapterProducts.MyViewHolder> () {
    private var mList = emptyList<ProductsItem>()

    fun updateListProducts(mlist: List<ProductsItem>) {
        mList = mList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mProductsimg = itemView.imgCel
        val mName = itemView.name
        val mId = itemView.codigo
        val mPrice = itemView.price

        val itemView = itemView.setOnClickListener {
            mProductsId.passData(mList[adapterPosition])
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.celulares,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mProductsItem: ProductsItem = mList[position]

        holder.mId.text = mProductsItem.id.toString()
        holder.mName.text = mProductsItem.name
        holder.mPrice.text = mProductsItem.price.toString()

        //mefalta una parte
        Glide.with(holder.itemView.context)
            .load(mProductsItem.image)
            .transform(CenterCrop(), RoundedCorners(20))
            .into(holder.mProductsimg)

    }

    override fun getItemCount() = mList.size

    interface  ProductsId{
        fun passData(mProductId: ProductsItem)
    }

}