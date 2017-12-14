package com.fengchaoli.acenter.repository;

import com.fengchaoli.acenter.model.NotifyData;
import com.fengchaoli.acenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface NotifyDataRepository extends JpaRepository<NotifyData, String>, JpaSpecificationExecutor<NotifyData> {

}
