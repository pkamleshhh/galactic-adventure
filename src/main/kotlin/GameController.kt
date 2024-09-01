import model.Projectile
import model.ship.EnemyShip
import model.ship.MovementDirection
import model.ship.PlayerShip
import scheduler.Scheduler

class GameController(
    private val playerShip: PlayerShip,
    private val enemyShip: EnemyShip,
    private val scheduler: Scheduler
) {

    private val playerShipProjectiles: MutableSet<Projectile> = HashSet()

    init {
        schedulePlayerShipShooting()
        schedulePlayerShipProjectileMovement()
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

    fun getPlayerShip() = playerShip

    fun getPlayerShipProjectiles() = playerShipProjectiles

    private fun shootPlayerShipProjectile() {
        playerShipProjectiles.add(playerShip.shootProjectile())
    }

    private fun movePlayerShipProjectile() {
        this.playerShipProjectiles.forEach { it.move() }
    }

    fun movePlayerShip(direction: MovementDirection) {
        when (direction) {
            MovementDirection.UP -> playerShip.moveUp()

            MovementDirection.DOWN -> playerShip.moveDown()

            MovementDirection.RIGHT -> playerShip.moveRight()

            MovementDirection.LEFT -> playerShip.moveLeft()
        }
    }
}