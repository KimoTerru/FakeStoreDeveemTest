package com.dev.fakestoredeveemtest.presentation.screen.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.fakestoredeveemtest.databinding.ItemCardProductBinding
import com.dev.fakestoredeveemtest.domain.models.ProductModel

class CardProductAdapter(
    private val productModelList: List<ProductModel>,
    private val clickListener: (ProductModel) -> Unit = {}
) : RecyclerView.Adapter<CardProductAdapter.ProductCardHolder>() {

    inner class ProductCardHolder(
        private val itemCardProductBinding: ItemCardProductBinding
    ) : RecyclerView.ViewHolder(itemCardProductBinding.root) {
        fun bind(productModelItem: ProductModel) {
            itemCardProductBinding.apply {
                removeProductRv.setOnClickListener {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCardHolder {
        return ProductCardHolder(
            ItemCardProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductCardHolder, position: Int) {
        val productItem = productModelList[position]
        holder.bind(productItem)
    }

    override fun getItemCount(): Int = productModelList.size

}
