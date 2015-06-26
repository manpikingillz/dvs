package ug.or.use.dvs.controller;

import com.sun.faces.el.FacesCompositeELResolver;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import org.omnifaces.util.Faces;
import ug.or.use.dvs.controller.util.JsfUtil;
import ug.or.use.dvs.controller.util.JsfUtil.PersistAction;
import ug.or.use.dvs.facade.InvestorFacade;
import ug.or.use.dvs.model.Investor;

@Named("investorController")
@SessionScoped
public class InvestorController implements Serializable {

    @EJB
    private ug.or.use.dvs.facade.InvestorFacade ejbFacade;
    private List<Investor> items = null;
    private Investor selected;
    private Investor investor = new Investor();
    private boolean createAccount;
    private List<Investor> filteredItems;

    public InvestorController() {
    }

    public Investor getSelected() {
        if(selected==null){
            selected = new Investor();
        }
        return selected;
    }

    public void setSelected(Investor selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InvestorFacade getFacade() {
        return ejbFacade;
    }

    public Investor prepareCreate() {
        selected = new Investor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InvestorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InvestorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InvestorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Investor> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Investor getInvestor(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Investor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Investor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void goToCreateAccount(){
        setCreateAccount(true);
        Faces.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index/investor/createAccount.xhtml?faces-redirect=true");
//        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml?faces-redirect=true");
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public boolean isCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(boolean createAccount) {
        this.createAccount = createAccount;
    }

    public List<Investor> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Investor> filteredItems) {
        this.filteredItems = filteredItems;
    }

    @FacesConverter(forClass = Investor.class)
    public static class InvestorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InvestorController controller = (InvestorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "investorController");
            return controller.getInvestor(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Investor) {
                Investor o = (Investor) object;
                return getStringKey(o.getPersonId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Investor.class.getName()});
                return null;
            }
        }

    }

}
