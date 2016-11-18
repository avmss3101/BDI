import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class testando {
	public static void main(String[] args) {
		String frase;
		JLabel label1 = new JLabel("ID:");
		
		JLabel label2 = new JLabel("Titulo:");
		JLabel label3 = new JLabel("Ano:");
		JLabel label4 = new JLabel("Os 10 mais bem avaliados");
		JLabel label5 = new JLabel("Os 10 mais votados");
		JLabel label6 = new JLabel("Os 10 mais antigos");
		JLabel label7 = new JLabel("Os 10 mais recentes");
		JTextField campo = new JTextField();
		JOptionPane.showConfirmDialog(null,	new Object[]{label1, label2, label3, label4, label5, label6, label7, campo}, "Campo", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		frase = campo.getText();
		}
}
