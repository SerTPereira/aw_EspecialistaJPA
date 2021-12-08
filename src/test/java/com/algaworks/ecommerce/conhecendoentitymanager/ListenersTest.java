package com.algaworks.ecommerce.conhecendoentitymanager;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class ListenersTest extends EntityManagerTest {

	@Test
	public void carregarEntidades() {
		Produto produto = entityManager.find(Produto.class, 1);
		Pedido pedido = entityManager.find(Pedido.class, 1);
	}
	
	@Test
	public void acionarListener() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        System.out.println(">> Start Transaction: >>");
        entityManager.getTransaction().begin();

        System.out.println(">> Persist: >>");
        entityManager.persist(pedido);
        System.out.println(">> Start Flush: >>");
        entityManager.flush();

        System.out.println(">> Set Status Pedido: >>");
        pedido.setStatus(StatusPedido.PAGO);
        
        System.out.println(">> Commit: >>");
        entityManager.getTransaction().commit();

        System.out.println(">> Clear: >>");
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assert.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
	}
}
