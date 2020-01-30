package com.mint.libgdxkt

import com.mint.libgdxkt.screens.*
import com.mint.libgdxkt.tools.lang.*

/**
 * @author Mint
 */
class Game : com.badlogic.gdx.Game() {

    lateinit var str: InteractiveStringsTemplate
    private var lang = InteractiveStringsTemplate.Langs.EN

    override fun create() {

        str = En()

        setScreen(HomeScreen(this))
    }

    fun selectLang(lang: InteractiveStringsTemplate.Langs) {

        if (this.lang != lang) str = when (lang) {
            InteractiveStringsTemplate.Langs.EN -> En()
            InteractiveStringsTemplate.Langs.RU -> Ru()
        }
        this.lang = lang
    }
}
