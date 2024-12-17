package com.siesquen.infraccionservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.siesquen.infraccionservice.entity.Infraccion;

public interface InfraccionService {
	public List<Infraccion> findAll(Pageable page);
	public Infraccion findById(int id);
	public Infraccion findByDni(String dni);
    public Infraccion create(Infraccion obj);
    public Infraccion update(Infraccion obj);
    public boolean delete(int id);
}
