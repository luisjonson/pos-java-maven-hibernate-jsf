package managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import model.UsuarioPessoa;

@ManagedBean
@ViewScoped
public class TelefoneManagerBean {
	private UsuarioPessoa user = new UsuarioPessoa();
	
	@PostConstruct
	public void init() {

		String codUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigouser");
		System.out.println(codUser);
	}

}
