import javax.swing.JFrame;
/**
 * Stellt die Guikomponente JFrame da um auf dem darin enthaltenen JPanel zu zeichenn
 * <p>Konstruktoren:
 * @param title : String
 * @param width : Breite
 * @param height : Height
 * 
 * @author dennisb/ismaila
 *
 */
public class Screen extends JFrame{
	/**
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public Screen(String title, int width, int height) {
		
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
}
