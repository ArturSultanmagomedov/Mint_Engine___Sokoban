package com.mint.libgdxkt.tools

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.mint.libgdxkt.Game

/**
 * @author Mint
 */
abstract class ScreenX(protected var game: Game) : InputAdapter(), Screen {

    protected var camera = OrthographicCamera()
    protected lateinit var stage: Stage
    protected var viewport: Viewport
    protected var batch: SpriteBatch

    // delta из метода render передается сюда. Можно было бы использовать
    // Gdx.graphics.deltaTime, но зачем усложнять себе и другим жизнь
    protected var dt = 0f
        private set

    // индикатор вызова паузы
    protected var pause = false
        private set

    // используется внутри метода render для очистки экрана
    protected val clearColor = Color(0f, 0f, 0f, 1f)

    // метод fixedUpdate() вызывается ровно 1 / fixValue раз в секунду
    protected var fixValue = 0.01f
    private var lastTime: Long = 0

    init {

        viewport = ExtendViewport(450 / 3f, 800 / 3f, 600 / 3f, 1000 / 3f, camera)
        batch = SpriteBatch()
        createUI()
    }

    override fun show() {}

    override fun render(delta: Float) {

        dt = delta

        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)

        ///////////        ///////////        ///////////

        if (!pause) {

            stage.act(delta)
            stage.draw()
        }

        update()

        if (TimeUtils.nanoTime() - lastTime > 1000 * 1000 * 1000 * fixValue) {

            lastTime = TimeUtils.nanoTime()
            fixedUpdate()
        }
    }

    override fun resize(width: Int, height: Int) {

        viewport.update(width, height)

//        createUI()
    }

    override fun pause() {
        pause = true
    }

    override fun resume() {
        pause = false
    }

    override fun hide() {}

    private fun createUI() {
        stage = Stage(viewport)
        Gdx.input.inputProcessor = stage
    }

    override fun dispose() {
        try {
            batch.dispose()
        } catch (ignored: Exception) {
        }
        try {
            stage.dispose()
        } catch (ignored: Exception) {
        }
    }

    abstract fun update()
    abstract fun fixedUpdate()
}
