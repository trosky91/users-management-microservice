
package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.domain.model.PedidoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreatePedidoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdatePedidoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.PedidoRestResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class PedidoRestMapper {

  public PedidoModel toCreateModel(
      final CreatePedidoRestRequest request) {

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

  public PedidoModel toUpdateModel(
      final String id,
      final UpdatePedidoRestRequest request) {

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

  public PedidoRestResponse toResponse(final PedidoModel pedido) {
    return new PedidoRestResponse(
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

  public List<PedidoRestResponse> toResponseList(
      final List<PedidoModel> pedidos) {

    return pedidos.stream()
        .map(PedidoRestMapper::toResponse)
        .toList();
  }
}
