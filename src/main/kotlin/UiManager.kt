import model.ship.MovementDirection
import scheduler.Scheduler
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel


class UiManager(private val gameController: GameController, private val scheduler: Scheduler) : JPanel(), KeyListener {

    init {
        preferredSize = Dimension(GameConfig.FRAME_WIDTH, GameConfig.FRAME_HEIGHT)
        background = Color.WHITE
        isFocusable = true
        addKeyListener(this)
        scheduler.schedule(this::repaint, 0, GameConfig.FRAME_UPDATE_INTERVAL)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        drawPlayerShip(g as Graphics2D)
        drawPlayerShipProjectiles(g)
        drawEnemyShip(g)
        drawEnemyShipProjectiles(g)
        g.dispose()
    }

    private fun drawPlayerShipProjectiles(g: Graphics2D) {
        this.gameController.getPlayerShipProjectiles().forEach {
            g.color = Color.BLACK
            g.fillRect(
                it.getPositionX(),
                it.getPositionY(),
                GameConfig.PLAYER_SHIP_BULLET_WIDTH,
                GameConfig.PLAYER_SHIP_BULLET_HEIGHT
            )
        }
    }

    private fun drawPlayerShip(g: Graphics2D) {
        g.color = Color.GREEN
        g.drawImage(
            gameController.getPlayerShipImage(),
            gameController.getPlayerShip().getPositionX(),
            gameController.getPlayerShip().getPositionY(),
            GameConfig.PLAYER_SHIP_WIDTH,
            GameConfig.PLAYER_SHIP_HEIGHT, null
        )
    }

    private fun drawEnemyShipProjectiles(g: Graphics2D) {
        this.gameController.getEnemyShipProjectiles().forEach {
            g.color = Color.BLACK
            g.drawString(
                "X",it.getPositionX(),
                it.getPositionY()
            )
        }
    }

    private fun drawEnemyShip(g: Graphics2D) {
        g.color = Color.GREEN
        g.drawImage(
            gameController.getEnemyShipImage(),
            gameController.getEnemyShip().getPositionX(),
            gameController.getEnemyShip().getPositionY(),
            GameConfig.ENEMY_SHIP_WIDTH,
            GameConfig.ENEMY_SHIP_HEIGHT, null
        )
    }

    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent?) {
        val keyCode = e!!.keyCode
        when (keyCode) {
            KeyEvent.VK_UP -> gameController.movePlayerShip(MovementDirection.UP)

            KeyEvent.VK_DOWN -> gameController.movePlayerShip(MovementDirection.DOWN)

            KeyEvent.VK_LEFT -> gameController.movePlayerShip(MovementDirection.LEFT)

            KeyEvent.VK_RIGHT -> gameController.movePlayerShip(MovementDirection.RIGHT)
        }
    }

    override fun keyReleased(e: KeyEvent?) {}
}
