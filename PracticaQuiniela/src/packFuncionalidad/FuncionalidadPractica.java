package packFuncionalidad;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import packDefiniciones.AdministracionApuestas;
import packDefiniciones.Calendario;
import packDefiniciones.Constantes;
import packDefiniciones.Jornada;
import packDefiniciones.ListaEquipos;
import packDefiniciones.Partido;
import packDefiniciones.PremiosAcertantes;
import packDefiniciones.Quiniela;
import packDefiniciones.Resultado;
import packDefiniciones.Resultados;

/**
 * Este fichero contiene los subprogramas que debes desarrollar para la práctica
 */
public class FuncionalidadPractica {

	/**
	 * Esta función añada un nuevo partido a la lista de apuestas múltiples
	 * realizadas en la quiniela. La función comprueba que los datos son
	 * correctos, más concretamente:
	 * 
	 * <ul>
	 * <li>El partido es válido</li>
	 * <li>No se ha realizado una apuesta múltiple en ese partido previamente
	 * </li>
	 * </ul>
	 * 
	 * @param pQuiniela
	 *            la quiniela en la se desea añadir una nueva apuesta múltiple
	 * @param pPartido
	 *            el partido en el que se desea realizar la apuesta múltiple
	 * @return true si se ha podido realizar la operación y false en caso
	 *         contrario
	 * 
	 *         <p>
	 *         <b>PRE:</b> pQuiniela contiene una quiniela inicializada (no
	 *         <em>null</em>)
	 *         </p>
	 */
	public static boolean addApuestaMultiple(Quiniela pQuiniela, int pPartido) {
		boolean valida = true; //Suponemos que es v�lida salvo que incumpla alguna de las condiciones
		if(pQuiniela.numApuestasMultiples < Constantes.NUM_APUESTAS_MULTIPLES && pPartido >= 0 && pPartido <= Constantes.PARTIDOS){ //Se comprueba que no haya hecho el número máximo de apuestas múltiples y que el partido es válido
			int i = 0;
			while(valida && i < pQuiniela.numApuestasMultiples){//Comprobamos que no haya realizado una apuesta m�ltiple para ese partido (mientras se cumpla ser� valido, sino no)
				if(pQuiniela.apuestasMultiples[i] == pPartido){//Ya habr� realizado una apuesta m�ltiple para ese partido -> no es v�lido
					valida = false;
				}
				i++;
			}
			if(valida){//Si la apuesta m�ltiple es válida la añade y devolverá true
				pQuiniela.apuestasMultiples[i] = pPartido; //Se a�ade el partido a la lista de apuestas m�ltiples
				pQuiniela.numApuestasMultiples++; //Se incrementa el n�mero de apuestas m�ltiples
			}
		}
		else { //Incumple alguna de las condiciones -> no es v�lida
			valida = false;
		}	
		return valida;
	}

	/**
	 * Esta función realiza la apuesta de un partido en la quiniela. La función
	 * comprueba que los datos son correctos, más concretamente:
	 * 
	 * <ul>
	 * <li>El partido es válido</li>
	 * <li>La apuesta es correcta
	 * <ul>
	 * <li>Si es una apuesta sencilla, la apuesta debe ser <em>1</em>,
	 * <em>X</em>, <em>x</em> o <em>2</em>
	 * <li>Si es una apuesta múltiple, la apuesta debe ser <em>X</em>,
	 * <em>x</em> o <em>2</em>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * 
	 * @param pQuiniela
	 *            la quiniela en la que se quiere realizar la apuesta
	 * @param pPartido
	 *            partido en el que se quiere realizar la apuesta
	 * @param pResultado
	 *            caracter que indica la apuesta realizada
	 * @return true si se ha podido realizar la operación y false en caso
	 *         contrario <br>
	 *         <p>
	 *         <b>PRE:</b> pQuiniela contiene una quiniela inicializada (no
	 *         <em>null</em>)
	 *         </p>
	 */
	public static boolean realizarApuestaPartido(Quiniela pQuiniela, int pPartido, char pResultado) {
		boolean valida = true;
		if(pPartido >= 0 && pPartido <= Constantes.PARTIDOS) {//Primero comprobamos que el partido sea v�lido
			boolean multiple = false;
			int i = 0;
			while(!multiple && i < pQuiniela.numApuestasMultiples){//Se comprueba si la apuesta es m�ltiple o no buscandola en la lista de apuestas m�ltiples de la quiniela
				if(pQuiniela.apuestasMultiples[i] == pPartido){//Si est� en la lista, la apuesta es m�ltiple
					multiple = true;
				}
				i++;
			}
			if(multiple){//Si es m�ltiple se comprueba que el resultado sea X o 2
				if(pResultado != 'X' && pResultado != '2'){
					valida = false;
				}
			}
			else{//Sino es m�ltiple se comprueba que sea 1, X o 2
				if(pResultado != '1' && pResultado != 'X' && pResultado != '2'){
					valida = false;
				}
			}
			if(valida){//Si es v�lida se realiza la apuesta
				pQuiniela.apuesta[pPartido] = pResultado;
				if(multiple){//Si la apuesta es m�ltiple, adem�s hay que a�adirla en la lista de apuestas m�ltiple
					addApuestaMultiple(pQuiniela, pPartido);
				}
			}
		}
		else {
			valida = false;
		}
		return valida;
	}

