package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.PedidoModel;

public interface GetPedidoByIdUseCase {
  PedidoModel execute(String pedidoId);
}