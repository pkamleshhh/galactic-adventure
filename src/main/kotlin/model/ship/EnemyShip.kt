package model.ship

import GameConfig
import model.projectile.Rock

class EnemyShip : Ship {
    private var x = GameConfig.FRAME_WIDTH - 100
    private var y = 50

    override fun getPositionX(): Int = this.x

    override fun getPositionY(): Int = this.y

    override fun moveUp() {
        y -= GameConfig.ENEMY_SHIP_SPEED
    }

    override fun moveDown() {
        y += GameConfig.ENEMY_SHIP_SPEED
    }

    override fun moveRight() {
        x += GameConfig.ENEMY_SHIP_SPEED
    }

    override fun moveLeft() {
        x -= GameConfig.ENEMY_SHIP_SPEED
    }

    override fun shootProjectile(): Rock {
        return Rock(this.x , this.y + GameConfig.ENEMY_SHIP_HEIGHT / 2)
    }
}