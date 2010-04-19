/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.xwiki.xoo;

import com.sun.star.uno.XComponentContext;

/**
 * A singleton with the global components
 * 
 * @version $Id$
 * @since 1.0 M
 */

public class XWikiExtension
{

    static private XWikiExtension _instance = null;

    /* the connection settings used for connecting at the server */
    private ConnectionSettings settings;

    /* the status of the current extension */
    private ExtensionStatus status;

    /* the OpenOffice component context */
    private XComponentContext m_xContext;

    private String imagesDirUrl = null;

    /**
     * Constructor.
     */
    private XWikiExtension()
    {
        status = new ExtensionStatus();
        settings = new ConnectionSettings();
    }

    /**
     * @return an instance of this class
     */
    public static synchronized XWikiExtension getInstance()
    {
        if (null == _instance) {
            _instance = new XWikiExtension();
        }
        return _instance;
    }


    /**
     * @return the connection settings of the current extension
     */
    public ConnectionSettings getSettings()
    {
        return settings;
    }

    /**
     * @return the extension status
     */
    public ExtensionStatus getExtensionStatus()
    {
        return status;
    }

    /**
     * @return the extension status
     */
    public void setExtensionStatus(ExtensionStatus status)
    {
        this.status = status;
    }

    /**
     * Sets the OpenOffice component context
     * 
     * @param context the OpenOffice component context
     */
    public void setComponentContext(XComponentContext context)
    {
        this.m_xContext = context;
    }

    /**
     * @return the OpenOffice component context
     */
    public XComponentContext getComponentContext()
    {
        return this.m_xContext;
    }

    public void setImagesDirUrl(String imagesUrl)
    {
        this.imagesDirUrl = imagesUrl;
    }

    public String getImagesDirUrl()
    {
        return this.imagesDirUrl;
    }

}
