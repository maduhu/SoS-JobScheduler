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
package sos.util;

import org.apache.commons.logging.Log;

/**
 * Implementierung des Log Interface, das vom <i>Jakarta Commons Logging</i> package verwendet wird.<br>
 * Diese Implementierung unterdr�ckt alle Logausgaben.
 * 
 * @author fs
 * @since 24.02.2006
 */
public class SOSJCLNullLogger implements Log {
		
		public SOSJCLNullLogger () {
		}
		
		public SOSJCLNullLogger (String name) {
		}
		
		public void debug(Object arg0, Throwable arg1) {
		}

		public void debug(Object arg0) {
		}

		public void error(Object arg0, Throwable arg1) {
		}

		public void error(Object arg0) {
		}

		public void fatal(Object arg0, Throwable arg1) {
		}

		public void fatal(Object arg0) {
		}

		public void info(Object arg0, Throwable arg1) {
		}

		public void info(Object arg0) {
		}

		public boolean isDebugEnabled() {
			return false;
		}

		public boolean isErrorEnabled() {
			return false;
		}

		public boolean isFatalEnabled() {
			return false;
		}

		public boolean isInfoEnabled() {
			return false;
		}

		public boolean isTraceEnabled() {
			return false;
		}

		public boolean isWarnEnabled() {
			return false;
		}

		public void trace(Object arg0, Throwable arg1) {
		}

		public void trace(Object arg0) {
		}

		public void warn(Object arg0, Throwable arg1) {
		}

		public void warn(Object arg0) {
		}
}
