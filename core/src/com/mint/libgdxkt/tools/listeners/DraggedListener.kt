package com.mint.libgdxkt.tools.listeners

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

/**
 * @author Mint
 */
class DraggedListener(private val actor: Actor) : ClickListener() {

    private var zoom = false

    override fun touchDragged(event: InputEvent, x: Float, y: Float, pointer: Int) {

        super.touchDragged(event, x, y, pointer)

        actor.setPosition(event.stageX - touchDownX,
                event.stageY - touchDownY)
    }

    override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {

        if (!zoom) zoom(3f)

        return super.touchDown(event, x, y, pointer, button)
    }

    override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {

        super.touchUp(event, x, y, pointer, button)

        if (zoom) zoom(-3f)
    }

    private fun zoom(scale: Float) {

        actor.setSize(actor.width + scale, actor.height + scale)

        if (actor is Label) {
            actor.setFontScale(actor.fontScaleX + 0.5f / scale,
                    actor.fontScaleY + 0.5f / scale)
        }

        zoom = !zoom
    }
}
