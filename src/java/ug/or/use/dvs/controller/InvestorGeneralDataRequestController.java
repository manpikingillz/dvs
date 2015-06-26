package ug.or.use.dvs.controller;

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
import ug.or.use.dvs.controller.util.JsfUtil;
import ug.or.use.dvs.controller.util.JsfUtil.PersistAction;
import ug.or.use.dvs.facade.InvestorGeneralDataRequestFacade;
import ug.or.use.dvs.model.InvestorGeneralDataRequest;

@Named("investorGeneralDataRequestController")
@SessionScoped
public class InvestorGeneralDataRequestController implements Serializable {

    @EJB
    private ug.or.use.dvs.facade.InvestorGeneralDataRequestFacade ejbFacade;
    private List<InvestorGeneralDataRequest> items = null;
    private InvestorGeneralDataRequest selected;

    public InvestorGeneralDataRequestController() {
    }

    public InvestorGeneralDataRequest getSelected() {
        if (selected == null) {
            selected = new InvestorGeneralDataRequest();
        }
        return selected;
    }

    public void setSelected(InvestorGeneralDataRequest selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InvestorGeneralDataRequestFacade getFacade() {
        return ejbFacade;
    }

    public InvestorGeneralDataRequest prepareCreate() {
        selected = new InvestorGeneralDataRequest();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InvestorGeneralDataRequestCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InvestorGeneralDataRequestUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InvestorGeneralDataRequestDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<InvestorGeneralDataRequest> getItems() {
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

    public InvestorGeneralDataRequest getInvestorGeneralDataRequest(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<InvestorGeneralDataRequest> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<InvestorGeneralDataRequest> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }


    @FacesConverter(forClass = InvestorGeneralDataRequest.class)
    public static class InvestorGeneralDataRequestControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InvestorGeneralDataRequestController controller = (InvestorGeneralDataRequestController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "investorGeneralDataRequestController");
            return controller.getInvestorGeneralDataRequest(getKey(value));
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
            if (object instanceof InvestorGeneralDataRequest) {
                InvestorGeneralDataRequest o = (InvestorGeneralDataRequest) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), InvestorGeneralDataRequest.class.getName()});
                return null;
            }
        }

    }

}
