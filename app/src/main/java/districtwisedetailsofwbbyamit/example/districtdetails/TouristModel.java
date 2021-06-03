package districtwisedetailsofwbbyamit.example.districtdetails;

public class TouristModel {

    private  String Name,Details,Image;

    public TouristModel ()
    {

    }

    public TouristModel(String Name,String Details,String Image) {
        this.Name = Name;
        this.Details=Details;
        this.Image = Image;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
