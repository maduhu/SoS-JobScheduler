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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jdom.Element;
import sos.scheduler.editor.app.Editor;
import sos.scheduler.editor.app.IUpdateLanguage;
import sos.scheduler.editor.app.Messages;
import sos.scheduler.editor.app.SOSJOEMessageCodes;
import sos.scheduler.editor.app.Utils;
import sos.scheduler.editor.conf.ISchedulerUpdate;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.listeners.PeriodListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class PeriodForm extends SOSJOEMessageCodes implements IUpdateLanguage {

    @SuppressWarnings("unused")
    private final String            conSVNVersion       = "$Id: PeriodForm.java 20985 2013-09-04 09:13:12Z ur $";

	private Label lblOrSecond;
	private Label label18_2;
	private Text stSeconds;
	private Label label12_1;
	private Text stMinutes;
	private Label label11_1;
	private Text stHour;

	public static String SINGLE_START = JOE_M_PeriodForm_SingleStart.label();
	public static String REPEAT_TIME =  JOE_M_PeriodForm_RepeatTime.label();
	public static String ABSOLUTE_TIME = JOE_M_PeriodForm_AbsoluteTime.label();

	private PeriodListener    listener                  = null;
	private boolean           onOrder;
	private Composite         gPeriod                    = null;
	private Label             label1                     = null;
	private Button            bLetRun                    = null;
	private Label             label2                     = null;
	private Text              sBeginHours                = null;
	private Label             label3                     = null;
	private Text              sBeginMinutes              = null;
	private Label             label4                     = null;
	private Text              sBeginSeconds              = null;
	private Label             label5                     = null;
	private Label             label6                     = null;
	private Text              sEndHours                  = null;
	private Label             label7                     = null;
	private Text              sEndMinutes                = null;
	private Label             label8                     = null;
	private Text              sEndSeconds                = null;
	private Label             label9                     = null;
	private Label             lRunOnce                   = null;
	private Button            cRunOnce                   = null;
	private boolean           event                      = true;
	private Button            bApply                     = null;
	private String            savBeginHours              = "";
	private String            savBeginMinutes            = "";
	private String            savBeginSeconds            = "";
	private String            savEndHours                = "";
	private String            savEndMinutes              = "";
	private String            savEndSeconds              = "";
	private boolean           assistent                  = false;

	private int               _type                      = Editor.PERIODS;

	private ISchedulerUpdate  _gui                       = null;

	private Combo             cboWhenHoliday             = null;

	private Group             startTimePeriodGroup       = null; 

	private Combo             cboStarttime               = null;
	
 	

	/**
	 * @wbp.parser.constructor
	 */
	public PeriodForm(Composite parent, int style, int type) {
		super(parent, style);
		_type = type;
		initialize();
		setToolTipText();

		setRunOnce(false);

	}

	public PeriodForm(Composite parent, int style, boolean assistent_) {		
		this(parent, style, Editor.JOB_WIZARD);
		assistent = assistent_;
	}

	public void setParams(SchedulerDom dom, boolean onOrder) {
		this.onOrder = onOrder;
		if(onOrder && startTimePeriodGroup != null)
			startTimePeriodGroup.setText(JOE_M_PeriodForm_StartTimeNA.label());

		listener = new PeriodListener(dom);
	}


	private void initialize() {

		this.setLayout(new FillLayout());				

		createTimeSlotGroup();

		if(_type != Editor.RUNTIME) {
			createStartTimeGroup();
			createWhenHoliday();
		}
	}




	public void fillPeriod() {
		event = false;
		if (listener.getPeriod() != null ) {

			//event = false;
			sBeginHours.setText(Utils.fill(2, String.valueOf(listener.getBeginHours())));
			sBeginMinutes.setText(Utils.fill(2, String.valueOf(listener.getBeginMinutes())));
			//event = true;
			sBeginSeconds.setText(Utils.fill(2, String.valueOf(listener.getBeginSeconds())));

			//event = false;
			sEndHours.setText(Utils.fill(2, String.valueOf(listener.getEndHours())));
			sEndMinutes.setText(Utils.fill(2, String.valueOf(listener.getEndMinutes())));
			//event = true;
			sEndSeconds.setText(Utils.fill(2, String.valueOf(listener.getEndSeconds())));

			/*
			if(_type != Editor.RUNTIME) {
				//event = false;
				sAbsoluteRepeatHours.setText(Utils.fill(2, String.valueOf(listener.getAbsoluteRepeatHours())));
				sAbsoluteRepeatMinutes.setText(Utils.fill(2, String.valueOf(listener.getAbsoluteRepeatMinutes())));

				sAbsoluteRepeatSeconds.setText(Utils.fill(2, String.valueOf(listener.getAbsoluteRepeatSeconds())));
				//event = true;
			}
			 */
			if (!onOrder) {
				if(_type != Editor.RUNTIME) {

					//
					int har = listener.getAbsoluteRepeatHours().length() == 0 ? 0 : Integer.parseInt(listener.getAbsoluteRepeatHours());
					int mar = listener.getAbsoluteRepeatMinutes().length() == 0 ? 0 : Integer.parseInt(listener.getAbsoluteRepeatMinutes());
					int sar = listener.getAbsoluteRepeatSeconds().length() == 0 ? 0 : Integer.parseInt(listener.getAbsoluteRepeatSeconds());
					boolean isAbsoluteRepeat = (har + mar + sar ) > 0; 

					int hrt = listener.getRepeatHours().length() == 0 ? 0 : Integer.parseInt(listener.getRepeatHours());
					int mrt = listener.getRepeatMinutes().length() == 0 ? 0 : Integer.parseInt(listener.getRepeatMinutes());
					int srt = listener.getRepeatSeconds().length() == 0 ? 0 : Integer.parseInt(listener.getRepeatSeconds());
					boolean isRepeatTime = (hrt + mrt + srt ) > 0; 

					int hst = listener.getSingleHours().length() == 0 ? 0 : Integer.parseInt(listener.getSingleHours());
					int mst = listener.getSingleMinutes().length() == 0 ? 0 : Integer.parseInt(listener.getSingleMinutes());
					int sst = listener.getSingleSeconds().length() == 0 ? 0 : Integer.parseInt(listener.getSingleSeconds());
					boolean isSingleStart = (hst + mst + sst ) > 0;

					if(isSingleStart) {

						cboStarttime.setText(SINGLE_START);
						stHour.setText(Utils.fill(2, String.valueOf(listener.getSingleHours())));
						stMinutes.setText(Utils.fill(2, String.valueOf(listener.getSingleMinutes())));
						stSeconds.setText(Utils.fill(2, String.valueOf(listener.getSingleSeconds())));

					} else if (isRepeatTime) {

						cboStarttime.setText(REPEAT_TIME);
						stHour.setText(Utils.fill(2, String.valueOf(listener.getRepeatHours())));
						stMinutes.setText(Utils.fill(2, String.valueOf(listener.getRepeatMinutes())));
						stSeconds.setText(Utils.fill(2, String.valueOf(listener.getRepeatSeconds())));


					} else if (isAbsoluteRepeat) {

						cboStarttime.setText(ABSOLUTE_TIME);
						stHour.setText(Utils.fill(2, String.valueOf(listener.getAbsoluteRepeatHours())));
						stMinutes.setText(Utils.fill(2, String.valueOf(listener.getAbsoluteRepeatMinutes())));
						stSeconds.setText(Utils.fill(2, String.valueOf(listener.getAbsoluteRepeatSeconds())));

					} else {
						cboStarttime.setText(SINGLE_START);
						stHour.setText("00");
						stMinutes.setText("00");
						stSeconds.setText("00");

					}

				}
				if (cRunOnce.isVisible())
					cRunOnce.setSelection(listener.getRunOnce());
			}

			bLetRun.setSelection(listener.getLetRun());
			sBeginHours.setFocus();

			if(cboWhenHoliday != null) {
				cboWhenHoliday.setItems(listener.getWhenHolidays());
				cboWhenHoliday.setText(listener.getWhenHoliday());
			}

		}else if(listener.getAtElement() != null) {
			if(_type != Editor.RUNTIME) {
				//event = false;
				listener.setPeriod(listener.getAtElement());
				/*
				sSingleHours.setText(Utils.fill(2, String.valueOf(listener.getSingleHours())));
				sSingleMinutes.setText(Utils.fill(2, String.valueOf(listener.getSingleMinutes())));				
				sSingleSeconds.setText(Utils.fill(2, String.valueOf(listener.getSingleSeconds())));
				 */
				stHour.setText(Utils.fill(2, String.valueOf(listener.getSingleHours())));
				stMinutes.setText(Utils.fill(2, String.valueOf(listener.getSingleMinutes())));
				stSeconds.setText(Utils.fill(2, String.valueOf(listener.getSingleSeconds())));
			}
			if(cboWhenHoliday != null) {
				cboWhenHoliday.setItems(listener.getWhenHolidays());
				cboWhenHoliday.setText(listener.getWhenHoliday());
			}
		}
		event = true;
	}




	public void setPeriod(Element period) {

		listener.setPeriod(period);
		fillPeriod();
	}

	public void setAtElement(Element at) {
		listener.setAtElement(at);
		fillPeriod();

	}

	public Element getPeriod() {
		return listener.getPeriod();
	}



	public void setEnabled(boolean enabled) {
		event = false;
		boolean singleStart = false;

		if(_type != Editor.RUNTIME) {
			
			if(listener.getPeriod() != null)
				singleStart = isSingleStart();			
		}

		if(!enabled){
			savBeginHours              = "";
			savBeginMinutes            = "";
			savBeginSeconds            = "";
			savEndHours                = "";
			savEndMinutes              = "";
			savEndSeconds              = "";
		}

		gPeriod.setEnabled(enabled);


		bLetRun.setEnabled(enabled && !singleStart && !onOrder);

		if(singleStart && bLetRun.getSelection()) {
			bLetRun.setSelection(false);
			listener.setLetRun(false);
		}
		//TIME SLOT
		cRunOnce.setEnabled(enabled && !onOrder);
		sBeginHours.setEnabled(enabled && !singleStart);
		sBeginMinutes.setEnabled(enabled && !singleStart);
		sBeginSeconds.setEnabled(enabled && !singleStart);
		sEndHours.setEnabled(enabled && !singleStart);
		sEndMinutes.setEnabled(enabled && !singleStart);
		sEndSeconds.setEnabled(enabled && !singleStart);


		if(_type != Editor.RUNTIME) {
			//START TIME

			if(!cboStarttime.getText().equals(SINGLE_START) && cboStarttime.getText().length() > 0) {						
				cboStarttime.setEnabled(enabled  && !onOrder && !singleStart);
				stHour.setEnabled(enabled  && !onOrder && !singleStart);
				stMinutes.setEnabled(enabled  && !onOrder && !singleStart);
				stSeconds.setEnabled(enabled  && !onOrder && !singleStart);

			} else {

				cboStarttime.setEnabled(enabled && !onOrder);
				stHour.setEnabled(enabled && !onOrder );
				stMinutes.setEnabled(enabled && !onOrder );
				stSeconds.setEnabled(enabled && !onOrder );
			}

		}
		if (singleStart) {
			if (!sBeginHours.getText().equals(""))
				savBeginHours = sBeginHours.getText();
			if (!sBeginMinutes.getText().equals(""))
				savBeginMinutes = sBeginMinutes.getText();
			if (!sBeginSeconds.getText().equals(""))
				savBeginSeconds = sBeginSeconds.getText();
			if (!sEndHours.getText().equals(""))
				savEndHours = sEndHours.getText();
			if (!sEndMinutes.getText().equals(""))
				savEndMinutes = sEndMinutes.getText();
			if (!sEndSeconds.getText().equals(""))
				savEndSeconds = sEndSeconds.getText();

			if(_type != Editor.RUNTIME) {
			}

			sBeginHours.setText("");
			sBeginMinutes.setText("");
			sBeginSeconds.setText("");
			sEndHours.setText("");
			sEndMinutes.setText("");
			sEndSeconds.setText("");
			if(_type != Editor.RUNTIME) {
				if(!cboStarttime.getText().equals(SINGLE_START)) {						
					cboStarttime.setText("");
					stHour.setText("");
					stMinutes.setText("");
					stSeconds.setText("");					
				} 
			}
		} else {
			event = false;
			if (savEndHours.equals("") || (savEndHours.equals("00") && savEndMinutes.equals("00") &&  savEndSeconds.equals("00"))) {
			  // savEndHours = "24"; 
		      // Wegen JS-790 auskommentiert. 
			}
			
			if (!savBeginHours.equals(""))
				sBeginHours.setText(savBeginHours);
			if (!savBeginMinutes.equals(""))
				sBeginMinutes.setText(savBeginMinutes);
			if (!savBeginSeconds.equals(""))
				sBeginSeconds.setText(savBeginSeconds);
			if (!savEndHours.equals("") )
			 	sEndHours.setText(savEndHours);
			if (!savEndMinutes.equals(""))
				sEndMinutes.setText(savEndMinutes);
			if (!savEndSeconds.equals(""))
				sEndSeconds.setText(savEndSeconds);
			
		}
		event = true;
	}


	public void setRunOnce(boolean visible) {
		lRunOnce.setVisible(visible);
		cRunOnce.setVisible(visible);
	}


	private boolean beginBeforeAfter() {
		int bh = Utils.str2int(0, sBeginHours.getText());
		int bm = Utils.str2int(0, sBeginMinutes.getText());
		int bs = Utils.str2int(0, sBeginSeconds.getText());
		int eh = Utils.str2int(0, sEndHours.getText());
		int em = Utils.str2int(0, sEndMinutes.getText());
		int es = Utils.str2int(0, sEndSeconds.getText());
		int gbs = bs + (bm * 60) + (bh * 60 * 60);
		int ges = es + (em * 60) + (eh * 60 * 60);
		if (gbs > ges && gbs != 0 && ges != 0 && bh < 24 && bm < 60 && bs < 60 && eh < 24 && em < 60 && es < 60) {
			Utils.setBackground(99, 0, sBeginHours);
			Utils.setBackground(99, 0, sBeginMinutes);
			Utils.setBackground(99, 0, sBeginSeconds);
			Utils.setBackground(99, 0, sEndHours);
			Utils.setBackground(99, 0, sEndMinutes);
			Utils.setBackground(99, 0, sEndSeconds);
			return true;
		} else {

			Utils.setBackground(0, 23, sBeginHours);
			Utils.setBackground(0, 59, sBeginMinutes);
			Utils.setBackground(0, 59, sBeginSeconds);
			Utils.setBackground(0, 24, sEndHours);
			Utils.setBackground(0, 59, sEndMinutes);
			Utils.setBackground(0, 59, sEndSeconds);

			return false;
		}
	}

//	TODO TOOLTIPS
	public void setToolTipText() {
		setStarttimeToolTip();
	}
	
	private void setStarttimeToolTip() {
		if(_type != Editor.RUNTIME) {
			if(cboStarttime.getText().equals(SINGLE_START)) {
				stHour.setToolTipText(JOE_Tooltip_PeriodForm_SingleStartHours.label());
				stMinutes.setToolTipText(JOE_Tooltip_PeriodForm_SingleStartMinutes.label());
				stSeconds.setToolTipText(JOE_Tooltip_PeriodForm_SingleStartSeconds.label());
			} else if(cboStarttime.getText().equals(ABSOLUTE_TIME)) {
				stHour.setToolTipText(JOE_Tooltip_PeriodForm_RepeatHours.label());
				stMinutes.setToolTipText(JOE_Tooltip_PeriodForm_RepeatMinutes.label());
				stSeconds.setToolTipText(JOE_Tooltip_PeriodForm_RepeatSeconds.label());
			} else if(cboStarttime.getText().equals(REPEAT_TIME)) {
				stHour.setToolTipText(JOE_Tooltip_PeriodForm_RepeatHours.label());
				stMinutes.setToolTipText(JOE_Tooltip_PeriodForm_RepeatMinutes.label());
				stSeconds.setToolTipText(JOE_Tooltip_PeriodForm_RepeatSeconds.label());
			}
		}
	
	}


	public void setApplyButton(Button b) {
		bApply = b;
		getShell().setDefaultButton(bApply);
	}

	public PeriodListener getListener() {
		return listener;
	}


	/**
	 * This method initializes group
	 */
	private void createTimeSlotGroup() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		gridLayout.horizontalSpacing = 0;
		//gPeriod = new Group(this, SWT.NONE);
		gPeriod = new Composite(this, SWT.NONE);
		gPeriod.setEnabled(false);
		gPeriod.setLayout(gridLayout);

		final Group groupSlottime = JOE_G_PeriodForm_TimeSlot.Control(new Group(gPeriod, SWT.NONE));
		groupSlottime.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 8;
		groupSlottime.setLayout(gridLayout_1);
		
		label1 = JOE_L_PeriodForm_LetRun.Control(new Label(groupSlottime, SWT.NONE));
		
		bLetRun = JOE_B_PeriodForm_LetRun.Control(new Button(groupSlottime, SWT.CHECK));
		bLetRun.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				listener.setLetRun(bLetRun.getSelection());
				updateFont();
				if (bApply != null) {
					bApply.setEnabled(true);					
				}
			}
		});
		
		new Label(groupSlottime, SWT.NONE);
		
		lRunOnce = JOE_L_PeriodForm_RunOnce.Control(new Label(groupSlottime, SWT.NONE));
		lRunOnce.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 3, 1));
		
		cRunOnce = JOE_B_PeriodForm_RunOnce.Control(new Button(groupSlottime, SWT.CHECK));
		cRunOnce.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));
		cRunOnce.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				listener.setRunOnce(cRunOnce.getSelection());
				updateFont();
				if (bApply != null) {
					bApply.setEnabled(true);
				}
			}
		});
		
		label2 = JOE_L_PeriodForm_BeginTime.Control(new Label(groupSlottime, SWT.NONE));
		label2.setLayoutData(new GridData(86, SWT.DEFAULT));
		
		sBeginHours = JOE_T_PeriodForm_BeginHours.Control(new Text(groupSlottime, SWT.BORDER));
		sBeginHours.setTextLimit(2);
		sBeginHours.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				refreshPeriodsTable(e);
			}
		});
		sBeginHours.setLayoutData(new GridData(24, SWT.DEFAULT));
		sBeginHours.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});
		sBeginHours.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				setBeginHours();
			}
		});
		
		label3 = JOE_L_Colon.Control(new Label(groupSlottime, SWT.NONE));
		
		sBeginMinutes = JOE_T_PeriodForm_BeginMinutes.Control(new Text(groupSlottime, SWT.BORDER));
		sBeginMinutes.setTextLimit(2);
		sBeginMinutes.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				refreshPeriodsTable(e);
			}
		});
		sBeginMinutes.setLayoutData(new GridData(24, SWT.DEFAULT));
		sBeginMinutes.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});

		sBeginMinutes.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				setBeginminutes();
			}
		});
		
		label4 = JOE_L_Colon.Control(new Label(groupSlottime, SWT.NONE));

		sBeginSeconds = JOE_T_PeriodForm_BeginSeconds.Control(new Text(groupSlottime, SWT.BORDER));
		sBeginSeconds.setTextLimit(2);
		sBeginSeconds.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				refreshPeriodsTable(e);
			}
		});
		final GridData gridData_2 = new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1);
		gridData_2.widthHint = 24;
		sBeginSeconds.setLayoutData(gridData_2);
		sBeginSeconds.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});
		sBeginSeconds.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				setBeginSeconds();
			}
		});
		
		label5 = JOE_L_JobAssistent_TimeFormat.Control(new Label(groupSlottime, SWT.NONE));
		label5.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));

		label6 = JOE_L_PeriodForm_EndTime.Control(new Label(groupSlottime, SWT.NONE));
		label6.setLayoutData(new GridData(86, SWT.DEFAULT));
		
		sEndHours = JOE_T_PeriodForm_EndHours.Control(new Text(groupSlottime, SWT.BORDER));
		sEndHours.setTextLimit(2);
		sEndHours.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				//refreshPeriodsTable(e);
			}
		});
		sEndHours.setLayoutData(new GridData(24, SWT.DEFAULT));
		sEndHours.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});
		sEndHours.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				//setEndHours();
			}
		});
		
		label7 = JOE_L_Colon.Control(new Label(groupSlottime, SWT.NONE));
		
		sEndMinutes = JOE_T_PeriodForm_EndMinutes.Control(new Text(groupSlottime, SWT.BORDER));
		sEndMinutes.setTextLimit(2);
		sEndMinutes.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				refreshPeriodsTable(e);
			}
		});
		sEndMinutes.setLayoutData(new GridData(24, SWT.DEFAULT));
		sEndMinutes.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});
		sEndMinutes.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				setEndMinutes();
			}
		});
		
		label8 = JOE_L_Colon.Control(new Label(groupSlottime, SWT.NONE));

		sEndSeconds = JOE_T_PeriodForm_EndSeconds.Control(new Text(groupSlottime, SWT.BORDER));
		sEndSeconds.setTextLimit(2);
		sEndSeconds.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				refreshPeriodsTable(e);
			}
		});
		final GridData gridData_3 = new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1);
		gridData_3.widthHint = 24;
		sEndSeconds.setLayoutData(gridData_3);
		sEndSeconds.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});
		sEndSeconds.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				setEndSeconds();
			}
		});

		label9 = JOE_L_JobAssistent_TimeFormat.Control(new Label(groupSlottime, SWT.NONE));
		label9.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, true, false));
	}

	private void createStartTimeGroup() {

		startTimePeriodGroup = JOE_G_PeriodForm_StartTime.Control(new Group(gPeriod, SWT.NONE));
		final GridData gridData_2 = new GridData(GridData.FILL, GridData.CENTER, true, false);
		startTimePeriodGroup.setLayoutData(gridData_2);
		final GridLayout gridLayout = new GridLayout();
		startTimePeriodGroup.setLayout(gridLayout);

		final Composite composite = new Composite(startTimePeriodGroup, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, false, false));
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.marginWidth = 0;
		gridLayout_1.marginHeight = 0;
		gridLayout_1.numColumns = 8;
		composite.setLayout(gridLayout_1);

		cboStarttime = JOE_Cbo_PeriodForm_StartTime.Control(new Combo(composite, SWT.READ_ONLY));
		final GridData gridData_3 = new GridData(GridData.BEGINNING, GridData.CENTER, true, false);
		cboStarttime.setLayoutData(gridData_3);
		cboStarttime.setItems(new String[] {SINGLE_START, REPEAT_TIME, ABSOLUTE_TIME });
		cboStarttime.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {

			}
		});
		cboStarttime.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				 
				if (bApply != null && event) {
					boolean hasTime = (stHour.getText().concat(stMinutes.getText()).concat(stSeconds.getText()).trim()).replaceAll("0", "").length() > 0;
					bApply.setEnabled(hasTime);					
				}
				if(cboStarttime.getText().equalsIgnoreCase(SINGLE_START)) {
					lblOrSecond.setVisible(false);
				} else {
					lblOrSecond.setVisible(true);
				} 
				listener.clearNONSingleStartAttributes();
				listener.clearSingleStartAttributes();

				setHours();
				
				if(!cboStarttime.getText().equalsIgnoreCase(SINGLE_START)) {
					setBeginHours();
					setBeginminutes();
					setBeginSeconds();

					setEndHours();
					setEndMinutes();
					setEndSeconds();
				}
				setEnabled(true);
								
				setStarttimeToolTip();
			}
		});
		cboStarttime.setText(SINGLE_START);

		stHour = new Text(composite, SWT.BORDER);
		stHour.setTextLimit(2);
		stHour.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				setHours();
			}
		});
		stHour.addFocusListener(new FocusAdapter() {
			public void focusGained(final FocusEvent e) {
				stHour.selectAll();
			}
		});
		stHour.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});
		stHour.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				refreshPeriodsTable(e);
			}
		});
		stHour.setLayoutData(new GridData(24, SWT.DEFAULT));

		label11_1 = JOE_L_Colon.Control(new Label(composite, SWT.NONE));

		stMinutes = new Text(composite, SWT.BORDER);
		stMinutes.setTextLimit(2);
		stMinutes.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				refreshPeriodsTable(e);
			}
		});
		stMinutes.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				setMinutes();
			}
		});
		stMinutes.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
			}
		});
		stMinutes.addFocusListener(new FocusAdapter() {
			public void focusGained(final FocusEvent e) {
				stMinutes.selectAll();
			}
		});
		stMinutes.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});
		final GridData gridData_4 = new GridData(24, SWT.DEFAULT);
		stMinutes.setLayoutData(gridData_4);

		label12_1 = JOE_L_Colon.Control(new Label(composite, SWT.NONE));

		stSeconds = new Text(composite, SWT.BORDER);
		stSeconds.setTextLimit(2);
		stSeconds.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				refreshPeriodsTable(e);
			}
		});
		stSeconds.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				if(_type != Editor.RUNTIME)
					return;
				setSecound();				
			}
		});
		stSeconds.addFocusListener(new FocusAdapter() {
			public void focusGained(final FocusEvent e) {
				stSeconds.selectAll();
			}
		});
		stSeconds.addVerifyListener(new VerifyListener() {
			public void verifyText(final VerifyEvent e) {
				e.doit = Utils.isOnlyDigits(e.text);
			}
		});
		stSeconds.setLayoutData(new GridData(24, SWT.DEFAULT));

		label18_2 = JOE_L_JobAssistent_TimeFormat.Control(new Label(composite, SWT.NONE));
		label18_2.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));

		lblOrSecond = JOE_L_PeriodForm_OrSS.Control(new Label(composite, SWT.NONE));
		lblOrSecond.setVisible(false);
		lblOrSecond.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));

	}

	public void setSchedulerUpdate(ISchedulerUpdate gui) {
		_gui = gui;
	}

	private void updateFont() {
		if(_type == Editor.RUNTIME && _gui != null)
			_gui.updateFont();
	}

	private void createWhenHoliday()         {

		final Group whenHolidayGroup = JOE_G_PeriodForm_WhenHoliday.Control(new Group(gPeriod, SWT.NONE));
		final GridData gridData_4 = new GridData(GridData.FILL, GridData.FILL, true, false);
		whenHolidayGroup.setLayoutData(gridData_4);
		whenHolidayGroup.setLayout(new GridLayout());

		cboWhenHoliday = JOE_Cbo_PeriodForm_WhenHoliday.Control(new Combo(whenHolidayGroup, SWT.READ_ONLY));
		cboWhenHoliday.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				listener.setWhenHoliday(cboWhenHoliday.getText(), bApply);				
			}
		});
		cboWhenHoliday.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
	}

	public boolean isSingleStart() {

		if(listener.getPeriod() == null)
			return false;

		String s = Utils.getAttributeValue("single_start", listener.getPeriod());
		s = s.replaceAll("0", "").trim();
		s = s.replaceAll(":", "").trim();
		return !s.equals("");

	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(boolean event) {
		this.event = event;
	}

	/**
	 * Period darf nur einmal repeat oder absolute_repeat haben
	 * @param hasIt
	 */
	public void hasRepeatTimes(boolean hasRepeatIntervall) {
		if(hasRepeatIntervall) {
			cboStarttime.setItems(new String[] {SINGLE_START});

		} else {
			cboStarttime.setItems(new String[] { SINGLE_START, REPEAT_TIME, ABSOLUTE_TIME });
		}
	}

	/**
	 * Stunde f�r Start Time setzen. 
	 */
	private void setHours() {
		
		Utils.setBackground(0, 23, stHour);
		if (event){
			if(bApply != null)
				bApply.setEnabled(true);
			
			if(cboStarttime.getText().equals(REPEAT_TIME)) {

				if (!(stMinutes.getText() + stHour.getText()).equals("")) {
					Utils.setBackground(0, 59, stSeconds);
				} else {
					stSeconds.setBackground(null);
				}

				listener.setPeriodTime(23, bApply, "repeat", stHour.getText(), stMinutes.getText(), stSeconds.getText());

			} else if(cboStarttime.getText().equals(ABSOLUTE_TIME)) {

				listener.setPeriodTime(23, bApply, "absolute_repeat", stHour.getText(), stMinutes.getText(), stSeconds.getText());

			} else if(cboStarttime.getText().equals(SINGLE_START)) {

				if(isSingleStart())
					listener.clearNONSingleStartAttributes();
				listener.setPeriodTime(23, bApply, "single_start", stHour.getText(), stMinutes.getText(), stSeconds.getText());

			}
			
		}		
	}

	private void  setMinutes() {
		
		if(event) {
			if(bApply != null)
				bApply.setEnabled(true);
			Utils.setBackground(0, 59, stMinutes);
			if(cboStarttime.getText().equals(REPEAT_TIME)) {

				if (!(stMinutes.getText() + stHour.getText()).equals("")) {
					Utils.setBackground(0, 59, stSeconds);
				} else {
					stSeconds.setBackground(null);
				}

				listener.setPeriodTime(23, bApply, "repeat", stHour.getText(), stMinutes.getText(),	stSeconds.getText());

			} else if(cboStarttime.getText().equals(ABSOLUTE_TIME)) {

				//Utils.setBackground(0, 23, stHour);				
				listener.setPeriodTime(23, bApply, "absolute_repeat", stHour.getText(), stMinutes.getText(), stSeconds.getText());

			} else if(cboStarttime.getText().equals(SINGLE_START)) {

				Utils.setBackground(0, 23, stHour);
				listener.setPeriodTime(23, bApply, "single_start", stHour.getText(), stMinutes.getText(), stSeconds.getText());

			}
		}
	}

	private void setSecound() {
		
		
		if(event) {
			if(bApply != null)
				bApply.setEnabled(true);
			Utils.setBackground(0, 59, stSeconds);
			if(cboStarttime.getText().equals(REPEAT_TIME)) {

				if (!(stMinutes.getText() + stHour.getText()).equals("")) {
					Utils.setBackground(0, 59, stSeconds);
				} else {
					stSeconds.setBackground(null);
				}

				if (Utils.str2int(stSeconds.getText()) > 59) {
					listener.setRepeatSeconds(bApply, stSeconds.getText());
				} else {
					listener.setPeriodTime(23, bApply, "repeat", stHour.getText(), stMinutes.getText(),
							stSeconds.getText());
				}


			} else if(cboStarttime.getText().equals(ABSOLUTE_TIME)) {

				listener.setPeriodTime(23, bApply, "absolute_repeat", stHour.getText(),stMinutes.getText(), stSeconds.getText());

			} else if(cboStarttime.getText().equals(SINGLE_START)) {
				
				if(isSingleStart())
					listener.clearNONSingleStartAttributes();
				listener.setPeriodTime(23, bApply, "single_start", stHour.getText(),
						stMinutes.getText(), stSeconds.getText());						
			}
		}
	}

	private void setBeginHours() {
		
		
		if (!beginBeforeAfter()) {
			Utils.setBackground(0, 23, sBeginHours);
		}
		if (event)  {        

			listener.setPeriodTime(23, bApply, "begin", sBeginHours.getText(), sBeginMinutes.getText(),
					sBeginSeconds.getText());
			if(listener.get_dom() != null &&  _type == Editor.RUNTIME) 
				listener.get_dom().setChanged(true);
			updateFont();
			
		}
	}
	
	private void setBeginminutes() {
		
		
		if (!beginBeforeAfter()) {
			Utils.setBackground(0, 59, sBeginMinutes);
		}

		if (event) {

			listener.setPeriodTime(23, bApply, "begin", sBeginHours.getText(), sBeginMinutes.getText(),
					sBeginSeconds.getText());
			updateFont();
			if(listener.get_dom() != null &&  _type == Editor.RUNTIME) listener.get_dom().setChanged(true);

		}
	}

	private void setBeginSeconds() {
	
		
		if (!beginBeforeAfter()) {
			Utils.setBackground(0, 59, sBeginSeconds);
		}
		if (event)  {   

			listener.setPeriodTime(24, bApply, "begin", sBeginHours.getText(), sBeginMinutes.getText(),
					sBeginSeconds.getText());

			updateFont();
			if(listener.get_dom() != null &&  _type == Editor.RUNTIME) listener.get_dom().setChanged(true);

		}
	}

	private void setEndHours() {
		
		if (!beginBeforeAfter()) {
			Utils.setBackground(0, 24, sEndHours);
		}
		if (event){
			listener.setPeriodTime(24, bApply, "end", sEndHours.getText(), sEndMinutes.getText(), sEndSeconds
					.getText());
			if(listener.get_dom() != null &&  _type == Editor.RUNTIME) listener.get_dom().setChanged(true);
			updateFont();
		}
	}

	private void setEndMinutes() {
		
		if (!beginBeforeAfter()) {
			Utils.setBackground(0, 59, sEndMinutes);
		}

		if (event){
			listener.setPeriodTime(24, bApply, "end", sEndHours.getText(), sEndMinutes.getText(), sEndSeconds
					.getText());
			updateFont();
			if(listener.get_dom() != null &&  _type == Editor.RUNTIME) listener.get_dom().setChanged(true);
		}
	}

	private void setEndSeconds() {
		
		if (!beginBeforeAfter()) {
			Utils.setBackground(0, 59, sEndSeconds);
		}

		if (event){
			listener.setPeriodTime(24, bApply, "end", sEndHours.getText(), sEndMinutes.getText(), sEndSeconds
					.getText());
			updateFont();

			if(listener.get_dom() != null &&  _type == Editor.RUNTIME &&  _type == Editor.RUNTIME) listener.get_dom().setChanged(true);
		}
	}

	/*public void setPeriodsForm(PeriodsForm periodsForm_) {
		periodsForm = periodsForm_;
	}*/
	
	private void refreshPeriodsTable(KeyEvent e) {
		if(bApply != null) {
			bApply.setEnabled(true);
						
		}
		//System.out.println((e.keyCode == SWT.CR) + " " + e.keyCode );
		/*
		//if (e.keyCode == SWT.CR) {
		if (e.keyCode == SWT.CR) {
			System.out.println("apply aufruf");
			if(periodsForm != null) {
				periodsForm.applyPeriod();
				//periodsForm.refreshPeriodsTable();
				//this.setEnabled(false);
			}
		}
		//periodsForm.refreshPeriodsTable();
		 * 
		 */
	}

	public void savePeriod(){

		
		int hB = sBeginHours.getText().length() == 0 ? 0 : Integer.parseInt(sBeginHours.getText());
		int mB = sBeginMinutes.getText().length() == 0 ? 0 : Integer.parseInt(sBeginMinutes.getText());
		int sB = sBeginSeconds.getText().length() == 0 ? 0 : Integer.parseInt(sBeginSeconds.getText());
		boolean begin = (hB + mB + sB ) > 0; 
		
		if(begin)
			setBeginHours();

		int hE = sEndHours.getText().length() == 0 ? 0 : Integer.parseInt(sEndHours.getText());
		int mE = sEndMinutes.getText().length() == 0 ? 0 : Integer.parseInt(sEndMinutes.getText());
		int sE = sEndSeconds.getText().length() == 0 ? 0 : Integer.parseInt(sEndSeconds.getText());
		boolean end = (hE + mE + sE ) > 0; 

		if(end)
			setEndHours();
		
		int hs = stHour.getText().length() == 0 ? 0 : Integer.parseInt(stHour.getText());
		int ms = stMinutes.getText().length() == 0 ? 0 : Integer.parseInt(stMinutes.getText());
		int ss = stSeconds.getText().length() == 0 ? 0 : Integer.parseInt(stSeconds.getText());

		boolean isCbo = (hs + ms + ss) > 0;

		//
		if(isCbo) {
			
			//SINGLE Start
			if(cboStarttime.getText().equals(SINGLE_START) && cboStarttime.getText().length() > 0) {
				listener.clearNONSingleStartAttributes();
			}
			setHours();			
		}



	}
} // @jve:decl-index=0:visual-constraint="10,10"
