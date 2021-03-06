/**
 * Copyright (C) 2014 BigLoupe http://bigloupe.github.io/SoS-JobScheduler/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
/********************************************************* begin of preamble
**
** Copyright (C) 2003-2012 Software- und Organisations-Service GmbH. 
** All rights reserved.
**
** This file may be used under the terms of either the 
**
**   GNU General Public License version 2.0 (GPL)
**
**   as published by the Free Software Foundation
**   http://www.gnu.org/licenses/gpl-2.0.txt and appearing in the file
**   LICENSE.GPL included in the packaging of this file. 
**
** or the
**  
**   Agreement for Purchase and Licensing
**
**   as offered by Software- und Organisations-Service GmbH
**   in the respective terms of supply that ship with this file.
**
** THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
** IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
** THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
** PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
** BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
** CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
** SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
** INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
** CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
** ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
** POSSIBILITY OF SUCH DAMAGE.
********************************************************** end of preamble*/
package sos.scheduler.editor.doc.listeners;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.jdom.Element;

import sos.scheduler.editor.app.Editor;
import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Options;
import sos.scheduler.editor.app.TreeData;
import sos.scheduler.editor.app.Utils;

import sos.scheduler.editor.doc.DocumentationDom;
import sos.scheduler.editor.doc.IUpdateTree;
import sos.scheduler.editor.doc.forms.ApplicationsForm;
import sos.scheduler.editor.doc.forms.ConnectionsForm;
import sos.scheduler.editor.doc.forms.DatabasesForm;
import sos.scheduler.editor.doc.forms.FilesForm;
import sos.scheduler.editor.doc.forms.JobForm;
import sos.scheduler.editor.doc.forms.JobScriptForm;
import sos.scheduler.editor.doc.forms.NoteForm;
import sos.scheduler.editor.doc.forms.ParamsForm;
import sos.scheduler.editor.doc.forms.PayloadForm;
import sos.scheduler.editor.doc.forms.ProcessForm;
import sos.scheduler.editor.doc.forms.ProfilesForm;
import sos.scheduler.editor.doc.forms.ReleasesForm;
import sos.scheduler.editor.doc.forms.ReleaseForm;
import sos.scheduler.editor.doc.forms.AuthorsForm;
import sos.scheduler.editor.doc.forms.ResourcesForm;
import sos.scheduler.editor.doc.forms.ScriptForm;
import sos.scheduler.editor.doc.forms.SectionsForm;
import sos.scheduler.editor.doc.forms.SettingForm;
import sos.scheduler.editor.doc.forms.DocumentationForm;
import sos.scheduler.editor.doc.forms.DatabaseResourcesForm;


public class DocumentationListener implements IUpdateTree {
	
	
    private DocumentationDom _dom;

    private TreeItem         _profiles;

    private TreeItem         _connections;

    private static ArrayList _IDs;

    private DocumentationForm _gui;
    
    public static String PREFIX_DATABSE =  "Database: ";

    public DocumentationListener(DocumentationForm gui, DocumentationDom dom) {
    	_gui = gui;
        _dom = dom;
    }


