package mx.itesm.entradas;

import mx.itesm.niveles.Nivel;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Esta clase se encarga de obtener información del acelerómetro y realizar una
 * acción al detectar que el dispositivo se agita
 * 
 * @author Edwin Antonio González Urzua
 * @author Alejandro Segura Gómez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Juárez Durán
 * @author Marlen Aguilar Durán
 * @version 1.0. 29/10/2011
 * 
 */
public class Acelerometro {

	private SensorManager adminSensores;

	// Se salvan los valores actuales de la aceleración una variable por eje
	private float aceleracionX;
	private float aceleracionY;
	private float aceleracionZ;

	// Se salvan los valores anteriores de la aceleración, una variable por eje
	private float aceleracionAnteriorX;
	private float aceleracionAnteriorY;
	private float aceleracionAnteriorZ;

	// Nos dice si es el primer "shake"
	private boolean primerShake = true;

	// Se guarda el delta de aceleración que representa un movimiento rápido
	private final float deltaAceleracionShake = 1.5f;

	// Se ha iniciado un "shake"
	private boolean inicioShake = false;
	
	private boolean estaActivado;
	
	//Nivel del que obtendrá elementos para realizar las acciones
	private Nivel nivel;
	
	// Se inicializa un listener de eventos de sensores
	private final SensorEventListener listener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent se) {
			if (estaActivado) {
				actualizarParametrosAcelerometro(se.values[0], se.values[1],
						se.values[2]);
				if ((!inicioShake) && cambioAceleracion()) {
					// Marca el movimiento inicial de un "shake"
					inicioShake = true;
				} else if ((inicioShake) && cambioAceleracion()) {
					// Marca el movimiento final de un "shake", por lo tanto ejecuta
					// la accion
					accionShake();
				} else if ((inicioShake) && (!cambioAceleracion())) {
					// Caso en el que se empieza un "shake", pero no se termina
					inicioShake = false;

				}
			}
		}

		// No se requiere este método, debido a que no se necesita un cambio de
		// precisión
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

	};
	

	/**
	 * Constructor de la clase Acelerómetro. Define al administrador de sensores
	 * 
	 * @param actividad
	 *            Define la actividad en la que estará trabajando el
	 *            acelerómetro.
	 */
	public Acelerometro(Activity actividad, Nivel nivel) {
		adminSensores = (SensorManager) actividad
				.getSystemService(Context.SENSOR_SERVICE);
		adminSensores.registerListener(listener,
				adminSensores.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);		
		this.nivel=nivel;
		estaActivado=true;
	}

	/**
	 * Método que se encarga de actualizar los datos del acelerómetro en todos
	 * los ejes
	 * 
	 * @param nuevaAceleracionX
	 *            Parámetro que indica la nueva aceleración en x
	 * @param nuevaAceleracionY
	 *            Parámetro que indica la nueva aceleración en y
	 * @param nuevaAceleracionZ
	 *            Parámetro que indica la nueva aceleración en z
	 */
	private void actualizarParametrosAcelerometro(float nuevaAceleracionX,
			float nuevaAceleracionY, float nuevaAceleracionZ) {

		if (primerShake) {
			aceleracionAnteriorX = nuevaAceleracionX;
			aceleracionAnteriorY = nuevaAceleracionY;
			aceleracionAnteriorZ = nuevaAceleracionZ;
			primerShake = false;
		} else {
			aceleracionAnteriorX = aceleracionX;
			aceleracionAnteriorY = aceleracionY;
			aceleracionAnteriorZ = aceleracionZ;
		}
		aceleracionX = nuevaAceleracionX;
		aceleracionY = nuevaAceleracionY;
		aceleracionZ = nuevaAceleracionZ;
	}

	/**
	 * Método que detecta un cambio de aceleración en dos ejes
	 * 
	 * @return Si hubo un cambio en dos ejes o no.
	 */
	private boolean cambioAceleracion() {
		float deltaX = Math.abs(aceleracionAnteriorX - aceleracionX);
		float deltaY = Math.abs(aceleracionAnteriorY - aceleracionY);
		float deltaZ = Math.abs(aceleracionAnteriorZ - aceleracionZ);
		return (deltaX > deltaAceleracionShake && deltaY > deltaAceleracionShake)
				|| (deltaX > deltaAceleracionShake && deltaZ > deltaAceleracionShake)
				|| (deltaY > deltaAceleracionShake && deltaZ > deltaAceleracionShake);
	}

	/**
	 * Método que realiza una acción cuando un el celular se agita.
	 */
	private void accionShake() {		
		nivel.getProtagonista().atacar();
		nivel.setAtacando(true);		
	}
	

	public void desactivar() {
		estaActivado=false;
	}
	
}