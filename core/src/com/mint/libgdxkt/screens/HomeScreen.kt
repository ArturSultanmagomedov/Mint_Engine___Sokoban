package com.mint.libgdxkt.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.mint.libgdxkt.Game
import com.mint.libgdxkt.tools.ScreenX

/**
 * @author Mint
 */
class HomeScreen(game: Game) : ScreenX(game) {

    init {

        stage.addActor(Label("UwU", Label.LabelStyle(BitmapFont(), Color.WHITE)))

        clearColor.set(0.1f, 0.1f, 0.1f, 1f)
    }

    override fun update() {
        if (Gdx.input.justTouched()) {
            game.screen = PlayScreen(game)
        }
    }

    override fun fixedUpdate() {

    }
}
