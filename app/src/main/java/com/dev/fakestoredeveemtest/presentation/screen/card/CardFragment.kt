package com.dev.fakestoredeveemtest.presentation.screen.card

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.fakestoredeveemtest.R
import com.dev.fakestoredeveemtest.databinding.FragmentCardBinding
import com.dev.fakestoredeveemtest.domain.models.ProductModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment(R.layout.fragment_card) {

    private val viewBinding: FragmentCardBinding by viewBinding()
    private val viewModel: CardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllProductsBasket()

        setupCardScreenContent()
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    private fun setupCardScreenContent() = viewBinding.apply {

        cardToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.stateScreen.observe(viewLifecycleOwner) {
            cardNestedScroll.visibility = it.showContent
            cardEmptyView.visibility = it.showEmptyBox
        }

        viewModel.productModelLiveData.observe(viewLifecycleOwner) { data ->
            productsCacheListRv.apply {
                adapter = CardProductAdapter(
                    data,
                    clickListener = {
                        deleteProduct(it)
                    }
                )
            }
        }

        buyBasketButton.apply {

            viewModel.stateBuyButton.observe(viewLifecycleOwner) {
                text = getText(it.text)
                setTextColor(ColorStateList.valueOf(requireContext().getColor(it.colorContent)))
                setBackgroundTintList(ColorStateList.valueOf(requireContext().getColor(it.backgroundColor)))
                setCompoundDrawableTintList(ColorStateList.valueOf(requireContext().getColor(it.colorContent)))

                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    it.icon,
                    0
                )

                background = AppCompatResources.getDrawable(requireContext(), it.backgroundDrawable)
            }

            setOnClickListener {
                viewModel.navigateToProducts(findNavController(), requireContext())
            }
        }
    }

    private fun deleteProduct(productModel: ProductModel) {
        viewModel.deleteProduct(productModel)
    }
}