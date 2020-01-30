package com.mint.libgdxkt.tools.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.TimeUtils

/**
 * @author Mint
 */
open class AnimatedActor(private val d: Array<Drawable>, var speed: Float) : Image() {
    /** пояснения к конструктору
     * d - массив с кадрами анимации
     * speed - сколько секунд показывается один кадр
     */

    var isEnd = true
        private set
    var isLoop = true
    private var step = 0
    private var lastTime: Long = 0

    override fun act(delta: Float) {

        super.act(delta)

        if (!isEnd && TimeUtils.nanoTime() - lastTime > 1000 * 1000 * 1000 * speed) {

            lastTime = TimeUtils.nanoTime()
            drawable = d[step]
            step++
            if (step == d.size) {
                if (isLoop) step = 0 else stop()
            }
        }
    }

    fun start() {

        isEnd = false
        step = 0
        drawable = d[step]
        lastTime = TimeUtils.nanoTime()
    }

    private fun stop() {

        isEnd = true
        step = 0
        lastTime = 0
    }
}
