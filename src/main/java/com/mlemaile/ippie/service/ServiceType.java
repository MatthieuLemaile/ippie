package com.mlemaile.ippie.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.persistence.TypeDao;
import com.mlemaile.ippie.service.dto.TypeDto;
import com.mlemaile.ippie.service.mapper.MapperType;

@Service
public class ServiceType {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceType.class);

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

    public TypeDto findOne ( long id ) {
        if (id < 0) {
            throw new IllegalArgumentException("The id must be positive.");
        }
        Type type = typeDao.findOne(id)
                .orElseThrow( () -> new IllegalArgumentException("The given id does not exists"));
        return MapperType.INSTANCE.toDto(type);
    }

    /**
     * This method either save or update the type in the database.
     * @param dto the Type to save/update
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

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Saving Type in the database : " + type);
        }

        Optional<Type> optionalType = typeDao.save(type);
        if (optionalType.isPresent()) {
            dto = MapperType.INSTANCE.toDto(optionalType.get());

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Save successful for" + optionalType.get());
            }
            return true;
        }

        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("Can't save type : " + type);
        }
        return false;
    }
}
