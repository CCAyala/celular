package com.example.crudCelular.controler;

import com.example.crudCelular.entity.celuar;
import com.example.crudCelular.servicioCeluar.servicio_celular;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class controlador {

    private servicio_celular servicio;


    public controlador(servicio_celular servicio){this.servicio=servicio;}


    @GetMapping("/listarCelular")
    public List<celuar> listarCelular(){return servicio.listaCelulares();}

    @GetMapping("/buscarCelular/{nit}")
    public celuar buscarCelular(@PathVariable int nit){return servicio.buscarcelular(nit);}


    @PostMapping("/agregarCelular")
    public void agregarCelular(@RequestBody celuar celular){ servicio.agregarCelular(celular);

    }

    @PutMapping("/actualizarCelular/{nit}")
    public ResponseEntity<String> actualizarCelular(@PathVariable int nit, @RequestBody celuar celular) {
        for (celuar ce : servicio.celulars) {
            if (ce.getNit() == nit) {
                ce.setMarca(celular.getMarca());
                ce.setTamano(celular.getTamano());
                ce.setCapacidad(celular.getCapacidad());
                ce.setPrecio(celular.getPrecio());
                return ResponseEntity.ok("el celular se  actualizo exitosamente");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el celular con el NIT proporcionado");
    }


    @DeleteMapping("/eliminarCelular/{NIT}")
    public ResponseEntity<String> eliminarCelular(@PathVariable int NIT) {
        try {
            servicio.eliminarCelular(NIT);
            return ResponseEntity.ok("se elimino correctamente");
        } catch (RuntimeException e) {
          return   ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encontro el celular");

        }

    }
}