    public void fillTree(Tree tree) {
        tree.removeAll();
        Utils.setResetElement(_dom.getRoot());
        Element desc = _dom.getRoot();

        TreeItem item = new TreeItem(tree, SWT.NONE);
        item.setText("Job");
        item.setData(new TreeData(Editor.DOC_JOB, desc.getChild("job", _dom.getNamespace()), Options
                .getDocHelpURL("job")));

        TreeItem item2 = new TreeItem(item, SWT.NONE);
        item2.setText("Process");
        item2.setData(new TreeData(Editor.DOC_PROCESS, desc.getChild("job", _dom.getNamespace()), Options
                .getDocHelpURL("process")));
        item2 = new TreeItem(item, SWT.NONE);
        item2.setText("Script");
        item2.setData(new TreeData(Editor.DOC_SCRIPT, desc.getChild("job", _dom.getNamespace()), Options
                .getDocHelpURL("script")));
        item2 = new TreeItem(item, SWT.NONE);
        item2.setText("Monitor");
        item2.setData(new TreeData(Editor.DOC_MONITOR, desc.getChild("job", _dom.getNamespace()), Options
                .getDocHelpURL("monitor")));
        item.setExpanded(true);

        item = new TreeItem(tree, SWT.NONE);
        item.setText("Releases");
        item.setData(new TreeData(Editor.DOC_RELEASES, desc.getChild("releases", _dom.getNamespace()), Options
                .getDocHelpURL("releases")));
        
        treeFillReleases(item, desc.getChild("releases", _dom.getNamespace()));
        item.setExpanded(true);
        
        item = new TreeItem(tree, SWT.NONE);
        item.setText("Resources");
        item.setData(new TreeData(Editor.DOC_RESOURCES, desc, Options.getDocHelpURL("resources")));
        item2 = new TreeItem(item, SWT.NONE);
        item2.setText("Files");
        item2.setData(new TreeData(Editor.DOC_FILES, desc, Options.getDocHelpURL("files")));
        item2 = new TreeItem(item, SWT.NONE);
        item2.setText("Databases");
        item2.setData(new TreeData(Editor.DOC_DATABASES, desc, Options.getDocHelpURL("databases")));
        item.setExpanded(true);
        treeFillDatabaseResources(item2, desc.getChild("resources", _dom.getNamespace()));

        item = new TreeItem(tree, SWT.NONE);
        item.setText("Configuration");
        item.setData(new TreeData(Editor.DOC_CONFIGURATION, desc.getChild("configuration", _dom.getNamespace()),
                Options.getDocHelpURL("configuration")));
        item2 = new TreeItem(item, SWT.NONE);
        item2.setText("Parameters");
        item2.setData(new TreeData(Editor.DOC_PARAMS, desc.getChild("configuration", _dom.getNamespace()), Options
                .getDocHelpURL("parameters")));
        item.setExpanded(true);
        item2 = new TreeItem(item, SWT.NONE);
        item2.setText("Payload");
        item2.setData(new TreeData(Editor.DOC_PAYLOAD, desc.getChild("configuration", _dom.getNamespace()), Options
                .getDocHelpURL("payload")));
        item2 = new TreeItem(item, SWT.NONE);
        item2.setText("Settings");
        item2.setData(new TreeData(Editor.DOC_SETTINGS, desc.getChild("configuration", _dom.getNamespace()), Options
                .getDocHelpURL("settings")));

        _profiles = new TreeItem(item2, SWT.NONE);
        item2.setExpanded(true);
        _profiles.setText("Profiles");
        _profiles.setData(new TreeData(Editor.DOC_PROFILES, desc.getChild("configuration", _dom.getNamespace()),
                Options.getDocHelpURL("profiles")));
        fillProfiles();

        _connections = new TreeItem(item2, SWT.NONE);
        _connections.setText("Connections");
        _connections.setData(new TreeData(Editor.DOC_CONNECTIONS, desc.getChild("configuration", _dom.getNamespace()),
                Options.getDocHelpURL("connections")));
        fillConnections();

        item = new TreeItem(tree, SWT.NONE);
        item.setText("Documentation");
        /*if(desc.getChild("documentation", desc.getNamespace()) == null)
        	desc.addContent(new Element("documentation", desc.getNamespace())).addContent(new Element("div", org.jdom.Namespace.getNamespace("http://www.w3.org/1999/xhtml")));
        else if(desc.getChild("documentation", desc.getNamespace()).getChild("div",org.jdom.Namespace.getNamespace("http://www.w3.org/1999/xhtml"))==null)
        	desc.getChild("documentation", desc.getNamespace()).addContent(new Element("div", org.jdom.Namespace.getNamespace("http://www.w3.org/1999/xhtml")));
        */
        item.setData(new TreeData(Editor.DOC_DOCUMENTATION, desc, Options.getDocHelpURL("documentation")));
        

    }


    private Element getSettings() {
        Element configuration = _dom.getRoot().getChild("configuration", _dom.getNamespace());
        return configuration.getChild("settings", _dom.getNamespace());
    }


    public void fillProfiles() {
        Element settings = getSettings();
        _profiles.removeAll();
        if (settings != null) {
            for (Iterator it = settings.getChildren("profile", _dom.getNamespace()).iterator(); it.hasNext();) {
                Element element = (Element) it.next();
                TreeItem item = new TreeItem(_profiles, SWT.NONE);
                String name = Utils.getAttributeValue("name", element);
                item.setText("Sections [Profile: " + (name != null ? name : ProfilesListener.defaultName) + "]");
                item.setData(new TreeData(Editor.DOC_SECTIONS, element, Options.getDocHelpURL("sections")));
                _profiles.setExpanded(true);
                fillSections(item, element, false);
            }
        }
    }


