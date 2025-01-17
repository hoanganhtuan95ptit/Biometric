package com.mikhaellopez.biometricsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mikhaellopez.biometric.BiometricHelper
import com.mikhaellopez.biometric.BiometricPromptInfo

class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    private val biometricHelper by lazy { BiometricHelper(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textBiometricType).text =
            getString(R.string.biometric_type, biometricHelper.getBiometricType())
        val btnPrompt = view.findViewById<Button>(R.id.btnPrompt)
        btnPrompt.visibility = if (biometricHelper.biometricEnable()) View.VISIBLE else View.GONE

        btnPrompt.setOnClickListener {
            biometricHelper.showBiometricPrompt(
                BiometricPromptInfo(
                    title = "Title", // Mandatory
                    negativeButtonText = "Cancel", // Mandatory
                    subtitle = "Subtitle",
                    description = "Description",
                    confirmationRequired = true
                )
            ) {
                // Do something when success
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }
        }
    }

}