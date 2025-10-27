package com.WorkFlowManager.project.Specifications;

import com.WorkFlowManager.project.dto.PreviaFilterDTO;
import com.WorkFlowManager.project.model.Previa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public class PreviaSpecification {

    public static Specification<Previa> filterByPrevia(PreviaFilterDTO filter) {

        return (root, query, cb) -> {
            
            List<Predicate> predicates = new ArrayList<>();

            if (filter.idEscala() != null && filter.idEscala().length > 0) {
                predicates.add(root.get("idEscala").in((Object[]) filter.idEscala()));
            }

            if (filter.idMilitar() != null && filter.idMilitar().length > 0) {
                predicates.add(root.get("idMilitar").in((Object[]) filter.idMilitar()));
            }

            if (filter.dataInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("data"), filter.dataInicio()));
            }

            if (filter.dataFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("data"), filter.dataFim()));
            }

            if (filter.status() != null) {
                predicates.add(cb.equal(root.get("status"), filter.status()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}