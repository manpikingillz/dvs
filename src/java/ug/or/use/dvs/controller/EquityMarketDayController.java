package ug.or.use.dvs.controller;

import ug.or.use.dvs.model.EquityMarketDay;
import ug.or.use.dvs.controller.util.JsfUtil;
import ug.or.use.dvs.controller.util.JsfUtil.PersistAction;
import ug.or.use.dvs.facade.EquityMarketDayFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

@Named("equityMarketDayController")
@ViewScoped
public class EquityMarketDayController implements Serializable {

    @EJB
    private ug.or.use.dvs.facade.EquityMarketDayFacade ejbFacade;
    private List<EquityMarketDay> items = null;
    private EquityMarketDay selected;

    public EquityMarketDayController() {
    }

    public EquityMarketDay getSelected() {
        if(selected == null){
            initializeEmbeddableKey();
            selected = new EquityMarketDay();
        }
        return selected;
    }

    public void setSelected(EquityMarketDay selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EquityMarketDayFacade getFacade() {
        return ejbFacade;
    }

    public EquityMarketDay prepareCreate() {
        selected = new EquityMarketDay();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EquityMarketDayCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EquityMarketDayUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EquityMarketDayDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EquityMarketDay> getItems() {
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

    public EquityMarketDay getEquityMarketDay(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<EquityMarketDay> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EquityMarketDay> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = EquityMarketDay.class)
    public static class EquityMarketDayControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EquityMarketDayController controller = (EquityMarketDayController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "equityMarketDayController");
            return controller.getEquityMarketDay(getKey(value));
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
            if (object instanceof EquityMarketDay) {
                EquityMarketDay o = (EquityMarketDay) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EquityMarketDay.class.getName()});
                return null;
            }
        }

    }

}
