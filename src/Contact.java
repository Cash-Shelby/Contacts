package ContactManager;
public class Contact {

    private  String phone;
    public String name;

    public Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
    }


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String setContactString(){
        return this.name + " | " + this.phone;

    }
}
