package br.com.aula02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aula02 {
	// nome do driver JDBC

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	// endereco URL do banco de dados

	static final String DB_URL = "jdbc:mysql://localhost/bd_ii_aula_1";

	// credenciais de acesso ao banco de dados

	static final String usuario = "root";

	static final String senha = "123456";

	public static void main(String[] args) {

		Connection conexao = null;
		Statement instrucao = null;
		String consulta = null;

		// Registrar o JDBC driver 
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Abrir uma conexao

			conexao = DriverManager.getConnection(DB_URL, usuario, senha);

			instrucao = conexao.createStatement();

			consulta = "SELECT nome_do_estudante, id_estudante, senioridade, graduação from estudante";

			ResultSet conjuntoResultado = instrucao.executeQuery(consulta);

			// Extrair os resultados da conjunto resultado

			if (!conjuntoResultado.next()) {
				System.out.println("A tabela estudante está vazia");
			} else {

				// Recuperar todos os dados de acordo com cada linha retornada
				// pelo resultado.

				String nome_do_estudante = conjuntoResultado.getString("nome_do_estudante");
				int id_estudante = conjuntoResultado.getInt("id_estudante");
				String senioridade = conjuntoResultado.getString("senioridade");
				String graduacao = conjuntoResultado.getString("graduação");

				// Imprimir os valores na tela

				System.out.print("nome_do_estudante: " + nome_do_estudante);
				System.out.print(", id_estudante: " + id_estudante);
				System.out.print(", senioridade: " + senioridade);
				System.out.println(", graduacao: " + graduacao);

			}
			while (conjuntoResultado.next())
				;

			conjuntoResultado.close();
			instrucao.close();
			conexao.close();
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
		}

	}

}