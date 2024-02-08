package com.example.sample24l12sharedpreferences

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.sample24l12sharedpreferences.databinding.FragmentPrefsBinding

class PrefsFragment : Fragment() {

    private var _binding: FragmentPrefsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val prefsManager by lazy {
        PrefsManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentPrefsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            checkbox.isChecked = prefsManager.switchIsChecked
            edittext.setText(prefsManager.editTextValue)

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                prefsManager.switchIsChecked = isChecked
            }
            edittext.addTextChangedListener { text ->
                prefsManager.editTextValue =text.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}