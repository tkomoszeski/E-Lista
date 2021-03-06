package com.skpcp.elista.uzytkownik.api;

import com.skpcp.elista.utils.exceptions.MyServerException;
import com.skpcp.elista.uzytkownik.EStan;
import com.skpcp.elista.uzytkownik.dto.UzytkownikDTO;
import com.skpcp.elista.uzytkownik.service.IUzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by Tomek on 2016-03-20.
 */
@RestController
@RequestMapping(value = "/elista/uzytkownicy")
public class UzytkownikController {
    @Autowired
    IUzytkownikService serwisUzytkownika;



    @RequestMapping(value = "pobierzPoId/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UzytkownikDTO> znajdzUzytkownikaPoId(@PathVariable("id") Long aId){
        try{
        return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikaPoId(aId), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }


    @RequestMapping(value = "/pobierzWszystkich",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UzytkownikDTO>> znajdzWszystkich(){
        return new ResponseEntity<>(serwisUzytkownika.znajdzWszystkichUzytkownikow(),HttpStatus.OK);
    }

    @RequestMapping(value ="/pobierzPoImieniu/{imie}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UzytkownikDTO>> znajdzPoImieniu(@PathVariable("imie")String aImie ){
        return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikowPoImieniu(aImie),HttpStatus.OK);
    }

    @RequestMapping(value ="/pobierzPoNazwisku/{nazwisko}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UzytkownikDTO>> znajdzPoNazwisku(@PathVariable("nazwisko")String aNazwisko){
        return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikowPoNazwisku(aNazwisko),HttpStatus.OK);
    }

    @RequestMapping(value = "pobierzPoImieniuINazwisku/{imie},{nazwisko}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UzytkownikDTO>> znajdzPoImieniuINazwisku(@PathVariable("imie")String aImie,@PathVariable("nazwisko") String aNazwisko){
        return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikowPoImieniuINazwisku(aImie,aNazwisko),HttpStatus.OK);
    }

    @RequestMapping(value = "/zapiszUzytkownika",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<UzytkownikDTO> zapiszUzytkownika(@RequestBody UzytkownikDTO aUzytkownikDTO){
        try {
            return new ResponseEntity<>(serwisUzytkownika.zapiszUzytkownika(aUzytkownikDTO), HttpStatus.OK);
        }catch (MyServerException e) {
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }
    }

    @RequestMapping(value = "usunPoId/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> usunUzytkownika(@PathVariable("id")Long aId){
        serwisUzytkownika.usunUzytkownika(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "aktywujPoId/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> aktywujUzytkownika(@PathVariable("id")Long aId){
        serwisUzytkownika.aktywujUzytkownika(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "pobierzPoAktywnosci/{aktywnosc}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UzytkownikDTO>> znajdzWszystkichPoAktywnosci(@PathVariable("aktywnosc")EStan aAktywnosc){
        return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikowPoAktywnosci(aAktywnosc),HttpStatus.OK);
    }

    @RequestMapping(value = "pobierzPoNazwieRoli/{rola.nazwa}",method = RequestMethod.GET)
    public ResponseEntity<List<UzytkownikDTO>> znajdzWszystkichUzytkownikowPoNazwieRoli(@PathVariable("rola.nazwa")String aNazwa){
        return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikowPoNazwieRoli(aNazwa),HttpStatus.OK);
    }

    @RequestMapping(value = "pobierzPoEmailuId/{email}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UzytkownikDTO> znajdzUzytkownikaPoEmailu(@PathVariable("email") String aEmail){
        try{
            return new ResponseEntity<>(serwisUzytkownika.znajdzUzytkownikaPoEmailu(aEmail), HttpStatus.OK);
        }catch (MyServerException e){
            return new ResponseEntity<>(e.getHeaders(),e.getStatus());
        }

    }
}
