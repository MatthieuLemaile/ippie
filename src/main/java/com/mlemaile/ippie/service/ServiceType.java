package com.mlemaile.ippie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.persistence.TypeDao;
import com.mlemaile.ippie.service.dto.TypeDto;
import com.mlemaile.ippie.service.mapper.MapperType;

@Service
public class ServiceType {

    @Autowired
    private TypeDao typeDao;

    /**
     * This method retrieve all Type from the database.
     * 
     * @return The List of all TypeDto.
     */
    public List<TypeDto> findAll () {
        return MapperType.INSTANCE.toListDto(typeDao.findAll());
    }

    /**
     * This method either save or update the type in the database.
     * @param dto
     *            the Type to save/update
     * @return a boolean which is true if the operation went well
     */
    public boolean save ( TypeDto dto ) {
        if (dto == null) {
            throw new IllegalArgumentException("The given dto is null");
        }
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "The given dto has a null or empty/white space name.");
        }
        Type type = MapperType.INSTANCE.toModel(dto);
        Optional<Type> optionalType = typeDao.save(type);
        if (optionalType.isPresent()) {
            dto = MapperType.INSTANCE.toDto(optionalType.get());
            return true;
        }
        return false;
    }
}
