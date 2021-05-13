package districtwisedetailsofwbbyamit.example.districtdetails;

public class DistrictModel {

    private  String DISTNAME;

    public DistrictModel ()
    {

    }

    public DistrictModel(String distname) {
        this.DISTNAME = distname;

    }

    public String getDISTNAME() {
        return DISTNAME;
    }

    public void setDISTNAME(String DISTNAME) {
        this.DISTNAME = DISTNAME;
    }
}

