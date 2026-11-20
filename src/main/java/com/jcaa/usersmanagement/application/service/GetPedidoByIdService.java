package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetPedidoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPedidoByIdPort;
import com.jcaa.usersmanagement.domain.model.PedidoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPedidoByIdService implements GetPedidoByIdUseCase {

  private final GetPedidoByIdPort getPedidoByIdPort;

  @Override
  public PedidoModel execute(final String pedidoId) {
    return getPedidoByIdPort.getById(pedidoId)
        .orElseThrow(() ->
            new IllegalArgumentException(
                "Pedido not found with ID: " + pedidoId));
  }
}