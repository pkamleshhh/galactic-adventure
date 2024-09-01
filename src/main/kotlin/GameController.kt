import model.Projectile
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

    private val playerShipProjectiles: MutableSet<Projectile> = HashSet()
    private lateinit var playerShipImage: BufferedImage
    private lateinit var enemyShipImage: BufferedImage

    init {
        schedulePlayerShipShooting()
        schedulePlayerShipProjectileMovement()
        loadShipImages()
    }

    private fun loadShipImages() {
        try {
            playerShipImage = ImageIO.read(javaClass.getResourceAsStream("player_ship.png"))
            enemyShipImage = ImageIO.read(javaClass.getResourceAsStream("player_ship.png"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun schedulePlayerShipShooting() {
        scheduler.schedulePlayerShipShooting(
            this::shootPlayerShipProjectile,
            0,
            GameConfig.PLAYER_SHIP_SHOOTING_INTERVAL
        )
    }

    private fun schedulePlayerShipProjectileMovement() {
        scheduler.scheduleUiRendering(
            this::movePlayerShipProjectile,
            15,
            GameConfig.PLAYER_SHIP_PROJECTILE_MOVEMENT_INTERVAL
        )
    }

    private fun shootPlayerShipProjectile() {
        playerShipProjectiles.add(playerShip.shootProjectile())
    }

    private fun movePlayerShipProjectile() {
        this.playerShipProjectiles.forEach { it.move() }
    }

    fun getPlayerShip() = playerShip

    fun getPlayerShipProjectiles() = playerShipProjectiles

    fun movePlayerShip(direction: MovementDirection) {
        when (direction) {
            MovementDirection.UP -> playerShip.moveUp()

            MovementDirection.DOWN -> playerShip.moveDown()

            MovementDirection.RIGHT -> playerShip.moveRight()

            MovementDirection.LEFT -> playerShip.moveLeft()
        }
    }

    fun getPlayerShipImage() = playerShipImage
}