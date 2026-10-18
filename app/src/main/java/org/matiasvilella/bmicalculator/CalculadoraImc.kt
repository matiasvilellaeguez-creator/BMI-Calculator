package org.matiasvilella.bmicalculator

/** Categories utilitzades per mostrar el resultat de l'IMC. */
enum class CategoriaImc {
    PES_BAIX,
    PES_SALUDABLE,
    SOBREPES,
    OBESITAT
}

/** Resultat del calcul: valor numeric i categoria corresponent. */
data class ResultatImc(val valor: Double, val categoria: CategoriaImc)

/** Logica separada de la pantalla per poder-la comprovar amb tests. */
object CalculadoraImc {

    fun calcular(pesKg: Int, alcadaCm: Int): ResultatImc {
        require(pesKg in 1..300) { "El pes ha d'estar entre 1 i 300 kg" }
        require(alcadaCm in 100..220) { "L'alcada ha d'estar entre 100 i 220 cm" }

        // Convertim centímetres a metres: IMC = pes / (alcada * alcada).
        val alcadaMetres = alcadaCm / 100.0
        val imc = pesKg / (alcadaMetres * alcadaMetres)

        // Fem servir el valor original, abans de mostrar-lo amb un decimal.
        val categoria = when {
            imc < 18.5 -> CategoriaImc.PES_BAIX
            imc < 25.0 -> CategoriaImc.PES_SALUDABLE
            imc < 30.0 -> CategoriaImc.SOBREPES
            else -> CategoriaImc.OBESITAT
        }

        return ResultatImc(imc, categoria)
    }
}
