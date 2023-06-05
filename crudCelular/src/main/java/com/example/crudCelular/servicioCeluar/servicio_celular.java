package com.example.crudCelular.servicioCeluar;

import com.example.crudCelular.entity.celuar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class servicio_celular {


    public List<celuar> celulars = new ArrayList<>();


    public servicio_celular() {

        celulars.add(new celuar(1, "samsumg", 6.7f, "64gb", 790.600f));
        celulars.add(new celuar(2, "motorola", 7.2f, "128gb", 990.600f));
        celulars.add(new celuar(3, "Lg", 7.7f, "64gb", 890.600f));
        celulars.add(new celuar(4, "sony", 9.7f, "128gb", 1200.600f));
        celulars.add(new celuar(5, "Iphone", 5.7f, "64gb", 1090.600f));
        celulars.add(new celuar(6, "linux", 6.9f, "128gb", 1390.600f));
        celulars.add(new celuar(7, "xiomi", 7.2f, "64gb", 980.600f));

    }


    public List<celuar> listaCelulares() {
        return celulars;
    }

    public celuar buscarcelular(int nit) {
        for (celuar celu : celulars) {
            if (celu.getNit()==nit) {
                return celu;
            }

        }


        return null;
    }


    public String agregarCelular(celuar celularAgregado) {

        if (buscarcelular(celularAgregado.nit) == null) {
            celulars.add(celularAgregado);
            return "el celular se agrego con exito";
        }else {



            return  "el celular ya existe";
        }
    }



    public String actualizarCelular(celuar celularActualizado) {
        for (celuar ce : celulars) {
            if (ce.getNit() == celularActualizado.getNit()) {
                ce.setMarca(celularActualizado.getMarca());
                ce.setTamano(celularActualizado.getTamano());
                ce.setCapacidad(celularActualizado.getCapacidad());
                ce.setPrecio(celularActualizado.getPrecio());
                return "se_actualizo";
            }
        }
        return "no_actualizo";
    }

    public void eliminarCelular(int nit){
        celuar celular= buscarcelular(nit);
        if (celular!=null){
            celulars.remove(celular);
        }else {
            throw new RuntimeException("no se encuantra el celular");
        }

        }
}
