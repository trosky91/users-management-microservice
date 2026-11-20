
package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PedidoModel;

import java.util.List;

public interface GetPedidosByClientePort {
  List<PedidoModel> getByCliente(String cliente);
}
