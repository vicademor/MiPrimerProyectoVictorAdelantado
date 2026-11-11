package com.example.sprint1

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Componente 1: Spinner (spinner_curso)
        val spinnerCurso = findViewById<Spinner>(R.id.spinner_curso)
        val cursos = arrayOf("1.º DAM", "2.º DAM", "1.º DAW", "2.º DAW", "1.º ASIR", "2.º ASIR")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cursos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurso.adapter = adapter

        // Componente 2: WebView (web_view)
        val webView = findViewById<WebView>(R.id.web_view)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://developer.android.com")

        // Componente 3: SeekBar (seekbar_nivel)
        val seekBarNivel = findViewById<SeekBar>(R.id.seekbar_nivel)
        val txtNivel = findViewById<TextView>(R.id.title_nivel)

        // Establecer el texto inicial con el valor actual de la SeekBar
        txtNivel.text = getString(R.string.nivel_seleccionado_format, seekBarNivel.progress)

        seekBarNivel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                txtNivel.text = getString(R.string.nivel_seleccionado_format, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}