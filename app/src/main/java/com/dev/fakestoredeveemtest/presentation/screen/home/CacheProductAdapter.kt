package com.dev.fakestoredeveemtest.presentation.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.fakestoredeveemtest.databinding.ItemProductBinding
import com.dev.fakestoredeveemtest.domain.models.ProductModel

class CacheProductAdapter(
    private val productModelList: List<ProductModel>,
    private val clickListener: (ProductModel) -> Unit = {}
) : RecyclerView.Adapter<CacheProductAdapter.ProductHolder>() {

    inner class ProductHolder(
        private val itemProductBinding: ItemProductBinding
    ) : RecyclerView.ViewHolder(itemProductBinding.root) {
        fun bind(productModelItem: ProductModel) {
            itemProductBinding.apply {
                root.setOnClickListener {
                    clickListener.invoke(productModelItem)
                }
                Glide.with(productImageRv)
                    .load(productModelItem.image)
                    .into(productImageRv)
                productNameRv.text = productModelItem.title
                productPriceRv.text = productModelItem.price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val productItem = productModelList[position]
        holder.bind(productItem)
    }

    override fun getItemCount(): Int = productModelList.size

}
