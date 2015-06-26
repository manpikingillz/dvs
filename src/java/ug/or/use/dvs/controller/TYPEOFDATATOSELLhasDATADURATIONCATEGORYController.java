package ug.or.use.dvs.controller;

import ug.or.use.dvs.model.TYPEOFDATATOSELLhasDATADURATIONCATEGORY;
import ug.or.use.dvs.controller.util.JsfUtil;
import ug.or.use.dvs.controller.util.JsfUtil.PersistAction;
import ug.or.use.dvs.facade.TYPEOFDATATOSELLhasDATADURATIONCATEGORYFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import ug.or.use.dvs.model.InvestorGeneralDataRequest;
import ug.or.use.dvs.model.InvestorRequestedData;
import ug.or.use.dvs.model.User;

@Named("tYPEOFDATATOSELLhasDATADURATIONCATEGORYController")
@SessionScoped
public class TYPEOFDATATOSELLhasDATADURATIONCATEGORYController implements Serializable {

    @EJB
    private ug.or.use.dvs.facade.TYPEOFDATATOSELLhasDATADURATIONCATEGORYFacade ejbFacade;
    @EJB
    private ug.or.use.dvs.facade.InvestorGeneralDataRequestFacade investorGeneralDataRequestFacade;
    private List<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> items = null;
    private List<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> selectedItems = null;
    private TYPEOFDATATOSELLhasDATADURATIONCATEGORY selected;

    @Inject
    private ClientSessionController clientSessionController;

    public TYPEOFDATATOSELLhasDATADURATIONCATEGORYController() {
    }

    public TYPEOFDATATOSELLhasDATADURATIONCATEGORY getSelected() {
        if (selected == null) {
            selected = new TYPEOFDATATOSELLhasDATADURATIONCATEGORY();
        }
        return selected;
    }

    public void setSelected(TYPEOFDATATOSELLhasDATADURATIONCATEGORY selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TYPEOFDATATOSELLhasDATADURATIONCATEGORYFacade getFacade() {
        return ejbFacade;
    }

    public void saveRequest() {
        /////////////////////////////Save the general data request made by the investor//////////////////////
        float totalAmount = 0;
        //get the person - logged in user;
        User loggedInUser = clientSessionController.getCurrentUser();

        //Get the date today
        Date today = Calendar.getInstance().getTime();

        //calculate the amount
        for (int i = 0; i < getItemsAvailableSelectMany().size(); i++) {
            totalAmount += getItemsAvailableSelectMany().get(i).getPrice();
        }
        //active status - default is not active
        boolean activeStatus = false;

        InvestorGeneralDataRequest generalDataRequest = new InvestorGeneralDataRequest();
        generalDataRequest.setPersonId(loggedInUser);
        generalDataRequest.setRequestDate(today);
        generalDataRequest.setAmount(totalAmount);
        generalDataRequest.setActiveStatus(activeStatus);
        investorGeneralDataRequestFacade.create(generalDataRequest);

        ///////////////////////////////Save the data requested for//////////////////////////////
        //Get the data request for which requested data we wish to save - this is the general request we have just saved up here...:)
        InvestorGeneralDataRequest investorGeneralDataRequest = investorGeneralDataRequestFacade.getLastEntityRow("id");

        //Save requested data
        InvestorRequestedData investorRequestedData = new InvestorRequestedData();
        for (int i = 0; i < getItemsAvailableSelectMany().size(); i++) {
            investorRequestedData.setInvestorGeneralDataRequest(investorGeneralDataRequest);
            investorRequestedData.setTYPEOFDATATOSELLhasDATADURATIONCATEGORY(getItemsAvailableSelectMany().get(i));
            investorRequestedData.setActiveStatus(true);
            investorRequestedData.setRequestDate(today);
        }
        
        //Show a success message
        JsfUtil.addErrorMessage("You have successfully subscribed for the data");
    }

    public TYPEOFDATATOSELLhasDATADURATIONCATEGORY prepareCreate() {
        selected = new TYPEOFDATATOSELLhasDATADURATIONCATEGORY();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TYPEOFDATATOSELLhasDATADURATIONCATEGORYCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TYPEOFDATATOSELLhasDATADURATIONCATEGORYUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TYPEOFDATATOSELLhasDATADURATIONCATEGORYDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> getSelectedItems() {
        if(selectedItems == null){
            selectedItems = new ArrayList<>();
        }
        return selectedItems;
    }

    public void setSelectedItems(List<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> selectedItems) {
        this.selectedItems = selectedItems;
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

    public TYPEOFDATATOSELLhasDATADURATIONCATEGORY getTYPEOFDATATOSELLhasDATADURATIONCATEGORY(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TYPEOFDATATOSELLhasDATADURATIONCATEGORY.class)
    public static class TYPEOFDATATOSELLhasDATADURATIONCATEGORYControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TYPEOFDATATOSELLhasDATADURATIONCATEGORYController controller = (TYPEOFDATATOSELLhasDATADURATIONCATEGORYController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tYPEOFDATATOSELLhasDATADURATIONCATEGORYController");
            return controller.getTYPEOFDATATOSELLhasDATADURATIONCATEGORY(getKey(value));
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
            if (object instanceof TYPEOFDATATOSELLhasDATADURATIONCATEGORY) {
                TYPEOFDATATOSELLhasDATADURATIONCATEGORY o = (TYPEOFDATATOSELLhasDATADURATIONCATEGORY) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TYPEOFDATATOSELLhasDATADURATIONCATEGORY.class.getName()});
                return null;
            }
        }

    }

}
