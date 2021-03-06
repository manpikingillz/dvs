package ug.or.use.dvs.controller;

import java.io.Serializable;
import java.util.Collection;
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
import ug.or.use.dvs.facade.UserGroupFacade;
import ug.or.use.dvs.model.UserGroup;

@Named("userGroupController")
@SessionScoped
public class UserGroupController implements Serializable {

    @EJB
    private ug.or.use.dvs.facade.UserGroupFacade ejbFacade;
    private List<UserGroup> items = null;
    private UserGroup selected;

    public UserGroupController() {
    }

    public UserGroup getSelected() {
        return selected;
    }

    public void setSelected(UserGroup selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserGroupFacade getFacade() {
        return ejbFacade;
    }

    public UserGroup prepareCreate() {
        selected = new UserGroup();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserGroupCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserGroupUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserGroupDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public Collection<UserGroup> getItems() {
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

    public UserGroup getUserGroup(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<UserGroup> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UserGroup> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

//    @FacesConverter(forClass = UserGroup.class)
    @FacesConverter(value = "userGroupControllerConverter")
    public static class UserGroupControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserGroupController controller = (UserGroupController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userGroupController");
            return controller.getUserGroup(getKey(value));
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
            if (object instanceof UserGroup) {
                UserGroup o = (UserGroup) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserGroup.class.getName()});
                return null;
            }
        }

    }

}
