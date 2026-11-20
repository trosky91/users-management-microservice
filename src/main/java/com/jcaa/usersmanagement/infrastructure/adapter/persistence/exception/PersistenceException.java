
package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

  private static final String MESSAGE_SAVE = "Failed to save user with ID: '%s'.";
  private static final String MESSAGE_UPDATE = "Failed to update user with ID: '%s'.";
  private static final String MESSAGE_FIND = "Failed to find user with ID: '%s'.";
  private static final String MESSAGE_EMAIL = "Failed to find user with email: '%s'.";
  private static final String MESSAGE_ALL = "Failed to retrieve all users.";
  private static final String MESSAGE_DELETE = "Failed to delete user with ID: '%s'.";
  private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

  private static final String MESSAGE_SAVE_PEDIDO = "Failed to save pedido with ID: '%s'.";
  private static final String MESSAGE_UPDATE_PEDIDO = "Failed to update pedido with ID: '%s'.";
  private static final String MESSAGE_FIND_PEDIDO = "Failed to find pedido with ID: '%s'.";
  private static final String MESSAGE_ALL_PEDIDOS = "Failed to retrieve all pedidos.";
  private static final String MESSAGE_DELETE_PEDIDO = "Failed to delete pedido with ID: '%s'.";
  private static final String MESSAGE_CLIENTE = "Failed to find pedidos for cliente: '%s'.";

  private PersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE, userId), cause);
  }

  public static PersistenceException becauseUpdateFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE, userId), cause);
  }

  public static PersistenceException becauseFindByIdFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND, userId), cause);
  }

  public static PersistenceException becauseFindByEmailFailed(
      final String email, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_EMAIL, email), cause);
  }

  public static PersistenceException becauseFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL, cause);
  }

  public static PersistenceException becauseDeleteFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE, userId), cause);
  }

  public static PersistenceException becauseConnectionFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_CONNECTION, cause);
  }

  public static PersistenceException becauseSavePedidoFailed(
      final String pedidoId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE_PEDIDO, pedidoId), cause);
  }

  public static PersistenceException becauseUpdatePedidoFailed(
      final String pedidoId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE_PEDIDO, pedidoId), cause);
  }

  public static PersistenceException becauseFindPedidoByIdFailed(
      final String pedidoId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND_PEDIDO, pedidoId), cause);
  }

  public static PersistenceException becauseFindAllPedidosFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL_PEDIDOS, cause);
  }

  public static PersistenceException becauseDeletePedidoFailed(
      final String pedidoId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE_PEDIDO, pedidoId), cause);
  }

  public static PersistenceException becauseFindPedidosByClienteFailed(
      final String cliente, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_CLIENTE, cliente), cause);
  }
}
