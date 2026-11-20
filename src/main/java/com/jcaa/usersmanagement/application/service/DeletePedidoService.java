package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeletePedidoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeletePedidoPort;
import com.jcaa.usersmanagement.application.port.out.GetPedidoByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePedidoService implements DeletePedidoUseCase {

  private final DeletePedidoPort deletePedidoPort;
  private final GetPedidoByIdPort getPedidoByIdPort;

  @Override
  public void execute(final String pedidoId) {
    getPedidoByIdPort.getById(pedidoId)
        .orElseThrow(() ->
            new IllegalArgumentException(
                "Pedido not found with ID: " + pedidoId));

    deletePedidoPort.delete(pedidoId);
  }
}