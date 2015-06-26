/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ug.or.use.dvs.facade;

import ug.or.use.dvs.facade.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ug.or.use.dvs.model.Gender;

/**
 *
 * @author Dell
 */
@Stateless
public class GenderFacade extends AbstractFacade<Gender> {
    @PersistenceContext(unitName = "dvsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GenderFacade() {
        super(Gender.class);
    }
    
}
