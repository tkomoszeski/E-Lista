package com.skpcp.elista.grupa.api;

import com.skpcp.elista.grupa.dto.GrupaDTO;
import com.skpcp.elista.grupa.service.IGrupaService;
import com.skpcp.elista.utils.exceptions.MyServerException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tomasz Komoszeski on 2016-04-27.
 */

@RestController
@Transactional
@RequestMapping(value = "elista/grupy")
public class GrupaController {

    @Autowired
    IGrupaService iGrupaService;

    @RequestMapping(value = "/pobierzGrupePoId/{id}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GrupaDTO> znajdzGrupePoId(@PathVariable("id") Long aId){
        try {
            return new ResponseEntity<>(iGrupaService.znajdzGrupePoId(aId), HttpStatus.OK);
        }catch (MyServerException e){
            return  new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }

    @RequestMapping(value = "/zapiszGrupe",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<GrupaDTO> zapiszGrupe(@RequestBody GrupaDTO aGrupa){
        try {
            return new ResponseEntity<>(iGrupaService.zapiszGrupe(aGrupa), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }

    @RequestMapping(value = "/dodajUzytkownikaDoGrupy",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<GrupaDTO> dodajUzytkownikaDoGrupy(@RequestBody GrupaDTO aGrupaDTO){
        try{
        return new ResponseEntity<>(iGrupaService.dodajUzytkownikDoGrupy(aGrupaDTO),HttpStatus.OK);
        } catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }

    }

    @RequestMapping(value = "/pobierzGrupePoNazwie/{nazwa}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GrupaDTO> znajdzGrupePoNazwie(@PathVariable("nazwa") String aNazwa){
        try{
        return new ResponseEntity<>(iGrupaService.znajdzGrupePoNazwie(aNazwa), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }



    @RequestMapping(value = "/pobierzGrupePoLiderze/{lider.id}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GrupaDTO> znajdzGrupePoLiderze(@PathVariable("lider.id") Long aId){
        try{
        return new ResponseEntity<>(iGrupaService.znajdzGrupePoLiderzeId(aId), HttpStatus.OK);
        }catch(MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }

    @RequestMapping(value = "/usunGrupePoId/{id}",method = RequestMethod.PUT)
    @ResponseBody
    ResponseEntity<GrupaDTO> usunGrupePoId(@PathVariable("id")Long aId){
        iGrupaService.usunGrupePoId(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
