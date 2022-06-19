package modelos;

/**
 * Clase encargada de generar aprendizaje para clasificar hombres y mujeres
 * 
 * @author Jose
 *
 */
public class Perceptron {

	public static double APRENDIZAJE = 0.25;
	private int[][] dataSet = { { 1, 0, 1, 0, 0, 1, 1 }, { 0, 0, 1, 0, 0, 1, 1 }, { 0, 1, 1, 1, 1, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0, 0, 0 }, { 0, 1, 0, 1, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 1 }, { 1, 1, 0, 0, 1, 0, 0 },
			{ 1, 1, 0, 0, 0, 1, 0 }, { 1, 1, 1, 0, 0, 0, 1 }, { 1, 1, 1, 0, 1, 0, 1 }, { 1, 1, 1, 1, 0, 0, 1 },
			{ 0, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1 } };
	private int[][] dataSetTest = { { 1, 1, 1, 0, 1, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 1, 0, 1, 0, 1, 0 }, { 1, 0, 1, 0, 1, 0, 1 }, { 1, 1, 1, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1, 1, 0 },
			{ 1, 1, 0, 1, 0, 0, 0 }, { 1, 1, 0, 1, 1, 0, 0 }, { 0, 0, 1, 1, 1, 1, 1 }, };
	public double pesoX1, pesoX2, pesoX3, pesoX4, pesoX5, pesoX6, baias;

	/**
	 * Metodo constructor de la clase
	 * Aquí se inicializan los datos de entrada para comanezar la iteración
	 */
	public Perceptron() {
		this.pesoX1 = 0;
		this.pesoX2 = 0;
		this.pesoX3 = 0;
		this.pesoX4 = 0;
		this.pesoX5 = 0;
		this.pesoX6 = 0;
		this.baias = 0;
	}

	/**
	 * Funcion de activacion HARDLIMIT
	 * @param Entrada para el HARDLIMIT
	 * @return 0 o 1 dependiendo de la función de activación
	 */
	private int hardLimit(double value) {
		if (value >= 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Genera el aprendizaje de la neurona
	 * llama la función de evaluar los pesos
	 * 
	 * @param numberIterations: Cantidad de iteraciones
	 */
	public void aprendizaje(int numberIterations) {
		System.out.println("Iniciando Aprendizaje....");
		for (int i = 0; i < numberIterations; i++) {
			evaluarPesos();
		}
		System.out.println("Aprendizaje terminado");
	}

	/**
	 * Evalua la funcion conseguida en el aprendizaje con  datos de prueba 
	 * 
	 * @return Efectividad del perceptrón devuelto en porcentaje
	 */
	public double porcentajeAcierto() {
		int count = 0;
		for (int i = 0; i < dataSetTest.length; i++) {
			int value = hardLimit(calculeYValue(dataSetTest[i][0], dataSetTest[i][1], dataSetTest[i][2],
					dataSetTest[i][3], dataSetTest[i][5], dataSetTest[i][5]));
			if (value == dataSetTest[i][6]) {
				count++;
			}
		}
		return (count / dataSetTest.length) * 100;
	}

	/**
	 * Calcula el valor de la "Y" evaluado en la ecuacion de aprendizaje 
	 * Los parametros son cada uno de los valores que se multiplicarán por su respectvo peso
	 * 
	 * @param x1
	 * @param x2
	 * @param x3
	 * @param x4
	 * @param x5
	 * @param x6
	 * @return el valor de "Y" evaluado en la ecuacion de aprendizaje
	 */
	private double calculeYValue(int x1, int x2, int x3, int x4, int x5, int x6) {
		return x1 * pesoX1 + x2 * pesoX2 + x3 * pesoX3 + x4 * pesoX4 + x5 * pesoX5 + x6 * pesoX6 - baias;
	}

	/**
	 * Itera los dataset y va calculando el valor de los nuevos pesos, el bias y el error respectivo
	 */
	private void evaluarPesos() {

		for (int i = 0; i < dataSet.length; i++) {

			double y = calculeYValue(dataSet[i][0], dataSet[i][1], dataSet[i][2], dataSet[i][3], dataSet[i][4],
					dataSet[i][5]);

			int hardLimit = hardLimit(y);

			int error = dataSet[i][6] - hardLimit;

			double delta1 = APRENDIZAJE * error * dataSet[i][0];
			double delta2 = APRENDIZAJE * error * dataSet[i][1];
			double delta3 = APRENDIZAJE * error * dataSet[i][2];
			double delta4 = APRENDIZAJE * error * dataSet[i][3];
			double delta5 = APRENDIZAJE * error * dataSet[i][4];
			double delta6 = APRENDIZAJE * error * dataSet[i][5];

			pesoX1 = pesoX1 + delta1;
			pesoX2 = pesoX2 + delta2;
			pesoX3 = pesoX3 + delta3;
			pesoX4 = pesoX4 + delta4;
			pesoX5 = pesoX5 + delta5;
			pesoX6 = pesoX6 + delta6;
			baias = baias - (APRENDIZAJE * error);

			textPerceptron(dataSet[i][0], dataSet[i][1], dataSet[i][2], dataSet[i][3], dataSet[i][4], dataSet[i][5]);
		}
	}

	/**
	 * Imprime la ecuación resultante
	 */
	public void mostrarEcuaciones() {
		System.out.println("Ecuacion de la neurona 1: (" + pesoX1 + " * X1 + " + pesoX2 + " * X2 + " + pesoX3
				+ " * X3 + " + pesoX4 + " * X4 + " + pesoX5 + " * X5 + " + pesoX6 + " * X6 - " + baias);
	}

	/**
	 * Clasifica, por hombres y mujeres, dependiendo del dataset ingresado al principio.
	 * 
	 * Los parametros son los features
	 * 
	 * @param x1
	 * @param x2
	 * @param x3
	 * @param x4
	 * @param x5
	 * @param x6
	 * @return hombre o mujer,
	 */
	public String textPerceptron(int x1, int x2, int x3, int x4, int x5, int x6) {
		return (hardLimit(calculeYValue(x1, x2, x3, x4, x5, x6)) == 1) ? "Hombre" : "Mujer";
	}

	public static void main(String[] args) {
		Perceptron perceptron = new Perceptron();
		perceptron.aprendizaje(100);
		perceptron.mostrarEcuaciones();
		System.out.println("porcentaje de efectividad :" + perceptron.porcentajeAcierto() + "%");
		
		System.out.println("(" + perceptron.textPerceptron(0, 0, 1, 1, 1, 1) + "}");
	}
}
