package com.mlemaile.ippie.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.persistence.ModelDao;
import com.mlemaile.ippie.service.dto.ModelDto;
import com.mlemaile.ippie.service.mapper.MapperModel;

@Service
public class ServiceModel {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceModel.class);

    @Autowired
    private ModelDao modelDao;

    public List<ModelDto> findAll () {
        return MapperModel.INSTANCE.toListDto(modelDao.findAll());
    }

    /**
     * This method either save or update the type in the database.
     * 
     * @param dto
     *            the Type to save/update
     * @return a boolean which is true if the operation went well
     */
    public boolean save ( ModelDto dto ) {
        if (dto == null) {
            throw new IllegalArgumentException("The given dto is null");
        }
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "The given dto has a null or empty/white space name.");
        }
        if (dto.getTypeId() == 0) {
            throw new IllegalArgumentException("The given dto has a non existant Type");
        }
        Model model = MapperModel.INSTANCE.toModel(dto);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Saving Model in the database : " + model);
        }

        Optional<Model> optionalModel = modelDao.save(model);
        if (optionalModel.isPresent()) {
            dto = MapperModel.INSTANCE.toDto(optionalModel.get());

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Save successful for" + optionalModel.get());
            }
            return true;
        }

        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("Can't save model : " + model);
        }
        return false;
    }
}
