package Entity.User;

import Entity.Exception.UnauthorizedAccessException;

public interface Autentication {
    public void login() throws UnauthorizedAccessException;
    public void register();
    
}
