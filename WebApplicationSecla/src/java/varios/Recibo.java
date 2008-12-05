package varios;

public class Recibo {

	private int numero;
	private String fechaConfeccion;
	private String razonSocial;
	private String monto;
	private String estadoRecibo;
	private String motivo;
    private String numeroCuota;
    private String banco;
    private String numeroCheque;
    private String fechaDeVencimiento;

	
	public Recibo() {
	}

    public Recibo(int numero, String fechaConfeccion, String razonSocial, String monto, String numeroCuota, String banco, String numeroCheque, String fechaDeVencimiento) {
        this.numero = numero;
        this.fechaConfeccion = fechaConfeccion;
        this.razonSocial = razonSocial;
        this.monto = monto;
        this.numeroCuota = numeroCuota;
        this.banco = banco;
        this.numeroCheque = numeroCheque;
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public Recibo(int numero, String fechaConfeccion, String razonSocial, String monto, String numeroCuota) {
        this.numero = numero;
        this.fechaConfeccion = fechaConfeccion;
        this.razonSocial = razonSocial;
        this.monto = monto;
        this.numeroCuota = numeroCuota;
    }

    

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getFechaConfeccion() {
		return fechaConfeccion.toString();
	}

	public void setFechaConfeccion(String fechaconfeccion) {
		this.fechaConfeccion = fechaconfeccion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getEstadorecibo() {
		return estadoRecibo;
	}

	public void setEstadorecibo(String estadoboleta) {
		this.estadoRecibo = estadoboleta;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

    /**
     * @return the numeroCuota
     */
    public String getNumeroCuota() {
        return numeroCuota;
    }

    /**
     * @param numeroCuota the numeroCuota to set
     */
    public void setNumeroCuota(String numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    /**
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * @return the numeroCheque
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * @return the fechaDeVencimiento
     */
    public String getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    /**
     * @param fechaDeVencimiento the fechaDeVencimiento to set
     */
    public void setFechaDeVencimiento(String fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }
	
}
