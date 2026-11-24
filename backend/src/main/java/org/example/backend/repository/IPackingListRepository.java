package org.example.backend.repository;

import org.example.backend.model.PackingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPackingListRepository extends JpaRepository<PackingList, Integer> {

}
