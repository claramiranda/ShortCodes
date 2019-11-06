package networkEcho_III;

import java.io.Serializable;

public class ObjetoMensagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3118136462462422951L;
	
	public String texto        = "";
	
	public ObjetoMensagem(String texto) {
		this.texto=texto;
	}
}
