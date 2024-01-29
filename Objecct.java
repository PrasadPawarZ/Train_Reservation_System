package OnlineReservationSystem;

import java.io.Serializable;

public class Objecct implements Serializable {
    String finalName, finalSrcStation, finalDesStation, finalTime, finalPrice;
    Long finalPrnNo, finalPhoneNo;
    public Boolean isTicked = true;
    public void setValues(String Name,String SrcStation,String DesStation,String Time,Long PrnNo,Long PhoneNo, String Price){
        finalName = Name;
        finalSrcStation = SrcStation;
        finalDesStation = DesStation;
        finalTime = Time;
        finalPrnNo = PrnNo;
        finalPhoneNo = PhoneNo;
        finalPrice = Price;
    }
}
