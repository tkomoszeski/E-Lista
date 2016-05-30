package com.skpcp.elista.grupa.service.impl;

import com.skpcp.elista.grupa.dto.*;
import com.skpcp.elista.grupa.ob.GrupaOB;
import com.skpcp.elista.grupa.repository.IGrupaRepository;
import com.skpcp.elista.grupa.service.IGrupaService;
import com.skpcp.elista.utils.converters.GrupaConverter;
import com.skpcp.elista.utils.exceptions.MyServerException;
import com.skpcp.elista.uzytkownik.ob.UzytkownikOB;
import com.skpcp.elista.uzytkownik.repository.IUzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-04-27.
 */
@Service
@Transactional
public class GrupaServiceImpl implements IGrupaService {

    @Autowired
    IGrupaRepository grupaRepository;

    @Autowired
    IUzytkownikRepository uzytkownikRepository;


    @Override
    public GrupaDTOUzytkownik zapiszGrupe(GrupaDTOBezIdTechDate aGrupaDTO) throws MyServerException{
        if(aGrupaDTO == null) throw new MyServerException("Nie znaleziono pola", HttpStatus.NOT_FOUND,new HttpHeaders());

        UzytkownikOB pUzytkownikOB = aGrupaDTO.getLider() == null ? null : uzytkownikRepository.findOne(aGrupaDTO.getLider());
        if(pUzytkownikOB == null) throw new MyServerException("Nie znaleziono uzytkownika", HttpStatus.NOT_FOUND,new HttpHeaders());//nie ma użytkownika który by mógł założyć grupe

        GrupaOB pGrupaOB = aGrupaDTO.getNazwa() == null ? null : grupaRepository.znajdzGrupePoNazwie(aGrupaDTO.getNazwa());
        if(pGrupaOB == null){
            pGrupaOB = new GrupaOB(aGrupaDTO.getNazwa(),pUzytkownikOB);
            return GrupaConverter.grupaOBDoGrupaDTOUzytkownik(grupaRepository.save(pGrupaOB));
        }
        //jeżeli istniała to edytuj
        pGrupaOB.setLider(pUzytkownikOB); //zmieniam lidera
        return GrupaConverter.grupaOBDoGrupaDTOUzytkownik((grupaRepository.save(pGrupaOB)));
    }

    @Override
    public GrupaDTOUzytkownik znajdzGrupePoId(Long aId) throws MyServerException {
        GrupaOB pGrupaOB = grupaRepository.findOne(aId);
        if(pGrupaOB == null) throw new MyServerException("Nie ma takiej grupy", HttpStatus.NOT_FOUND,new HttpHeaders());
        return GrupaConverter.grupaOBDoGrupaDTOUzytkownik(pGrupaOB);
    }

    @Override
    public GrupaDTOUzytkownik znajdzGrupePoNazwie(String aNazwa) throws MyServerException {
        GrupaOB pGrupaOB = grupaRepository.znajdzGrupePoNazwie(aNazwa);
        if(pGrupaOB == null) throw new MyServerException("Nie ma takiej grupy", HttpStatus.NOT_FOUND,new HttpHeaders());
        return  GrupaConverter.grupaOBDoGrupaDTOUzytkownik(pGrupaOB);
    }

    @Override
    public List<GrupaDTOBezLiderAleZIdTechDate> znajdzGrupePoIdLidera(Long aIdLidera) {
            List<GrupaOB> listaGrupaOB = grupaRepository.znajdzGrupePoIdLidera(aIdLidera);
            List<GrupaDTOBezLiderAleZIdTechDate> listaGrupaDTO = new ArrayList<>();
            for(GrupaOB pGrupaOB : listaGrupaOB){
                listaGrupaDTO.add(GrupaConverter.grupaOBDoGrupaDTOBezLiderAleZIdTechDate(pGrupaOB));
            }
            return listaGrupaDTO;
        }


    @Override
    public List<GrupaDTOBezLiderAleZIdTechDate> znajdzGrupePoNazwieLidera(String aEmail)  {
        List<GrupaOB> listaGrupaOB = grupaRepository.znajdzGrupePoEmailuLidera(aEmail);
        List<GrupaDTOBezLiderAleZIdTechDate> listaGrupaDTO = new ArrayList<>();
        for(GrupaOB pGrupaOB : listaGrupaOB){
            listaGrupaDTO.add(GrupaConverter.grupaOBDoGrupaDTOBezLiderAleZIdTechDate(pGrupaOB));
        }
        return listaGrupaDTO;
    }

    @Override
    public void usunGrupePoId(Long aId) throws MyServerException {
        GrupaOB pGrupaOB = grupaRepository.findOne(aId);
        if(pGrupaOB == null) throw new MyServerException("Nie znaleziono grupy",HttpStatus.NOT_FOUND,new HttpHeaders());
        grupaRepository.delete(aId);
    }

    @Override
    public List<GrupaDTOUzytkownik> znajdzWszystkieGrupy() {
        List<GrupaDTOUzytkownik> listaGrupDTO = new ArrayList<>();
        List<GrupaOB> listaGrupOB = grupaRepository.findAll();

        for(GrupaOB grupaOB : listaGrupOB){
            listaGrupDTO.add(GrupaConverter.grupaOBDoGrupaDTOUzytkownik(grupaOB));
        }

        return listaGrupDTO;
    }
}
