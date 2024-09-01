package model

class Projectile(initialX: Int, initialY: Int) {
    private var x: Int = initialX
    private val y: Int = initialY

    fun move() {
        this.x+=10
    }

    fun getX() = this.x

    fun getY() = this.y
}
