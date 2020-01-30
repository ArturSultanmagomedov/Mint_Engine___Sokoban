package com.mint.libgdxkt.tools.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

/**
 * @author Mint
 */
open class BaseActor(texture: Texture, width: Int = texture.width,
                     height: Int = texture.height, x: Int = 0, y: Int = 0) :
        Image(TextureRegionDrawable(TextureRegion(texture, x, y, width, height)))
