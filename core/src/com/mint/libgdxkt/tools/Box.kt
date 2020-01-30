package com.mint.libgdxkt.tools

import com.badlogic.gdx.graphics.Texture
import com.mint.libgdxkt.screens.PlayScreen
import com.mint.libgdxkt.tools.actors.BaseActor

/**
 * @author Mint
 */
class Box(texture: Texture) : BaseActor(texture) {

    var mapX: Int = (x / 20f).toInt()
    var mapY: Int = (y / 20f).toInt()
    private var pMapX = mapX
    private var pMapY = mapY
    private var cx = x
    private var cy = y

    lateinit var playScreen: PlayScreen

    fun update() {

        mapX = (x / 20f).toInt()
        mapY = (y / 20f).toInt()

        playScreen.charsMap[mapX][mapY] = 3
        if (pMapX != mapX || pMapY != mapY) playScreen.charsMap[pMapX][pMapY] = 0
    }

    fun has(dir: XUnit.Dir, n: Int): Boolean = when (dir) {

        XUnit.Dir.UP -> mapY + 1 < playScreen.charsMap[0].size && playScreen.charsMap[mapX][mapY + 1] == n
        XUnit.Dir.DOWN -> mapY - 1 >= 0 && playScreen.charsMap[mapX][mapY - 1] == n
        XUnit.Dir.RIGHT -> mapX + 1 < playScreen.charsMap.size && playScreen.charsMap[mapX + 1][mapY] == n
        XUnit.Dir.LEFT -> mapX - 1 >= 0 && playScreen.charsMap[mapX - 1][mapY] == n
    }

    fun move(dir: XUnit.Dir) {

        pMapX = mapX
        pMapY = mapY

        when (dir) {

            XUnit.Dir.UP -> {cy += 20; mapY++}
            XUnit.Dir.DOWN -> {cy -= 20; mapY--}
            XUnit.Dir.RIGHT -> {cx += 20; mapX++}
            XUnit.Dir.LEFT -> {cx -= 20; mapX--}
        }
    }

    fun updatePosition() {

        when {
            x < cx -> x += 2
            x > cx -> x -= 2
        }
        when {
            y < cy -> y += 2
            y > cy -> y -= 2
        }
    }

    fun setCPos(x: Float, y: Float) {
        cx = x; cy = y
    }
}
