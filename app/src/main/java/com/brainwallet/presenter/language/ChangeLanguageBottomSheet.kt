package com.brainwallet.presenter.language

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.brainwallet.R
import com.brainwallet.databinding.ChangeLanguageBottomSheetBinding
import com.brainwallet.data.model.Language
import com.brainwallet.navigation.LegacyNavigation
import com.brainwallet.tools.util.LocaleHelper
import com.brainwallet.tools.util.Utils
import com.brainwallet.tools.util.getString
import com.brainwallet.ui.RoundedBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ChangeLanguageBottomSheet : RoundedBottomSheetDialogFragment() {
    lateinit var binding: ChangeLanguageBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ChangeLanguageBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { dismiss() }

        val currentLanguage = LocaleHelper.instance.currentLocale
        binding.toolbar.title = currentLanguage.desc

        val adapter =
            LanguageAdapter(Language.values()).apply {
                selectedPosition = currentLanguage.ordinal
                onLanguageChecked = {
                    binding.toolbar.title = it.desc
                    binding.okButton.text = getString(LocaleHelper.getLocale(it), R.string.Button_ok)
                }
            }
        binding.recyclerView.adapter = adapter

        binding.recyclerView.post {
            binding.recyclerView.scrollToPosition(adapter.selectedPosition)
        }

        binding.okButton.setOnClickListener {
            dismiss()
            if (LocaleHelper.instance.setLocaleIfNeeded(adapter.selectedLanguage())) {
                LegacyNavigation.openComposeScreen(
                    context = requireContext(),
                )
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        val behavior = dialog.behavior
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.setPeekHeight(0, true)
        behavior.setExpandedOffset(Utils.getPixelsFromDps(context, 16))
        behavior.isFitToContents = false
        behavior.isHideable = true

        behavior.addBottomSheetCallback(
            object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(
                    bottomSheet: View,
                    slideOffset: Float,
                ) {
                }

                override fun onStateChanged(
                    bottomSheet: View,
                    newState: Int,
                ) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
            },
        )

        dialog.setOnShowListener {
            val bottomSheet: FrameLayout? = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            val lp = bottomSheet?.layoutParams
            lp?.height = ViewGroup.LayoutParams.MATCH_PARENT
            bottomSheet?.layoutParams = lp
        }
        return dialog
    }
}
