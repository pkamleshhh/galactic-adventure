import model.projectile.Projectile
import model.ship.EnemyShip
import model.ship.MovementDirection
import model.ship.PlayerShip
import scheduler.Scheduler
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class GameController(
    private val playerShip: PlayerShip,
    private val enemyShip: EnemyShip,
    private val scheduler: Scheduler
) {

    private val bullets: MutableSet<Projectile> = HashSet()
    private val rocks: MutableSet<Projectile> = HashSet()
    private lateinit var playerShipImage: BufferedImage
    private lateinit var enemyShipImage: BufferedImage

    init {
        schedulePlayerShipShooting()
        schedulePlayerShipProjectileMovement()
        scheduleEnemyShipShooting()
        scheduleEnemyShipProjectileMovement()
        scheduleEnemyShipMovement()
        loadShipImages()
    }

    private fun scheduleEnemyShipMovement() {
        scheduler.schedule(this::moveEnemyShipRandomly, 15, GameConfig.ENEMY_SHIP_MOVEMENT_INTERVAL)
    }

    private fun loadShipImages() {
        try {
            playerShipImage = ImageIO.read(javaClass.getResourceAsStream("player_ship.png"))
            enemyShipImage = ImageIO.read(javaClass.getResourceAsStream("enemy_ship.png"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun schedulePlayerShipShooting() {
        scheduler.schedule(
            this::shootPlayerShipProjectile,
            0,
            GameConfig.PLAYER_SHIP_SHOOTING_INTERVAL
        )
    }

    private fun schedulePlayerShipProjectileMovement() {
        scheduler.schedule(
            this::movePlayerShipProjectile,
            15,
            GameConfig.PLAYER_SHIP_PROJECTILE_MOVEMENT_INTERVAL
        )
    }

    private fun scheduleEnemyShipShooting() {
        scheduler.schedule(
            this::shootEnemyShipProjectile,
            0,
            GameConfig.ENEMY_SHIP_SHOOTING_INTERVAL
        )
    }

    private fun scheduleEnemyShipProjectileMovement() {
        scheduler.schedule(
            this::moveEnemyShipProjectile,
            15,
            GameConfig.ENEMY_SHIP_PROJECTILE_MOVEMENT_INTERVAL
        )
    }

    private fun shootPlayerShipProjectile() {
        bullets.add(playerShip.shootProjectile())
    }

    private fun movePlayerShipProjectile() {
        bullets.forEach { it.move() }
    }

    private fun shootEnemyShipProjectile() {
        rocks.add(enemyShip.shootProjectile())
    }

    private fun moveEnemyShipProjectile() {
        rocks.forEach { it.move() }
    }

    private fun moveEnemyShipRandomly() {
        if (enemyShip.getPositionY() == 40) {
            enemyShip.moveDown()
            return
        }
        if (enemyShip.getPositionY() == GameConfig.FRAME_HEIGHT) {
            enemyShip.moveUp()
            return
        }
        val direction = (Math.random() * 2).toInt()
        if (direction == 0) {
            enemyShip.moveUp()
        } else {
            enemyShip.moveDown()
        }
    }

    fun getPlayerShip() = playerShip

    fun getPlayerShipProjectiles() = bullets

    fun getEnemyShip() = enemyShip

    fun getEnemyShipProjectiles() = rocks

    fun getPlayerShipImage() = playerShipImage

    fun getEnemyShipImage() = enemyShipImage

    fun movePlayerShip(direction: MovementDirection) {
        when (direction) {
            MovementDirection.UP -> playerShip.moveUp()

            MovementDirection.DOWN -> playerShip.moveDown()

            MovementDirection.RIGHT -> playerShip.moveRight()

            MovementDirection.LEFT -> playerShip.moveLeft()
        }
    }


}