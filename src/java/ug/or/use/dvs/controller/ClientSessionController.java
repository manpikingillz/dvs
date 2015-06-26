/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ug.or.use.dvs.controller;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ug.or.use.dvs.facade.UserFacade;
import ug.or.use.dvs.model.User;


/**
 *
 * @author Realtech 5
 */
@Named
@SessionScoped
public class ClientSessionController implements Serializable {

    private @Inject UserFacade userFacade;
    private User currentUser;
    /**
     * Creates a new instance of ClientSessionController
     */
    public ClientSessionController() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public void evaluateCurrentUser(String username){
        currentUser = userFacade.getEntityRowGivenColumnValue("username", username);
    }
}
