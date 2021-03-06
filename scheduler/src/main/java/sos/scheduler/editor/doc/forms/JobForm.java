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

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;
import org.jdom.JDOMException;

import sos.scheduler.editor.app.IUpdateLanguage;
import sos.scheduler.editor.app.Options;
import sos.scheduler.editor.app.SOSJOEMessageCodes;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.doc.DocumentationDom;
import sos.scheduler.editor.doc.SourceGenerator;
import sos.scheduler.editor.doc.listeners.JobListener;

public class JobForm extends SOSJOEMessageCodes implements IUpdateLanguage {
	@SuppressWarnings("unused")
	private final static String	conSVNVersion	= "$Id: JobForm.java 19102 2013-02-11 09:35:38Z ur $";
	private JobListener			listener		= null;
	private Group				group			= null;
	@SuppressWarnings("unused")
	private Label				label			= null;
	private Text				tName			= null;
	@SuppressWarnings("unused")
	private Label				label1			= null;
	private Text				tTitle			= null;
	@SuppressWarnings("unused")
	private Label				label2			= null;
	@SuppressWarnings("unused")
	private Label				label3			= null;
	private Combo				cOrder			= null;
	private Combo				cTasks			= null;
	private Combo				cbJobType;
	private Text				sourceOutputPath;
	private Text				packageName;

	public JobForm(final Composite parent, final int style, final DocumentationDom dom, final Element job) {
		super(parent, style);
		initialize();
		setToolTipText();

		listener = new JobListener(dom, job);
		initValues();
	}

	private void initialize() {
		createGroup();
		setSize(new Point(696, 462));
		setLayout(new FillLayout());

	}

	/**
	 * This method initializes group
	 */
	private void createGroup() {
		GridData gridData1 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		GridLayout gridLayout = new GridLayout(2, false);

		group = JOE_G_JobForm_Job.Control(new Group(this, SWT.NONE));
		group.setLayout(gridLayout);

		label = JOE_L_Name.Control(new Label(group, SWT.NONE));

		tName = JOE_T_JobForm_Name.Control(new Text(group, SWT.BORDER));
		tName.setLayoutData(gridData); // Generated
		tName.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setName(tName.getText());
				Utils.setBackground(tName, true);
			}
		});

		label1 = JOE_L_JobForm_Title.Control(new Label(group, SWT.NONE));

		tTitle = JOE_T_JobForm_Title.Control(new Text(group, SWT.BORDER));
		tTitle.setLayoutData(gridData1); // Generated
		tTitle.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			@Override
			public void modifyText(final org.eclipse.swt.events.ModifyEvent e) {
				listener.setTitle(tTitle.getText());
				Utils.setBackground(tTitle, true);
			}
		});

		label2 = JOE_L_JobForm_Order.Control(new Label(group, SWT.NONE));

		createCOrder();

		label3 = JOE_L_JobForm_Tasks.Control(new Label(group, SWT.NONE));

		cTasks = JOE_Cbo_JobForm_Tasks.Control(new Combo(group, SWT.BORDER | SWT.READ_ONLY));
		cTasks.setLayoutData(new GridData(200, SWT.DEFAULT)); // Generated
		cTasks.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			@Override
			public void widgetSelected(final org.eclipse.swt.events.SelectionEvent e) {
				listener.setTasks(cTasks.getText());
			}

			@Override
			public void widgetDefaultSelected(final org.eclipse.swt.events.SelectionEvent e) {
				//
			}
		});

		new Label(group, SWT.NONE);

		final Button vorschauButton = JOE_B_JobForm_Preview.Control(new Button(group, SWT.NONE));
		vorschauButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				listener.preview();
			}
		});

		new Label(group, SWT.NONE);
		new Label(group, SWT.NONE);

		Label lblO = JOE_L_JobForm_OutputPath.Control(new Label(group, SWT.NONE));
		lblO.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		sourceOutputPath = JOE_T_JobForm_OutputPath.Control(new Text(group, SWT.BORDER));
		sourceOutputPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		@SuppressWarnings("unused")
		Label lblPackageName = JOE_L_JobForm_PackageName.Control(new Label(group, SWT.NONE));

		packageName = JOE_T_JobForm_PackageName.Control(new Text(group, SWT.BORDER));
		packageName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblJobType = JOE_L_JobForm_JobType.Control(new Label(group, SWT.NONE));
		lblJobType.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		cbJobType = JOE_Cbo_JobForm_JobType.Control(new Combo(group, SWT.NONE));
		cbJobType.setItems(new String[] { "Job in a Job Chain", "Standalone Job" });
		GridData gd_cbJobType = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_cbJobType.widthHint = 190;
		cbJobType.setLayoutData(gd_cbJobType);
		// cbJobType.setText("Standalone Job");
		cbJobType.setText(cbJobType.getItem(1));

		new Label(group, SWT.NONE);

		Button btnNewButton = JOE_B_JobForm_GenerateSource.Control(new Button(group, SWT.NONE));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent arg0) {

				generateJavaSource();

			}

		});
		createCTasks();
	}

	protected void generateJavaSource() {
		SourceGenerator s = new SourceGenerator();
		File documentation;
		try {

			documentation = listener.writeToFile();
			s.setDefaultLang("en");
			s.setJavaClassName(tName.getText());
			s.setJobdocFile(documentation);
			if (sourceOutputPath.getText().trim().equals("")) {
				File tmp = File.createTempFile(Options.getXSLTFilePrefix(), Options.getXSLTFileSuffix());
				tmp.deleteOnExit();
				sourceOutputPath.setText(tmp.getParent());
			}
			s.setOutputDir(new File(sourceOutputPath.getText()));
			s.setPackageName(packageName.getText());

			// TODO Migrate Template path to JOE_HOME Folder
			File f = new File(Options.getSchedulerData(), "config/JOETemplates/java/xsl");
			s.setTemplatePath(f);
			if (!f.exists()) {
				MessageBox mb = new MessageBox(getShell(), SWT.ICON_INFORMATION);
				mb.setMessage(JOE_M_FileNotFound.params(f.getAbsolutePath()));
				mb.open();
			}
			else {
				if (cOrder.getText().equalsIgnoreCase("no") && cbJobType.getText().equalsIgnoreCase("Standalone Job")) {
					s.setStandAlone(true);
				}
				else {
					s.setStandAlone(false);
				}

				s.execute();

			}

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (JDOMException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes cOrder
	 */
	private void createCOrder() {
		GridData gridData2 = new GridData(GridData.BEGINNING, GridData.CENTER);
		gridData2.widthHint = 200;

		cOrder = JOE_Cbo_JobForm_Order.Control(new Combo(group, SWT.BORDER | SWT.READ_ONLY));
		cOrder.setLayoutData(gridData2); // Generated
		cOrder.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			@Override
			public void widgetSelected(final org.eclipse.swt.events.SelectionEvent e) {
				listener.setOrder(cOrder.getText());
			}

			@Override
			public void widgetDefaultSelected(final org.eclipse.swt.events.SelectionEvent e) {
			}
		});
	}

	/**
	 * This method initializes cTasks
	 */
	private void createCTasks() {
//
	}

	private void initValues() {
		tName.setText(listener.getName());
		tTitle.setText(listener.getTitle());
		cOrder.setItems(listener.getOrderValues());
		cOrder.select(cOrder.indexOf(listener.getOrder()));
		cTasks.setItems(listener.getTasksValues());
		cTasks.select(cTasks.indexOf(listener.getTasks()));

		tName.setFocus();
	}

	@Override
	public void setToolTipText() {
//
	}

} // @jve:decl-index=0:visual-constraint="10,10"
