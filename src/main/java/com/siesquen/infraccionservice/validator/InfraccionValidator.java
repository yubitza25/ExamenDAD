package com.siesquen.infraccionservice.validator;

import com.siesquen.infraccionservice.entity.Infraccion;
import com.siesquen.infraccionservice.exception.ValidateServiceException;

public class InfraccionValidator {

    public static void validate(Infraccion infraccion) {
        if (infraccion.getDni() == null || infraccion.getDni().trim().isEmpty()) {
            throw new ValidateServiceException("El DNI es requerido");
        }
        if (infraccion.getDni().length() != 8) {
            throw new ValidateServiceException("El DNI debe tener exactamente 8 caracteres");
        }

        if (infraccion.getFecha() == null) {
            throw new ValidateServiceException("La fecha de la infracción es requerida");
        }

        if (infraccion.getTipoInfraccion() == null || infraccion.getTipoInfraccion().trim().isEmpty()) {
            throw new ValidateServiceException("El tipo de infracción es requerido");
        }
        if (infraccion.getTipoInfraccion().length() > 20) {
            throw new ValidateServiceException("El tipo de infracción no debe tener más de 20 caracteres");
        }

        if (infraccion.getUbicacion() == null || infraccion.getUbicacion().trim().isEmpty()) {
            throw new ValidateServiceException("La ubicación es requerida");
        }
        if (infraccion.getUbicacion().length() > 200) {
            throw new ValidateServiceException("La ubicación no debe tener más de 200 caracteres");
        }
        if (infraccion.getEstado() == null || infraccion.getEstado().trim().isEmpty()) {
            throw new ValidateServiceException("El estado es requerido");
        }
        if (infraccion.getEstado().length() > 20) {
            throw new ValidateServiceException("El estado no debe tener más de 20 caracteres");
        }
    }
}
