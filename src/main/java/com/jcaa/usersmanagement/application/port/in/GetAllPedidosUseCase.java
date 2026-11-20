package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.PedidoModel;
import java.util.List;

public interface GetAllPedidosUseCase {
  List<PedidoModel> execute();
}