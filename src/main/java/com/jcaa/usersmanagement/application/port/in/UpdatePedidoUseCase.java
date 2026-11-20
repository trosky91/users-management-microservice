package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.PedidoModel;

public interface UpdatePedidoUseCase {
  PedidoModel execute(PedidoModel pedido);
}