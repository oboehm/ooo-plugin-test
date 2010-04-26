package com.example;

import com.sun.star.awt.MessageBoxButtons;
import com.sun.star.awt.XMessageBox;
import com.sun.star.awt.XMessageBoxFactory;
import com.sun.star.awt.XWindowPeer;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XFrame;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

public class Tools {

    protected static void showMessage(XComponentContext xContext, String sTitle, String sMessage, boolean bQuery) {

        XMultiComponentFactory xmcf = xContext.getServiceManager();

        Object desktop;
        try {
            desktop = xmcf.createInstanceWithContext("com.sun.star.frame.Desktop", xContext);
            XDesktop xDesktop = (XDesktop) UnoRuntime.queryInterface(XDesktop.class, desktop);
            XFrame m_xFrame = xDesktop.getCurrentFrame();
            XWindowPeer xPeer = (XWindowPeer) UnoRuntime.queryInterface(XWindowPeer.class, m_xFrame
                    .getContainerWindow());
            showMessage(xContext, xPeer, sTitle, sMessage, bQuery);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    protected static boolean showMessage(XComponentContext xContext, XWindowPeer xParentPeer, String sTitle,
            String sMessage, boolean bQuery) {
        boolean bResult = false;

        if (xContext != null) {

            if (xParentPeer != null) {
                XMessageBoxFactory xMBFactory = null;
                XMessageBox xMB = null;
                try {
                    XMultiComponentFactory xFactory = xContext.getServiceManager();
                    if (xFactory != null)
                        xMBFactory = (XMessageBoxFactory) UnoRuntime.queryInterface(XMessageBoxFactory.class, xFactory
                                .createInstanceWithContext("com.sun.star.awt.Toolkit", xContext));

                    if (xMBFactory != null) {
                        if (bQuery) {
                            xMB = xMBFactory.createMessageBox(xParentPeer, new com.sun.star.awt.Rectangle(),
                                    "querybox", MessageBoxButtons.BUTTONS_YES_NO | MessageBoxButtons.DEFAULT_BUTTON_NO,
                                    sTitle, sMessage);
                        }

                        else {
                            xMB = xMBFactory.createMessageBox(xParentPeer, new com.sun.star.awt.Rectangle(),
                                    "errorbox", MessageBoxButtons.BUTTONS_OK, sTitle, sMessage);
                        }
                        if (xMB != null) {
                            short ret = xMB.execute();
                            if (bQuery && ret == (short) 3)
                                bResult = false;
                            else
                                bResult = true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    if (xMB != null)
                        disposeComponent(xMB);
                }
            }
        }

        return bResult;
    }

    /**
     * Disposes a component
     * 
     * @param oObject
     *            the object to dispose
     */
    public static void disposeComponent(Object oObject) {
        if (oObject != null) {
            XComponent xComp = (XComponent) UnoRuntime.queryInterface(XComponent.class, oObject);
            if (xComp != null)
                xComp.dispose();

        }
    }

}
