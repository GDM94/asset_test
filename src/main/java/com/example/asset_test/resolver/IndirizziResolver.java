package com.example.asset_test.resolver;



import com.example.communication.model.Indirizzo;
import com.example.communication.services.IndrizzoService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IndirizziResolver {
    @Autowired
    IndrizzoService indirizzoService;

    @GraphQLQuery
    public Optional<Indirizzo> indirizzoById(Long id){
        return indirizzoService.indirizzoById(id);
    }

    @GraphQLQuery
    public Iterable<Indirizzo> indirizzoAll(){
        return indirizzoService.indirizzoAll();
    }


    @GraphQLMutation
    public Indirizzo newIndirizzo(Long id, Long idana, String descrizione){
        return  indirizzoService.newIndirizzo(id, idana, descrizione);
    }

    @GraphQLMutation
    public Optional<Indirizzo> updateIndirizzo(Long id, String descrizione){
        return indirizzoService.updateIndirizzo(id, descrizione);
    }

    @GraphQLMutation
    public boolean deleteIndirizzo(Long id){
        return indirizzoService.deleteIndirizzo(id);
    }
}
