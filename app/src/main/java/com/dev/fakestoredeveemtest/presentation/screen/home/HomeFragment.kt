package com.dev.fakestoredeveemtest.presentation.screen.home

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.dev.fakestoredeveemtest.R
import com.dev.fakestoredeveemtest.databinding.BottomSheetProductBinding
import com.dev.fakestoredeveemtest.databinding.DialogCategoriesBinding
import com.dev.fakestoredeveemtest.databinding.FragmentHomeBinding
import com.dev.fakestoredeveemtest.domain.models.ProductModel
import com.dev.fakestoredeveemtest.util.BuyButtonType
import com.dev.fakestoredeveemtest.util.ProductCategoriesType
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewBinding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHomeScreenContent()
        viewModel.getProducts()
    }

    private fun setupHomeScreenContent() = viewBinding.apply {

        viewModel.stateScreen.observe(viewLifecycleOwner) {
            homeNestedScroll.visibility = it.showContent
            homeEmptyView.visibility = it.showEmptyBox
        }

        viewModel.selectedCategoryLiveData.observe(viewLifecycleOwner) { category ->
            homeCollapsingToolbarLayout.subtitle = category.category.uppercase()
        }

        homeToolbar.apply {
            setNavigationOnClickListener {
                categoriesAlertDialog()
            }
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.product_screen_nav_item -> {
                        findNavController().navigate(R.id.action_fragment_home_to_fragment_card)
                        true
                    }

                    else -> {
                        true
                    }
                }
            }
        }

        viewModel.productModelLiveData.observe(viewLifecycleOwner) { products ->
            productsCacheListRv.adapter = CacheProductAdapter(
                productModelList = products,
                clickListener = {
                    showDetailProductBottomSheet(it)
                }
            )
        }
    }

    private fun categoriesAlertDialog() {

        val dialogViewBinding: DialogCategoriesBinding =
            DialogCategoriesBinding.inflate(LayoutInflater.from(context))
        val dialogBuilder = MaterialAlertDialogBuilder(requireContext()).create()
        dialogBuilder.setView(dialogViewBinding.root)

        viewModel.selectedCategoryLiveData.observe(viewLifecycleOwner) {
            dialogViewBinding.categoriesRadioGroup.apply {
                check(it.id)
            }
        }

        dialogViewBinding.apply {
            categoriesRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.all_button -> viewModel.changeCurrentCategoryProducts(ProductCategoriesType.All)

                    R.id.men_s_clothing_button -> viewModel.changeCurrentCategoryProducts(
                        ProductCategoriesType.Mens_clothing
                    )

                    R.id.jewelry_button -> viewModel.changeCurrentCategoryProducts(
                        ProductCategoriesType.Jewelery
                    )

                    R.id.electronics_button -> viewModel.changeCurrentCategoryProducts(
                        ProductCategoriesType.Electronics
                    )

                    R.id.women_s_clothing_button -> viewModel.changeCurrentCategoryProducts(
                        ProductCategoriesType.Womens_clothing
                    )
                }
            }
            declineButton.setOnClickListener {
                dialogBuilder.dismiss()
            }
            acceptButton.setOnClickListener {
                dialogBuilder.dismiss()
                viewModel.updateProductsByCategory()
            }
        }

        dialogBuilder.setOnDismissListener {
            viewModel.changeCurrentCategoryProducts(ProductCategoriesType.All)
        }

        dialogBuilder.show()
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    private fun showDetailProductBottomSheet(productModel: ProductModel) {
        val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        val bindingBottomSheet = BottomSheetProductBinding.inflate(layoutInflater)
        dialog.setContentView(bindingBottomSheet.root)

        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED

        viewModel.resetCounterNavigate()

        viewModel.changeStateBuyButton(BuyButtonType.AddToCard)

        bindingBottomSheet.apply {
            Glide.with(productImage)
                .load(productModel.image)
                .into(productImage)
            productName.text = productModel.title

            productCategory.text = productModel.category.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }

            productPrice.text = productModel.price
            productDescription.text = productModel.description

            buyNowButton.apply {
                viewModel.selectedStateBuyButton.observe(viewLifecycleOwner) {

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

                    background = getDrawable(requireContext(), it.backgroundDrawable)

                }
                setOnClickListener {
                    viewModel.changeStateBuyButton(BuyButtonType.GoToCard)

                    viewModel.saveProductInCart(productModel)

                    viewModel.setDoubleClickListener()

                    viewModel.navigateToCard(findNavController(), dialog)
                }
            }
        }

        dialog.show()
    }
}