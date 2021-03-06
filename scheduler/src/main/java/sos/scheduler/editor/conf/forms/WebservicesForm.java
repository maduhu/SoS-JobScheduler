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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.jdom.Element;
import sos.scheduler.editor.app.ContextMenu;
import sos.scheduler.editor.app.Editor;
import sos.scheduler.editor.app.IUnsaved;
import sos.scheduler.editor.app.IUpdateLanguage;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.SOSJOEMessageCodes;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.listeners.WebservicesListener;

public class WebservicesForm extends SOSJOEMessageCodes implements IUnsaved, IUpdateLanguage {


	private        WebservicesListener listener          = null;

	private        Group               group             = null;

	private static Table               tServices         = null;

	private        Button              bRemove           = null;

	private        Button              bNew              = null;

	private        Label               label8            = null;

	private        SchedulerDom        _dom              = null;
	
	
	
	public WebservicesForm(Composite parent, int style, SchedulerDom dom, Element config, ISchedulerUpdate main) {
		super(parent, style);
		_dom = dom;
		listener = new WebservicesListener(dom, config, main);
		initialize();
		setToolTipText();

		listener.fillTable(tServices);   
		
	}


	public void apply() {
		//if (isUnsaved())

			//applyService();

	}


	public boolean isUnsaved() {
		return false;
	}


	private void initialize() {
		this.setLayout(new FillLayout());
		createGroup();
		setSize(new org.eclipse.swt.graphics.Point(653, 468));
	}


	/**
	 * This method initializes group
	 */
	 private void createGroup() {
		GridData gridData19 = new org.eclipse.swt.layout.GridData();
		gridData19.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData19.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		GridData gridData3 = new org.eclipse.swt.layout.GridData();
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.verticalSpan = 1;
		gridData3.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		
		group = JOE_G_WebservicesForm_WebServices.Control(new Group(this, SWT.NONE));
		
		GridData gridData1 = new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.FILL, true, true, 1, 3);
		tServices = JOE_Tbl_WebservicesForm_Services.Control(new Table(group, SWT.BORDER | SWT.FULL_SELECTION));
		tServices.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(final MouseEvent e) {
				if(tServices.getSelectionCount() > 0)
					ContextMenu.goTo(tServices.getSelection()[0].getText(0), _dom, Editor.WEBSERVICE);
			}
		});
		tServices.setHeaderVisible(true);
		tServices.setLayoutData(gridData1);
		tServices.setLinesVisible(true);
		tServices.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			 public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				 boolean selection = tServices.getSelectionCount() > 0;
				 bRemove.setEnabled(selection);
				 if (selection) {
					 listener.selectService(tServices.getSelectionIndex());
					 /*setInput(true);
					 sTimeout.setEnabled(!cChain.getText().equals(""));
					 tRequest.setEnabled(!sTimeout.getEnabled());
					 tResponse.setEnabled(!sTimeout.getEnabled());
					 tForward.setEnabled(!sTimeout.getEnabled());
					 */
				 }
			 }
		});
		
		TableColumn tableColumn = JOE_TCl_WebservicesForm_Name.Control(new TableColumn(tServices, SWT.NONE));
		tableColumn.setWidth(150);
		
		TableColumn tableColumn1 = JOE_TCl_WebservicesForm_URL.Control(new TableColumn(tServices, SWT.NONE));
		tableColumn1.setWidth(150);
		
		TableColumn tableColumn2 = JOE_TCl_WebservicesForm_JobChain.Control(new TableColumn(tServices, SWT.NONE));
		tableColumn2.setWidth(100);
		
		GridData gridData5 = new org.eclipse.swt.layout.GridData(GridData.FILL, GridData.BEGINNING, false, false);
		
		bNew = JOE_B_WebservicesForm_New.Control(new Button(group, SWT.NONE));
		bNew.setLayoutData(gridData5);
		getShell().setDefaultButton(bNew);
		bNew.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				listener.newService(tServices);
				//setInput(true);
				//tName.setFocus();
			}
		});
		
		group.setLayout(gridLayout);
		label8 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		label8.setText("Label");
		label8.setLayoutData(gridData19);
		
		bRemove = JOE_B_WebservicesForm_Remove.Control(new Button(group, SWT.NONE));
		bRemove.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				if (tServices.getSelectionCount() > 0) {
					int index = tServices.getSelectionIndex();
					listener.removeService(index);
					tServices.remove(index);
					if (index >= tServices.getItemCount())
						index--;
					if (tServices.getItemCount() > 0) {
						tServices.select(index);
						listener.selectService(index);
						//setInput(true);
					}// else
						//setInput(false);
				}
				bRemove.setEnabled(tServices.getSelectionCount() > 0);
			}
		});
		bRemove.setEnabled(false);
		bRemove.setLayoutData(gridData3);
	 }

	
	 public void setToolTipText() {
//
	 }


	public static Table getTable() {
		return tServices ;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
