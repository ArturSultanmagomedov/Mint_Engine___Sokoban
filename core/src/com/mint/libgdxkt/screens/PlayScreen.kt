package com.mint.libgdxkt.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.mint.libgdxkt.Game
import com.mint.libgdxkt.tools.XUnit
import com.mint.libgdxkt.tools.Box
import com.mint.libgdxkt.tools.ScreenX
import com.mint.libgdxkt.tools.actors.BaseActor
import java.io.FileInputStream

/**
 * @author Mint
 */
class PlayScreen(game: Game) : ScreenX(game) {

    private lateinit var l: Label
    private lateinit var xUnit: XUnit
    private lateinit var xUI: Table

    private val dotsArray: com.badlogic.gdx.utils.Array<BaseActor>
    lateinit var charsMap: Array<IntArray>
    var boxesArray: com.badlogic.gdx.utils.Array<Box>

    init {

        fixValue = 1 / 50f

        clearColor.set(0.1f, 0.1f, 0.2f, 1f)

        boxesArray = com.badlogic.gdx.utils.Array()
        dotsArray = com.badlogic.gdx.utils.Array()

        initStage()
    }

    override fun update() {

        xUnit.update()

        inputLogic()

        boxesArray.forEach { box ->
            var uwu = 0
            box.update()
            dotsArray.forEach { dot ->
                if (dot.x == box.x && dot.y == box.y) {
                    uwu++
                }
                if (uwu > 0) {
                    box.setColor(0.7f, 0.7f, 0.2f, 1f)
                } else {
                    box.setColor(0.2f, 0.7f, 0.4f, 1f)
                }
            }
        }
    }

    override fun fixedUpdate() {

        camera.position.set(xUnit.x + xUnit.width / 2, xUnit.y + xUnit.height / 2, 0f)
        xUI.setPosition(camera.position.x - xUI.width / 2,
                camera.position.y - xUI.height / 2)

        xUnit.updatePosition()

        boxesArray.forEach {
            it.updatePosition()
        }
    }

    private fun inputLogic() {

        when {
            Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A) -> xUnit.go(XUnit.Dir.LEFT)
            Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D) -> xUnit.go(XUnit.Dir.RIGHT)
            Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W) -> xUnit.go(XUnit.Dir.UP)
            Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.S) -> xUnit.go(XUnit.Dir.DOWN)
        }
    }

    private fun initStage() {

        xUI = Table()
        xUI.setSize(stage.width, stage.height)


        val level = 1
        initMap(level)


        xUnit = XUnit(arrayOf(
                TextureRegionDrawable(TextureRegion(Texture(
                        "img/slime.png"), 0, 0, 7, 7)),
                TextureRegionDrawable(TextureRegion(Texture(
                        "img/slime.png"), 0, 7, 7, 7)),
                TextureRegionDrawable(TextureRegion(Texture(
                        "img/slime.png"), 0, 0, 7, 7)),
                TextureRegionDrawable(TextureRegion(Texture(
                        "img/slime.png"), 7, 7, 7, 7))
        ), 0.15f, 20f, 20f, 60f, 60f)
        stage.addActor(xUnit)
        xUnit.playScreen = this
        xUnit.start()

        l = Label("level - $level  ", Label.LabelStyle(BitmapFont(), Color.WHITE))
        l.setPosition(xUI.right, xUI.top, Align.topRight)
        xUI.addActor(l)

        stage.addActor(xUI)
    }

    private fun initMap(level: Int) {

        val fIn = FileInputStream("maps/...level...$level...in")
        val scan = java.util.Scanner(fIn)


        val width = scan.nextInt()
        val height = scan.nextInt()

        charsMap = Array(width) { IntArray(height) { 0 } }

        val wallTexture = Texture("img/wall.png")
        val dotTexture = Texture("img/dot.png")
        val boxTexture = Texture("img/box.png")

        val map = Table()
        for (i in height - 1 downTo 0) {
            for (j in 0 until width) {

                charsMap[j][i] = scan.nextInt()

                when (charsMap[j][i]) {
                    1 -> {

                        val m = BaseActor(wallTexture)
                        m.setColor(0.17f, 0.17f, 0.27f, 1f)
                        m.setPosition(20f * j, 20f * i)
                        m.setSize(20f, 20f)
                        map.addActor(m)
                    }
                    2 -> {

                        val m = BaseActor(dotTexture)
                        m.setColor(0.7f, 0.7f, 0.2f, 1f)
                        m.setPosition(20f * j, 20f * i)
                        m.setSize(20f, 20f)
                        dotsArray.add(m)
                        map.addActor(m)
                    }
                    3 -> {

                        val m = Box(boxTexture)
                        m.setColor(0.2f, 0.7f, 0.4f, 1f)
                        m.setSize(20f, 20f)
                        m.setCPos(20f * j, 20f * i)
                        m.setPosition(20f * j, 20f * i)
                        boxesArray.add(m)
                        m.playScreen = this
                        map.addActor(m)
                    }
                    4 -> {

                        val m1 = BaseActor(dotTexture)
                        m1.setColor(0.7f, 0.7f, 0.2f, 1f)
                        m1.setPosition(20f * j, 20f * i)
                        m1.setSize(20f, 20f)
                        dotsArray.add(m1)
                        map.addActor(m1)

                        val m2 = Box(boxTexture)
                        m2.setColor(0.2f, 0.7f, 0.4f, 1f)
                        m2.setSize(20f, 20f)
                        m2.setCPos(20f * j, 20f * i)
                        m2.setPosition(20f * j, 20f * i)
                        boxesArray.add(m2)
                        m2.playScreen = this
                        map.addActor(m2)
                    }
                }
            }
        }

        stage.addActor(map)
    }
}
