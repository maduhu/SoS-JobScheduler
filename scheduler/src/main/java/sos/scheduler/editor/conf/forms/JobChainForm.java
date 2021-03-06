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
package sos.scheduler.editor.conf.forms;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;

import sos.scheduler.editor.app.Editor;
import sos.scheduler.editor.app.IUnsaved;
import sos.scheduler.editor.app.IUpdateLanguage;
import sos.scheduler.editor.app.MainWindow;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.SOSJOEMessageCodes;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.listeners.JobChainListener;

public class JobChainForm extends SOSJOEMessageCodes implements IUnsaved, IUpdateLanguage {

    private final String        conClassName       = "JobChainForm";
    final String                conMethodName      = conClassName + "::enclosing_method";
    @SuppressWarnings("unused")
    private final String        conSVNVersion      = "$Id: JobChainForm.java 18591 2012-12-03 12:38:32Z ur $";
    private static final Logger logger             = Logger.getLogger(JobChainForm.class);

    private JobChainListener    listener           = null;
    private Group               jobChainGroup      = null;
    private Label               chainNameLabel     = null;
    private Text                tName              = null;
    private Button              bRecoverable       = null;
    private Button              bVisible           = null;
    private Button              butDetails         = null;
    private ISchedulerUpdate    update             = null;
    private Button              butDistributed     = null;
    private Text                txtTitle           = null;
    private boolean             init               = false;
    private boolean             changeJobChainName = true;
    private Text                sMaxorders;

    public JobChainForm(Composite parent, int style, SchedulerDom dom, Element jobChain) {
        super(parent, style);
        init = true;
        listener = new JobChainListener(dom, jobChain);
        initialize();
        setToolTipText();
        fillChain(false, false);
        this.setEnabled(Utils.isElementEnabled("job_chain", dom, jobChain));
        init = false;

    }

    public void apply() {
    }

    public boolean isUnsaved() {
        return false;
    }

    private void initialize() {

        this.setLayout(new FillLayout());
        createGroup();
        setSize(new org.eclipse.swt.graphics.Point(676, 464));
        tName.setFocus();
    }

    /**
     * This method initializes group
     */
    private void createGroup() {
        jobChainGroup = new Group(this, SWT.NONE);
//      jobChainGroup.setText(Messages.getLabel("JobChain") + ": " + (listener.getChainName() != null ? listener.getChainName() : ""));
        String strJobChainName = "";
        if (listener.getChainName() != null)
        	strJobChainName = listener.getChainName();
        jobChainGroup.setText(JOE_M_JobChainForm_JobChain.params(strJobChainName));

        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginTop = 10;
        gridLayout.numColumns = 3;
        jobChainGroup.setLayout(gridLayout);
        
        chainNameLabel = JOE_L_JobChainForm_ChainName.Control(new Label(jobChainGroup, SWT.NONE));
        chainNameLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
        
        tName = JOE_T_JobChainForm_ChainName.Control(new Text(jobChainGroup, SWT.BORDER));
        tName.addVerifyListener(new VerifyListener() {
            public void verifyText(final VerifyEvent e) {
                if (!init) {// w�hrend der initialiserung sollen keine �berpr�fungen stattfinden
                    // String name = listener.getChainName();
                    e.doit = Utils.checkElement(listener.getChainName(), listener.get_dom(), Editor.JOB_CHAIN, null);
                    /*System.out.println(e.doit);
                    if(e.doit) {
                    	init = true; 
                    	name = name.substring(0, e.start) + e.text + name.substring(e.start,  e.end);
                    	tName.setText(name); 
                    	listener.setChainName(name);
                    	init = false;
                    }
                    */
                }
            }
        });
        tName.addFocusListener(new FocusAdapter() {
            public void focusGained(final FocusEvent e) {
                // tName.selectAll();
            }
        });
        final GridData gridData_4 = new GridData(GridData.FILL, GridData.BEGINNING, true, false, 1, 1);
        gridData_4.widthHint = 273;
        
        tName.setLayoutData(gridData_4);
        tName.setText(listener.getChainName());
        tName.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        tName.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
            public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
                if (init)
                    return;
                String newName = tName.getText().trim();
                boolean existname = Utils.existName(newName, listener.getChain(), "job_chain");
                if (existname)
                    tName.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
                else {
                    // getShell().setDefaultButton(bApplyChain);
                    tName.setBackground(null);
                }
                if (update != null)
//                  update.updateTreeItem("Job Chain: " + newName);
                	update.updateTreeItem(JOE_M_JobChainForm_JobChain.params(newName));
                listener.setChainName(newName);
                String strJobChainName = "";
                if (listener.getChainName() != null)
                	strJobChainName = listener.getChainName();
                jobChainGroup.setText(JOE_M_JobChainForm_JobChain.params(strJobChainName));
//                jobChainGroup.setText("Job Chain: " + (listener.getChainName() != null ? listener.getChainName() : ""));
                changeJobChainName = true;
            }
        });

        butDetails = JOE_B_JobChainForm_Parameter.Control(new Button(jobChainGroup, SWT.NONE));
        butDetails.setEnabled(true);
        butDetails.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                if (listener.get_dom().isChanged() && changeJobChainName) {
                    if (listener.get_dom().getFilename() == null) {
//                        MainWindow.message(Messages.getLabel("jobchain.must.saved"), SWT.ICON_WARNING);
                    	MainWindow.message(JOE_M_JobChainForm_SaveChain.label(), SWT.ICON_WARNING);
                    } else {
//                        MainWindow.message(Messages.getLabel("jobchain.name.changed"), SWT.ICON_WARNING);
                    	MainWindow.message(JOE_M_JobChainForm_ChainNameChanged.label(), SWT.ICON_WARNING);
                    }
                    return;
                }
                else {
                    changeJobChainName = false;
                }
                showDetails(null);
            }
        });

        @SuppressWarnings("unused")
		final Label titleLabel = JOE_L_JobChainForm_Title.Control(new Label(jobChainGroup, SWT.NONE));

        txtTitle = JOE_T_JobChainForm_Title.Control(new Text(jobChainGroup, SWT.BORDER));
        txtTitle.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                if (init)
                    return;
                listener.setTitle(txtTitle.getText());
            }
        });
        txtTitle.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));

