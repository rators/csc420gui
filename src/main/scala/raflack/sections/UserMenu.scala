package raflack.sections

import java.io.File
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JLabel, JPanel}

import hw2.SwingImpl._
import net.miginfocom.swing.MigLayout

/**
  *
  */
class UserMenu extends JPanel(new MigLayout()) {
  val url = getClass.getResource("/flags/user.png")
  val imgFile = new File(url.getFile)
  val image = ImageIO.read(imgFile).getScaledInstance(150, 100, 0)
  val imageIcon = new ImageIcon(image)
  val userImage = new JLabel(imageIcon)
  this += userImage -> "dock north"
}
