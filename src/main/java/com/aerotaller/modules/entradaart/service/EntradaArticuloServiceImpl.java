package com.aerotaller.modules.entradaart.service;

import com.aerotaller.modelos.Almacen;
import com.aerotaller.modelos.Articulo;
import com.aerotaller.modelos.DetalleEntradaArticulo;
import com.aerotaller.modelos.EntradaArticulo;
import com.aerotaller.modelos.EstadoEntrada;
import com.aerotaller.modelos.Proveedor;
import com.aerotaller.modelos.Usuario;
import com.aerotaller.modules.articulo.repository.ArticuloRepository;
import com.aerotaller.modules.auth.repository.UsuarioRepository;
import com.aerotaller.modules.catalogo.repository.AlmacenRepository;
import com.aerotaller.modules.catalogo.repository.ProveedorRepository;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloDetalleRequestDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloDetalleResponseDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroRequestDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroResponseDto;
import com.aerotaller.modules.entradaart.repository.DetalleEntradaArticuloRepository;
import com.aerotaller.modules.entradaart.repository.EntradaArticuloRepository;
import com.aerotaller.modules.entradaart.repository.EstadoEntradaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.aerotaller.modelos.EntradaArticulo;
import com.aerotaller.modelos.DetalleEntradaArticulo;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloListadoResponseDto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntradaArticuloServiceImpl implements EntradaArticuloService
{

    private final EntradaArticuloRepository entradaArticuloRepository;
    private final DetalleEntradaArticuloRepository detalleEntradaArticuloRepository;
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProveedorRepository proveedorRepository;
    private final AlmacenRepository almacenRepository;
    private final EstadoEntradaRepository estadoEntradaRepository;

    public EntradaArticuloServiceImpl(
            EntradaArticuloRepository entradaArticuloRepository,
            DetalleEntradaArticuloRepository detalleEntradaArticuloRepository,
            ArticuloRepository articuloRepository,
            UsuarioRepository usuarioRepository,
            ProveedorRepository proveedorRepository,
            AlmacenRepository almacenRepository,
            EstadoEntradaRepository estadoEntradaRepository
    )
    {
        this.entradaArticuloRepository = entradaArticuloRepository;
        this.detalleEntradaArticuloRepository = detalleEntradaArticuloRepository;
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
        this.proveedorRepository = proveedorRepository;
        this.almacenRepository = almacenRepository;
        this.estadoEntradaRepository = estadoEntradaRepository;
    }

    @Override
    @Transactional
    public EntradaArticuloRegistroResponseDto registrarEntradaCompleta(EntradaArticuloRegistroRequestDto dto)
    {
        if (dto.getDetalles() == null || dto.getDetalles().isEmpty())
        {
            throw new RuntimeException("Debes registrar al menos un artículo en la entrada.");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        Proveedor proveedor = null;
        if (dto.getProveedor() != null)
        {
            proveedor = proveedorRepository.findById(dto.getProveedor())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado."));
        }

        Almacen almacenDestino = almacenRepository.findById(dto.getAlmacenDestino())
                .orElseThrow(() -> new RuntimeException("Almacén destino no encontrado."));

        EstadoEntrada estadoEntrada = estadoEntradaRepository.findById(dto.getEstadoEntrada())
                .orElseThrow(() -> new RuntimeException("Estado de entrada no encontrado."));

        // 1. Crear encabezado con folio temporal
        EntradaArticulo entrada = new EntradaArticulo();
        entrada.setFolio("TEMP-" + System.currentTimeMillis());
        entrada.setFechaEntrada(LocalDateTime.now());
        entrada.setUsuario(usuario);
        entrada.setProveedor(proveedor);
        entrada.setAlmacenDestino(almacenDestino);
        entrada.setEstadoEntrada(estadoEntrada);
        entrada.setObservaciones(dto.getObservaciones());

        EntradaArticulo entradaGuardada = entradaArticuloRepository.save(entrada);

        // 2. Folio definitivo
        String folioDefinitivo = String.format("ENT-%03d", entradaGuardada.getIdEntrada());
        entradaGuardada.setFolio(folioDefinitivo);
        entradaGuardada = entradaArticuloRepository.save(entradaGuardada);

        List<EntradaArticuloDetalleResponseDto> detallesResponse = new ArrayList<>();

        // 3. Registrar artículos + detalle
        for (EntradaArticuloDetalleRequestDto detalleDto : dto.getDetalles())
        {

            Articulo articulo = new Articulo();
            articulo.setCodigo(detalleDto.getCodigo());
            articulo.setNoSerie(detalleDto.getNoSerie());
            articulo.setDescripcion(detalleDto.getDescripcion());
            articulo.setCategoria(detalleDto.getCategoria());
            articulo.setUnidadMedida(detalleDto.getUnidadMedida());
            articulo.setAlmacen(dto.getAlmacenDestino());
            articulo.setUbicacion(detalleDto.getUbicacion());
            articulo.setProveedor(dto.getProveedor());
            articulo.setPrecioCompra(BigDecimal.valueOf(detalleDto.getPrecioCompra()));
            articulo.setStock(detalleDto.getCantidad());
            articulo.setCondicion(detalleDto.getCondicion());

            Articulo articuloGuardado = articuloRepository.save(articulo);

            DetalleEntradaArticulo detalle = new DetalleEntradaArticulo();
            detalle.setEntrada(entradaGuardada);
            detalle.setArticulo(articuloGuardado);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setUbicacion(detalleDto.getUbicacion());
            detalle.setPrecioUnitario(BigDecimal.valueOf(detalleDto.getPrecioCompra()));

            detalleEntradaArticuloRepository.save(detalle);

            EntradaArticuloDetalleResponseDto detalleResp = new EntradaArticuloDetalleResponseDto();
            detalleResp.setIdArticulo(articuloGuardado.getIdArticulo());
            detalleResp.setCodigo(articuloGuardado.getCodigo());
            detalleResp.setDescripcion(articuloGuardado.getDescripcion());
            detalleResp.setCantidad(detalle.getCantidad());
            detalleResp.setUbicacion(detalle.getUbicacion());
            detalleResp.setPrecioUnitario(detalle.getPrecioUnitario().doubleValue());

            detallesResponse.add(detalleResp);
        }

        // 4. Respuesta final
        EntradaArticuloRegistroResponseDto response = new EntradaArticuloRegistroResponseDto();
        response.setIdEntrada(entradaGuardada.getIdEntrada());
        response.setFolio(entradaGuardada.getFolio());
        response.setFechaEntrada(
                entradaGuardada.getFechaEntrada() != null
                        ? entradaGuardada.getFechaEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : ""
        );
        response.setProveedor(proveedor != null ? proveedor.getNombre() : "-");
        response.setAlmacenDestino(almacenDestino.getNombre());
        response.setEstado(estadoEntrada.getNombre());
        response.setRecibidoPor(usuario.getNombre());
        response.setDetalles(detallesResponse);

        return response;
    }

    @Override
    public List<EntradaArticuloListadoResponseDto> listarEntradas()
    {
        return entradaArticuloRepository.findAll()
                .stream()
                .map(this::mapToListadoDto)
                .toList();
    }

    @Override
    public EntradaArticuloRegistroResponseDto obtenerEntradaPorId(Integer idEntrada)
    {
        EntradaArticulo entrada = entradaArticuloRepository.findById(idEntrada)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada."));

        return mapToRegistroResponse(entrada);
    }

    private EntradaArticuloListadoResponseDto mapToListadoDto(EntradaArticulo entrada)
    {
        EntradaArticuloListadoResponseDto dto = new EntradaArticuloListadoResponseDto();

        dto.setIdEntrada(entrada.getIdEntrada());
        dto.setFolio(entrada.getFolio());
        dto.setFechaEntrada(
                entrada.getFechaEntrada() != null
                        ? entrada.getFechaEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : ""
        );
        dto.setProveedor(entrada.getProveedor() != null ? entrada.getProveedor().getNombre() : "-");
        dto.setAlmacenDestino(entrada.getAlmacenDestino().getNombre());
        dto.setRecibidoPor(entrada.getUsuario().getNombre());
        dto.setEstado(entrada.getEstadoEntrada().getNombre());
        dto.setTotalArticulos(entrada.getDetalles() != null ? entrada.getDetalles().size() : 0);

        return dto;
    }

    private EntradaArticuloRegistroResponseDto mapToRegistroResponse(EntradaArticulo entrada)
    {
        EntradaArticuloRegistroResponseDto response = new EntradaArticuloRegistroResponseDto();

        response.setIdEntrada(entrada.getIdEntrada());
        response.setFolio(entrada.getFolio());
        response.setFechaEntrada(
                entrada.getFechaEntrada() != null
                        ? entrada.getFechaEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : ""
        );
        response.setProveedor(entrada.getProveedor() != null ? entrada.getProveedor().getNombre() : "-");
        response.setAlmacenDestino(entrada.getAlmacenDestino().getNombre());
        response.setEstado(entrada.getEstadoEntrada().getNombre());
        response.setRecibidoPor(entrada.getUsuario().getNombre());

        List<com.aerotaller.modules.entradaart.dto.EntradaArticuloDetalleResponseDto> detalles =
                entrada.getDetalles().stream().map(detalle ->
                {
                    com.aerotaller.modules.entradaart.dto.EntradaArticuloDetalleResponseDto dto =
                            new com.aerotaller.modules.entradaart.dto.EntradaArticuloDetalleResponseDto();

                    dto.setIdArticulo(detalle.getArticulo().getIdArticulo());
                    dto.setCodigo(detalle.getArticulo().getCodigo());
                    dto.setDescripcion(detalle.getArticulo().getDescripcion());
                    dto.setCantidad(detalle.getCantidad());
                    dto.setUbicacion(detalle.getUbicacion());
                    dto.setPrecioUnitario(
                            detalle.getPrecioUnitario() != null ? detalle.getPrecioUnitario().doubleValue() : 0.0
                    );

                    return dto;
                }).toList();

        response.setDetalles(detalles);

        return response;
    }
}