	/**
	 * Esta función carga y devuelve una quiniela de un fichero de texto. Para
	 * ello comprueba que los datos son correctos. Si el fichero especificado no
	 * existe o los datos no son correctos devuelve <em>null</em>. La función
	 * realiza las siguientes comprobaciones:
	 * 
	 * <ul>
	 * <li>La jornada especificada en el fichero debe ser válida</li>
	 * <li>El número de apuestas múltiples debe ser válido</li>
	 * <li>Los partidos en los que se realiza la apuesta múltiple deben ser
	 * correctos</li>
	 * <li>Las apuestas realizadas deben ser correctas</li>
	 * </ul>
	 *
	 * 
	 * @param pFich
	 *            El fichero que contiene los datos a partir de los cuales se va
	 *            a generar una quiniela
	 * @return la quiniela cargada si el fichero contiene los datos que
	 *         representa una una quiniela correcta o <em>null</em> en caso
	 *         contrario <br>
	 *         <p>
	 *         <b>PRE:</b> pFich contiene el nombre del fichero (no
	 *         <em>null</em>)
	 *         </p>
	 */
	public static Quiniela cargarQuiniela(String pFich) {
		Quiniela quiniela = null;
		try(Scanner fichero = new Scanner(new File(pFich))){//Tratar abrir el fichero
			//Si hay mas lineas darle el valor de la siguiente liena a las variables
			if (fichero.hasNextLong()){
				long id = fichero.nextLong();
				if(fichero.hasNextInt()){
					int jornada = fichero.nextInt();
					if (fichero.hasNextLine() && jornada > 0 && jornada <= Constantes.JORNADAS){//Si la jornada es correcta seguir leyendo fichero
						String nombre = fichero.nextLine();
						nombre = fichero.nextLine();
						if (fichero.hasNextInt()){
							int numApuestasMultiples = fichero.nextInt();
							if(numApuestasMultiples <= Constantes.NUM_APUESTAS_MULTIPLES && numApuestasMultiples >= 0){//Si las apuestas multiples son correctas seguir leyendo fichero
								quiniela = new Quiniela();
								quiniela.identificador = id;
								quiniela.numJornada = jornada;
								quiniela.nombreApostante = nombre;
								quiniela.apuestasMultiples = new int[Constantes.NUM_APUESTAS_MULTIPLES];
								quiniela.apuesta = new char[Constantes.PARTIDOS];
								boolean valido = true;
								int i = 0;
								while (valido && i < numApuestasMultiples && fichero.hasNextInt()){//Cargar cada apuesta multiple mientras sean correctas
									valido = addApuestaMultiple(quiniela, fichero.nextInt()-1);
									i++;
								}
								int k = 0;
								while (valido && k <= Constantes.PARTIDOS && fichero.hasNext()){//Cargar cada partido mientras sea correcto
									valido = realizarApuestaPartido(quiniela, k, fichero.next().charAt(0));
									k++;
								}
								if (!valido){
									quiniela = null;
								}
							}
						}
					}
				}
			}
		}
		catch (FileNotFoundException ex) {
			return null;
		}
		return quiniela;
	}

	/**
	 * Esta función devuelve el precio de la quiniela. Las quinielas tienen un
	 * precio base de 1,5 euros. Por cada apuesta múltiple, se suma 0,5 euros al
	 * precio.
	 * 
	 * @param pQuiniela
	 *            La quiniela de la que se quiere calcular el precio
	 * @return El precio de la quiniela <br>
	 *         <p>
	 *         <b>PRE:</b> pQuiniela contiene una quiniela completada (no
	 *         <em>null</em>)
	 *         </p>
	 */
	public static float calcularPrecioQuiniela(Quiniela pQuiniela) {
		return 1.5f + pQuiniela.numApuestasMultiples*0.5f; //devolver el precio de la quiniela (1.5�) mas 0.5� por cada apuesta multiple
	}

