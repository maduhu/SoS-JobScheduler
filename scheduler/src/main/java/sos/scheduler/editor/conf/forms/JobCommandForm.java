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

import javax.xml.transform.TransformerException;
import sos.scheduler.editor.app.Editor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;
import org.jdom.JDOMException;
import sos.scheduler.editor.app.IUnsaved;
import sos.scheduler.editor.app.IUpdateLanguage;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.SOSJOEMessageCodes;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.listeners.JobCommandListener;

public class JobCommandForm extends SOSJOEMessageCodes implements IUnsaved, IUpdateLanguage {

	private JobCommandListener listener             = null;

	private Group              jobsAndOrdersGroup   = null;

	private SashForm           sashForm             = null;

	private Group              gDescription         = null;

	private Label              lblJob               = null;

	private Text               tTitle               = null;

	private Combo              tState               = null;

	private Text               tStartAt             = null;

	private Text               tPriority            = null;

	private Combo              cJobchain            = null;

	private Button             bReplace             = null;

	private Text               tJob                 = null;
	
	private int                type                 = -1;

	private Label              jobchainLabel        = null;

	private Label              priorityLabel        = null;

	private Label              titleLabel           = null;
	
	private Label              stateLabel           = null;

	private Label              replaceLabel         = null;
	
	private Combo              cboEndstate          = null; 

	private Label              endStateLabel        = null;
	
	private boolean            event                = false;
	

	public JobCommandForm(Composite parent, int style, SchedulerDom dom, Element command, ISchedulerUpdate main)
	throws JDOMException, TransformerException {
		super(parent, style);

		listener = new JobCommandListener(dom, command, main);
		if(command.getName().equalsIgnoreCase("start_job")) {
			type = Editor.JOB;
		} else {
			type = Editor.COMMANDS;
		}
		initialize();
		setToolTipText();			
		event = true;
 		if (command.getParentElement() != null ){        	
			this.jobsAndOrdersGroup.setEnabled(Utils.isElementEnabled("job", dom, command.getParentElement()));        	
		}

	}


	public void apply() {
		//if (isUnsaved())
			//addParam();
		//addCommand();
	}


	public boolean isUnsaved() {
		return false;
	}


	private void initialize() {
		this.setLayout(new FillLayout());
		createGroup();	
	}

/**
	 * This method initializes group
	 */
	private void createGroup_() {
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.makeColumnsEqualWidth = true;
		gridLayout2.numColumns = 1;
		jobsAndOrdersGroup = new Group(this, SWT.NONE);
		jobsAndOrdersGroup.setText("Commands for Job: " + listener.getName() + (listener.isDisabled() ? " (Disabled)" : "")); //TODO lang "Commands for Job: "...
		jobsAndOrdersGroup.setLayout(gridLayout2);
		GridData gridData18 = new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true, 1, 2);
		sashForm = new SashForm(jobsAndOrdersGroup, SWT.NONE);
		sashForm.setOrientation(512);
		sashForm.setLayoutData(gridData18);
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 2;
		gDescription = new Group(sashForm, SWT.NONE);
		gDescription.setText("Jobs and Orders"); //TODO lang "Jobs and Orders"
		gDescription.setLayout(gridLayout3);
		

		jobchainLabel = new Label(gDescription, SWT.NONE);
		final GridData gridData_10 = new GridData();
		jobchainLabel.setLayoutData(gridData_10);
		jobchainLabel.setText("Job chain"); //TODO lang "Job chain"
		

		cJobchain = new Combo(gDescription, SWT.NONE);
		cJobchain.setEnabled(false);
		cJobchain.setItems(listener.getJobChains());		
		cJobchain.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(!event)
					return;
				
				listener.setJobChain(cJobchain.getText());	
				
				String curstate = Utils.getAttributeValue("state", listener.getCommand());

					tState.setItems(listener.getStates());                		
					tState.setText(curstate);

                
                String curEndstate =  Utils.getAttributeValue("end_state", listener.getCommand());

