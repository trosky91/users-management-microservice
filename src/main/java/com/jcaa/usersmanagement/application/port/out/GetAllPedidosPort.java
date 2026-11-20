
package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PedidoModel;

import java.util.List;

public interface GetAllPedidosPort {
  List<PedidoModel> getAll();
}
