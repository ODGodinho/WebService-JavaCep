import java.rmi.RemoteException;
import java.util.Scanner;

import javax.xml.rpc.ServiceException;

import CEPService_pkg.CEPService;
import CEPService_pkg.CEPServiceLocator;
import CEPService_pkg.CEPServicePort;

public class Main {

	public static String[] authorization = new String[2];

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		authorization = Authorization.getUseAuthorization(keyboard);
		int type = getKeyBoardSearchType(keyboard);

		switch (type) {
		case 1:
			showAddressByCep(keyboard);
			break;
		case 2:
			showCepByAddress(keyboard);
			break;
		}

		keyboard.close();
	}

	public static int getKeyBoardSearchType(Scanner keyboard) {
		System.out.println("Qual Tipo de Pesquisa deseja Fazer: ");

		System.out.println(ConsoleColors.CYAN_BRIGHT + "1. "
				+ ConsoleColors.RESET + "Pesquisar endereço pelo CEP");
		System.out.println(ConsoleColors.CYAN_BRIGHT + "2. "
				+ ConsoleColors.RESET + "Pesquisar CEP pelo Endereço");

		int type;
		String typeText;
		while (true) {
			typeText = keyboard.nextLine();
			if (Validation.isValidType(typeText)) {
				type = Integer.parseInt(typeText);
				break;
			}
			System.out.print(ConsoleColors.RED_BOLD_BRIGHT
					+ "Digite um tipo Valido: " + ConsoleColors.RESET);
		}

		return type;

	}

	public static void showAddressByCep(Scanner keyboard) {
		String cepNumber = getKeyBoardCep(keyboard);

		CEPService servico = new CEPServiceLocator();

		try {

			CEPServicePort porta = servico.getCEPServicePort();
			String currentCep = porta.obterLogradouroAuth(cepNumber,
					authorization[0], authorization[1]);

			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT
					+ "Seu Endereço é: " + ConsoleColors.RESET);
			System.out.println(ConsoleColors.PURPLE_UNDERLINED + currentCep);

		} catch (ServiceException e) {
			System.err
					.println("Problema ao recuperar uma porta de comunicação");
		} catch (RemoteException e) {
			System.err.println("Problema ao recuperar o Cep Informado!");
		}
	}

	public static void showCepByAddress(Scanner keyboard) {
		String[] addressKeyBoard = getKeyBoardAddress(keyboard);

		CEPService servico = new CEPServiceLocator();
		int count = 0;
		try {

			CEPServicePort porta = servico.getCEPServicePort();
			String[] addressList = porta.obterCEPAuth(addressKeyBoard[0],
					addressKeyBoard[1], addressKeyBoard[2], authorization[0],
					authorization[1]);

			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT
					+ "Endereços Encontrados: " + ConsoleColors.RESET);
			for (String address : addressList) {
				System.out.println(ConsoleColors.PURPLE_UNDERLINED + address
						+ ConsoleColors.RESET);
				if (!address.equalsIgnoreCase("Logradouro não encontrado")) {
					++count;
				}
			}

			System.out.println("TOTAL: " + ConsoleColors.BLUE_BOLD_BRIGHT
					+ count);

		} catch (ServiceException e) {
			System.err
					.println("Problema ao recuperar uma porta de comunicação");
		} catch (RemoteException e) {
			System.err.println("Problema ao recuperar o Cep Informado!");
		}
	}

	/**
	 * Solicita ao cliente o cep e retorna o cep
	 * 
	 * @return String
	 */
	public static String getKeyBoardCep(Scanner keyboard) {
		System.out.print("Digite o Cep: ");
		String cepNumber = keyboard.nextLine();

		while (!Validation.isValidCep(cepNumber)) {
			System.out.print(ConsoleColors.RED_BOLD_BRIGHT
					+ "Digite um Cep Válido: " + ConsoleColors.RESET);
			cepNumber = keyboard.nextLine();
		}

		return cepNumber;
	}

	/**
	 * Solicita ao cliente o endereço
	 * 
	 * @return String
	 */
	private static String[] getKeyBoardAddress(Scanner keyboard) {
		String address = null, city = null, state = null;

		while (!Validation.isValidString(address)) {
			System.out.print(ConsoleColors.GREEN_BOLD + "Digite uma rua: "
					+ ConsoleColors.RESET);
			address = keyboard.nextLine();
		}

		while (!Validation.isValidCity(city)) {
			System.out.print(ConsoleColors.GREEN_BOLD + "Digite uma Cidade: "
					+ ConsoleColors.RESET);
			city = keyboard.nextLine();
		}

		while (!Validation.isValidState(state)) {
			System.out.print(ConsoleColors.GREEN_BOLD
					+ "Digite a sigla do Estado: " + ConsoleColors.RESET);
			state = keyboard.nextLine();
		}

		return new String[] { address, city, state };
	}

}
