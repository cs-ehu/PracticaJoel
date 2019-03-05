package packFuncionalidad;

import java.util.Date;

import junit.framework.TestCase;
import packDefiniciones.AdministracionApuestas;
import packDefiniciones.Calendario;
import packDefiniciones.Constantes;
import packDefiniciones.ListaEquipos;
import packDefiniciones.PremiosAcertantes;
import packDefiniciones.Quiniela;
import packDefiniciones.Resultado;
import packDefiniciones.Resultados;

public class PruebasPractica extends TestCase {
	private final static boolean LONG_TEST = false;
	private final static int NUM_ELEMENTS = LONG_TEST? Integer.MAX_VALUE: 10000;
	
	private Resultados resultados;
	private AdministracionApuestas administracionApuestas;
	private ListaEquipos listaEquipos;
	private Calendario calendario;
	private Quiniela quiniela1, quiniela2, quiniela3, quiniela4, quiniela5,
			quiniela6, quiniela7, quiniela8;
	private Quiniela[] quinielasPruebas;

	/**
	 * Inicializa los datos que se van a utilizar para las pruebas. Este
	 * subprograma se ejecuta siempre antes de cada una de las pruebas, lo que
	 * permite conocer el estado previo y determinar cuál debería ser el
	 * resultado de la ejecución de cada subprograma.
	 */
	protected void setUp() throws Exception {

		administracionApuestas = new AdministracionApuestas();
		resultados = inicializarResultados();

		listaEquipos = new ListaEquipos();
		calendario = new Calendario();
		Funcionalidad.inicializar(listaEquipos, calendario);

		quiniela1 = new Quiniela();
		quiniela1.identificador = new Date().getTime();
		quiniela1.nombreApostante = "Apostante1";
		quiniela1.numApuestasMultiples = 0;
		quiniela1.numJornada = 0;
		for (int i = 0; i < Constantes.PARTIDOS; i++) {
			quiniela1.apuesta[i] = '1';
		}

		quiniela2 = new Quiniela();
		quiniela2.identificador = new Date().getTime();
		quiniela2.nombreApostante = "Apostante2";
		quiniela2.numApuestasMultiples = 1;
		quiniela2.apuestasMultiples[0] = 8;
		quiniela2.numJornada = 0;
		for (int i = 0; i < Constantes.PARTIDOS; i++) {
			if (i != 8) {
				quiniela2.apuesta[i] = '1';
			} else {
				quiniela2.apuesta[i] = '2';
			}
		}

		quiniela3 = new Quiniela();
		quiniela3.identificador = new Date().getTime();
		quiniela3.nombreApostante = "Apostante3";
		quiniela3.numApuestasMultiples = 2;
		quiniela3.apuestasMultiples[0] = 8;
		quiniela3.apuestasMultiples[1] = 9;
		quiniela3.numJornada = 0;
		for (int i = 0; i < Constantes.PARTIDOS; i++) {
			if (i != 8 && i != 9) {
				quiniela3.apuesta[i] = '1';
			} else if (i == 9) {
				quiniela3.apuesta[i] = 'X';
			} else {
				quiniela3.apuesta[i] = '2';
			}
		}

		quiniela4 = new Quiniela();
		quiniela4.identificador = new Date().getTime();
		quiniela4.nombreApostante = "Apostante4";
		quiniela4.numApuestasMultiples = 3;
		quiniela4.apuestasMultiples[0] = 3;
		quiniela4.apuestasMultiples[1] = 4;
		quiniela4.apuestasMultiples[2] = 5;
		quiniela4.numJornada = 0;
		for (int i = 0; i < Constantes.PARTIDOS; i++) {
			char apuesta;
			switch (i) {
			case 3:
			case 4:
				apuesta = 'X';
				break;
			case 5:
				apuesta = '2';
				break;
			default:
				apuesta = '1';
			}
			quiniela4.apuesta[i] = apuesta;
		}

		quiniela5 = new Quiniela();
		quiniela5.identificador = new Date().getTime();
		quiniela5.nombreApostante = "Apostante2";
		quiniela5.numApuestasMultiples = 4;
		quiniela5.apuestasMultiples[0] = 3;
		quiniela5.apuestasMultiples[1] = 4;
		quiniela5.apuestasMultiples[2] = 5;
		quiniela5.apuestasMultiples[3] = 6;
		quiniela5.numJornada = 0;
		for (int i = 0; i < Constantes.PARTIDOS; i++) {
			char apuesta;
			switch (i) {
			case 3:
			case 4:
				apuesta = 'X';
				break;
			case 5:
			case 6:
				apuesta = '2';
				break;
			default:
				apuesta = '1';
			}
			quiniela5.apuesta[i] = apuesta;
		}

		quiniela6 = new Quiniela();
		quiniela6.identificador = new Date().getTime();
		quiniela6.nombreApostante = "Apostante2";
		quiniela6.numApuestasMultiples = 4;
		quiniela6.apuestasMultiples[0] = 3;
		quiniela6.apuestasMultiples[1] = 4;
		quiniela6.apuestasMultiples[2] = 5;
		quiniela6.apuestasMultiples[3] = 6;
		quiniela6.numJornada = 0;
		for (int i = 0; i < Constantes.PARTIDOS; i++) {
			char apuesta;
			switch (i) {
			case 3:
			case 4:
			case 7:
				apuesta = 'X';
				break;
			case 5:
			case 6:
				apuesta = '2';
				break;
			default:
				apuesta = '1';
			}
			quiniela6.apuesta[i] = apuesta;
		}

		quiniela7 = new Quiniela();
		quiniela7.identificador = new Date().getTime();
		quiniela7.nombreApostante = "Apostante2";
		quiniela7.numApuestasMultiples = 4;
		quiniela7.apuestasMultiples[0] = 3;
		quiniela7.apuestasMultiples[1] = 4;
		quiniela7.apuestasMultiples[2] = 5;
		quiniela7.apuestasMultiples[3] = 6;
		quiniela7.numJornada = 0;
		for (int i = 0; i < Constantes.PARTIDOS; i++) {
			char apuesta;
			switch (i) {
			case 3:
			case 4:
			case 5:
			case 7:
				apuesta = 'X';
				break;
			case 6:
				apuesta = '2';
				break;
			default:
				apuesta = '1';
			}
			quiniela7.apuesta[i] = apuesta;
		}

		quiniela8 = new Quiniela();
		quiniela8.identificador = new Date().getTime();
		quiniela8.nombreApostante = "Apostante2";
		quiniela8.numApuestasMultiples = 0;
		quiniela8.numJornada = 0;
		for (int i = 0; i < Constantes.PARTIDOS; i++) {
			char apuesta;
			switch (i) {
			case 5:
			case 7:
				apuesta = 'X';
				break;
			case 6:
				apuesta = '2';
				break;
			default:
				apuesta = '1';
			}
			quiniela8.apuesta[i] = apuesta;
		}

		quinielasPruebas = new Quiniela[] { quiniela1, quiniela2, quiniela3,
				quiniela4, quiniela5, quiniela6, quiniela7, quiniela8 };
		
		administracionApuestas = new AdministracionApuestas();
	}

