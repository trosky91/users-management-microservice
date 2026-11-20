
package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoPersistenceDto(
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

