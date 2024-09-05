package model.ship

import model.projectile.Projectile

interface Ship {
    fun getPositionX():Int
    fun getPositionY():Int
    fun moveUp()
    fun moveDown()
    fun moveRight()
    fun moveLeft()
    fun shootProjectile(): Projectile
}