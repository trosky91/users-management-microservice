
package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoEntity(
    String id,
    LocalDate fecha,
    LocalDate fechaEnvio,
    LocalDate fechaEntrega,
    String cliente,
    String proveedor,
    BigDecimal valor,
    String estado,
    String pais,
    String departamento,
    String ciudad,
    String nomenclaturaVivienda,
    BigDecimal propina) {}
