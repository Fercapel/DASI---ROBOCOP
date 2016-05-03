package icaro.aplicaciones.agentes.componentesInternos.movimientoRobocop;

public interface ItfUsoMovimientoCtrlRopocop {
	
	public void inicializarInfoMovimiento(int x, int y);
    public void moverADestino(String identDest, int x, int y);
    public void cambiaDestino(String identDest, int x, int y);
    public int[] getCoordenadasActuales() ;
    public void setCoordenadasActuales(int x, int y) ;
    public String getIdentEstadoMovRobot();
    public void parar();
    public void bloquear();
    public void continuar();
    public boolean paradoEnDestino(String identDestino);
    public void imposibleAvanzarADestino();
}
