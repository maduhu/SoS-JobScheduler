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
package com.sos.JSHelper.Options;

import java.io.IOException;

import com.sos.JSHelper.io.Files.JSFile;

/**
* \class SOSOptionInFileName
*
* \brief SOSOptionInFileName -
*
* \details
*
* \section SOSOptionInFileName.java_intro_sec Introduction
*
* \section SOSOptionInFileName.java_samples Some Samples
*
* \code
*   .... code goes here ...
* \endcode
*
* <p style="text-align:center">
* <br />---------------------------------------------------------------------------
* <br /> APL/Software GmbH - Berlin
* <br />##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
* <br />---------------------------------------------------------------------------
* </p>
* \author KB
* @version $Id: SOSOptionInFileName.java 19850 2013-04-08 08:44:29Z kb $16.05.2010
* \see reference
*
* Created on 16.05.2010 22:55:17
 */

/**
 * @author KB
 *
 */
public class SOSOptionInFileName extends JSOptionInFileName {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 5320294338809514909L;

	private final String		conClassName		= "SOSOptionInFileName";

	private JSFile				objFile				= null;

	//	SOSOptionInFileName() {
	//		//
	//	}

	/**
	 * \brief SOSOptionInFileName
	 *
	 * \details
	 *
	 * @param pPobjParent
	 * @param pPstrKey
	 * @param pPstrDescription
	 * @param pPstrValue
	 * @param pPstrDefaultValue
	 * @param pPflgIsMandatory
	 */
	public SOSOptionInFileName(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue,
			final String pPstrDefaultValue, final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
		intOptionType = isOptionTypeFileName;
	}

	public void CheckMandatory(final boolean pflgSetMandatory) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::CheckMandatory";
		this.isMandatory(pflgSetMandatory);
		this.CheckMandatory();

	} // private void CheckMandatory

	@Override
	public void CheckMandatory() {

		final String conMethodName = conClassName + "::CheckMandatory";

		if (this.isMandatory()) {
			this.JSFile();
			if (objFile != null) {
				String lstrFileName = strValue;
				try {
					if (isNotEmpty(lstrFileName)) {
						lstrFileName = objFile.getCanonicalPath();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				if (objFile.exists() == false) {
					throw new RuntimeException(String.format("%1$s: file '%2$s' does not exists", conMethodName, lstrFileName));
				}

				if (objFile.canRead() == false) {
					throw new RuntimeException(String.format("%1$s: file '%2$s' is not readable", conMethodName, lstrFileName));
				}
			}
		}

	} // private void CheckMandatory

	/**
	 *
	 * \brief JSFile
	 *
	 * \details
	 *
	 * \return JSFile
	 *
	 * @return
	 */
	public JSFile JSFile() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::JSFile";

		if (objFile == null) {
			if (isNotEmpty(strValue)) {
				objFile = new JSFile(strValue);
			}
		}

		return objFile;
	} // private JSFile JSFile
}
