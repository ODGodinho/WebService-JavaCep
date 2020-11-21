import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Authorization {

	/**
	 * solicita o login pelo teclado do usuario
	 * @param keyboard
	 * @return
	 */
	public static String[] getKeyBoardUserLogin(Scanner keyboard) {
		String login = "", password = "";

		while (!Validation.isValidString(login)) {
			System.out.print(ConsoleColors.CYAN_BOLD_BRIGHT + "Login: "
					+ ConsoleColors.BLACK);
			login = keyboard.nextLine();
			System.out.println(ConsoleColors.RESET);
		}

		while (!Validation.isValidString(password)) {
			System.out.print(ConsoleColors.CYAN_BOLD_BRIGHT + "Senha: "
					+ ConsoleColors.BLACK);
			password = keyboard.nextLine();
			System.out.println(ConsoleColors.RESET);
		}

		return new String[] { login, password };

	}

	/**
	 * retorna o login no arquivo de config
	 * @return
	 */
	public static String[] getFileUserLogin() {
		try (InputStream input = new FileInputStream("config.properties")) {

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			return new String[] { prop.getProperty("login"),
					prop.getProperty("password") };
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
	/**
	 * recupera o login padrao do arquivo
	 * @param keyboard
	 * @return
	 */
	public static String[] getUseAuthorization(Scanner keyboard) {
		return getUseAuthorization(keyboard, 1);
	}

	
	/**
	 * solicita metodo de login dinamico
	 * @param keyboard
	 * @param type
	 * @return
	 */
	public static String[] getUseAuthorization(Scanner keyboard, int type) {
		switch (type) {
		case 1:
			return getFileUserLogin();
		case 2:
			return getKeyBoardUserLogin(keyboard);
		}
		return null;
	}

}
