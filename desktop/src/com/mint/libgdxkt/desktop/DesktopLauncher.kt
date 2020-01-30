package com.mint.libgdxkt.desktop

import com.badlogic.gdx.Files
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mint.libgdxkt.Game

object DesktopLauncher {

    @JvmStatic
    fun main(arg: Array<String>) {

        System.setProperty("user.name", "Public")

        val config = LwjglApplicationConfiguration()
        config.title = "Mint Engine"
        config.useGL30 = true
//        config.addIcon("images/icon_128.png", Files.FileType.Internal)
//        config.addIcon("images/icon_32.png", Files.FileType.Internal)
//        config.addIcon("images/icon_16.png", Files.FileType.Internal)
        config.resizable = true
        config.foregroundFPS = 60
        config.backgroundFPS = 60
        config.height = 16 * 35
        config.width = 9 * 35
        config.y = 150

        LwjglApplication(Game(), config)
    }
}
