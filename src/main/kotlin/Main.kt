import model.ship.EnemyShip
import model.ship.PlayerShip
import scheduler.Scheduler
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import javax.swing.JFrame

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val frame = JFrame("Galactic Adventure")
            val executorService: ScheduledExecutorService = Executors.newScheduledThreadPool(4)
            val scheduler = Scheduler(executorService)
            val gamePanel = UiManager(GameController(PlayerShip(), EnemyShip(), scheduler), scheduler)
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.contentPane = gamePanel
            frame.pack()
            frame.isVisible = true
        }
    }
}