package model.projectile

import GameConfig

class Bullet(initialX: Int, initialY: Int) :Projectile{
    private var x: Int = initialX
    private val y: Int = initialY

    override fun move() {
        this.x += GameConfig.PLAYER_SHIP_PROJECTILE_SPEED
    }

    override fun getPositionX() = this.x

    override fun getPositionY() = this.y
}
