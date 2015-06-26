/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.or.use.dvs.controller;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.util.Faces;

/**
 *
 * @author Dell
 */
@Named
@RequestScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private String navigateString = "";

    @Inject
    private ClientSessionController clientSessionController;
    private HttpServletRequest request;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    public void login() throws IOException, ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        request = Faces.getRequest();

        try {
            //JAAS Login
            request.login(username, password);

            //Evaluate LoggedIn User
            clientSessionController.evaluateCurrentUser(username);

            // gets the user principle and navigates to the appropriate page
            Principal principal = request.getUserPrincipal();
            if (request.isUserInRole("Administrator")) {
                navigateString = "/faces/pages/protected/administrator/AdministratorHome.xhtml";
            } else if (request.isUserInRole("DataEntrant")) {
                navigateString = "/faces/pages/protected/dataEntrant/DataEntrantHome.xhtml";
            } else if (request.isUserInRole("Investor")) {
                navigateString = "/faces/pages/protected/investor/InvestorHome.xhtml";
            }
            try {
                //  Logger.getLogger(LoginController.class.getName()).log(Level.INFO , " User ({0}) loging in #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, " IOException, Login Controller" + "Username : " + principal.getName(), ex);
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }
        } catch (ServletException e) {
            //Check whether there is someone already logged in. destroy the session and loggin again
            if(e.getMessage().contains("This is request has already been authenticated")){
                Faces.getSession().invalidate();
                login();
            }
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, e.toString());
            context.addMessage(null, new FacesMessage("Error!", "The username or password you provided does not match our records."));
        }
    }

    public void logout() {
        try {
            Faces.getRequest().logout();
            if (Faces.getSession() != null) {
                Faces.getSession().invalidate();
            }
            Faces.getContext().getExternalContext().redirect(Faces.getRequest().getContextPath() + "/faces/Login.xhtml");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
