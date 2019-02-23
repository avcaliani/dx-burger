package br.avcaliani.dxburgerapi.service;

import br.avcaliani.dxburgerapi.domain.entity.Ingredient;
import br.avcaliani.dxburgerapi.domain.to.IngredientTO;
import br.avcaliani.dxburgerapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Ingredient Service Implementation.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository repository;

    /**
     * @see IngredientService#find()
     */
    @Override
    public List<IngredientTO> find() {
        return repository.find();
    }

    /**
     * @see IngredientService#findMissing(List)
     */
    @Override
    public List<Ingredient> findMissing(List<String> names) {
        if (names == null || names.isEmpty())
            return this.repository.findAll();
        return this.repository.findMissingIngredients(names);
    }
}