	/**
	 * Inicializa los resultados para realizar comprobaciones
	 * 
	 * @return devuelve los resultados inicializados
	 */
	private Resultados inicializarResultados() {
		Resultados res = new Resultados();
		res.resultados = new Resultado[Constantes.EQUIPOS][Constantes.EQUIPOS];

		res.resultados[10][4] = new Resultado();
		res.resultados[10][4].golesLocal = 3;
		res.resultados[10][4].golesVisitante = 2;

		res.resultados[3][13] = new Resultado();
		res.resultados[3][13].golesLocal = 3;
		res.resultados[3][13].golesVisitante = 0;

		res.resultados[7][2] = new Resultado();
		res.resultados[7][2].golesLocal = 3;
		res.resultados[7][2].golesVisitante = 0;

		res.resultados[15][12] = new Resultado();
		res.resultados[15][12].golesLocal = 2;
		res.resultados[15][12].golesVisitante = 0;

		res.resultados[8][18] = new Resultado();
		res.resultados[8][18].golesLocal = 2;
		res.resultados[8][18].golesVisitante = 0;

		res.resultados[0][16] = new Resultado();
		res.resultados[0][16].golesLocal = 0;
		res.resultados[0][16].golesVisitante = 0;

		res.resultados[11][6] = new Resultado();
		res.resultados[11][6].golesLocal = 1;
		res.resultados[11][6].golesVisitante = 4;

		res.resultados[9][17] = new Resultado();
		res.resultados[9][17].golesLocal = 1;
		res.resultados[9][17].golesVisitante = 1;

		res.resultados[19][14] = new Resultado();
		res.resultados[19][14].golesLocal = 1;
		res.resultados[19][14].golesVisitante = 0;

		res.resultados[1][5] = new Resultado();
		res.resultados[1][5].golesLocal = 1;
		res.resultados[1][5].golesVisitante = 0;

		return res;
	}