	/**
	 * Sella una quiniela en la administración de apuestas y devuelve el precio
	 * de la quiniela. Se pueden sellar tantas quinielas como se desee.
	 * 
	 * 
	 * @param pAdmApuestas
	 *            La administración de apuestas que recoge todas las quinielas
	 *            selladas hasta el momento, agrupadas por jornada
	 * @param pQuiniela
	 *            La quiniela que se desea sellar
	 * @return Devuelve el precio que debe abonar el apostante al sellar la
	 *         quiniela <br>
	 *         <p>
	 *         <b>PRE:</b> pQuiniela contiene una quiniela completada (no
	 *         <em>null</em>)
	 *         </p>
	 */
	public static float sellarApuesta(AdministracionApuestas pAdmApuestas, Quiniela pQuiniela) {
		Quiniela [] quinielasJornada = pAdmApuestas.quinielas[pQuiniela.numJornada];
		
		if(quinielasJornada[quinielasJornada.length-1] != null){ //Comprobar si el sitio donde la queremos sellar es nulo
			Quiniela [] aux = new Quiniela[quinielasJornada.length+1];//Si esta lleno crearemos un sitio auxiliar nuevo pero con mas longitud
			for(int i=0; i<quinielasJornada.length; i++){//Las a�adiremos las anteriores y la nueva en la auxiliar
				aux[i] = quinielasJornada[i];
			}
			aux[quinielasJornada.length] = pQuiniela;
			pAdmApuestas.quinielas[pQuiniela.numJornada] = aux;
		}
		else{
			quinielasJornada[quinielasJornada.length-1] = pQuiniela; // Si el sitio es nulo la a�adiremos ah�
		}
		return calcularPrecioQuiniela(pQuiniela);
	}

	/**
	 * Esta función devuelve el número de aciertos que ha tenido la quiniela de
	 * acuerdo a los resultados que se han dado.
	 * 
	 * @param pCal
	 *            el calendario que contiene el calendario de partidos de la
	 *            liga
	 * @param pResultados
	 *            Los resultados de los partidos jugados hasta el momento
	 * @param pListaEquipos
	 *            la lista de equipos
	 * @param pQuiniela
	 *            La quiniela para la que se desea calcular el número de
	 *            aciertos
	 * @return Devuelve el número de aciertos de la quiniela <br>
	 *         <p>
	 *         <b>PRE:</b> pQuiniela contiene una quiniela completada (no
	 *         <em>null</em>) y pResultados contiene los resultados de todos los
	 *         partidos hasta el momento, incluidos los partidos de la jornada a
	 *         la que corresponde la quiniela. pCal contiene el calendario
	 *         completo de la liga y PlistaEquipos contiene la lista de equipos
	 *         que participan en la liga
	 *         </p>
	 */
	public static int calcularNumeroAciertos(Calendario pCal, Resultados pResultados, ListaEquipos pListaEquipos,
			Quiniela pQuiniela) {
			Jornada jornada = pCal.jornadas[pQuiniela.numJornada];
			Partido [] partidos = jornada.partidos;
			char[] resultados = new char[Constantes.PARTIDOS];
			int numAciertos = 0;
			for(int i=0; i< partidos.length; i++){
				int eqL = 0; 
				int eqV = 0; 
				int j = 0;
				boolean encontradoEqL = false; 
				boolean encontradoEqV = false;
				while( (!encontradoEqL || !encontradoEqV) && j < pListaEquipos.equipos.length){//Comrpobamos que los equipos del partido esten en nuestra lista de equipos
					if(partidos[i].equipoLocal.equals(pListaEquipos.equipos[j])){
						encontradoEqL = true;
						eqL = j;
					}
					if(partidos[i].equipoVisitante.equals(pListaEquipos.equipos[j])){
						encontradoEqV = true;
						eqV = j;
					}
					j++;
				}
				if(encontradoEqL && encontradoEqV){ //Si los datos son correctos a�adiremos el resultado
					Resultado resultado = pResultados.resultados[eqL][eqV];
					char r;
					if(resultado.golesLocal > resultado.golesVisitante){
						r = '1'; //Si el local ha marcado mas goles le asignaremos un 1
					}
					else if(resultado.golesLocal < resultado.golesVisitante){
						r = '2'; //Si el visitante ha marcado mas goles le asignaremos un 2
					}
						else{
							r = 'X'; //Si no le asignaremos una X
						}
					resultados[i] = r;
				}
			}
			for(int i = 0; i< pQuiniela.apuesta.length; i++){
				if(pQuiniela.apuesta[i] == resultados[i]){ // Si la apuesta realizada coincide con el resultado sumaremos 1 a el numero de aciertos
					numAciertos++;
				}
				else{
					boolean multiple = false;
					int j = 0;
					while(!multiple && j<pQuiniela.numApuestasMultiples){ //Miraremos si la apuesta es multiple
						if(i == pQuiniela.apuestasMultiples[j])multiple = true;
						j++;
					}
					if(multiple && resultados[i] == '1'){ //Si el resultado es un 1 y se esta comprobando una apuesta multiple sumaremos 1 al numero de aciertos
						numAciertos++;
					}
				}
			}
			
		return numAciertos;
	}

