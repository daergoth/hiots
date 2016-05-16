package net.daergoth.serviceapi.sensors;

/**
 * Fake measurement generator service for simulated {@code Sensor}s.
 * Every {@code DummySensorVO} will have data automatically 
 * generated for it every {@code MIN_UPDATE_INTERVAL} milliseconds.
 * <p>
 * To use this service, you only need a {@code DummySensorVO} subclass instance.
 * The service automatically starts on application startup,
 * so starting it isn't needed, only if it has been stopped.
 * 
 * @see net.daergoth.serviceapi.sensors.dummy.DummySensorVO
 */
public interface DummyDataGeneratorLocal {
	
	/**
	 * Time interval in milliseconds, how frequently data will be generated.
	 */
	public static final long MIN_UPDATE_INTERVAL = 5000;
	
	/**
	 * Starts data generation.
	 * Only needed if the service has been stopped deliberately. 
	 */
	public void startGenerating();
	
	/**
	 * Stops data generation.
	 */
	public void stopGenerating();
	
	/**
	 * Checks if the service is running.
	 * @return true if the service is running, false if not
	 */
	public boolean isGenerating();
	
	/**
	 * Generate new {@code SensorData} for all simulated {@code Sensor}s.
	 */
	public void generateAllDummies();
	
}
