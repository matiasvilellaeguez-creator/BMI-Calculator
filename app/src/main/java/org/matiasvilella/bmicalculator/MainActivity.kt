package org.matiasvilella.bmicalculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.widget.doAfterTextChanged
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var pes = 80
    private var alcada = 180

    private lateinit var etNom: EditText
    private lateinit var tvPes: TextView
    private lateinit var tvAlcada: TextView
    private lateinit var sbAlcada: SeekBar
    private lateinit var cardResultat: View
    private lateinit var tvResultatNom: TextView
    private lateinit var tvImc: TextView
    private lateinit var tvCategoria: TextView
    private lateinit var tvStatusLine: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // La UI futurista utilitza un fons fosc: icones clares a les barres del sistema.
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        val pantalla = findViewById<ScrollView>(R.id.main)
        // Respectem les barres de sistema en pantalles amb edge-to-edge.
        ViewCompat.setOnApplyWindowInsetsListener(pantalla) { vista, insets ->
            val barres = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            vista.setPadding(barres.left, barres.top, barres.right, barres.bottom)
            insets
        }

        // Obtenim les vistes que s'han definit a activity_main.xml.
        etNom = findViewById(R.id.et_nom)
        tvPes = findViewById(R.id.tv_pes)
        tvAlcada = findViewById(R.id.tv_alcada)
        sbAlcada = findViewById(R.id.sb_alcada)
        cardResultat = findViewById(R.id.card_resultat)
        tvResultatNom = findViewById(R.id.tv_resultat_nom)
        tvImc = findViewById(R.id.tv_imc)
        tvCategoria = findViewById(R.id.tv_categoria)
        tvStatusLine = findViewById(R.id.tv_status_line)

        val btPlus = findViewById<Button>(R.id.bt_plus)
        val btMinus = findViewById<Button>(R.id.bt_minus)
        val btCalculate = findViewById<Button>(R.id.bt_calculate)

        // Recuperem els valors quan es gira el mobil o es recrea l'activitat.
        pes = savedInstanceState?.getInt(CLAU_PES) ?: 80
        alcada = savedInstanceState?.getInt(CLAU_ALCADA) ?: 180
        etNom.setText(savedInstanceState?.getString(CLAU_NOM) ?: "")
        sbAlcada.progress = alcada - 100
        actualitzarValors()
        cardResultat.visibility = View.GONE

        // Si es canvia algun valor, l'ultim resultat deixa de ser valid.
        etNom.doAfterTextChanged {
            etNom.error = null
            ocultarResultat()
        }

        btPlus.setOnClickListener {
            if (pes < 300) {
                pes++
                actualitzarValors()
                ocultarResultat()
            }
        }

        btMinus.setOnClickListener {
            if (pes > 1) {
                pes--
                actualitzarValors()
                ocultarResultat()
            }
        }

        // SeekBar: progres de 0 a 120, que equival a 100..220 cm.
        sbAlcada.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                alcada = progress + 100
                actualitzarValors()
                ocultarResultat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btCalculate.setOnClickListener {
            val nom = etNom.text.toString().trim()

            // Cal informar el nom per poder calcular i mostrar el resultat.
            if (nom.isEmpty()) {
                ocultarResultat()
                tvStatusLine.setText(R.string.status_error)
                etNom.error = getString(R.string.error_nom)
                etNom.requestFocus()
                return@setOnClickListener
            }

            val resultat = CalculadoraImc.calcular(pes, alcada)
            mostrarResultat(nom, resultat)
            // Fem visible el diagnòstic i desplacem la pantalla fins al resultat.
            pantalla.post { pantalla.smoothScrollTo(0, cardResultat.bottom) }
        }

        if (savedInstanceState?.getBoolean(CLAU_RESULTAT_VISIBLE) == true) {
            val nom = etNom.text.toString().trim()
            if (nom.isNotEmpty()) {
                mostrarResultat(nom, CalculadoraImc.calcular(pes, alcada))
            }
        }
    }

    private fun actualitzarValors() {
        tvPes.text = getString(R.string.pes_format, pes)
        tvAlcada.text = getString(R.string.alcada_format, alcada)
    }

    private fun ocultarResultat() {
        cardResultat.visibility = View.GONE
        tvStatusLine.setText(R.string.status_canvi)
    }

    private fun mostrarResultat(nom: String, resultat: ResultatImc) {
        val (categoriaText, colorText) = when (resultat.categoria) {
            CategoriaImc.PES_BAIX -> R.string.pes_baix to "#99CCFF"
            CategoriaImc.PES_SALUDABLE -> R.string.pes_saludable to "#91F9A9"
            CategoriaImc.SOBREPES -> R.string.sobrepes to "#FFCF78"
            CategoriaImc.OBESITAT -> R.string.obesitat to "#FF8686"
        }

        tvResultatNom.text = getString(R.string.resultat_nom, nom)
        tvImc.text = String.format(Locale.getDefault(), "%.1f", resultat.valor)
        tvCategoria.setText(categoriaText)
        tvCategoria.setTextColor(Color.parseColor(colorText))
        cardResultat.visibility = View.VISIBLE
        tvStatusLine.setText(R.string.status_ok)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CLAU_NOM, etNom.text.toString())
        outState.putInt(CLAU_PES, pes)
        outState.putInt(CLAU_ALCADA, alcada)
        outState.putBoolean(CLAU_RESULTAT_VISIBLE, cardResultat.visibility == View.VISIBLE)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val CLAU_NOM = "nom"
        private const val CLAU_PES = "pes"
        private const val CLAU_ALCADA = "alcada"
        private const val CLAU_RESULTAT_VISIBLE = "resultat_visible"
    }
}
