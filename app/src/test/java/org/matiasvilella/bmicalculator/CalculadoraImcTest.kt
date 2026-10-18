package org.matiasvilella.bmicalculator

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculadoraImcTest {

    @Test
    fun calculaElValorDelImc() {
        val resultat = CalculadoraImc.calcular(pesKg = 80, alcadaCm = 180)
        assertEquals(24.691358, resultat.valor, 0.000001)
        assertEquals(CategoriaImc.PES_SALUDABLE, resultat.categoria)
    }

    @Test
    fun comprovaLesQuatreCategories() {
        assertEquals(CategoriaImc.PES_BAIX, CalculadoraImc.calcular(60, 200).categoria)
        assertEquals(CategoriaImc.PES_SALUDABLE, CalculadoraImc.calcular(74, 200).categoria)
        assertEquals(CategoriaImc.SOBREPES, CalculadoraImc.calcular(100, 200).categoria)
        assertEquals(CategoriaImc.OBESITAT, CalculadoraImc.calcular(120, 200).categoria)
    }

    @Test
    fun comprovaElsLimitsDeLaClassificacio() {
        assertEquals(CategoriaImc.PES_BAIX, CalculadoraImc.calcular(73, 200).categoria)
        assertEquals(CategoriaImc.PES_SALUDABLE, CalculadoraImc.calcular(74, 200).categoria)
        assertEquals(CategoriaImc.PES_SALUDABLE, CalculadoraImc.calcular(99, 200).categoria)
        assertEquals(CategoriaImc.SOBREPES, CalculadoraImc.calcular(100, 200).categoria)
        assertEquals(CategoriaImc.OBESITAT, CalculadoraImc.calcular(120, 200).categoria)
    }

    @Test(expected = IllegalArgumentException::class)
    fun rebutjaPesZero() {
        CalculadoraImc.calcular(0, 180)
    }

    @Test(expected = IllegalArgumentException::class)
    fun rebutjaAlcadaForaDelRang() {
        CalculadoraImc.calcular(80, 221)
    }
}
