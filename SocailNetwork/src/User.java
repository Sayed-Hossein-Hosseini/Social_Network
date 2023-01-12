import java.util.regex.Pattern;

public class User {
    //-----------PERSONAL INFORMATION----------
    private String name;
    private String family;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;

    //------------GET FUNCTIONS----------------

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    //------------SET FUNCTIONS----------------

    public void setName(String name) throws InvalidInput {
        if (name.length() >= 5)
            this.name = name;
        else
            throw new InvalidInput();
    }

    public void setFamily(String family) throws InvalidInput {
        if (family.length() >= 5)
            this.family = family;
        else
            throw new InvalidInput();

    }

    public void setPhoneNumber(String phoneNumber) throws InvalidInput {
        boolean p = Pattern.matches("(0|\\+98)[9][0-9]{9}", phoneNumber);
        if (p) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new InvalidInput();
        }
    }

    public void setEmail(String email) throws InvalidInput {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        boolean e = Pattern.matches(emailRegex, email);
        if (e) {
            this.email = email;
        } else {
            throw new InvalidInput();
        }

    }

    public void setPassword(String password) throws InvalidInput {
        if (password.length() >= 5)
            this.password = password;
        else
            throw new InvalidInput();

    }

    public void setUserName(String userName) throws InvalidInput {
        if (userName.length() >= 5)
            this.userName = userName;
        else
            throw new InvalidInput();
    }

    //------------CONSTRUCTOR FUNCTIONS----------------

    public User(String name, String family, String email, String phoneNumber, String userName, String password) throws InvalidInput{
        this.setName(name);
        this.setFamily(family);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setUserName(userName);
        this.setPassword(password);
    }
}
