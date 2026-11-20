
package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PedidoModel;

import java.util.Optional;

public interface GetPedidoByIdPort {
  Optional<PedidoModel> getById(String pedidoId);
}
