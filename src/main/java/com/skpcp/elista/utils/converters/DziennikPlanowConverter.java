package com.skpcp.elista.utils.converters;

import com.skpcp.elista.dziennikplanow.dto.DziennikPlanowDTO;
import com.skpcp.elista.dziennikplanow.ob.DziennikPlanowOB;

/**
 * Created by bidzis on 2016-03-22.
 */
public class DziennikPlanowConverter {


        public static DziennikPlanowDTO dziennikplanowOBdoDziennikPlanowowDTO(DziennikPlanowOB aDziennikPlanowOB){
            if(aDziennikPlanowOB == null) return null;

            return new DziennikPlanowDTO(aDziennikPlanowOB.getId(),aDziennikPlanowOB.getTechDate(),UzytkownikConverter.uzytOBdoUzytkDTO(aDziennikPlanowOB.getUzytkownik()),aDziennikPlanowOB.getDzienTygodnia(),aDziennikPlanowOB.getPlanOd(),aDziennikPlanowOB.getPlanDo());
        }
}
