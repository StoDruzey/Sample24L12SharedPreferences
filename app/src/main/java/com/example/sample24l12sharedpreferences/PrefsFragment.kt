package com.example.sample24l12sharedpreferences

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.sample24l12sharedpreferences.databinding.FragmentPrefsBinding

class PrefsFragment : Fragment() {

    private var _binding: FragmentPrefsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val prefs by lazy {
        requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
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
            checkbox.isChecked = prefs.getBoolean("isChecked", false)
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                prefs
                    .edit()
                    .putBoolean("isChecked", isChecked)
                    .apply()
            }
            edittext.setText(prefs.getString("editText", null) ?: "no text")

            prefs
                .edit()
                .putString("edittext", edittext.text.toString())
                .apply()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}