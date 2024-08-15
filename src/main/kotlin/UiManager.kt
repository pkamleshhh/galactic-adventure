import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel
import javax.swing.Timer


class UiManager : JPanel() {

    init {
        preferredSize = Dimension(800, 600)
        background = Color.WHITE

        Timer(16) {
            repaint()
        }.start()
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

    }
}
