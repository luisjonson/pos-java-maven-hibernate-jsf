package managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;

import dao.DaoGeneric;
import model.UsuarioPessoa;

@ManagedBean(name = "usuarioPessoaManagedBean")
@ViewScoped
public class UsuarioPessoaManagedBean {

	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
	private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();

	@PostConstruct
	public void init() {
		list = daoGeneric.listar(UsuarioPessoa.class);
	}
	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	public List<UsuarioPessoa> getList() {
		
		return list;
	}

	public String salva() {
		daoGeneric.salvar(usuarioPessoa);
		list.add(usuarioPessoa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Salvo com sucesso."));

		return "";
	}

	public String novo() {
		usuarioPessoa = new UsuarioPessoa();

		return "";
	}

	public String remove() {
		try {
			daoGeneric.deletarPoId(usuarioPessoa);
			list.remove(usuarioPessoa);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Delete com sucesso."));
			usuarioPessoa = new UsuarioPessoa();
		} catch (Exception e) {
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", "Existemteefone para usuario"));
			}
		}
		
		return "";
	}
}
