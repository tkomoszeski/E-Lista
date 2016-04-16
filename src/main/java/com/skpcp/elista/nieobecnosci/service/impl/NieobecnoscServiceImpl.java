package com.skpcp.elista.nieobecnosci.service.impl;


import com.skpcp.elista.nieobecnosci.dto.NieobecnoscDTO;
import com.skpcp.elista.nieobecnosci.ob.NieobecnoscOB;
import com.skpcp.elista.nieobecnosci.repository.INieobecnoscRepository;
import com.skpcp.elista.nieobecnosci.service.INieobecnoscService;

import com.skpcp.elista.utils.NieobecnoscConverter;
import com.skpcp.elista.utils.UzytkownikConverter;
import com.skpcp.elista.uzytkownik.dto.UzytkownikDTO;
import com.skpcp.elista.uzytkownik.ob.UzytkownikOB;
import com.skpcp.elista.uzytkownik.repository.IUzytkownikRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IGOR on 2016-03-22.
 */
@Service
@Transactional
public class NieobecnoscServiceImpl implements INieobecnoscService {
    @Autowired
    INieobecnoscRepository iNieobecnosciRepository;

    @Autowired
    IUzytkownikRepository iUzytkownikRepository;

    @Override
    public NieobecnoscDTO znajdzNieobecnoscPoId(Long aId){
        NieobecnoscOB pNieobecnosciOB = iNieobecnosciRepository.findOne(aId);
        if(pNieobecnosciOB == null) return null;
        return NieobecnoscConverter.nieoOBdonieoDTO(pNieobecnosciOB);
    }

    @Override
    public List<NieobecnoscDTO> znajdzWszystkieNieobecnosci(){
        List<NieobecnoscDTO> listaWynikowaNieobecnosciDTO = new ArrayList<>();
        List<NieobecnoscOB> listaNieobecnosciOB = iNieobecnosciRepository.findAll();
        for(NieobecnoscOB nieobecnosci : listaNieobecnosciOB) listaWynikowaNieobecnosciDTO.add(NieobecnoscConverter.nieoOBdonieoDTO(nieobecnosci));

        return listaWynikowaNieobecnosciDTO;
    }
    @Override
    public List<NieobecnoscDTO> znajdzNieobecnoscPoIdUzytkownika(Long aIdUzytkownika){
        List<NieobecnoscDTO> listaWynikowaNieobecnosciDTO = new ArrayList<>();
        List<NieobecnoscOB> listaNieobecnosciOB = iNieobecnosciRepository.znajdzNieobecnosciPoUzytkowniku(aIdUzytkownika);
        for(NieobecnoscOB nieobecnoscOB : listaNieobecnosciOB)
        listaWynikowaNieobecnosciDTO.add(NieobecnoscConverter.nieoOBdonieoDTO(nieobecnoscOB));
        return listaWynikowaNieobecnosciDTO;
    }
    @Override
    public NieobecnoscDTO znajdzNieobecnoscPoDacieIUzytkowniku(Date aData, Long aIdUzytkownika){
        NieobecnoscOB pNieobecnoscOB = iNieobecnosciRepository.znajdzNieobecnoscPoDacieIUzytkowniku(aData,aIdUzytkownika);//zwróc mi wszystkie nieobecności
        if(pNieobecnoscOB == null) return null;
        return NieobecnoscConverter.nieoOBdonieoDTO(pNieobecnoscOB);

    }
    @Override
    public List<NieobecnoscDTO> znajdzNieobecnosciPoTypie(String aTyp){
        List<NieobecnoscDTO> listaWynikowaNieobecnosciDTO = new ArrayList<>();//utworzenie pojemnika
        List<NieobecnoscOB> listaNieobecnosciOB = iNieobecnosciRepository.znajdzNieboecnosciPoTypie(aTyp);//zwróc mi wszystkie nieobecności
        //przepisanie moich nieobecnosci
        for(NieobecnoscOB nieobecnosc : listaNieobecnosciOB) listaWynikowaNieobecnosciDTO.add(NieobecnoscConverter.nieoOBdonieoDTO(nieobecnosc)); //zmień każdą instancję NieobecnosciOB do instancji DTO

        return listaWynikowaNieobecnosciDTO;//zwróć DTO

    }

    @Override
    public List<NieobecnoscDTO> znajdzNieobecnosciPoTypieIUzytkowniku(String aTyp, Long aIdUzytkownika) {
        List<NieobecnoscDTO> listaWynikowaNieobecnosciDTO = new ArrayList<>();//utworzenie pojemnika
        List<NieobecnoscOB> listaNieobecnosciOB = iNieobecnosciRepository.znajdzPoTypieNieobecnosciIUzytkowniku(aTyp,aIdUzytkownika);//zwróc mi wszystkie nieobecności
        //przepisanie moich nieobecnosci
        for(NieobecnoscOB nieobecnosc : listaNieobecnosciOB) listaWynikowaNieobecnosciDTO.add(NieobecnoscConverter.nieoOBdonieoDTO(nieobecnosc)); //zmień każdą instancję NieobecnosciOB do instancji DTO

        return listaWynikowaNieobecnosciDTO;//zwróć DTO
    }
    @Override
    public NieobecnoscDTO zapiszNieobecnosc(NieobecnoscDTO aNieobecnosciDTO){
        if(aNieobecnosciDTO == null){
            return null;
        }

        UzytkownikDTO pUzytkownikDTO = aNieobecnosciDTO.getUzytkownik() == null ? null : aNieobecnosciDTO.getUzytkownik();
        if(pUzytkownikDTO == null) return null;// coś poszło nie tak
        //skoro nie jest nullem przystępujemy do pracy
        UzytkownikOB pUztkownikOB = pUzytkownikDTO.getId() == null ? null : iUzytkownikRepository.findOne(pUzytkownikDTO.getId());
        if(pUztkownikOB == null){
            return null; //coś poszło nie tak
        }
        //sprawdzam czy dany rekord z OB już istnieje
        NieobecnoscOB pNieobecnosciOB = aNieobecnosciDTO.getId() == null ? null : iNieobecnosciRepository.findOne(aNieobecnosciDTO.getId());

        if(pNieobecnosciOB == null){//gdy nie ma takiego to zapisz
            return NieobecnoscConverter.nieoOBdonieoDTO(iNieobecnosciRepository.save(NieobecnoscConverter.nieoDTOdoNieoOB(aNieobecnosciDTO)));
        }
        //tutaj odwołując się do obiektu pNieobecnosciOB mogę zrobić walidację danych po stronie serwera (na OB)
        //edytuj istniejącego
        pNieobecnosciOB.setUzytkownik(UzytkownikConverter.uzytDTOdoUzytkOB(aNieobecnosciDTO.getUzytkownik()));
        pNieobecnosciOB.setData(aNieobecnosciDTO.getData());
        pNieobecnosciOB.setIloscGodzin(aNieobecnosciDTO.getIlosc());
        pNieobecnosciOB.setTyp(aNieobecnosciDTO.getTyp());
        return NieobecnoscConverter.nieoOBdonieoDTO(iNieobecnosciRepository.save(pNieobecnosciOB));

    }
    @Override
    public void usunNieobecnosci(Long aId) {
        iNieobecnosciRepository.delete(aId);
    }



    

}