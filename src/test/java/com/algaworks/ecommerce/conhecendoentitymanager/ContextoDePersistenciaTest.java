package com.algaworks.ecommerce.conhecendoentitymanager;

import java.math.BigDecimal;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class ContextoDePersistenciaTest extends EntityManagerTest {

	@Test
	public void usarContextoPersistencia() {
		entityManager.getTransaction().begin();

		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(new BigDecimal(100));;
		
		Produto produto2 = new Produto();
		produto2.setNome("Caneca para caf?");
		produto2.setPreco(new BigDecimal(10));
		produto2.setDescricao("Boa caneca para caf?");
		System.out.println(">>> Chamar Persist >>>");
		entityManager.persist(produto2);
		System.out.println("<<< Chamar Persist <<<");
		
		Produto produto3 = new Produto();
		produto3.setNome("Caneca para ch?");
		produto3.setPreco(new BigDecimal(10));
		produto3.setDescricao("Boa caneca para ch?");
		System.out.println(">>> Chamar Merge >>>");
		produto3 = entityManager.merge(produto3);
		System.out.println("<<< Chamar Merge <<<");
		
		System.out.println(">>> Chamar Flush >>>");
		entityManager.flush();
		System.out.println("<<< Chamar Flush <<<");
		
		produto3.setDescricao("Alterar descri??o");
		
		entityManager.getTransaction().commit();
	}
}
