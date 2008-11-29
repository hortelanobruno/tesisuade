package varios;

import java.util.Date;

public class Recibo {

	private int numero;
	private String fecharendicion;
	private String beneficiario;
	private int monto;
	private String estadorecibo;
	private String motivo;
	
	public Recibo() {
		// TODO Auto-generated constructor stub
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getFecharendicion() {
		return fecharendicion.toString();
	}

	public void setFecharendicion(String fecharendicion) {
		this.fecharendicion = fecharendicion;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public String getEstadorecibo() {
		return estadorecibo;
	}

	public void setEstadorecibo(String estadoboleta) {
		this.estadorecibo = estadoboleta;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