	/**
	 * Esta función devuelve las cantidades que percibirán los acertantes de
	 * cada categoría. Las quinielas con 10 aciertos se reparten el 40% de la
	 * recaudación, las quinielas con 9 aciertos el 20% y las quinielas con 8 el
	 * 10%. Si la jornada indicada no es válida, devuelve <em>null</em>.
	 * 
	 * 
	 * @param pCal
	 *            El calendario de partidos de la liga
	 * @param pListaEquip
	 *            La lista de equipos que participan en la liga
	 * @param pRes
	 *            Los resultados hasta el momento
	 * @param pAdm
	 *            La administración de apuestas con todas las quinielas selladas
	 *            hasta el momento
	 * @param pJorn
	 *            la Jornada para la que se deben calcular los premios para los
	 *            acertantes
	 * @return Los premios que percibiran los acertantes de cada categoria. Null
	 *         si la jornada no es válida
	 *         <p>
	 *         <b>PRE:</b> La administración de apuestas contiene todas las
	 *         quinielas selladas hasta el momento y pResultados contiene los
	 *         resultados de todos los partidos hasta el momento, incluidos los
	 *         partidos de la jornada a la que corresponde la quiniela. pCal
	 *         contiene el calendario completo de la liga y PlistaEquipos
	 *         contiene la lista de equipos que participan en la liga.
	 *         </p>
	 */
	public static PremiosAcertantes calcularPremiosAcertantes(Calendario pCal,
			ListaEquipos pListaEquip, Resultados pRes,
			AdministracionApuestas pAdm, int pJorn) {
		if(pJorn >= 0 && pJorn < Constantes.JORNADAS){ //Comprobar que la jornada sea correcta
			Quiniela [] quinielas = pAdm.quinielas[pJorn];
			float recaudacion = 0.0f;
			int acertantes10 = 0;
			int acertantes9 = 0;
			int acertantes8 = 0;
			int aciertos = 0;
			for(int i=0; i < quinielas.length; i++){
				aciertos = calcularNumeroAciertos(pCal, pRes, pListaEquip, quinielas[i]); 
				recaudacion = recaudacion + calcularPrecioQuiniela(quinielas[i]); //Calcularemos la recaudacion recibida por todas las quinielas
				//Calcularemos cauntas quinielas hay de 10, 9 y 8 aciertos, que seran las premiadas
				if(aciertos == 10){
					acertantes10++;
				}
				if(aciertos == 9){
					acertantes9++;
				}
				if(aciertos == 8){
					acertantes8++;
				}
			}
			//Calcularemos el premio de la quiniela dependiendo de los aciertos que haya tenido
			PremiosAcertantes premios = new PremiosAcertantes();
			if(acertantes10 == 0) premios.premio10Aciertos = 0;
			else premios.premio10Aciertos = (recaudacion*Constantes.PORCENTAJE_10_ACIERTOS)/acertantes10;
			if(acertantes9 == 0) premios.premio9Aciertos = 0;
			else premios.premio9Aciertos = recaudacion*Constantes.PORCENTAJE_9_ACIERTOS/acertantes9;
			if(acertantes8 == 0) premios.premio8Aciertos = 0;
			else premios.premio8Aciertos = recaudacion*Constantes.PORCENTAJE_8_ACIERTOS/acertantes8;
			return premios;
		}
		else return null;
	}

}
