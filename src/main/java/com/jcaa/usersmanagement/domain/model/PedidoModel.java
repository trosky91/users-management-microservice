package com.jcaa.usersmanagement.domain.model;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class PedidoModel {

  String id;
  LocalDate fecha;
  LocalDate fechaEnvio;
  LocalDate fechaEntrega;
  String cliente;
  String proveedor;
  BigDecimal valor;
  String estado;
  String pais;
  String departamento;
  String ciudad;
  String nomenclaturaVivienda;
  BigDecimal propina;

  public static PedidoModel create(
      final String id,
      final LocalDate fecha,
      final LocalDate fechaEnvio,
      final LocalDate fechaEntrega,
      final String cliente,
      final String proveedor,
      final BigDecimal valor,
      final String estado,
      final String pais,
      final String departamento,
      final String ciudad,
      final String nomenclaturaVivienda,
      final BigDecimal propina) {

    return new PedidoModel(
        id,
        fecha,
        fechaEnvio,
        fechaEntrega,
        cliente,
        proveedor,
        valor,
        estado,
        pais,
        departamento,
        ciudad,
        nomenclaturaVivienda,
        propina);
  }
}