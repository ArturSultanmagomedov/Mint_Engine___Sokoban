package com.mint.libgdxkt.tools

import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.mint.libgdxkt.screens.PlayScreen
import com.mint.libgdxkt.tools.actors.AnimatedActor

/**
 * @author Mint
 */
class XUnit(d: Array<Drawable>, speed: Float, width: Float, height: Float,
            x: Float, y: Float) : AnimatedActor(d, speed) {

    var mapX: Int = (x / width).toInt()
    var mapY: Int = (y / height).toInt()
    private var cx = x
    private var cy = y

    lateinit var playScreen: PlayScreen

    init {

        setSize(width, height)
        setPosition(x, y)
    }

    fun update() {

        mapX = (x / width).toInt()
        mapY = (y / height).toInt()
    }

    // проверка, есть ли на карте объект в указанном направлкнии
    private fun has(dir: Dir, n: Int): Boolean = when (dir) {

        Dir.UP -> mapY + 1 < playScreen.charsMap[0].size && playScreen.charsMap[mapX][mapY + 1] == n
        Dir.DOWN -> mapY - 1 >= 0 && playScreen.charsMap[mapX][mapY - 1] == n
        Dir.RIGHT -> mapX + 1 < playScreen.charsMap.size && playScreen.charsMap[mapX + 1][mapY] == n
        Dir.LEFT -> mapX - 1 >= 0 && playScreen.charsMap[mapX - 1][mapY] == n
    }

    fun go(dir: Dir) {

        if (cx == x && cy == y) {
            if (has(dir, 3)) {

                var b: Box? = null
                playScreen.boxesArray.forEach {
                    when (dir) {
                        Dir.UP -> if (it.mapX == mapX && it.mapY == mapY + 1) b = it
                        Dir.DOWN -> if (it.mapX == mapX && it.mapY == mapY - 1) b = it
                        Dir.RIGHT -> if (it.mapX == mapX + 1 && it.mapY == mapY) b = it
                        Dir.LEFT -> if (it.mapX == mapX - 1 && it.mapY == mapY) b = it
                    }
                }

                if (!b!!.has(dir, 1) && !b!!.has(dir, 3)) {

                    b!!.move(dir)
                    move(dir)
                }
            } else if (!has(dir, 1)) move(dir)
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

    private fun move(dir: Dir) = when (dir) {

        Dir.UP -> cy += height
        Dir.DOWN -> cy -= height
        Dir.RIGHT -> cx += width
        Dir.LEFT -> cx -= width
    }

    enum class Dir {
        UP, DOWN, RIGHT, LEFT
    }
}
