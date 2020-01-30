package com.mint.libgdxkt.tools.lang

/**
 * @author Mint
 */
interface InteractiveStringsTemplate {

    fun app_name(): String

    fun lang(): String

    enum class Langs { RU, EN }
}
