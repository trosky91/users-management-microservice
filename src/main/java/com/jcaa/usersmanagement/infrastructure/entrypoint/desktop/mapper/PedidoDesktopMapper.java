
package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.domain.model.PedidoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreatePedidoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.PedidoResponse;

import java.util.List;
import java.util.UUID;

public final class PedidoDesktopMapper {

  private PedidoDesktopMapper() {}

  public static PedidoModel toCreateModel(
      final CreatePedidoRequest request) {

    return PedidoModel.create(
        UUID.randomUUID().toString(),
        request.fecha(),
        request.fechaEnvio(),
        request.fechaEntrega(),
        request.cliente(),
        request.proveedor(),
        request.valor(),
        request.estado(),
        request.pais(),
        request.departamento(),
        request.ciudad(),
        request.nomenclaturaVivienda(),
        request.propina()
    );
  }

  public static PedidoModel toUpdateModel(
      final String id,
      final CreatePedidoRequest request) {

    return PedidoModel.create(
        id,
        request.fecha(),
        request.fechaEnvio(),
        request.fechaEntrega(),
        request.cliente(),
        request.proveedor(),
        request.valor(),
        request.estado(),
        request.pais(),
        request.departamento(),
        request.ciudad(),
        request.nomenclaturaVivienda(),
        request.propina()
    );
  }

  public static PedidoResponse toResponse(
      final PedidoModel pedido) {

    return new PedidoResponse(
        pedido.getId(),
        pedido.getFecha(),
        pedido.getFechaEnvio(),
        pedido.getFechaEntrega(),
        pedido.getCliente(),
        pedido.getProveedor(),
        pedido.getValor(),
        pedido.getEstado(),
        pedido.getPais(),
        pedido.getDepartamento(),
        pedido.getCiudad(),
        pedido.getNomenclaturaVivienda(),
        pedido.getPropina()
    );
  }

  public static List<PedidoResponse> toResponseList(
      final List<PedidoModel> pedidos) {

    return pedidos.stream()
        .map(PedidoDesktopMapper::toResponse)
        .toList();
  }
}
