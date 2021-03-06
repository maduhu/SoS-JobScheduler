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
package sos.scheduler.editor.app;

import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.jdom.xpath.XPath;

import sos.scheduler.editor.actions.forms.ActionsForm;
import sos.scheduler.editor.conf.SchedulerDom;
import sos.scheduler.editor.conf.forms.SchedulerForm;
import sos.scheduler.editor.conf.listeners.SchedulerListener;


public class ContextMenu {

	private SchedulerDom            _dom                  = null;

	private Combo                    _combo                 = null;

	private Menu                    _menu                 = null;

	private static final String      GOTO                 = "Goto";

	private static final String      DELETE               = "Delete";

	private static int               _type                = -1;


	public ContextMenu(final Combo combo, final SchedulerDom dom, final int type) {
		_combo = combo;
		_dom = dom;
		_type = type;

		createMenu();
	}


	public int message(final String message, final int style) {
		MessageBox mb = new MessageBox(_combo.getShell(), style);
		mb.setMessage(message);
		return mb.open();
	}


	private void createMenu() {
		_menu = new Menu(_combo.getShell(), SWT.POP_UP);

		MenuItem item = new MenuItem(_menu, SWT.PUSH);
		item.addListener(SWT.Selection, getListener());


		if(_type == Editor.SCRIPT)
			item.setText(ContextMenu.DELETE);
		else
			item.setText(ContextMenu.GOTO);



		_menu.addListener(SWT.Show, new Listener() {
			@Override
			public void handleEvent(final Event e) {
				MenuItem item = null;
				if(_type == Editor.SCRIPT) {
					 item = getItem(ContextMenu.DELETE);
				}else
					item = getItem(ContextMenu.GOTO);

				if(item != null)
					item.setEnabled(true);
			}
		});
	}


	public Menu getMenu() {
		return _menu;
	}



	private Listener getListener() {

		return new Listener() {
			@Override
			public void handleEvent(final Event e) {
				if(_type == Editor.SCRIPT)
					delete(_combo, _dom, _type);
				else
					goTo(_combo.getText(), _dom, _type);

			}

		};
	}

