package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllPedidosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllPedidosPort;
import com.jcaa.usersmanagement.domain.model.PedidoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPedidosService implements GetAllPedidosUseCase {

  private final GetAllPedidosPort getAllPedidosPort;

  @Override
  public List<PedidoModel> execute() {
    return getAllPedidosPort.getAll();
  }
}