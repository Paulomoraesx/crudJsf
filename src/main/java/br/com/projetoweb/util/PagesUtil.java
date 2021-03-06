package br.com.projetoweb.util;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class PagesUtil {
    public static void redirectPage(String page) throws IOException {
        FacesContext context  = FacesContext.getCurrentInstance();
        String url = context.getExternalContext().getRequestContextPath();
        context.getExternalContext().redirect(url+"/pages/"+page+".xhtml");
    }

    public static void fecharDialog(String dialog) {
        getRequestContext().execute("PF('" + dialog + "').hide();");
    }

    private static RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }
}
