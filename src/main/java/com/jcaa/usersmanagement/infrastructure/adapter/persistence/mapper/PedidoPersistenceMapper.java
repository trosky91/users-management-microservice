
package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.PedidoModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.PedidoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.PedidoEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PedidoPersistenceMapper {

  public PedidoPersistenceDto fromModelToDto(final PedidoModel pedido) {
    return new PedidoPersistenceDto(
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
        pedido.getPropina());
  }

  public PedidoEntity fromResultSetToEntity(final ResultSet resultSet)
      throws SQLException {
    return new PedidoEntity(
        resultSet.getString("id"),
        getLocalDate(resultSet, "fecha"),
        getLocalDate(resultSet, "fecha_envio"),
        getLocalDate(resultSet, "fecha_entrega"),
        resultSet.getString("cliente"),
        resultSet.getString("proveedor"),
        resultSet.getBigDecimal("valor"),
        resultSet.getString("estado"),
        resultSet.getString("pais"),
        resultSet.getString("departamento"),
        resultSet.getString("ciudad"),
        resultSet.getString("nomenclatura_vivienda"),
        resultSet.getBigDecimal("propina"));
  }

  public PedidoModel fromEntityToModel(final PedidoEntity entity) {
    return PedidoModel.create(
        entity.id(),
        entity.fecha(),
        entity.fechaEnvio(),
        entity.fechaEntrega(),
        entity.cliente(),
        entity.proveedor(),
        entity.valor(),
        entity.estado(),
        entity.pais(),
        entity.departamento(),
        entity.ciudad(),
        entity.nomenclaturaVivienda(),
        entity.propina());
  }

  public PedidoModel fromResultSetToModel(final ResultSet resultSet)
      throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<PedidoModel> fromResultSetToModelList(final ResultSet resultSet)
      throws SQLException {
    final List<PedidoModel> pedidos = new ArrayList<>();
    while (resultSet.next()) {
      pedidos.add(fromResultSetToModel(resultSet));
    }
    return pedidos;
  }

  private java.time.LocalDate getLocalDate(
      final ResultSet resultSet, final String column) throws SQLException {
    final java.sql.Date date = resultSet.getDate(column);
    return date != null ? date.toLocalDate() : null;
  }
}
