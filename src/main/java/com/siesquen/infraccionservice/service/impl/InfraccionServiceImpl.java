package com.siesquen.infraccionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.siesquen.infraccionservice.entity.Infraccion;
import com.siesquen.infraccionservice.exception.GeneralServiceException;
import com.siesquen.infraccionservice.exception.NoDataFoundException;
import com.siesquen.infraccionservice.exception.ValidateServiceException;
import com.siesquen.infraccionservice.repository.InfraccionRepository;
import com.siesquen.infraccionservice.service.InfraccionService;
import com.siesquen.infraccionservice.validator.InfraccionValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfraccionServiceImpl implements InfraccionService{
	
	@Autowired
    private InfraccionRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Infraccion> findAll(Pageable page) {
		try {
            return repository.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener todos las infracciones", e);
        }
	}

	@Override
	@Transactional(readOnly = true)
	public Infraccion findById(int id) {
		try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No se encontró la infraccion con ID: " + id));
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar la infraccion por ID", e);
        }
	}

	@Override
	@Transactional(readOnly = true)
	public Infraccion findByDni(String dni) {
		try {
            return repository.findByDni(dni);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar la infraccion por dni", e);
        }
	}

	@Override
	@Transactional
	public Infraccion create(Infraccion obj) {
		try {
            InfraccionValidator.validate(obj);
            return repository.save(obj);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al crear la infraccion", e);
        }
	}

	@Override
	@Transactional
	public Infraccion update(Infraccion obj) {
		try {
            InfraccionValidator.validate(obj);
            if (!repository.existsById(obj.getId())) {
                throw new NoDataFoundException("No existe la infraccion con ID: " + obj.getId());
            }
            return repository.save(obj);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al actualizar la infraccion", e);
        }
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		try {
            if (!repository.existsById(id)) {
                throw new NoDataFoundException("No existe la infraccion con ID: " + id);
            }
            repository.deleteById(id);
            return true;
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al eliminar la infraccion", e);
        }
	}

}
