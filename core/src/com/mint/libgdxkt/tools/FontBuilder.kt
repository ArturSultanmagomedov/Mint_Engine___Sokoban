package com.mint.libgdxkt.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter

object FontBuilder {

    fun createFont(size: Int, genColor: Color?, borderColor: Color?, borderWidth: Float, path: String?): BitmapFont {
        val font: BitmapFont
        val generator = FreeTypeFontGenerator(Gdx.files.internal(path))
        val parameter = FreeTypeFontParameter()
        parameter.color = genColor
        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщьыъэюя" +
                "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ" +
                "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "0123456789][_!$%#@|/?-+=()*&.;,{}'<>:\"\\№`~"
        parameter.borderColor = borderColor
        parameter.borderWidth = borderWidth
        parameter.size = size
        font = generator.generateFont(parameter)
        generator.dispose()
        return font
    }

    fun createFont(size: Int, genColor: Color?, colorBorder: Color?, selectFont: Int): BitmapFont {
        val key = "fonts/font$selectFont.ttf"
        return createFont(size, genColor, colorBorder, 3f, key)
    }

    fun createFont(size: Int, genColor: Color?, selectFont: Int): BitmapFont {
        val key = "fonts/font$selectFont.ttf"
        return createFont(size, genColor, Color.CLEAR, 0f, key)
    }
}
