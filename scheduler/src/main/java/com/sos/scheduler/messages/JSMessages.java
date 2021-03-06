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
/**
 *
 */
package com.sos.scheduler.messages;

import com.sos.i18n.annotation.I18NMsg;

/**
 * @author KB
 *
 */
public class JSMessages {

	public static final JSMsg	LOG_I_0010	= new JSMsg("LOG_I_0010");	// Log4j configured programmatically
	public static final JSMsg	LOG_I_0020	= new JSMsg("LOG_I_0020");	// JobSchedulerLog4JAppender is configured as log4j-appender

	public static final JSMsg	JSJ_E_0009	= new JSMsg("JSJ_E_0009");	// "Job '%1$s' terminated with error".
	public static final JSMsg	JSJ_I_0010	= new JSMsg("JSJ_I_0010");	// %1$s: Current Job is '%2$s'.
	public static final JSMsg	JSJ_I_0011	= new JSMsg("JSJ_I_0011");	// Job is running in a job-chain
	public static final JSMsg	JSJ_I_0012	= new JSMsg("JSJ_I_0012");	// Job is running as a standalone-job
	/**
	 * Order created by %1$s
	 */
	public static final JSMsg	JSJ_I_0017	= new JSMsg("JSJ_I_0017");	// Order created by %1$s
	public static final JSMsg	JSJ_I_0018	= new JSMsg("JSJ_I_0018");	// Order '%1$s' created for JobChain '%2$s'.
	public static final JSMsg	JSJ_I_0019	= new JSMsg("JSJ_I_0019");	// Next State is '%1$s'.
	public static final JSMsg	JSJ_I_0020	= new JSMsg("JSJ_I_0020");	// %1$s: Current NodeName is '%2$s'.
	public static final JSMsg	JSJ_I_0030	= new JSMsg("JSJ_I_0030");	// JSMsg after replacing-operation is = %1$s
	public static final JSMsg	JSJ_I_0090	= new JSMsg("JSJ_I_0090");	// set order-state to '%1$s'

	/**
	 * error occurred getting Parameters for job or order: %1$s
	 */
	public static final JSMsg	JSJ_F_0050	= new JSMsg("JSJ_F_0050");	// error occurred getting Parameters for job or order: %1$s
	/**
	 * error occurred reading Parameter from Variable_set : '%1$s'
	 */
	public static final JSMsg	JSJ_F_0060	= new JSMsg("JSJ_F_0060");	// error occurred reading Parameter from Variable_set : '%1$s'
	public static final JSMsg	JSJ_F_0080	= new JSMsg("JSJ_F_0080");	// Failed to write '%2$s' to '%1$s'
	public static final JSMsg	JSJ_F_0090	= new JSMsg("JSJ_F_0090");	// File '%1$s' for '%2$s' is not writable

	public static final JSMsg	JSJ_D_0010	= new JSMsg("JSJ_D_0010", 8);	// set parameter '%1$s' to value '%2$s'
	public static final JSMsg	JSJ_D_0031	= new JSMsg("JSJ_D_0031", 8);	// processing job parameter '%1$s': substitute '%2$s' with '%3$s'.
	/**
	 * "variable '%1$s' not found. no substitution done"
	 */
	public static final JSMsg	JSJ_D_0032	= new JSMsg("JSJ_D_0032", 3);	// variable '%1$s' not found. no substitution done
	public static final JSMsg	JSJ_D_0040	= new JSMsg("JSJ_D_0040", 1);	// No job- or order-parameters found. No replacing was done.
	/**
	 * processing job parameter '%1$s': substitute '%2$s' with '%3$s'.
	 */
	public static final JSMsg	JSJ_D_0044	= new JSMsg("JSJ_D_0044", 9);	// processing job parameter '%1$s': substitute '%2$s' with '%3$s'.
	/**
	 * Number of Parameters found for Job or Order is : %1$d
	 */
	public static final JSMsg	JSJ_D_0070	= new JSMsg("JSJ_D_0070", 3);	// Number of Parameters found for Job or Order is : %1$d
	/**
	 * Start replacing of job- and/or order-parameters .
	 */
	public static final JSMsg	JSJ_D_0080	= new JSMsg("JSJ_D_0080", 5);	// Start replacing of job- and/or order-parameters .