    public void fillConnections() {
        Element settings = getSettings();
        _connections.removeAll();
        if (settings != null) {
            for (Iterator it = settings.getChildren("connection", _dom.getNamespace()).iterator(); it.hasNext();) {
                Element element = (Element) it.next();
                TreeItem item = new TreeItem(_connections, SWT.NONE);
                String name = Utils.getAttributeValue("name", element);
                item.setText("Applications [Connection: " + (name != null ? name : ConnectionsListener.defaultName) + "]");
                item.setData(new TreeData(Editor.DOC_APPLICATIONS, element, Options.getDocHelpURL("applications")));
                _connections.setExpanded(true);
                fillApplications(item, element, false);
            }
        }
    }


    public void fillApplications(TreeItem parent, Element element, boolean expand) {
        parent.removeAll();
        for (Iterator it = element.getChildren("application", _dom.getNamespace()).iterator(); it.hasNext();) {
            Element section = (Element) it.next();
            TreeItem item = new TreeItem(parent, SWT.NONE);
            item.setText("Sections [Appl.: " + Utils.getAttributeValue("name", section) + "]");
            item.setData(new TreeData(Editor.DOC_SECTIONS, section, Options.getDocHelpURL("sections")));
            parent.setExpanded(expand);
            fillSections(item, section, false);
        }
    }


    public void fillSections(TreeItem parent, Element element, boolean expand) {
        parent.removeAll();
        for (Iterator it = element.getChildren("section", _dom.getNamespace()).iterator(); it.hasNext();) {
            Element section = (Element) it.next();
            TreeItem item = new TreeItem(parent, SWT.NONE);
            item.setText("Settings [Section: " + Utils.getAttributeValue("name", section) + "]");
            item.setData(new TreeData(Editor.DOC_SECTION_SETTINGS, section, Options.getDocHelpURL("setting")));
            parent.setExpanded(expand);
        }
    }


    public boolean treeSelection(Tree tree, Composite c) {
        try {
            if (tree.getSelectionCount() > 0) {

                // dispose the old form
                Control[] children = c.getChildren();
                for (int i = 0; i < children.length; i++) {
                    if (!Utils.applyFormChanges(children[i]))
                        return false;
                    children[i].dispose();
                }

                TreeItem item = tree.getSelection()[0];
                TreeData data = (TreeData) item.getData();

                _dom.setInit(true);

                switch (data.getType()) {
                    case Editor.DOC_JOB:
                        new JobForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_PROCESS:
                        new ProcessForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_SCRIPT:
                        new JobScriptForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_MONITOR:
                        ScriptForm form = new ScriptForm(c, SWT.NONE);
                        form.setTitle("Monitor");
                        form.setParams(_dom, data.getElement(), Editor.DOC_MONITOR);
                        form.init(true, true);
                        break;
                    case Editor.DOC_RELEASES:
                        new ReleasesForm(c, SWT.NONE, _dom, data.getElement(), _gui);
                        break;
                    case Editor.DOC_RELEASE:
                        new ReleaseForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                        
                    case Editor.DOC_RELEASE_AUTHOR:
                        new AuthorsForm(c, SWT.NONE, _dom, data.getElement());
                        break;                                            
                    case Editor.DOC_RESOURCES:
                        new ResourcesForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_DATABASES:
                        new DatabasesForm(c, SWT.NONE, _dom, data.getElement(), _gui);
                        break;
                    case Editor.DOC_DATABASES_RESOURCE:
                        new DatabaseResourcesForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_FILES:
                        new FilesForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_DOCUMENTATION:
                        NoteForm doc = new NoteForm(c, SWT.NONE, Editor.DOC_DOCUMENTATION);
                        doc.setTitle("Note");
                         doc.setParams(_dom, data.getElement(), "documentation", false);
                        break;
                    case Editor.DOC_CONFIGURATION:
                        NoteForm note = new NoteForm(c, SWT.NONE, Editor.DOC_CONFIGURATION);
                        note.setTitle("Configuration Note");
                        note.setParams(_dom, data.getElement(), "note", true);
                        break;
                    case Editor.DOC_PARAMS:
                        new ParamsForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_PAYLOAD:
                        new PayloadForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_SETTINGS:
                      	SettingsListener listener = new SettingsListener(_dom, data.getElement());
                        listener.setSettings();
                        NoteForm settings = new NoteForm(c, SWT.NONE, Editor.DOC_SETTINGS);
                        settings.setSettingsListener(listener);
                        settings.setTitle("Settings Note");
                        settings.setParams(_dom, listener.getSettingsElement(), "note", true);
                        break;
                    case Editor.DOC_PROFILES:
                        new ProfilesForm(c, SWT.NONE, _dom, data.getElement(), this);
                        break;
                    case Editor.DOC_SECTIONS:
                        new SectionsForm(c, SWT.NONE, _dom, data.getElement(), this, item);
                        break;
                    case Editor.DOC_SECTION_SETTINGS:
                        new SettingForm(c, SWT.NONE, _dom, data.getElement());
                        break;
                    case Editor.DOC_CONNECTIONS:
                        new ConnectionsForm(c, SWT.NONE, _dom, data.getElement(), this);
                        break;
                    case Editor.DOC_APPLICATIONS:
                        new ApplicationsForm(c, SWT.NONE, _dom, data.getElement(), this, item);
                        break;
                    default:
                        System.out.println("no form found for " + item.getText());
                }

                c.layout();

            }
        } catch (Exception e) {
            e.printStackTrace();
            MainWindow.message(e.getMessage(), SWT.ICON_ERROR);
        }
        _dom.setInit(false);
        return true;
    }


