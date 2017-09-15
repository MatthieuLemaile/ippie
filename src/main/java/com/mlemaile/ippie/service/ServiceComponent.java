package com.mlemaile.ippie.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.persistence.ComponentDao;
import com.mlemaile.ippie.service.dto.ComponentDto;
import com.mlemaile.ippie.service.mapper.MapperComponent;

@Service
public class ServiceComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceComponent.class);

    @Autowired
    private ComponentDao componentDao;
    @Autowired
    private MapperComponent mapperComponent;

    /**
     * This method find all Component
     * @return a List of all Component
     */
    public List<ComponentDto> findAll () {
        return mapperComponent.toListDto(componentDao.findAll());
    }
    
    /**
     * This method save the object in the database.
     * @param dto the object to save
     * @return True of the saving went well.
     */
    public boolean save ( ComponentDto dto ) {
        if (dto == null) {
            throw new IllegalArgumentException("The given dto is null");
        }
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "The given dto has a null or empty/white space name.");
        }
        Component c = mapperComponent.toModel(dto);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Saving Component in the database : " + c);
        }
        Optional<Component> compoOpt = componentDao.save(c);
        if (compoOpt.isPresent()) {
            dto = mapperComponent.toDto(compoOpt.get());

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Save successful for" + dto);
            }
            return true;
        }
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("Can't save component : " + dto);
        }
        return false;
    }

    /**
     * This method find the component corresponding to the given id.
     * @param componentId the id of the component to find.
     * @return a ComponentDto.
     */
    public ComponentDto findOne ( long componentId ) {
        if (componentId <= 0) {
            throw new IllegalArgumentException("The provided Id must be greater than 0");
        }
        return mapperComponent.toDto(componentDao.findOne(componentId).orElseThrow( () -> {
            return new IllegalArgumentException("The corresponding Component does not exeists.");
        }));
    }

    /**
     * This method delete the component corresponding to the given id.
     * @param id the id of the component to delete.
     */
    public void deleteComponent ( long id ) {
        if (id <= 0) {
            throw new IllegalArgumentException("The given id does not match any Component");
        }
        componentDao.delete(id);
    }
}
