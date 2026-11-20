
package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreatePedidoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeletePedidoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllPedidosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetPedidoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetPedidosByClienteUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdatePedidoUseCase;
import com.jcaa.usersmanagement.domain.model.PedidoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreatePedidoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.PedidoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.PedidoDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class PedidoController {

  private final CreatePedidoUseCase createPedidoUseCase;
  private final UpdatePedidoUseCase updatePedidoUseCase;
  private final DeletePedidoUseCase deletePedidoUseCase;
  private final GetPedidoByIdUseCase getPedidoByIdUseCase;
  private final GetAllPedidosUseCase getAllPedidosUseCase;
  private final GetPedidosByClienteUseCase getPedidosByClienteUseCase;

  public List<PedidoResponse> listAllPedidos() {
    final List<PedidoModel> pedidos = getAllPedidosUseCase.execute();
    return PedidoDesktopMapper.toResponseList(pedidos);
  }

  public PedidoResponse findPedidoById(final String id) {
    final PedidoModel pedido = getPedidoByIdUseCase.execute(id);
    return PedidoDesktopMapper.toResponse(pedido);
  }

  public List<PedidoResponse> findPedidosByCliente(final String cliente) {
    final List<PedidoModel> pedidos =
        getPedidosByClienteUseCase.execute(cliente);
    return PedidoDesktopMapper.toResponseList(pedidos);
  }

  public PedidoResponse createPedido(final CreatePedidoRequest request) {
    final PedidoModel model =
        PedidoDesktopMapper.toCreateModel(request);
    final PedidoModel pedido = createPedidoUseCase.execute(model);
    return PedidoDesktopMapper.toResponse(pedido);
  }

  public PedidoResponse updatePedido(
      final String id,
      final CreatePedidoRequest request) {

    final PedidoModel model =
        PedidoDesktopMapper.toUpdateModel(id, request);
    final PedidoModel pedido = updatePedidoUseCase.execute(model);
    return PedidoDesktopMapper.toResponse(pedido);
  }

  public void deletePedido(final String id) {
    deletePedidoUseCase.execute(id);
  }
}