    public static String[] getIDs(DocumentationDom dom, String ownID) {
        ArrayList ids = new ArrayList();
        _IDs = getIDs(ids, dom.getRoot(), ownID);
        return (String[]) _IDs.toArray(new String[] {});
    }


    private static ArrayList getIDs(ArrayList list, Element element, String ownID) {
        for (Iterator it = element.getChildren().iterator(); it.hasNext();) {
            Element e = (Element) it.next();
            if (e.getNamespace().equals(element.getNamespace())) {
                String id = e.getAttributeValue("id");
                if (id != null && !id.equals(ownID))
                    list.add(e.getName() + ": " + id);
                getIDs(list, e, ownID);
            }
        }
        return list;
    }


    public static String getID(String name) {
        int index = name.indexOf(':');
        if (index < 0)
            return name;
        else
            return name.substring(index + 2);
    }


    public static String getIDName(String id) {
        if (id.equals(""))
            return id;

        for (Iterator it = _IDs.iterator(); it.hasNext();) {
            String name = (String) it.next();
            if (getID(name).equals(id))
                return name;
        }

        // not found
        return "unknown: " + id;
    }


    public static void setCheckbox(Combo c, String[] values, String value) {
        c.setItems(values);
        c.setText(getIDName(value));
    }
    
    public void treeFillReleases(TreeItem parent, Element elem) {
    	parent.removeAll();
    	java.util.List listOfRelease = elem.getChildren("release", elem.getNamespace());
    	//parent.setExpanded(true);
    	for(int i = 0; i < listOfRelease.size(); i++) {
    		Element release = (Element)listOfRelease.get(i);
    		
    		//Release
    		TreeItem item = new TreeItem(parent, SWT.NONE);
    		item.setText("Release: " + Utils.getAttributeValue("id", release));
    		item.setData(new TreeData(Editor.DOC_RELEASE, release, Options.getDocHelpURL("releases")));
    		
    		
    		//Autoren
    		TreeItem itemAuthor = new TreeItem(item, SWT.NONE);
    		itemAuthor.setText("Author");    		
    		itemAuthor.setData(new TreeData(Editor.DOC_RELEASE_AUTHOR, release, Options.getDocHelpURL("releases")));
    		
    		item.setExpanded(true);
    		itemAuthor.setExpanded(true);    		
    	}
    	
    }
    
    public void treeFillDatabaseResources(TreeItem parent, Element resources) {
    	
    	if(resources == null )
    		return;
    	parent.removeAll();

    	java.util.List list = resources.getChildren("database", resources.getNamespace());
    	for(int i = 0; i< list.size(); i++) {
    		Element database = (Element)list.get(i);
    		TreeItem itemDatabase = new TreeItem(parent, SWT.NONE);
    		itemDatabase.setText(PREFIX_DATABSE +  Utils.getAttributeValue("name", database));    		
    		itemDatabase.setData(new TreeData(Editor.DOC_DATABASES_RESOURCE, database, Options.getDocHelpURL("databases")));
    		
    		parent.setExpanded(true);
    		itemDatabase.setExpanded(true);
    	}
    	
    }
    

}
