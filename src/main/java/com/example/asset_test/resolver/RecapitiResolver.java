package com.example.asset_test.resolver;


import com.example.communication.model.RecapitiTelefonici;
import com.example.communication.services.RecapitiService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RecapitiResolver {

    @Autowired
    RecapitiService recapitiService;

    @GraphQLQuery
    public Optional<RecapitiTelefonici> recapitoById(Long id){
        return recapitiService.recapitoById(id);
    }

    @GraphQLQuery
    public Iterable<RecapitiTelefonici> recapitoAll(){
        return recapitiService.recapitoAll();
    }

    @GraphQLMutation
    public RecapitiTelefonici newRecapiti(Long id, Long idana, String tipo_recapito, String numero_recapito){
        return recapitiService.newRecapiti(id, idana, tipo_recapito, numero_recapito);
    }

    @GraphQLMutation
    public Boolean deleteRecapiti(long idreca){
        return recapitiService.deleteRecapiti(idreca);
    }

    @GraphQLMutation
    public Optional<RecapitiTelefonici> updateRecapiti(Long id, String tipo_recapito, String numero_recapito){
        return recapitiService.updateRecapiti(id, tipo_recapito, numero_recapito);
    }

}
