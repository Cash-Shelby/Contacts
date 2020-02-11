package ContactManager;
public class Contact {

    private  String phone;
    private String name;
    private String email;

    public Contact(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
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
    public String getEmail(){
        return  this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String setContactString(){
        return this.name + " | " + this.phone;

    }
}
