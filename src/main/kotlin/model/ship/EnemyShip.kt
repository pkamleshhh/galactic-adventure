package model.ship

import model.Projectile

class EnemyShip : Ship {
    var x = 0
    var y = 0
    override fun getPositionX(): Int = this.x

    override fun getPositionY(): Int = this.y

    override fun moveUp() {
        this.y--
    }

    override fun moveDown() {
        this.y++
    }

    override fun moveRight() {
        this.x++
    }

    override fun moveLeft() {
        this.x--
    }

    override fun shootProjectile(): Projectile {
        TODO("Not yet implemented")
    }
}