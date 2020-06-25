package pl.wsb.collection.controller;

public class SessionController {
    private String eMail;
    private String Password;
    private static final SessionController instance = new SessionController();

    public static SessionController get() {
        return instance;
    };
    public SessionController()
    {
    }
    public String geteMail() {
        return this.eMail;
    }
    public String getPassword() {
        return this.Password;
    }

    public void SeteMail(String eMail) {
        this.eMail = eMail;
    }

    public void SetPassword(String Password) {
        this.Password = Password;
    }
}
