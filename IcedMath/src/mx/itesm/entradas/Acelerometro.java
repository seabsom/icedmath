package mx.itesm.entradas;

import mx.itesm.niveles.Nivel;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Esta clase se encarga de obtener informaci�n del aceler�metro y realizar una
 * acci�n al detectar que el dispositivo se agita
 * 
 * @author Edwin Antonio Gonz�lez Urzua
 * @author Alejandro Segura G�mez
 * @author Alejandro Flores Ibarra
 * @author Guillermo Ju�rez Dur�n
 * @author Marlen Aguilar Dur�n
 * @version 1.0. 29/10/2011
 * 
 */
public class Acelerometro {

	private SensorManager adminSensores;

	// Se salvan los valores actuales de la aceleraci�n una variable por eje
	private float aceleracionX;
	private float aceleracionY;
	private float aceleracionZ;

	// Se salvan los valores anteriores de la aceleraci�n, una variable por eje
	private float aceleracionAnteriorX;
	private float aceleracionAnteriorY;
	private float aceleracionAnteriorZ;

	// Nos dice si es el primer "shake"
	private boolean primerShake = true;

	// Se guarda el delta de aceleraci�n que representa un movimiento r�pido
	private final float deltaAceleracionShake = 1.5f;

	// Se ha iniciado un "shake"
	private boolean inicioShake = false;
	
	private boolean estaActivado;
	
	//Nivel del que obtendr� elementos para realizar las acciones
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

		// No se requiere este m�todo, debido a que no se necesita un cambio de
		// precisi�n
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

	};
	

	/**
	 * Constructor de la clase Aceler�metro. Define al administrador de sensores
	 * 
	 * @param actividad
	 *            Define la actividad en la que estar� trabajando el
	 *            aceler�metro.
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
	 * M�todo que se encarga de actualizar los datos del aceler�metro en todos
	 * los ejes
	 * 
	 * @param nuevaAceleracionX
	 *            Par�metro que indica la nueva aceleraci�n en x
	 * @param nuevaAceleracionY
	 *            Par�metro que indica la nueva aceleraci�n en y
	 * @param nuevaAceleracionZ
	 *            Par�metro que indica la nueva aceleraci�n en z
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
	 * M�todo que detecta un cambio de aceleraci�n en dos ejes
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
	 * M�todo que realiza una acci�n cuando un el celular se agita.
	 */
	private void accionShake() {		
		nivel.getProtagonista().atacar();
		nivel.setAtacando(true);		
	}
	

	public void desactivar() {
		estaActivado=false;
	}
	
}