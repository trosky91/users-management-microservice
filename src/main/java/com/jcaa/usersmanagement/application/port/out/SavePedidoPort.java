
package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PedidoModel;

public interface SavePedidoPort {
  PedidoModel save(PedidoModel pedido);
}
