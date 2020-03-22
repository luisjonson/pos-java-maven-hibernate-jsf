package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setIdade(45);
		pessoa.setLogin("testeUpd");
		pessoa.setNome("Paulo");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Egidio");
		pessoa.setEmail("javaavancado@javaavancado.com");

		daoGeneric.salvar(pessoa);

	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		System.out.println(pessoa);

	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(78L, UsuarioPessoa.class);

		pessoa.setIdade(99);
		pessoa.setNome("update");
		pessoa.setSenha("sd4s5d4s4d");

		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void testeDelete() throws Exception {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(71L, UsuarioPessoa.class);

		daoGeneric.deletarPoId(pessoa);

	}

	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------------------------");
		}

	}

	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	}

	@Test
	public void testeTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa usuario = (UsuarioPessoa) daoGeneric.pesquisar(67L, UsuarioPessoa.class);

		TelefoneUser telefone = new TelefoneUser();
		telefone.setTipo("celuar");
		telefone.setNumero("3030-0304");

		telefone.setUsuarioPessoa(usuario);

		daoGeneric.salvar(telefone);

	}

	@Test
	public void testeTelefones() {
		try {
			DaoGeneric daoGeneric = new DaoGeneric();
			UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(70L, UsuarioPessoa.class);

			for (TelefoneUser fone : pessoa.getTelefone()) {
				System.out.println(fone.getUsuarioPessoa().getNome());
				System.out.println(fone.getUsuarioPessoa().getEmail());
				System.out.println(fone.getTipo());
				System.out.println(fone.getNumero());

			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}
}
