package com.cantonnet.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cantonnet.modelo.Comprobante;
import com.cantonnet.servicios.ComprobanteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comprobantes")
@Tag(name = "Gestionar comprobantes de la venta", description = "Endpoints de comprobantes")
public class ComprobanteController {
	
	@Autowired
    private ComprobanteService comprobanteService;
    
	@Operation(summary = "Obtener listado de comprobantes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Listado responde con exito", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Comprobante.class))}),
		@ApiResponse(responseCode = "500", description = "Error del servicio", content = @Content)})
    @GetMapping
    public ResponseEntity<List<Comprobante>> getAllComprobantes() {
        List<Comprobante> comprobantes = comprobanteService.getAllComprobantes();
        return new ResponseEntity<>(comprobantes, HttpStatus.OK);
    }
    
	@Operation(summary = "Obtener comprobante por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Comprobante se obtuvo correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Comprobante.class))}),
		@ApiResponse(responseCode = "404", description = "Comprobante no se encuentra o no existe", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Comprobante> getComprobanteById(@PathVariable("id") int id) {
        Comprobante comprobante = comprobanteService.getComprobanteById(id);
        if (comprobante != null) {
            return new ResponseEntity<>(comprobante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
}
