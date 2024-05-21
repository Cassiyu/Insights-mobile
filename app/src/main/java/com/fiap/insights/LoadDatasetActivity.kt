package com.fiap.insights

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.smitcoderx.learn.furnspace.R

class LoadDatasetActivity : AppCompatActivity() {
    private lateinit var csvPlaceholder: TextView
    private lateinit var analyzeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_dataset)

        csvPlaceholder.setOnClickListener {
            openFilePicker()
        }

        csvPlaceholder.addTextChangedListener {
            analyzeButton.isEnabled = it?.isNotEmpty() == true
            val color = if (analyzeButton.isEnabled) R.color.primary else R.color.gray
            analyzeButton.backgroundTintList = ContextCompat.getColorStateList(this, color)
        }

        analyzeButton.setOnClickListener {

        }
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "text/csv"
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CSV && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            uri?.let {
                val fileName = it.lastPathSegment?.substringAfterLast('/')
                csvPlaceholder.text = fileName ?: "Arquivo Selecionado"
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_CSV = 1
    }
}