//        Format
        new Label(jobChainGroup, SWT.NONE);

        @SuppressWarnings("unused")
		Label lblMaxOrders = JOE_L_JobChainForm_MaxOrders.Control(new Label(jobChainGroup, SWT.NONE));

        sMaxorders = JOE_T_JobChainForm_MaxOrders.Control(new Text(jobChainGroup, SWT.BORDER));
        sMaxorders.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent arg0) {
                if (init)
                    return;
                int maxOrders;
                try {
                    maxOrders = Integer.parseInt(sMaxorders.getText().trim());
                }
                catch (NumberFormatException e) {
                    maxOrders = 0;
                }
                listener.setMaxorders(maxOrders);
            }
        });
        GridData gd_sMaxorders = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
        gd_sMaxorders.minimumWidth = 60;
        sMaxorders.setLayoutData(gd_sMaxorders);

//        Format
        new Label(jobChainGroup, SWT.NONE);
        new Label(jobChainGroup, SWT.NONE);
        //		new Label(jobChainGroup, SWT.NONE);

        bRecoverable = JOE_B_JobChainForm_Recoverable.Control(new Button(jobChainGroup, SWT.CHECK));
        bRecoverable.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
        bRecoverable.setSelection(true);
        bRecoverable.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (init)
                    return;
                listener.setRecoverable(bRecoverable.getSelection());
            }
        });
        
//        Format
        new Label(jobChainGroup, SWT.NONE);
        new Label(jobChainGroup, SWT.NONE);

        butDistributed = JOE_B_JobChainForm_Distributed.Control(new Button(jobChainGroup, SWT.CHECK));
        butDistributed.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
        butDistributed.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                if (init)
                    return;
                listener.setDistributed(butDistributed.getSelection());
                // getShell().setDefaultButton(bApplyChain);
                // bApplyChain.setEnabled(true);
            }
        });
        butDistributed.setSelection(listener.isDistributed());
        
//        Format
        new Label(jobChainGroup, SWT.NONE);
        new Label(jobChainGroup, SWT.NONE);

        bVisible = JOE_B_JobChainForm_Visible.Control(new Button(jobChainGroup, SWT.CHECK));
        bVisible.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
        bVisible.setSelection(true);
        bVisible.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                if (init)
                    return;
                listener.setVisible(bVisible.getSelection());
                // getShell().setDefaultButton(bApplyChain);
                // bApplyChain.setEnabled(true);
            }
        });
        
//        Format
        new Label(jobChainGroup, SWT.NONE);

//        if (!listener.get_dom().isLifeElement()) {
//        }
    }

    private void fillChain(boolean enable, boolean isNew) {
        tName.setEnabled(true);
        bRecoverable.setEnabled(true);
        bVisible.setEnabled(true);

        tName.setText(listener.getChainName());
        txtTitle.setText(listener.getTitle());

        bRecoverable.setSelection(listener.getRecoverable());
        bVisible.setSelection(listener.getVisible());

        tName.setBackground(null);

        sMaxorders.setText(String.valueOf(listener.getMaxOrders()));
    }

    public void setISchedulerUpdate(ISchedulerUpdate update_) {
        update = update_;
    }

    private void showDetails(String state) {
        if (tName.getText() != null && tName.getText().length() > 0) {
            // OrdersListener ordersListener = new OrdersListener(listener.get_dom(), update);
            // String[] listOfOrders = ordersListener.getOrderIds();
            // DetailDialogForm detail = new DetailDialogForm(tName.getText(), listOfOrders);
            // detail.showDetails();
            boolean isLifeElement = listener.get_dom().isLifeElement() || listener.get_dom().isDirectory();

            if (state == null) {
                // DetailDialogForm detail = new DetailDialogForm(tName.getText(), listOfOrders, isLifeElement,
                // listener.get_dom().getFilename());
                DetailDialogForm detail = new DetailDialogForm(tName.getText(), isLifeElement, listener.get_dom().getFilename());

                detail.showDetails();
                detail.getDialogForm().setParamsForWizzard(listener.get_dom(), update);
            }
            else {
                // DetailDialogForm detail = new DetailDialogForm(tName.getText(), state, listOfOrders, isLifeElement,
                // listener.get_dom().getFilename());
                DetailDialogForm detail = new DetailDialogForm(tName.getText(), state, null, isLifeElement, listener.get_dom().getFilename());

                detail.showDetails();
                detail.getDialogForm().setParamsForWizzard(listener.get_dom(), update);
            }

        }
        else {
            MainWindow.message(getShell(), JOE_M_JobAssistent_CancelWizard.label(), SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
        }

    }

    public void setToolTipText() {
//
    }

} // @jve:decl-index=0:visual-constraint="10,10"