	/**
	 * Subprograma que comprueba el correcto funcionamiento del subprograma
	 * {@link packFuncionalidad.FuncionalidadPractica#addApuestaMultiple(Quiniela, int)}
	 */
	public void testAddApuestaMultiple() {
		// Comprobar partido correcto
		assertFalse("No se comprueba que el partido sea válido",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, -1));
		assertFalse("No se comprueba que el partido sea válido",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 14));

		// Comrpobar que las apuestas múltiples se realizan bien
		assertTrue("No permite realizar una apuesta múltiple",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 0));
		assertEquals(
				"No se guarda adecuadamente el partido en el que se realiza la apuesta múltiple",
				0, quiniela1.apuestasMultiples[0]);
		assertEquals(
				"No se guarda adecuadamente el partido en el que se realiza la apuesta múltiple",
				1, quiniela1.numApuestasMultiples);
		assertTrue("No permite realizar una apuesta múltiple",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 1));
		assertEquals(
				"No se guarda adecuadamente el partido en el que se realiza la apuesta múltiple",
				1, quiniela1.apuestasMultiples[1]);
		assertEquals(
				"No se guarda adecuadamente el partido en el que se realiza la apuesta múltiple",
				2, quiniela1.numApuestasMultiples);
		assertTrue("No permite realizar una apuesta múltiple",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 2));
		assertEquals(
				"No se guarda adecuadamente el partido en el que se realiza la apuesta múltiple",
				2, quiniela1.apuestasMultiples[2]);
		assertEquals(
				"No se guarda adecuadamente el partido en el que se realiza la apuesta múltiple",
				3, quiniela1.numApuestasMultiples);
		// Comprobar que no se realizan apuestas múltiples repetidas
		assertFalse(
				"No controla que ya se haya realizado una apuesta múltiple para un partido",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 0));
		assertEquals(
				"No controla que ya se haya realizado una apuesta múltiple para un partido",
				3, quiniela1.numApuestasMultiples);
		assertFalse(
				"No controla que ya se haya realizado una apuesta múltiple para un partido",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 1));
		assertEquals(
				"No controla que ya se haya realizado una apuesta múltiple para un partido",
				3, quiniela1.numApuestasMultiples);
		assertFalse(
				"No controla que ya se haya realizado una apuesta múltiple para un partido",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 2));
		assertEquals(
				"No controla que ya se haya realizado una apuesta múltiple para un partido",
				3, quiniela1.numApuestasMultiples);
		// Comprobar que no se hacen apuestas de más
		assertTrue("No permite realizar una apuesta múltiple",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 3));
		assertEquals(
				"No se guarda adecuadamente el partido en el que se realiza la apuesta múltiple",
				3, quiniela1.apuestasMultiples[3]);
		assertEquals(
				"No se guarda adecuadamente el partido en el que se realiza la apuesta múltiple",
				4, quiniela1.numApuestasMultiples);
		assertFalse(
				"No controla que se hayan realizado todas las apuestas múltiples posibles",
				FuncionalidadPractica.addApuestaMultiple(quiniela1, 4));
		assertEquals(
				"No controla que se hayan realizado todas las apuestas múltiples posibles",
				4, quiniela1.numApuestasMultiples);

	}

	/**
	 * Subprograma que comprueba el correcto funcionamiento del subprograma
	 * {@link packFuncionalidad.FuncionalidadPractica#realizarApuestaPartido(Quiniela, int,char)}
	 */
	public void testRealizarApuestaPartido() {
		// Apuesta incorrecta
		assertFalse("No se comprueba que la apuesta sea un caracter válido",
				FuncionalidadPractica.realizarApuestaPartido(quiniela5, 0, 'a'));
		assertFalse("Se ha realizada la apuesta con un caracter no válido",
				quiniela5.apuesta[0] == 'a');
		// Partido incorrecto
		assertFalse("No se comprueba que el partido sea válido",
				FuncionalidadPractica
						.realizarApuestaPartido(quiniela5, -1, '1'));
		assertFalse("No se comprueba que el partido sea válido",
				FuncionalidadPractica
						.realizarApuestaPartido(quiniela5, 14, '1'));
		// Apuesta múltiple
		assertFalse(
				"Se permite apostar (1) en un partido en el que se ha realizado apuesta múltiple",
				FuncionalidadPractica.realizarApuestaPartido(quiniela5, 6, '1'));
		// Apuesta correcta
		assertTrue("Falla al realizar una apuesta correcta (1)",
				FuncionalidadPractica.realizarApuestaPartido(quiniela5, 9, '1'));
		assertEquals("No guarda la apuesta adecuadamente", '1',
				quiniela5.apuesta[9]);
		assertTrue("Falla al realizar una apuesta correcta (2)",
				FuncionalidadPractica.realizarApuestaPartido(quiniela5, 6, '2'));
		assertEquals("No guarda la apuesta adecuadamente", '2',
				quiniela5.apuesta[6]);
		assertTrue("Falla al realizar una apuesta correcta (X)",
				FuncionalidadPractica.realizarApuestaPartido(quiniela5, 3, 'X'));
		assertEquals("No guarda la apuesta adecuadamente", 'X',
				quiniela5.apuesta[3]);
	}

	/**
	 * Subprograma que comprueba el correcto funcionamiento del subprograma
	 * {@link packFuncionalidad.FuncionalidadPractica#calcularPrecioQuiniela(Quiniela)}
	 */
	public void testCalcularPrecioQuiniela() {
		assertEquals("No se calcula bien el precio de la quiniela", 1.5f,
				FuncionalidadPractica.calcularPrecioQuiniela(quiniela1));
		assertEquals(
				"No se calcula bien el precio de la quiniela con apuestas múltiples",
				2f, FuncionalidadPractica.calcularPrecioQuiniela(quiniela2));
		assertEquals(
				"No se calcula bien el precio de la quiniela con apuestas múltiples",
				2.5f, FuncionalidadPractica.calcularPrecioQuiniela(quiniela3));
		assertEquals(
				"No se calcula bien el precio de la quiniela con apuestas múltiples",
				3f, FuncionalidadPractica.calcularPrecioQuiniela(quiniela4));
		assertEquals(
				"No se calcula bien el precio de la quiniela con apuestas múltiples",
				3.5f, FuncionalidadPractica.calcularPrecioQuiniela(quiniela5));
	}

	/**
	 * Subprograma que comprueba el correcto funcionamiento del subprograma
	 * {@link packFuncionalidad.FuncionalidadPractica#calcularPremiosAcertantes(Calendario,ListaEquipos, Resultados,AdministracionApuestas, int)}
	 */
	public void testSellarApuesta() {
		float recaudacion = 21.0f;

		// Probar sellar con las quinielas
		assertEquals("Sellar no devuelve el precio correcto", 1.5f,
				FuncionalidadPractica.sellarApuesta(administracionApuestas,quiniela1));
		assertEquals("Sellar no devuelve el precio correcto", 2f,
				FuncionalidadPractica.sellarApuesta(administracionApuestas,quiniela2));
		assertEquals("Sellar no devuelve el precio correcto", 2.5f,
				FuncionalidadPractica.sellarApuesta(administracionApuestas,quiniela3));
		assertEquals("Sellar no devuelve el precio correcto", 3f,
				FuncionalidadPractica.sellarApuesta(administracionApuestas,quiniela4));
		assertEquals("Sellar no devuelve el precio correcto", 3.5f,
				FuncionalidadPractica.sellarApuesta(administracionApuestas,quiniela5));
		assertEquals("Sellar no devuelve el precio correcto", 3.5f,
				FuncionalidadPractica.sellarApuesta(administracionApuestas,quiniela6));
		assertEquals("Sellar no devuelve el precio correcto", 3.5f,
				FuncionalidadPractica.sellarApuesta(administracionApuestas,quiniela7));
		assertEquals("Sellar no devuelve el precio correcto", 1.5f,
				FuncionalidadPractica.sellarApuesta(administracionApuestas,quiniela8));

		// Comprobar que sellar soporta añadir todas las quinielas que haga
		// falta

	for (int i = 8; i < NUM_ELEMENTS; i++) {
			assertEquals("Sellar no devuelve el precio correcto", 1.5f,
					FuncionalidadPractica
							.sellarApuesta(administracionApuestas, new Quiniela()));
			recaudacion += 1.5f;
			PremiosAcertantes premios = FuncionalidadPractica
					.calcularPremiosAcertantes(calendario, listaEquipos,
							resultados, administracionApuestas, 0);
			assertEquals("El premio para los acertantes de 10 no es correcto",
					recaudacion * 0.2f, premios.premio10Aciertos, 0.01f);
			assertEquals("El premio para los acertantes de 9 no es correcto",
					recaudacion * 0.2f, premios.premio9Aciertos, 0.01f);
			assertEquals("El premio para los acertantes de 8 no es correcto",
					recaudacion * 0.1f, premios.premio8Aciertos, 0.01f);

		}
	}

	public void testCalcularNumeroAciertos() {
		assertEquals("No se calculan bien los aciertos", 7,
				FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela1));
		assertEquals(
				"No se calculan bien los aciertos con las apuestas múltiples",
				7, FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela2));
		assertEquals(
				"No se calculan bien los aciertos con las apuestas múltiples",
				7, FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela3));
		assertEquals(
				"No se calculan bien los aciertos con las apuestas múltiples",
				7, FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela4));
		assertEquals(
				"No se calculan bien los aciertos con las apuestas múltiples",
				8, FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela5));
		assertEquals(
				"No se calculan bien los aciertos con las apuestas múltiples",
				9, FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela6));
		assertEquals(
				"No se calculan bien los aciertos con las apuestas múltiples",
				10, FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela7));

		assertEquals("No se calculan bien los aciertos", 10,
				FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela8));
		quiniela1.apuesta = new char[Constantes.PARTIDOS];
		assertEquals("No se calculan bien los aciertos", 0,
				FuncionalidadPractica.calcularNumeroAciertos(calendario,
						resultados, listaEquipos, quiniela1));
	}

	/**
	 * Subprograma que comprueba el correcto funcionamiento del subprograma
	 * {@link packFuncionalidad.FuncionalidadPractica#calcularPremiosAcertantes(Calendario,ListaEquipos, Resultados,AdministracionApuestas, int)}
	 */
	public void testCalcularPremiosAcertantes() {
		for (Quiniela quiniela : quinielasPruebas) {
			FuncionalidadPractica.sellarApuesta(administracionApuestas,
					quiniela);
		}

		// Jornadas incorrectas
		assertNull("No se comprueba que la jornada sea correcta",
				FuncionalidadPractica.calcularPremiosAcertantes(calendario,
						listaEquipos, resultados, administracionApuestas, -1));

		assertNull("No se comprueba que la jornada sea correcta",
				FuncionalidadPractica.calcularPremiosAcertantes(calendario,
						listaEquipos, resultados, administracionApuestas,
						Constantes.JORNADAS));

		// Jornada correcta
		PremiosAcertantes premios = FuncionalidadPractica
				.calcularPremiosAcertantes(calendario, listaEquipos,
						resultados, administracionApuestas, 0);
		assertNotNull("No se crea el reparto de premios", premios);
		assertEquals("El premio para los acertantes de 10 no es correcto",
				4.2f, premios.premio10Aciertos, 0.01f);
		assertEquals("El premio para los acertantes de 9 no es correcto", 4.2f,
				premios.premio9Aciertos, 0.01f);
		assertEquals("El premio para los acertantes de 8 no es correcto", 2.1f,
				premios.premio8Aciertos, 0.01f);

		FuncionalidadPractica.sellarApuesta(administracionApuestas, quiniela8);
		FuncionalidadPractica.sellarApuesta(administracionApuestas, quiniela6);
		FuncionalidadPractica.sellarApuesta(administracionApuestas, quiniela5);
		
		premios = FuncionalidadPractica
				.calcularPremiosAcertantes(calendario, listaEquipos,
						resultados, administracionApuestas, 0);
		assertNotNull("No se crea el reparto de premios", premios);
		assertEquals("El premio para los acertantes de 10 no es correcto",
				3.93f, premios.premio10Aciertos, 0.01f);
		assertEquals("El premio para los acertantes de 9 no es correcto", 2.95f,
				premios.premio9Aciertos, 0.01f);
		assertEquals("El premio para los acertantes de 8 no es correcto", 1.48f,
				premios.premio8Aciertos, 0.01f);
	}

	/**
	 * Subprograma que comprueba el correcto funcionamiento del subprograma
	 * {@link packFuncionalidad.FuncionalidadPractica#cargarQuiniela(String)}
	 */
	public void testCargarQuiniela() {

		// Fichero que no existe
		assertNull(
				"No se trata adecuadamente el caso en el que el fichero no existe",
				FuncionalidadPractica.cargarQuiniela("kk.txt"));
		// Quinielas incorrectas
		assertNull("No se comprueba que no haya apuestas múltiples repetidas",
				FuncionalidadPractica.cargarQuiniela(PruebasPractica.class
						.getResource("/QuinielaE1.txt").getPath()));
		assertNull("No se comprueba el número adecuado de apuestas múltiples",
				FuncionalidadPractica.cargarQuiniela(PruebasPractica.class
						.getResource("/QuinielaE2.txt").getPath()));
		assertNull("No se comprueba que las apuestas sean correctas",
				FuncionalidadPractica.cargarQuiniela(PruebasPractica.class
						.getResource("/QuinielaE3.txt").getPath()));
		assertNull(
				"No se comprueba que la apuesta sea correcta en un partido con apuesta múltiple",
				FuncionalidadPractica.cargarQuiniela(PruebasPractica.class
						.getResource("/QuinielaE4.txt").getPath()));
		// Quiniela correcta
		Quiniela quinielaCorrecta = FuncionalidadPractica
				.cargarQuiniela(PruebasPractica.class.getResource(
						"/Quiniela.txt").getPath());
		assertNotNull("No se carga la quiniela adecuadamente", quinielaCorrecta);
		assertEquals("No carga el identificador adecuadamente",
				876765875768765876L, quinielaCorrecta.identificador);
		assertEquals("No carga la jornada adecuadamente", 9,
				quinielaCorrecta.numJornada);
		assertEquals("No carga el apostante adecuadamente", "Juán Rellán",
				quinielaCorrecta.nombreApostante);
		assertEquals("No carga el número de apuestas múltiples adecuadamente",
				4, quinielaCorrecta.numApuestasMultiples);
		assertEquals("No carga las apuestas múltiples adecuadamente", 1,
				quinielaCorrecta.apuestasMultiples[0]);
		assertEquals("No carga las apuestas múltiples adecuadamente", 4,
				quinielaCorrecta.apuestasMultiples[1]);
		assertEquals("No carga las apuestas múltiples adecuadamente", 7,
				quinielaCorrecta.apuestasMultiples[2]);
		assertEquals("No carga las apuestas múltiples adecuadamente", 9,
				quinielaCorrecta.apuestasMultiples[3]);
		assertEquals("No carga las apuestas adecuadamente", '1',
				quinielaCorrecta.apuesta[0]);
		assertEquals("No carga las apuestas adecuadamente", '2',
				quinielaCorrecta.apuesta[1]);
		assertEquals("No carga las apuestas adecuadamente", 'X',
				quinielaCorrecta.apuesta[2]);
		assertEquals("No carga las apuestas adecuadamente", '1',
				quinielaCorrecta.apuesta[3]);
		assertEquals("No carga las apuestas adecuadamente", 'X',
				quinielaCorrecta.apuesta[4]);
		assertEquals("No carga las apuestas adecuadamente", 'X',
				quinielaCorrecta.apuesta[5]);
		assertEquals("No carga las apuestas adecuadamente", '2',
				quinielaCorrecta.apuesta[6]);
		assertEquals("No carga las apuestas adecuadamente", '2',
				quinielaCorrecta.apuesta[7]);
		assertEquals("No carga las apuestas adecuadamente", '2',
				quinielaCorrecta.apuesta[8]);
		assertEquals("No carga las apuestas adecuadamente", '2',
				quinielaCorrecta.apuesta[9]);
	}

}
