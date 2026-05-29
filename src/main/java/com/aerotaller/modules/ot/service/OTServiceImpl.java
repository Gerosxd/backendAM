package com.aerotaller.modules.ot.service;

import com.aerotaller.modelos.Aeronave;
import com.aerotaller.modelos.Cliente;
import com.aerotaller.modelos.NuevaOT;
import com.aerotaller.modelos.OTDiscrepancia;
import com.aerotaller.modelos.OTTareaMantenimiento;
import com.aerotaller.modules.aeronave.repository.AeronaveRepository;
import com.aerotaller.modelos.ModeloAeronave;
// Cambia el import para usar el que está en catálogo
import com.aerotaller.modules.catalogo.repository.ModeloAeronaveRepository;
import com.aerotaller.modules.ot.dto.AeronaveComboResponse;
import com.aerotaller.modules.ot.dto.CrearOTRequest;
import com.aerotaller.modules.ot.dto.CrearOTResponse;
import com.aerotaller.modules.ot.dto.OTDiscrepanciaRequest;
import com.aerotaller.modules.ot.dto.OTTareaRequest;
import com.aerotaller.modules.ot.dto.SiguienteNoOTResponse;
import com.aerotaller.modules.catalogo.repository.ClienteRepository;
import com.aerotaller.modules.ot.repository.NuevaOTRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.aerotaller.modules.ot.dto.OTListadoResponse;
import com.aerotaller.modules.ot.dto.OTDetalleResponse;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class OTServiceImpl implements OTService
{

    private final NuevaOTRepository nuevaOTRepository;
    private final AeronaveRepository aeronaveRepository;
    private final ClienteRepository clienteRepository;
    private final ModeloAeronaveRepository modeloAeronaveRepository;

    public OTServiceImpl(NuevaOTRepository nuevaOTRepository,
                         AeronaveRepository aeronaveRepository,
                         ClienteRepository clienteRepository,
                         ModeloAeronaveRepository modeloAeronaveRepository)
    {
        this.nuevaOTRepository = nuevaOTRepository;
        this.aeronaveRepository = aeronaveRepository;
        this.clienteRepository = clienteRepository;
        this.modeloAeronaveRepository = modeloAeronaveRepository;
    }

    @Override
    public List<AeronaveComboResponse> obtenerMatriculas()
    {
        return aeronaveRepository.findAllByOrderByMatriculaAsc()
                .stream()
                .map(aeronave -> new AeronaveComboResponse(
                        aeronave.getIdAeronave(),
                        aeronave.getMatricula()
                ))
                .toList();
    }

    @Override
    public SiguienteNoOTResponse obtenerSiguienteNoOT()
    {
        return new SiguienteNoOTResponse(generarSiguienteNoOT());
    }

    @Override
    @Transactional
    public CrearOTResponse crearOT(CrearOTRequest request)
    {
        validarRequest(request);

        Aeronave aeronave = aeronaveRepository.findById(request.getIdAeronave().longValue())
                .orElseThrow(() -> new RuntimeException("La matrícula seleccionada no existe."));

        Cliente cliente = null;
        if (request.getIdCliente() != null)
        {
            cliente = clienteRepository.findById(request.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("El cliente seleccionado no existe."));
        }

        NuevaOT nuevaOT = new NuevaOT();
        nuevaOT.setNoOT(generarSiguienteNoOT());
        nuevaOT.setMatricula(aeronave);
        nuevaOT.setCliente(cliente);
        nuevaOT.setFechaCreacion(LocalDateTime.now());
        nuevaOT.setFechaApertura(
                request.getFechaApertura() != null ? request.getFechaApertura() : LocalDateTime.now()
        );
        nuevaOT.setFechaEntrega(request.getFechaEntrega());
        nuevaOT.setFechaCierre(request.getFechaCierre());
        nuevaOT.setEstado("Abierta");

        nuevaOT.setHorasTotales(request.getHorasTotales());
        nuevaOT.setCiclosTotales(request.getCiclosTotales());

        nuevaOT.setTiempoMotor1(request.getTiempoMotor1());
        nuevaOT.setCicloMotor1(request.getCicloMotor1());

        nuevaOT.setTiempoMotor2(request.getTiempoMotor2());
        nuevaOT.setCicloMotor2(request.getCicloMotor2());

        nuevaOT.setTiempoMotor3(request.getTiempoMotor3());
        nuevaOT.setCicloMotor3(request.getCicloMotor3());

        nuevaOT.setTiempoAPU(request.getTiempoAPU());
        nuevaOT.setCicloAPU(request.getCicloAPU());

        nuevaOT.setComentarioCliente(request.getComentarioCliente());

        if (request.getTareasMantenimiento() != null && !request.getTareasMantenimiento().isEmpty())
        {
            for (OTTareaRequest tareaRequest : request.getTareasMantenimiento())
            {
                validarTarea(tareaRequest);

                OTTareaMantenimiento tarea = new OTTareaMantenimiento();
                tarea.setCodigo(tareaRequest.getCodigo().trim());
                tarea.setDescripcion(tareaRequest.getDescripcion().trim());
                tarea.setTecnicos(
                        tareaRequest.getTecnicos() != null ? tareaRequest.getTecnicos().trim() : null
                );
                tarea.setHorasTotales(tareaRequest.getHorasTotales());
                tarea.setTipoTarea(
                        tareaRequest.getTipoTarea() != null ? tareaRequest.getTipoTarea().trim() : null
                );

                nuevaOT.agregarTarea(tarea);
            }
        }

        if (request.getDiscrepancias() != null && !request.getDiscrepancias().isEmpty())
        {
            for (OTDiscrepanciaRequest discrepanciaRequest : request.getDiscrepancias())
            {
                validarDiscrepancia(discrepanciaRequest);

                OTDiscrepancia discrepancia = new OTDiscrepancia();
                discrepancia.setCodigo(discrepanciaRequest.getCodigo().trim());
                discrepancia.setDescripcion(discrepanciaRequest.getDescripcion().trim());
                discrepancia.setEstatus(
                        discrepanciaRequest.getEstatus() != null ? discrepanciaRequest.getEstatus().trim() : null
                );
                discrepancia.setAcciones(
                        discrepanciaRequest.getAcciones() != null ? discrepanciaRequest.getAcciones().trim() : null
                );

                nuevaOT.agregarDiscrepancia(discrepancia);
            }
        }

        NuevaOT guardada = nuevaOTRepository.save(nuevaOT);

        return new CrearOTResponse(
                guardada.getIdOT(),
                guardada.getNoOT(),
                "OT creada correctamente."
        );
    }

    private void validarRequest(CrearOTRequest request)
    {
        if (request == null)
        {
            throw new RuntimeException("La solicitud está vacía.");
        }

        if (request.getIdAeronave() == null)
        {
            throw new RuntimeException("La matrícula es obligatoria.");
        }

        validarNumeroNoNegativo(request.getHorasTotales(), "Horas totales");
        validarEnteroNoNegativo(request.getCiclosTotales(), "Ciclos totales");

        validarNumeroNoNegativo(request.getTiempoMotor1(), "Tiempo motor 1");
        validarEnteroNoNegativo(request.getCicloMotor1(), "Ciclo motor 1");

        validarNumeroNoNegativo(request.getTiempoMotor2(), "Tiempo motor 2");
        validarEnteroNoNegativo(request.getCicloMotor2(), "Ciclo motor 2");

        validarNumeroNoNegativo(request.getTiempoMotor3(), "Tiempo motor 3");
        validarEnteroNoNegativo(request.getCicloMotor3(), "Ciclo motor 3");

        validarNumeroNoNegativo(request.getTiempoAPU(), "Tiempo APU");
        validarEnteroNoNegativo(request.getCicloAPU(), "Ciclo APU");
    }

    private void validarTarea(OTTareaRequest tarea)
    {
        if (tarea == null)
        {
            throw new RuntimeException("Se recibió una tarea vacía.");
        }

        if (tarea.getCodigo() == null || tarea.getCodigo().trim().isEmpty())
        {
            throw new RuntimeException("Cada tarea debe tener código.");
        }

        if (tarea.getDescripcion() == null || tarea.getDescripcion().trim().isEmpty())
        {
            throw new RuntimeException("Cada tarea debe tener descripción.");
        }

        validarNumeroNoNegativo(tarea.getHorasTotales(), "Horas totales de la tarea");
    }

    private void validarDiscrepancia(OTDiscrepanciaRequest discrepancia)
    {
        if (discrepancia == null)
        {
            throw new RuntimeException("Se recibió una discrepancia vacía.");
        }

        if (discrepancia.getCodigo() == null || discrepancia.getCodigo().trim().isEmpty())
        {
            throw new RuntimeException("Cada discrepancia debe tener código.");
        }

        if (discrepancia.getDescripcion() == null || discrepancia.getDescripcion().trim().isEmpty())
        {
            throw new RuntimeException("Cada discrepancia debe tener descripción.");
        }
    }

    private void validarNumeroNoNegativo(BigDecimal valor, String campo)
    {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new RuntimeException(campo + " no puede ser negativo.");
        }
    }

    private void validarEnteroNoNegativo(Integer valor, String campo)
    {
        if (valor != null && valor < 0)
        {
            throw new RuntimeException(campo + " no puede ser negativo.");
        }
    }

    private String generarSiguienteNoOT()
    {
        int anioCompleto = Year.now().getValue();
        int anioCorto = anioCompleto % 100;

        Optional<NuevaOT> ultimaOTOpt = nuevaOTRepository.findTopByOrderByIdOTDesc();

        int siguienteConsecutivo = 1;

        if (ultimaOTOpt.isPresent() && ultimaOTOpt.get().getNoOT() != null)
        {
            String ultimoNoOT = ultimaOTOpt.get().getNoOT();

            try
            {
                String[] partes = ultimoNoOT.split("-");
                if (partes.length == 2)
                {
                    int ultimoConsecutivo = Integer.parseInt(partes[1]);
                    siguienteConsecutivo = ultimoConsecutivo + 1;
                }
            } catch (Exception ignored)
            {
                siguienteConsecutivo = 1;
            }
        }

        String consecutivoFormateado = String.format("%03d", siguienteConsecutivo);
        return "AG/OT/" + anioCorto + "-" + consecutivoFormateado;
    }

    @Override
    public List<OTListadoResponse> listarOTs()
    {
        return nuevaOTRepository.findAllByOrderByFechaCreacionDescIdOTDesc()
                .stream()
                .map(ot -> new OTListadoResponse(
                        ot.getIdOT(),
                        ot.getNoOT(),
                        ot.getMatricula() != null ? ot.getMatricula().getMatricula() : null,
                        ot.getCliente() != null ? ot.getCliente().getCompania() : "Sin cliente",
                        ot.getFechaCreacion(),
                        ot.getFechaEntrega(),
                        ot.getFechaCierre(),
                        ot.getEstado()
                ))
                .toList();
    }

    @Override
    @Transactional
    public OTDetalleResponse obtenerPorId(Integer idOT) {
        NuevaOT ot = nuevaOTRepository.findById(idOT)
                .orElseThrow(() -> new RuntimeException("La Orden de Trabajo solicitada no existe."));

        OTDetalleResponse response = new OTDetalleResponse();
        response.setIdOT(ot.getIdOT());
        response.setNoOT(ot.getNoOT());
        response.setFechaCreacion(ot.getFechaCreacion());
        response.setFechaApertura(ot.getFechaApertura());
        response.setFechaEntrega(ot.getFechaEntrega());
        response.setFechaCierre(ot.getFechaCierre());
        response.setEstado(ot.getEstado());
        response.setHorasTotales(ot.getHorasTotales());
        response.setCiclosTotales(ot.getCiclosTotales());
        response.setComentarioCliente(ot.getComentarioCliente());

        // --- MAPEO DE LA AERONAVE Y BÚSQUEDA DEL MODELO ---
        if (ot.getMatricula() != null) {
            Aeronave aeronave = ot.getMatricula();
            response.setMatricula(aeronave.getMatricula());

            int idModelo = aeronave.getModeloAeronave();
            // Buscamos en la tabla ModeloAeronave usando el ID numérico
            modeloAeronaveRepository.findById(idModelo)
                    .ifPresentOrElse(
                            modeloEntity -> response.setModeloAeronave(modeloEntity.getMarca() + " " + modeloEntity.getModelo()),
                            () -> response.setModeloAeronave("ID Modelo: " + idModelo)
                    );
        }

        if (ot.getCliente() != null) {
            response.setClienteCompania(ot.getCliente().getCompania());
            response.setClienteContacto(ot.getCliente().getContacto());
        } else {
            response.setClienteCompania("Sin cliente");
        }

        // Mapeo de la lista de tareas
        List<OTTareaRequest> tareas = ot.getTareasMantenimiento().stream().map(t -> {
            OTTareaRequest dto = new OTTareaRequest();
            dto.setCodigo(t.getCodigo());
            dto.setDescripcion(t.getDescripcion());
            dto.setTecnicos(t.getTecnicos());
            dto.setHorasTotales(t.getHorasTotales());
            dto.setTipoTarea(t.getTipoTarea());
            return dto;
        }).toList();
        response.setTareasMantenimiento(tareas);

        // Mapeo de la lista de discrepancias
        List<OTDiscrepanciaRequest> disc = ot.getDiscrepancias().stream().map(d -> {
            OTDiscrepanciaRequest dto = new OTDiscrepanciaRequest();
            dto.setCodigo(d.getCodigo());
            dto.setDescripcion(d.getDescripcion());
            dto.setEstatus(d.getEstatus());
            dto.setAcciones(d.getAcciones());
            return dto;
        }).toList();
        response.setDiscrepancias(disc);

        return response;
    }

}