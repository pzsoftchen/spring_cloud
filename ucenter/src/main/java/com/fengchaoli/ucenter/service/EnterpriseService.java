package com.fengchaoli.ucenter.service;

import com.fengchaoli.ucenter.event.sync.enterprise.EnterpriseSyncEvent;
import com.fengchaoli.ucenter.form.EnterpriseForm;
import com.fengchaoli.ucenter.model.Enterprise;
import com.fengchaoli.ucenter.model.EnterpriseMeta;
import com.fengchaoli.ucenter.repository.EnterpriseRepository;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class EnterpriseService {

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public Enterprise getOne(String id){
        return enterpriseRepository.findOne(id);
    }

    public Enterprise insert(String id, EnterpriseForm enterpriseForm, String clientId){
        Enterprise enterprise = enterpriseRepository.findFirstByName(enterpriseForm.getName());
        if (enterprise == null)
            enterprise = new Enterprise();
        enterprise.setName(enterpriseForm.getName());

        EnterpriseMeta enterpriseMeta = enterprise.getEnterpriseMetas().stream().filter(meta ->
                ObjectUtil.equal(clientId,meta.getClientId())).findFirst().orElseGet(() -> new EnterpriseMeta());
        enterpriseMeta.setClientId(clientId);
        enterpriseMeta.setExtra(enterpriseForm.getExtra());
        enterprise.getEnterpriseMetas().add(enterpriseMeta);

        enterpriseRepository.save(enterprise);
        applicationEventMulticaster.multicastEvent(new EnterpriseSyncEvent(enterprise,clientId));
        return enterprise;
    }

    public Enterprise update(String id,EnterpriseForm enterpriseForm,String clientId){
        Enterprise enterprise = enterpriseRepository.getOne(id);
        EnterpriseMeta enterpriseMeta = enterprise.getEnterpriseMetas().stream().filter(meta ->
                ObjectUtil.equal(clientId,meta.getClientId())).findFirst().orElseGet(() -> new EnterpriseMeta());
        if(enterpriseMeta.getId() == null){
            enterpriseMeta.setClientId(clientId);
            enterpriseMeta.setExtra(enterpriseForm.getExtra());
            enterprise.getEnterpriseMetas().add(enterpriseMeta);
        }else{
            enterpriseMeta.setExtra(enterpriseForm.getExtra());
        }

        enterpriseRepository.save(enterprise);
        applicationEventMulticaster.multicastEvent(new EnterpriseSyncEvent(enterprise,clientId));
        return enterprise;
    }
}