                	cboEndstate.setItems(listener.getStates());
                	cboEndstate.setText(curEndstate);

				
			}
		});

		final GridData gridData_8 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_8.widthHint = 114;
		cJobchain.setLayoutData(gridData_8);
		lblJob = new Label(gDescription, SWT.NONE);
		lblJob.setLayoutData(new GridData(73, SWT.DEFAULT));
		lblJob.setText("Job / Order ID"); //TODO lang "Job / Order ID"

		tJob = new Text(gDescription, SWT.BORDER);
		tJob.addFocusListener(new FocusAdapter() {
			public void focusGained(final FocusEvent e) {
				tJob.selectAll();
			}
		});
		tJob.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(type == Editor.JOB){
					listener.setJob(tJob.getText());
				} else {
					listener.setOrderId(tJob.getText());
				}

			}
		});
		final GridData gridData_3 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_3.widthHint = 150;
		tJob.setLayoutData(gridData_3);
		final Label startAtLabel = new Label(gDescription, SWT.NONE);
		startAtLabel.setLayoutData(new GridData());
		startAtLabel.setText("Start at"); //TODO lang "Start at"

		tStartAt = new Text(gDescription, SWT.BORDER);
		tStartAt.addFocusListener(new FocusAdapter() {
			public void focusGained(final FocusEvent e) {
				tStartAt.selectAll();
			}
		});
		tStartAt.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				listener.setAt(tStartAt.getText());

			}
		});		
		final GridData gridData_4 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_4.widthHint = 150;
		tStartAt.setLayoutData(gridData_4);

		priorityLabel = new Label(gDescription, SWT.NONE);
		final GridData gridData_11 = new GridData();
		priorityLabel.setLayoutData(gridData_11);
		priorityLabel.setText("Priority"); //TODO lang "Priority"

		tPriority = new Text(gDescription, SWT.BORDER);
		tPriority.addFocusListener(new FocusAdapter() {
			public void focusGained(final FocusEvent e) {
				tPriority.selectAll();
			}
		});
		tPriority.setEnabled(false);
		tPriority.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				listener.setPriority(tPriority.getText());

			}
		});
		tPriority.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		titleLabel = new Label(gDescription, SWT.NONE);
		titleLabel.setLayoutData(new GridData());
		titleLabel.setText("Title"); //TODO lang "Title"

		tTitle = new Text(gDescription, SWT.BORDER);
		tTitle.addFocusListener(new FocusAdapter() {
			public void focusGained(final FocusEvent e) {
				tTitle.selectAll();		
			}
		});
		tTitle.setEnabled(false);
		tTitle.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				listener.setTitle(tTitle.getText());

			}
		});

		final GridData gridData_5 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_5.widthHint = 150;
		tTitle.setLayoutData(gridData_5);

		stateLabel = new Label(gDescription, SWT.NONE);
		stateLabel.setLayoutData(new GridData());
		stateLabel.setText("State"); //TODO lang "State"

		tState = new Combo(gDescription, SWT.BORDER);
		tState.setEnabled(false);
		tState.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(event)
					listener.setState(tState.getText());
				
			}
		});
		final GridData gridData_2 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_2.widthHint = 150;
		tState.setLayoutData(gridData_2);

		endStateLabel = new Label(gDescription, SWT.NONE);
		endStateLabel.setLayoutData(new GridData());
		endStateLabel.setText("End State"); //TODO lang "End State"

		cboEndstate = new Combo(gDescription, SWT.NONE);
		cboEndstate.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if(event)
					listener.setEndState(cboEndstate.getText());
			}
		});
		cboEndstate.setEnabled(false);
		cboEndstate.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		replaceLabel = new Label(gDescription, SWT.NONE);
		final GridData gridData_12 = new GridData();
		replaceLabel.setLayoutData(gridData_12);
		replaceLabel.setText("Replace"); //TODO lang "Replace"

		bReplace = new Button(gDescription, SWT.CHECK);
		bReplace.setSelection(true);
		bReplace.setEnabled(true);
		bReplace.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				listener.setReplace(bReplace.getSelection() ? "yes" : "no");
				
			}
		});
		bReplace.setLayoutData(new GridData());
		
		createSashForm();
	}


	/**
	 * This method initializes group
	 */
	private void createGroup() {
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.makeColumnsEqualWidth = true;
		gridLayout2.numColumns = 1;
		jobsAndOrdersGroup = new Group(this, SWT.NONE);
		String strT = JOE_M_JobCommand_CommandsForJob.params(listener.getName());
		if(listener.isDisabled())
			strT += " " + JOE_M_JobCommand_Disabled.label();
		jobsAndOrdersGroup.setText(strT);
		jobsAndOrdersGroup.setLayout(gridLayout2);
		
		GridData gridData18 = new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true, 1, 2);
		sashForm = new SashForm(jobsAndOrdersGroup, SWT.NONE);
		sashForm.setOrientation(512);
		sashForm.setLayoutData(gridData18);
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 2;
		
		gDescription = JOE_G_JobCommand_JobsAndOrders.Control(new Group(sashForm, SWT.NONE));
		gDescription.setLayout(gridLayout3);
		

		jobchainLabel = JOE_L_JobCommand_JobChain.Control(new Label(gDescription, SWT.NONE));
		final GridData gridData_10 = new GridData();
		jobchainLabel.setLayoutData(gridData_10);

		cJobchain = JOE_Cbo_JobCommand_JobChain.Control(new Combo(gDescription, SWT.NONE));
		cJobchain.setEnabled(false);
		cJobchain.setItems(listener.getJobChains());		
		cJobchain.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(!event)
					return;
				listener.setJobChain(cJobchain.getText());	
				String curstate = Utils.getAttributeValue("state", listener.getCommand());
					tState.setItems(listener.getStates());                		
					tState.setText(curstate);
                String curEndstate =  Utils.getAttributeValue("end_state", listener.getCommand());
                	cboEndstate.setItems(listener.getStates());
                	cboEndstate.setText(curEndstate);
			}
		});
		final GridData gridData_8 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_8.widthHint = 114;
		cJobchain.setLayoutData(gridData_8);
		
		lblJob = JOE_L_JobCommand_JobOrderID.Control(new Label(gDescription, SWT.NONE));
		lblJob.setLayoutData(new GridData(73, SWT.DEFAULT));
		
		tJob = JOE_T_JobCommand_Job.Control(new Text(gDescription, SWT.BORDER));
		tJob.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(type == Editor.JOB){
					listener.setJob(tJob.getText());
				} else {
					listener.setOrderId(tJob.getText());
				}
			}
		});
		final GridData gridData_3 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_3.widthHint = 150;
		tJob.setLayoutData(gridData_3);
		
		final Label startAtLabel = JOE_L_JobCommand_StartAt.Control(new Label(gDescription, SWT.NONE));
		startAtLabel.setLayoutData(new GridData());

		tStartAt = JOE_T_JobCommand_StartAt.Control(new Text(gDescription, SWT.BORDER));
		tStartAt.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				listener.setAt(tStartAt.getText());
			}
		});		
		final GridData gridData_4 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_4.widthHint = 150;
		tStartAt.setLayoutData(gridData_4);

		priorityLabel = JOE_L_JobCommand_Priority.Control(new Label(gDescription, SWT.NONE));
		final GridData gridData_11 = new GridData();
		priorityLabel.setLayoutData(gridData_11);

		tPriority = JOE_T_JobCommand_Priority.Control(new Text(gDescription, SWT.BORDER));
		tPriority.setEnabled(false);
		tPriority.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				listener.setPriority(tPriority.getText());
			}
		});
		tPriority.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		titleLabel = JOE_L_JobCommand_Title.Control(new Label(gDescription, SWT.NONE));
		titleLabel.setLayoutData(new GridData());

		tTitle = JOE_T_JobCommand_Title.Control(new Text(gDescription, SWT.BORDER));
		tTitle.setEnabled(false);
		tTitle.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				listener.setTitle(tTitle.getText());

			}
		});
		final GridData gridData_5 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_5.widthHint = 150;
		tTitle.setLayoutData(gridData_5);

		stateLabel = JOE_L_JobCommand_State.Control(new Label(gDescription, SWT.NONE));
		stateLabel.setLayoutData(new GridData());

		tState = JOE_T_JobCommand_State.Control(new Combo(gDescription, SWT.BORDER));
		tState.setEnabled(false);
		tState.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(event)
					listener.setState(tState.getText());
			}
		});
		final GridData gridData_2 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData_2.widthHint = 150;
		tState.setLayoutData(gridData_2);

		endStateLabel = JOE_L_JobCommand_EndState.Control(new Label(gDescription, SWT.NONE));
		endStateLabel.setLayoutData(new GridData());

		cboEndstate = JOE_Cbo_JobCommand_EndState.Control(new Combo(gDescription, SWT.NONE));
		cboEndstate.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if(event)
					listener.setEndState(cboEndstate.getText());
			}
		});
		cboEndstate.setEnabled(false);
		cboEndstate.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		replaceLabel = JOE_L_JobCommand_Replace.Control(new Label(gDescription, SWT.NONE));
		final GridData gridData_12 = new GridData();
		replaceLabel.setLayoutData(gridData_12);

		bReplace = JOE_B_JobCommand_Replace.Control(new Button(gDescription, SWT.CHECK));
		bReplace.setSelection(true);
		bReplace.setEnabled(true);
		bReplace.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				listener.setReplace(bReplace.getSelection() ? "yes" : "no");
			}
		});
		bReplace.setLayoutData(new GridData());
		
		createSashForm();
	}




	/**
	 * This method initializes sashForm
	 */
	private void createSashForm() {
		createGroup2();
	}


	/**
	 * This method initializes group2
	 */
	private void createGroup2() {
		if(type == Editor.JOB){
			setCommandsEnabled(false);			
		} else {			
			setCommandsEnabled(true);
		}
		clearFields();
		fillCommand();
	}



	private void clearFields() {
		if(type == Editor.JOB){
			
			tState.setVisible(false);
			cboEndstate.setVisible(false);
			tPriority.setVisible(false);
			cJobchain.setVisible(false);
			tTitle.setVisible(false);
			bReplace.setVisible(false);
			jobchainLabel.setVisible(false);
			priorityLabel.setVisible(false);
			titleLabel.setVisible(false);			
			stateLabel.setVisible(false);
			endStateLabel.setVisible(false);
			replaceLabel.setVisible(false);
			tJob.setFocus();
		} else {
			cJobchain.setFocus();
		}
		tJob.setVisible(true);
		tStartAt.setVisible(true);
		
	}


	private void setCommandsEnabled(boolean b) {
		tState.setEnabled(b);
		cboEndstate.setEnabled(b);
		tPriority.setEnabled(b);
		cJobchain.setEnabled(b);
		tTitle.setEnabled(b);
		bReplace.setEnabled(b);
	}


	public Element getCommand() {
		return listener.getCommand();
	}


	public void fillCommand() {
		if (listener.getCommand() !=  null) {

			tStartAt.setText(Utils.getAttributeValue("at", listener.getCommand()));
			if (type == Editor.COMMANDS) {
				cJobchain.setText(Utils.getAttributeValue("job_chain", listener.getCommand()));
				tJob.setText(Utils.getAttributeValue("id", listener.getCommand()));
				tTitle.setText(Utils.getAttributeValue("title", listener.getCommand()));
				tState.setItems(listener.getStates());
				tState.setText(Utils.getAttributeValue("state", listener.getCommand()));
				cboEndstate.setItems(listener.getStates());
				cboEndstate.setText(Utils.getAttributeValue("end_state", listener.getCommand()));
				
				tPriority.setText(Utils.getAttributeValue("priority", listener.getCommand()));
				bReplace.setSelection(Utils.getAttributeValue("replace", listener.getCommand()).equals("yes"));
			} else {
				tJob.setText(Utils.getAttributeValue("job", listener.getCommand()));
			}
		}
	}


	public void setToolTipText() {
//		
	}

	

	
} // @jve:decl-index=0:visual-constraint="10,10"
