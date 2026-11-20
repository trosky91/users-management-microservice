package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetPedidosByClienteUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPedidosByClientePort;
import com.jcaa.usersmanagement.domain.model.PedidoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPedidosByClienteService implements GetPedidosByClienteUseCase {

  private final GetPedidosByClientePort getPedidosByClientePort;

  @Override
  public List<PedidoModel> execute(final String cliente) {
    return getPedidosByClientePort.getByCliente(cliente);
  }
}