
package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePedidoRestRequest(
    @NotNull LocalDate fecha,
    LocalDate fechaEnvio,
    LocalDate fechaEntrega,
    @NotBlank String cliente,
    @NotBlank String proveedor,
    @NotNull @DecimalMin("0.0") BigDecimal valor,
    @NotBlank String estado,
    @NotBlank String pais,
    @NotBlank String departamento,
    @NotBlank String ciudad,
    String nomenclaturaVivienda,
    @DecimalMin("0.0") BigDecimal propina
) {}
