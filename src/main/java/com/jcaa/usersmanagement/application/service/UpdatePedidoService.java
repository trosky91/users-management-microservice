package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdatePedidoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPedidoByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdatePedidoPort;
import com.jcaa.usersmanagement.domain.model.PedidoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePedidoService implements UpdatePedidoUseCase {

  private final UpdatePedidoPort updatePedidoPort;
  private final GetPedidoByIdPort getPedidoByIdPort;

  @Override
  public PedidoModel execute(final PedidoModel pedido) {
    getPedidoByIdPort.getById(pedido.getId())
        .orElseThrow(() ->
            new IllegalArgumentException(
                "Pedido not found with ID: " + pedido.getId()));

    return updatePedidoPort.update(pedido);
  }
}