	public static final JSMsg	JSJ_E_0017	= new JSMsg("JSJ_E_0017");	// Compare operator not known: '%1$s'
																		//	public static final JSMsg				JSJ_E_0041	= new JSMsg("JSJ_E_0041"); // Order '%1$s' not found
	public static final JSMsg	JSJ_E_0042	= new JSMsg("JSJ_E_0042");	// exception raised %1$s

	/**
	 * parameter '%1$s' is missing but required for parameter '%2$s'
	 */
	public static final JSMsg	JSJ_E_0110	= new JSMsg("JSJ_E_0110");	// parameter '%1$s' is missing but required for parameter '%2$s'
	public static final JSMsg	JSJ_E_0120	= new JSMsg("JSJ_E_0120");	// This job runs not in a job chain (order driven job). Job parameter '%1$s' is
	// only valid for order driven jobs
	public static final JSMsg	JSJ_E_0130	= new JSMsg("JSJ_E_0130");	// invalid, non-numeric value for parameter '%1$s': %2$s

	public static final JSMsg	JSJ_W_0043	= new JSMsg("JSJ_W_0043");	// variable '%1$s' not found. no substitution done

	public static final JSMsg	JSJ_T_0010	= new JSMsg("JSJ_T_0010");	// greater or equal

	/**
	 * FileOperations
	 */
	public static final JSMsg	JFO_I_0014	= new JSMsg("JFO_I_0014");	// File deleted: %1$s"

	public static final JSMsg	JFO_I_0020	= new JSMsg("JFO_I_0020");
	public static final JSMsg	JFO_I_0019	= new JSMsg("JFO_I_0019");

	public static final JSMsg	JFO_I_0015	= new JSMsg("JFO_I_0015");	// %1$d %2$s files deleted

	public static final JSMsg	JFO_E_0016	= new JSMsg("JFO_E_0016");


	public static final JSMsg  JFO_F_0100 = new JSMsg("JFO_F_0100"); // "'%1$d' is not a valid value for parameter '%2$s'");
	/**
	 * "skip only either first files or last files"
	 */
	public static final JSMsg  JFO_F_0101 = new JSMsg("JFO_F_0101"); // "skip only either first files or last files");
	public static final JSMsg  JFO_F_0102 = new JSMsg("JFO_F_0102"); // "unsupported file mask found: %1$s ");
	public static final JSMsg  JFO_F_0103 = new JSMsg("JFO_F_0103"); // "missed constraint for file skipping (minFileAge, maxFileAge, minFileSize, maxFileSize)");
	/**
	 * "checking file '%1$s': no such file or directory"
	 */
	public static final JSMsg  JFO_I_0105 = new JSMsg("JFO_F_0105"); // "checking file '%1$s': no such file or directory");
	public static final JSMsg  JFO_I_0106 = new JSMsg("JFO_F_0106"); // "checking file '%1$s': file exists");


	/**
	 *
	 */

	public final static JSMsg	JSJ_E_0020	= new JSMsg("JSJ_E_0020");

	public final static JSMsg	JSJ_E_0040	= new JSMsg("JSJ_E_0040");
	public final static JSMsg	JSJ_E_0041	= new JSMsg("JSJ_E_0041");

	/**
	 *
	 */

	@I18NMsg
	public final static JSMsg	JSJ_F_0010	= new JSMsg("JSJ_F_0010");
	@I18NMsg
	public final static JSMsg	JSJ_F_0011	= new JSMsg("JSJ_F_0011");

	//	public static final String				JSJ_F_0015	= "JSJ_F_0015"; // error occurred instantiating '%1$s': %2$s
	//	public static final String				JSJ_F_0016	= "JSJ_F_0016"; // error occurred in '%1$s': %2$s

	public final static JSMsg	JSJ_F_0015	= new JSMsg("JSJ_F_0015");
	public final static JSMsg	JSJ_F_0016	= new JSMsg("JSJ_F_0016");

	/**
	 *
	 */
	public final static JSMsg	JSJ_I_0040	= new JSMsg("JSJ_I_0040");
	/**
	 * JSJ_F_107=%1$s - abended with sever errors
	 */
	@I18NMsg
	public final static JSMsg	JSJ_F_107	= new JSMsg("JSJ_F_107");
	@I18NMsg
	public final static JSMsg	JSJ_I_110	= new JSMsg("JSJ_I_110");
	/**
	 * %1$s - ended without errors
	 */
	@I18NMsg
	public final static JSMsg	JSJ_I_111	= new JSMsg("JSJ_I_111");

	/**
	 *
	 */
	private JSMessages() {
		// TODO Auto-generated constructor stub
	}

}
