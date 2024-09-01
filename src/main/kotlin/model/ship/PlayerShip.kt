package model.ship

import GameConfig
import model.Projectile

class PlayerShip : Ship {
    private var x = 50
    private var y = 50

    override fun getPositionX(): Int = this.x

    override fun getPositionY(): Int = this.y

    override fun moveUp() {
        y -= GameConfig.PLAYER_SHIP_SPEED
    }

    override fun moveDown() {
        y += GameConfig.PLAYER_SHIP_SPEED
    }

    override fun moveRight() {
        x += GameConfig.PLAYER_SHIP_SPEED
    }

    override fun moveLeft() {
        x -= GameConfig.PLAYER_SHIP_SPEED
    }

    override fun shootProjectile(): Projectile {
        return Projectile(this.x + GameConfig.PLAYER_SHIP_WIDTH, this.y + GameConfig.PLAYER_SHIP_HEIGHT/2)
    }
}