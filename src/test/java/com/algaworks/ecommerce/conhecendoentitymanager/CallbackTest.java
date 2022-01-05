package com.algaworks.ecommerce.conhecendoentitymanager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class CallbackTest extends EntityManagerTest {

	@Test
	public void acionarCallBacks() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

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
