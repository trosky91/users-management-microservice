
package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreatePedidoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeletePedidoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllPedidosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetPedidoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetPedidosByClienteUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdatePedidoUseCase;
import com.jcaa.usersmanagement.domain.model.PedidoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreatePedidoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdatePedidoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.PedidoRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.PedidoRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoRestController {

  private final CreatePedidoUseCase createPedidoUseCase;
  private final UpdatePedidoUseCase updatePedidoUseCase;
  private final DeletePedidoUseCase deletePedidoUseCase;
  private final GetPedidoByIdUseCase getPedidoByIdUseCase;
  private final GetAllPedidosUseCase getAllPedidosUseCase;
  private final GetPedidosByClienteUseCase getPedidosByClienteUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PedidoRestResponse create(
      @Valid @RequestBody final CreatePedidoRestRequest request) {

    final PedidoModel pedido =
        PedidoRestMapper.toCreateModel(request);

    return PedidoRestMapper.toResponse(
        createPedidoUseCase.execute(pedido));
  }

  @GetMapping
  public List<PedidoRestResponse> getAll() {
    return PedidoRestMapper.toResponseList(
        getAllPedidosUseCase.execute());
  }

  @GetMapping("/{id}")
  public PedidoRestResponse getById(
      @PathVariable final String id) {

    return PedidoRestMapper.toResponse(
        getPedidoByIdUseCase.execute(id));
  }

  @GetMapping("/cliente/{cliente}")
  public List<PedidoRestResponse> getByCliente(
      @PathVariable final String cliente) {

    return PedidoRestMapper.toResponseList(
        getPedidosByClienteUseCase.execute(cliente));
  }

  @PutMapping("/{id}")
  public PedidoRestResponse update(
      @PathVariable final String id,
      @Valid @RequestBody final UpdatePedidoRestRequest request) {

    final PedidoModel pedido =
        PedidoRestMapper.toUpdateModel(id, request);

    return PedidoRestMapper.toResponse(
        updatePedidoUseCase.execute(pedido));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final String id) {
    deletePedidoUseCase.execute(id);
  }
}
