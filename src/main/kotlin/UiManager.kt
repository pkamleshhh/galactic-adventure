import model.ship.MovementDirection
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import javax.swing.Timer


class UiManager(private val gameController: GameController) : JPanel(), KeyListener {

    init {
        preferredSize = Dimension(GameConfig.FRAME_WIDTH, GameConfig.FRAME_HEIGHT)
        background = Color.WHITE
        isFocusable = true
        addKeyListener(this)
        Timer(GameConfig.FRAME_UPDATE_INTERVAL) {
            repaint()
        }.start()
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        drawPlayerShip(g)
        drawProjectiles(g)
    }

    private fun drawProjectiles(g: Graphics) {
        this.gameController.getPlayerShipProjectiles().forEach {
            g.color = Color.BLACK
            g.fillRect(it.getX(), it.getY(), GameConfig.PLAYER_SHIP_BULLET_WIDTH, GameConfig.PLAYER_SHIP_BULLET_HEIGHT)
        }
    }

    private fun drawPlayerShip(g: Graphics) {
        g.color = Color.GREEN
        g.fillRect(
            gameController.getPlayerShip().getPositionX(),
            gameController.getPlayerShip().getPositionY(),
            GameConfig.PLAYER_SHIP_WIDTH,
            GameConfig.PLAYER_SHIP_HEIGHT
        )
        g.color = Color.BLACK
        g.drawString(
            "Player",
            gameController.getPlayerShip().getPositionX() + 5,
            gameController.getPlayerShip().getPositionY() + 15
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
