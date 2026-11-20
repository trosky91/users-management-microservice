
package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeletePedidoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllPedidosPort;
import com.jcaa.usersmanagement.application.port.out.GetPedidoByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetPedidosByClientePort;
import com.jcaa.usersmanagement.application.port.out.SavePedidoPort;
import com.jcaa.usersmanagement.application.port.out.UpdatePedidoPort;
import com.jcaa.usersmanagement.domain.model.PedidoModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.PedidoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.PedidoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PedidoRepositoryMySQL implements
    SavePedidoPort,
    UpdatePedidoPort,
    GetPedidoByIdPort,
    GetAllPedidosPort,
    GetPedidosByClientePort,
    DeletePedidoPort {

  private static final String SQL_INSERT =
      "INSERT INTO pedidos "
      + "(id, fecha, fecha_envio, fecha_entrega, cliente, proveedor, "
      + "valor, estado, pais, departamento, ciudad, nomenclatura_vivienda, propina) "
      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE pedidos SET fecha = ?, fecha_envio = ?, fecha_entrega = ?, "
      + "cliente = ?, proveedor = ?, valor = ?, estado = ?, pais = ?, "
      + "departamento = ?, ciudad = ?, nomenclatura_vivienda = ?, propina = ? "
      + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT * FROM pedidos WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT * FROM pedidos ORDER BY fecha DESC";

  private static final String SQL_SELECT_BY_CLIENTE =
      "SELECT * FROM pedidos WHERE cliente = ? ORDER BY fecha DESC";

  private static final String SQL_DELETE =
      "DELETE FROM pedidos WHERE id = ?";

  private final DataSource dataSource;

  @Override
  public PedidoModel save(final PedidoModel pedido) {
    final PedidoPersistenceDto dto =
        PedidoPersistenceMapper.fromModelToDto(pedido);

    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement =
             connection.prepareStatement(SQL_INSERT)) {

      setInsertParameters(statement, dto);
      statement.executeUpdate();

      return getById(dto.id())
          .orElseThrow(() ->
              new IllegalStateException("Pedido saved but could not be retrieved."));

    } catch (final SQLException exception) {
      throw PersistenceException.becauseSavePedidoFailed(dto.id(), exception);
    }
  }

  @Override
  public PedidoModel update(final PedidoModel pedido) {
    final PedidoPersistenceDto dto =
        PedidoPersistenceMapper.fromModelToDto(pedido);

    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement =
             connection.prepareStatement(SQL_UPDATE)) {

      setUpdateParameters(statement, dto);

      final int affectedRows = statement.executeUpdate();

      if (affectedRows == 0) {
        throw new IllegalArgumentException(
            "No pedido found with ID: " + dto.id());
      }

      return getById(dto.id())
          .orElseThrow(() ->
              new IllegalStateException("Updated pedido could not be retrieved."));

    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdatePedidoFailed(dto.id(), exception);
    }
  }

  @Override
  public Optional<PedidoModel> getById(final String pedidoId) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement =
             connection.prepareStatement(SQL_SELECT_BY_ID)) {

      statement.setString(1, pedidoId);

      try (final ResultSet resultSet = statement.executeQuery()) {
        if (!resultSet.next()) {
          return Optional.empty();
        }

        return Optional.of(
            PedidoPersistenceMapper.fromResultSetToModel(resultSet));
      }

    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindPedidoByIdFailed(
          pedidoId, exception);
    }
  }

  @Override
  public List<PedidoModel> getAll() {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement =
             connection.prepareStatement(SQL_SELECT_ALL);
         final ResultSet resultSet = statement.executeQuery()) {

      return PedidoPersistenceMapper.fromResultSetToModelList(resultSet);

    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllPedidosFailed(exception);
    }
  }

  @Override
  public List<PedidoModel> getByCliente(final String cliente) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement =
             connection.prepareStatement(SQL_SELECT_BY_CLIENTE)) {

      statement.setString(1, cliente);

      try (final ResultSet resultSet = statement.executeQuery()) {
        return PedidoPersistenceMapper.fromResultSetToModelList(resultSet);
      }

    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindPedidosByClienteFailed(
          cliente, exception);
    }
  }

  @Override
  public void delete(final String pedidoId) {
    try (final Connection connection = dataSource.getConnection();
         final PreparedStatement statement =
             connection.prepareStatement(SQL_DELETE)) {

      statement.setString(1, pedidoId);
      statement.executeUpdate();

    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeletePedidoFailed(
          pedidoId, exception);
    }
  }

  private void setInsertParameters(
      final PreparedStatement statement,
      final PedidoPersistenceDto dto) throws SQLException {

    statement.setString(1, dto.id());
    setDate(statement, 2, dto.fecha());
    setDate(statement, 3, dto.fechaEnvio());
    setDate(statement, 4, dto.fechaEntrega());
    statement.setString(5, dto.cliente());
    statement.setString(6, dto.proveedor());
    statement.setBigDecimal(7, dto.valor());
    statement.setString(8, dto.estado());
    statement.setString(9, dto.pais());
    statement.setString(10, dto.departamento());
    statement.setString(11, dto.ciudad());
    statement.setString(12, dto.nomenclaturaVivienda());
    statement.setBigDecimal(13, dto.propina());
  }

  private void setUpdateParameters(
      final PreparedStatement statement,
      final PedidoPersistenceDto dto) throws SQLException {

    setDate(statement, 1, dto.fecha());
    setDate(statement, 2, dto.fechaEnvio());
    setDate(statement, 3, dto.fechaEntrega());
    statement.setString(4, dto.cliente());
    statement.setString(5, dto.proveedor());
    statement.setBigDecimal(6, dto.valor());
    statement.setString(7, dto.estado());
    statement.setString(8, dto.pais());
    statement.setString(9, dto.departamento());
    statement.setString(10, dto.ciudad());
    statement.setString(11, dto.nomenclaturaVivienda());
    statement.setBigDecimal(12, dto.propina());
    statement.setString(13, dto.id());
  }

  private void setDate(
      final PreparedStatement statement,
      final int index,
      final java.time.LocalDate value) throws SQLException {

    if (value != null) {
      statement.setDate(index, Date.valueOf(value));
    } else {
      statement.setNull(index, Types.DATE);
    }
  }
}
