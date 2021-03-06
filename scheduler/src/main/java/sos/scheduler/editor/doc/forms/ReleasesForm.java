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
package sos.scheduler.editor.doc.forms;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.jdom.Element;
import sos.scheduler.editor.app.IUnsaved;
import sos.scheduler.editor.app.IUpdateLanguage;
import sos.scheduler.editor.app.SOSJOEMessageCodes;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.doc.DocumentationDom;
import sos.scheduler.editor.doc.listeners.ReleasesListener;

public class ReleasesForm extends SOSJOEMessageCodes implements IUnsaved, IUpdateLanguage {
	
	
    private ReleasesListener listener     = null;

    private DocumentationDom dom          = null;

    private Group            group        = null;

    private Table            tReleases    = null;

    private Button           bNew         = null;

    private Button           bRemove      = null;

    private DocumentationForm _gui        = null;


    public ReleasesForm(Composite parent, 
    		int style, 
    		DocumentationDom dom, 
    		Element parentElement,
    		DocumentationForm gui) {
        super(parent, style);
        listener = new ReleasesListener(dom, parentElement);
        _gui = gui;
        initialize();
        setToolTipText();

        this.dom = dom;
        
        listener.fillReleases(tReleases);
       // fNote.setTitle("HHallo");        
       // fNote.setParams(dom, listener.getRelease(), "note", true, !listener.isNewRelease());
    }


    private void initialize() {
        createGroup();
        setSize(new Point(801, 462));
        setLayout(new FillLayout());      
        bRemove.setEnabled(false);
    }


    /**
     * This method initializes group
     */
    private void createGroup() {
        GridData gridData2 = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
        GridData gridData1 = new GridData(GridData.FILL, GridData.CENTER, false, false);
        GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, true, 1, 2);
        GridLayout gridLayout = new GridLayout(2, false);
        
        group = JOE_G_ReleaseForm_Releases.Control(new Group(this, SWT.NONE));
        group.setLayout(gridLayout); // Generated
       
        createComposite();
        createGroup1();
        
        tReleases = JOE_Tbl_ReleasesForm_Releases.Control(new Table(group, SWT.FULL_SELECTION | SWT.BORDER));
        tReleases.setHeaderVisible(true); // Generated
        tReleases.setLayoutData(gridData); // Generated
        tReleases.setLinesVisible(true); // Generated
        tReleases.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tReleases.getSelectionCount() > 0) {
                    if (listener.selectRelease(tReleases.getSelectionIndex())) {                       
                        bRemove.setEnabled(true);
                    } else
                        tReleases.deselectAll();
                }
            }
        });
        
        TableColumn idTableColumn = JOE_TCl_ReleasesForm_ID.Control(new TableColumn(tReleases, SWT.NONE));
        idTableColumn.setWidth(250); // Generated
        
        TableColumn tableColumn5 = JOE_TCl_ReleasesForm_Title.Control(new TableColumn(tReleases, SWT.NONE));
        tableColumn5.setWidth(90); // Generated
        
        TableColumn tableColumn6 = JOE_TCl_ReleasesForm_Created.Control(new TableColumn(tReleases, SWT.NONE));
        tableColumn6.setWidth(90); // Generated
        
        TableColumn tableColumn1 = JOE_TCl_ReleasesForm_Modified.Control(new TableColumn(tReleases, SWT.NONE));
        tableColumn1.setWidth(60);
        
        bNew = JOE_B_ReleasesForm_NewRelease.Control(new Button(group, SWT.NONE));
        bNew.setLayoutData(gridData1); // Generated
        bNew.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                listener.newRelease();                                
                tReleases.deselectAll();
                try{
//                listener.applyRelease("New Realease", 
//                		String.valueOf((listener.getRelease().getParentElement().getChildren("release", dom.getNamespace()).size()))
//                		, sos.util.SOSDate.getCurrentDateAsString("yyyy-mm-dd")
//                		, sos.util.SOSDate.getCurrentDateAsString("yyyy-mm-dd"), 
//                		null);
                    listener.applyRelease(JOE_B_ReleasesForm_NewRelease.label(), 
                    		String.valueOf((listener.getRelease().getParentElement().getChildren("release", dom.getNamespace()).size()))
                    		, sos.util.SOSDate.getCurrentDateAsString("yyyy-mm-dd")
                    		, sos.util.SOSDate.getCurrentDateAsString("yyyy-mm-dd"), 
                    		null);
                listener.fillReleases(tReleases);
                _gui.updateReleases();
                } catch (Exception ex){
                	System.out.println(ex.toString());
                }
            }
        });
        
        bRemove = JOE_B_ReleasesForm_RemoveRelease.Control(new Button(group, SWT.NONE));
        bRemove.setLayoutData(gridData2); // Generated
        bRemove.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (tReleases.getSelectionCount() > 0) {
                	listener.removeRelease(tReleases.getSelectionIndex());
                    //listener.removeRelease();
                    bRemove.setEnabled(false);
                    listener.fillReleases(tReleases);
                    //if(tReleases.getSelectionCount() > 0)
                    //	tReleases.remove(tReleases.getSelectionCount());
                    //tReleases.deselectAll();
                    Utils.setBackground(tReleases, true);
                    _gui.updateReleases();
                }
            }
        });
    }


    /**
     * This method initializes group1
     */
    private void createGroup1() {
        GridData gridData5 = new GridData(GridData.FILL, GridData.FILL, true, true, 4, 2);
        gridData5.widthHint = 486;
//        GridLayout gridLayout1 = new GridLayout(5, false);
    }


    /**
     * This method initializes composite
     */
    private void createComposite() {
    }



    public void apply() {
        applyRelease();
    }

    public boolean isUnsaved() {
    	return false;
    }

    public void setToolTipText() {
//
    }


    private void applyRelease() {
        listener.fillReleases(tReleases);
        bRemove.setEnabled(tReleases.getSelectionCount() > 0);
        Utils.setBackground(tReleases, true);

        
    }

} // @jve:decl-index=0:visual-constraint="10,10"