	/*private void applyXMLChange(String newXML){

		if(_dom instanceof SchedulerDom) {
			if(!((sos.scheduler.editor.conf.SchedulerDom)_dom).isLifeElement())
				newXML = newXML.replaceAll("\\?>", "?><spooler>" )+ "</spooler>";
		}

		//System.out.println("debug: \n" + newXML);

		try {

			_dom.readString(newXML, true);


			refreshTree("main");


		} catch (Exception de) {
			try {
				new ErrorLog("error in " + sos.util.SOSClassUtil.getMethodName() , de);
			} catch(Exception ee) {
				//tu nichts
			}
			MainWindow.message(MainWindow.getSShell(), "..error while update XML: " + de.getMessage(), SWT.ICON_WARNING );
		}
	}

	private Listener getCopyListener() {
		return new Listener() {
			public void handleEvent(Event e) {
				Element element = getElement();
				if (element != null)
					_copy = (Element) element.clone();
			}
		};
	}


	private Listener getDeleteListener () {
		return new Listener() {
			public void handleEvent(Event e) {

				int ok = MainWindow.message("Do you wont really remove life file: " + _dom.getFilename(), //$NON-NLS-1$
						SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);

				if (ok == SWT.CANCEL || ok == SWT.NO)
					return;

				if(!new java.io.File(_dom.getFilename()).delete()) {
					MainWindow.message("could not remove life file", SWT.ICON_WARNING | SWT.OK);
				}
				sos.scheduler.editor.app.IContainer con = MainWindow.getContainer();
				con.getCurrentTab().dispose();

			}
		};
	}

	private Listener getClipboardListener() {
		return new Listener() {
			public void handleEvent(Event e) {

			}


		};
	}


	private Listener getPasteListener() {
		return new Listener() {
			public void handleEvent(Event e) {
				Element target = getElement();

				if ((target != null && _copy != null)) {
					String tName = target.getName();
					String cName = _copy.getName();

					if(_dom instanceof SchedulerDom && ((SchedulerDom)_dom).isLifeElement()) {

						//if(cName.equals("job")) {
						target = (Element)_copy.clone();
						TreeData data = (TreeData) _combo.getData();
						data.setElement(target);

						return;
						//}
					}

					if (tName.equals("jobs") && cName.equals("job")) { // copy job

						String append = "copy(" + (target.getChildren("job").size() + 1);
						Element currCopy = (Element)_copy.clone();

						if(existJobname(target, Utils.getAttributeValue("name", _copy)))
							currCopy.setAttribute("name", append + ")of_" + Utils.getAttributeValue("name", _copy));

						target.addContent(currCopy);

						refreshTree("jobs");
						//_gui.update();
						if(_dom instanceof SchedulerDom && !((SchedulerDom)_dom).isLifeElement())
							//_gui.updateJobs();
						_dom.setChanged(true);

					} else if (tName.equals("job") && cName.equals("run_time")) { // copy
						// run_time
						target.removeChildren("run_time");
						target.addContent(_copy);
						//_gui.updateJob();
						_dom.setChanged(true);
					} else if (tName.equals("config") && cName.equals("config")) { // copy
						// run_time
						//target.getParentElement().removeContent();
						Element spooler = target.getParentElement();
						spooler.removeChildren("config");
						spooler.addContent((Element)_copy.clone());

						refreshTree("main");
						_dom.setChanged(true);

						//_gui.update();
					}  else if (tName.equals("commands") && cName.equals("order")) { // copy job

						String append = "copy(" + (target.getChildren("order").size() + 1);
						Element currCopy = (Element)_copy.clone();


						currCopy.setAttribute("id", append + ")of_" + Utils.getAttributeValue("id", _copy));

						target.addContent(currCopy);

						refreshTree("main");
						//_gui.updateCommands();
						//_gui.updateOrders();
						//_gui.update();
						_dom.setChanged(true);

					} else if (tName.equals("job_chains") && cName.equals("job_chain")) { // copy job

						String append = "copy(" + (target.getChildren("job_chain").size() + 1);
						Element currCopy = (Element)_copy.clone();

						if(existJobname(target, Utils.getAttributeValue("name", _copy)))
							currCopy.setAttribute("name", append + ")of_" + Utils.getAttributeValue("name", _copy));

						target.addContent(currCopy);

						//_gui.updateJobChains();
						refreshTree("main");
						//_gui.update();
						_dom.setChanged(true);

					}
				}
			}
		};
	}

	 */
	private MenuItem getItem(final String name) {
		MenuItem[] items = _menu.getItems();
		for (MenuItem item : items) {
			if(item.getText().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}

	private static String removeTitle(String s) {
        if (s.contains(" - ")) {
            int p = s.indexOf(" - ");
            s = s.substring(0,p);
        }
        if (s.startsWith("*")) {
            s=s.substring(1);
        }
    return s;
	}

	public static void goTo(final String name, DomParser _dom, final int type) {
		try {

			if(name == null || name.length() == 0) {
				return;
			}

			if(_dom instanceof sos.scheduler.editor.actions.ActionsDom)
				_dom = _dom;
			else
				_dom = _dom;


			if(type==Editor.JOB) {

				XPath x3 = XPath.newInstance("//job[@name='"+ name + "']");
				List listOfElement_3 = x3.selectNodes(_dom.getDoc());

				if(!listOfElement_3.isEmpty()) {

					SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					for(int i = 0; i < tree.getItemCount(); i++) {
						TreeItem item = tree.getItem(i);
						if(item.getText().equals(SchedulerListener.JOBS)){
							TreeItem[] jobsItem = item.getItems();
							for (TreeItem jItem : jobsItem) {
								String strName = jItem.getText();
								strName = removeTitle(strName);

								// TODO get the name of the job from the Element, not from the description

                                if(strName.equals(name)){
									tree.setSelection(new TreeItem [] {jItem});
								 	f.updateTreeItem(jItem.getText());
								    f.updateTree("jobs");
									break;
								}
							}
						}
					}
				}
			} else if(type==Editor.MONITOR) {

				String[] split = name.split("_@_");
				String jobname = split[0];
				String monitorname = split[1];

				XPath x3 = XPath.newInstance("//job[@name='"+ jobname + "']/monitor[@name='"+ monitorname + "']");
				List listOfElement_3 = x3.selectNodes(_dom.getDoc());

				if(!listOfElement_3.isEmpty()) {

					SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
 					if(tree.getSelection()[0].getText().equals(SchedulerListener.MONITOR)){
						TreeItem[] monitorsItem = tree.getSelection()[0].getItems();
						for (TreeItem monitor : monitorsItem) {
							String strName = monitor.getText();
                            strName = removeTitle(strName);

                            if(strName.equals(monitorname)){
 								tree.setSelection(new TreeItem [] {monitor});
								f.updateTreeItem(monitorname);
								f.updateTree("monitor");
								break;
							}
						}
					} else {
						for(int i = 0; i < tree.getItemCount(); i++) {
							TreeItem item = tree.getItem(i);
							if(item.getText().equals(jobname)){
								TreeItem[] jobsItem = item.getItems();
								for (TreeItem jItem : jobsItem) {
									if(jItem.getText().equals("Monitor")){
										TreeItem[] monitorsItem = jItem.getItems();
										for (TreeItem monitor : monitorsItem) {
											if(monitor.getText().equals(monitorname)){
												//if(jItem.getText().endsWith("Job: "+ name)){
												tree.setSelection(new TreeItem [] {monitor});
												f.updateTreeItem(monitorname);
												f.updateTree("monitor");
												break;
											}
										}
									}
								}
							}
						}
					}
				}

			} else if(type==Editor.JOB_CHAIN) {

				XPath x3 = XPath.newInstance("//job_chain[@name='"+ name + "']");
				List listOfElement_3 = x3.selectNodes(_dom.getDoc());
				if(!listOfElement_3.isEmpty()) {
					SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					for(int i = 0; i < tree.getItemCount(); i++) {
						TreeItem item = tree.getItem(i);
						if(item.getText().equals(SchedulerListener.JOB_CHAINS)){
//						if(item.getText().equals(SOSJOEMessageCodes.JOE_L_SchedulerListener_JobChains.label())){
							TreeItem[] jobsItem = item.getItems();
							for (TreeItem jItem : jobsItem) {
								String strName = jItem.getText();
	                            strName = removeTitle(strName);

 								if(strName.equals(name)){
									tree.setSelection(new TreeItem [] {jItem});
									f.updateTreeItem(jItem.getText());
									f.updateTree("");
									jItem.setExpanded(true);
									break;
								}
							}
						}
					}
				}

			} else if (type == Editor.PROCESS_CLASSES) {

				XPath x3 = XPath.newInstance("//process_class[@name='"+ name + "']");
				List listOfElement_3 = x3.selectNodes(_dom.getDoc());
				if(!listOfElement_3.isEmpty()) {
					SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					for(int i = 0; i < tree.getItemCount(); i++) {
						TreeItem item = tree.getItem(i);
						//if(item.getText().equals("Process Classes")){
						if(item.getText().endsWith("Process Classes")){
							tree.setSelection(new TreeItem [] {item});
							f.updateTreeItem(item.getText());
							f.updateTree("");
							break;
						}
					}
				}
			} else if(type==Editor.SCHEDULE) {

				XPath x3 = XPath.newInstance("//schedule[@name='"+ name + "']");
				List listOfElement_3 = x3.selectNodes(_dom.getDoc());
				if(!listOfElement_3.isEmpty()) {
					SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					for(int i = 0; i < tree.getItemCount(); i++) {
						TreeItem item = tree.getItem(i);
						if(item.getText().equals(SchedulerListener.SCHEDULES)){

							TreeItem[] items = item.getItems();
							for (TreeItem jItem : items) {
								String strName = jItem.getText();
                                strName = removeTitle(strName);

                                if(strName.equals(name) ){
									tree.setSelection(new TreeItem [] {jItem});
									f.updateTreeItem(jItem.getText());
									f.updateTree("");
									break;
								}
							}
						}
					}
				}

			} else if(type == Editor.ORDER) {

				XPath x3 = XPath.newInstance("//order[@id='"+ name + "']");
				List listOfElement_3 = x3.selectNodes(_dom.getDoc());

				if(listOfElement_3.isEmpty()) {
					x3 = XPath.newInstance("//add_order[@id='"+ name + "']");
					listOfElement_3 = x3.selectNodes(_dom.getDoc());
				}

				if(!listOfElement_3.isEmpty()) {
					SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					for(int i = 0; i < tree.getItemCount(); i++) {
						TreeItem item = tree.getItem(i);
						if(item.getText().equals(SchedulerListener.ORDERS)){

							TreeItem[] items = item.getItems();
							for (TreeItem jItem : items) {
								String strName = jItem.getText();
                                strName = removeTitle(strName);

                                if(strName.equals(name) ){
                                    tree.setSelection(new TreeItem [] {jItem});
									f.updateTreeItem(jItem.getText());
									f.updateTree("");
									break;
								}
							}
						}
					}
				}

			} else if(type == Editor.WEBSERVICE) {

				XPath x3 = XPath.newInstance("//web_service[@name='"+ name + "']");
				List listOfElement_3 = x3.selectNodes(_dom.getDoc());

				if(!listOfElement_3.isEmpty()) {
					SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					for(int i = 0; i < tree.getItemCount(); i++) {
						TreeItem item = tree.getItem(i);
						if(item.getText().equals(SchedulerListener.HTTP_SERVER)){
							for(int k = 0; k < item.getItemCount(); k++) {
								TreeItem httpItem = item.getItem(k);

								if(httpItem.getText().equals(SchedulerListener.WEB_SERVICES)){

									TreeItem[] items = httpItem.getItems();
									for (TreeItem jItem : items) {
										if(jItem.getText().equals("Web Service: " + name)){
											tree.setSelection(new TreeItem [] {jItem});
											f.updateTreeItem(jItem.getText());
											f.updateTree("");
											break;
										}
									}
								}
							}
						}
					}
				}
			} else if (type == Editor.ACTIONS) {


				XPath x3 = XPath.newInstance("//action[@name='"+ name + "']");
				List listOfElement_3 = x3.selectNodes(_dom.getDoc());
				if(!listOfElement_3.isEmpty()) {
					ActionsForm f = (ActionsForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					for(int i = 0; i < tree.getItemCount(); i++) {
						TreeItem item = tree.getItem(i);
						if(item.getText().equals("Actions")){
							TreeItem[] jobsItem = item.getItems();
							for (TreeItem jItem : jobsItem) {
								//if(jItem.getText().equals("Job Chain: "+ name)){
								if(jItem.getText().endsWith(sos.scheduler.editor.actions.listeners.ActionsListener.ACTION_PREFIX + name)){
									tree.setSelection(new TreeItem [] {jItem});
									f.updateTreeItem(jItem.getText());
									f.updateTree("");
									break;
								}
							}
						}
					}
				}
			} else if(type == Editor.EVENTS) {
				//<event_group logic="or" group="1">
				XPath x3 = XPath.newInstance("//event_group[@group='"+ name + "']");


				List listOfElement_3 = x3.selectNodes(_dom.getDoc());
				if(!listOfElement_3.isEmpty()) {
					ActionsForm f = (ActionsForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					if(tree.getSelectionCount() > 0) {
						TreeItem itemp = tree.getSelection()[0];
						for(int i = 0; i < itemp.getItemCount(); i++) {
							TreeItem item = itemp.getItem(i);
							if(item.getText().endsWith(sos.scheduler.editor.actions.listeners.ActionsListener.GROUP_PREFIX + name)){
								tree.setSelection(new TreeItem [] {item});
								f.updateTreeItem(item.getText());
								f.updateTree("");
								break;
							}
						}
					}
				}
			} else if(type == Editor.ACTION_COMMANDS) {
				XPath x3 = XPath.newInstance("//command[@name='"+ name + "']");


				List listOfElement_3 = x3.selectNodes(_dom.getDoc());
				if(!listOfElement_3.isEmpty()) {
					ActionsForm f = (ActionsForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					if(tree.getSelectionCount() > 0) {
						TreeItem itemp = tree.getSelection()[0];
						for(int i = 0; i < itemp.getItemCount(); i++) {
							TreeItem item = itemp.getItem(i);
							if(item.getText().endsWith(sos.scheduler.editor.actions.listeners.ActionsListener.COMMAND_PREFIX + name)){
								tree.setSelection(new TreeItem [] {item});
								f.updateTreeItem(item.getText());
								f.updateTree("");
								break;
							}
						}
					}
				}
			} else if (type == Editor.JOB_COMMAND_EXIT_CODES &&
					sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor() instanceof ActionsForm){


				XPath x3 = null;
				String job = "";
				if(name.startsWith("start_job")) {
					job = name.substring("start_job: ".length());
					x3 = XPath.newInstance("//command/start_job[@job='"+ job + "']");

				} else {
					String child = name.substring(0, name.indexOf(": "));
					job = name.substring(child.length() + 2);
					x3 = XPath.newInstance("//command/"+child+"[@job_chain='"+ job + "']");

				}


				List listOfElement_3 = x3.selectNodes(_dom.getDoc());
				if(!listOfElement_3.isEmpty()) {
					ActionsForm f = (ActionsForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					if(tree.getSelectionCount() > 0) {
						TreeItem itemp = tree.getSelection()[0];
						for(int i = 0; i < itemp.getItemCount(); i++) {
							TreeItem item = itemp.getItem(i);
							if(item.getText().equals(name)){
								tree.setSelection(new TreeItem [] {item});
								f.updateTreeItem(item.getText());
								f.updateTree("");
								break;
							}
						}
					}
				}
			} else if (type == Editor.JOB_COMMAND_EXIT_CODES &&
					sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor() instanceof SchedulerForm){


				XPath x3 = null;
				String job = "";
				if(name.startsWith("start_job")) {
					job = name.substring("start_job: ".length());
					x3 = XPath.newInstance("//commands/start_job[@job='"+ job + "']");

				} else {
					String child = name.substring(0, name.indexOf(": "));
					job = name.substring(child.length() + 2);
					x3 = XPath.newInstance("//commands/"+child+"[@job_chain='"+ job + "']");

				}


				List listOfElement_3 = x3.selectNodes(_dom.getDoc());
				if(!listOfElement_3.isEmpty()) {
					SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
					if(f == null)
						return;
					Tree tree = f.getTree();
					if(tree.getSelectionCount() > 0) {
						TreeItem itemp = tree.getSelection()[0];
						for(int i = 0; i < itemp.getItemCount(); i++) {
							TreeItem item = itemp.getItem(i);
							if(item.getText().equals(name)){
								tree.setSelection(new TreeItem [] {item});
								f.updateTreeItem(item.getText());
								f.updateTree("");
								break;
							}
						}
					}
				}

			} else if(type == Editor.JOB_COMMAND) {
				SchedulerForm f = (SchedulerForm)sos.scheduler.editor.app.MainWindow.getContainer().getCurrentEditor();
				if(f == null)
					return;

				Tree tree = f.getTree();
				if(tree.getSelectionCount() > 0) {
					TreeItem itemp = tree.getSelection()[0];
					for(int i = 0; i < itemp.getItemCount(); i++) {
						TreeItem item = itemp.getItem(i);
						if(item.getText().equals(name)){
							tree.setSelection(new TreeItem [] {item});
							f.updateTreeItem(item.getText());
							f.updateTree("");
							break;
						}
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void delete(final Combo combo, final DomParser _dom, final int type) {
		try {
			//favoriten l�schen
			if(combo.getData("favorites") == null)
				return;
			if(type == Editor.SCRIPT) {
				String prefix = "monitor_favorite_";
				String name = combo.getText();
				String lan = "";
				if(combo.getData("favorites") != null)
					lan = ((HashMap)combo.getData("favorites")).get(name) +"_";
				name = prefix + lan + name;
				Options.removeProperty(name);
				combo.remove(combo.getText());
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
