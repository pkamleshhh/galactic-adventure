package model.ship

import GameConfig
import model.Projectile

class PlayerShip : Ship {
    private var x = 50
    private var y = 50
    private val step = 10

    override fun getPositionX(): Int = this.x

    override fun getPositionY(): Int = this.y

    override fun moveUp() {
        y -= step
    }

    override fun moveDown() {
        y += step
    }

    override fun moveRight() {
        x += step
    }

    override fun moveLeft() {
        x -= step
    }

    override fun shootProjectile(): Projectile {
        return Projectile(this.x + GameConfig.PLAYER_SHIP_WIDTH, this.y + GameConfig.PLAYER_SHIP_HEIGHT/2)
    }
}