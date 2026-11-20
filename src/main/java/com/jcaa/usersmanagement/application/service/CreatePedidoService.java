package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreatePedidoUseCase;
import com.jcaa.usersmanagement.application.port.out.SavePedidoPort;
import com.jcaa.usersmanagement.domain.model.PedidoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePedidoService implements CreatePedidoUseCase {

  private final SavePedidoPort savePedidoPort;

  @Override
  public PedidoModel execute(final PedidoModel pedido) {
    return savePedidoPort.save(pedido);
  }
}