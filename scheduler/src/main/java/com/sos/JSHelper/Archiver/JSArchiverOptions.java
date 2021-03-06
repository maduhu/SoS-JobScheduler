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
package com.sos.JSHelper.Archiver;

import java.util.HashMap;

import com.sos.JSHelper.Annotations.JSOptionClass;
import com.sos.JSHelper.Annotations.JSOptionDefinition;
import com.sos.JSHelper.Options.SOSOptionFolderName;
import com.sos.JSHelper.Options.JSOptionsClass;

/* ---------------------------------------------------------------------------
 APL/Software GmbH - Berlin
##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
Samstag, 27. Oktober 2007, Klaus.Buettner@sos-berlin.com (KB)
-------------------------------------------------------------------------------
<docu type="smcw" version="1.0">
<project>com.sos.IDocs</project>
<name>JSArchiverOptions.java</name>
<title>Archiver-Options
</title>
<description>
<para>
Archiver-Options
</para>
</description>
<params>
</params>
<keywords>
	<keyword>Archiver</keyword>
	<keyword>Options</keyword>
</keywords>
<categories>
<category>Options</category>
</categories>
<date>Samstag, 27. Oktober 2007</date>
<copyright>� 2000, 2001 by SOS GmbH Berlin</copyright>
<author>Klaus.Buettner@sos-berlin.com</author>
<changes>
 <change who='KB' when='Samstag, 27. Oktober 2007' id='created'>
   <what>
   <para>
   created
   </para>
   </what>
 </change>
</changes>
</docu>
---------------------------------------------------------------------------- */
@JSOptionClass(name = "JSArchiverOptions", description = "Optionen f�r die Archivierung von Dateien")
public class JSArchiverOptions extends JSOptionsClass {
	private final String		conClassName							= "JSArchiverOptions";

	/**
	* \brief ArchiveFolderName - Option: Name des Folder mit den archivierten Dateien
	*
	* \details
	*
	*/
	@JSOptionDefinition(name = "ArchiveFolderName", value = "./archive/", description = "Name des Folder mit den archivierten Dateien", key = "ArchiveFolderName", type = "JSOptionFolderName", mandatory = true)
	public SOSOptionFolderName	ArchiveFolderName						= new SOSOptionFolderName(this, // Verweis auf die SOSOptionClass-Instanz
																				this.conClassName + ".ArchiveFolderName", // Schl�ssel, i.d.r. identisch mit dem Namen der Option
																				"Name des Folder mit den archivierten Dateien", // Kurzbeschreibung
																				"./archive/", // Wert
																				"./archive/", // defaultwert
																				true // Option muss einen Wert haben
																		);

	/** String ArchiveFolderName: Name of Archive-Folder */
	private String				strArchiveFolderName					= "./archive/";
	private final String		conArchiveFolderNameSettingsKey			= this.conClassName + ".ArchiveFolderName";
	/** String FileName: Name of File to archive */
	private String				strFileName								= null;
	private final String		conFileNameSettingsKey					= this.conClassName + ".FileName";

	/** boolean CompressArchivedFile: Compress archived file or not */
	private boolean				flgCompressArchivedFile					= false;
	private final String		conCompressArchivedFileSettingsKey		= this.conClassName + ".CompressArchivedFile";

	/** boolean DeleteFileAfterArchiving: Delete File after archiving or not */
	private boolean				flgDeleteFileAfterArchiving				= false;
	private final String		conDeleteFileAfterArchivingSettingsKey	= this.conClassName + ".DeleteFileAfterArchiving";

	/** Boolean CreateTimeStamp: Create the archive-file-name using a timestamp */
	private boolean				flgCreateTimeStamp						= true;
	private final String		conCreateTimeStampSettingsKey			= this.conClassName + ".CreateTimeStamp";

	/** Boolean UseArchive: File has to be archived after processing */
	private boolean				flgUseArchive							= false;
	private final String		conUseArchiveSettingsKey				= this.conClassName + ".UseArchive";

	/* ---------------------------------------------------------------------------
	<constructor type="smcw" version="1.0">
	<classname>JSArchiverOptions</classname>
	<name></name>
	<title>Archiver-Options</title>
	<description>
	<para>
	Archiver-Options
	</para>
	</description>
	<params>
	</params>
	<keywords>
		<keyword>Archiver</keyword>
		<keyword>Options</keyword>
	</keywords>
	<categories>
	<category>Options</category>
	</categories>
	</constructor>
	---------------------------------------------------------------------------- */

	public JSArchiverOptions() {
	} // public JSArchiverOptions

	/* ---------------------------------------------------------------------------
	<constructor type="smcw" version="1.0">
	<name></name>
	<title>JSIDocOptions</title>
	<description>
	<para>
	Konstruktor JSIDocOptions, als Parameter eine HashMap mit den Optionen
	(so wie es im Dataswitch Standard ist).
	Dieser Konstruktor mappt die Werte aus der HashMap auf die Properties der
	Klasse.
	</para>
	</description>
	<params>
	   <param name="JSSettings" type="HashMap" ref="byvalue" >
	<para>
	Die Parameter, wie sie im Settings der JS-Datenbank definiert sind, sind
	in dieser HashMap enthalten und werden auf die Properties dieser Klasse
	gemappt.
	</para>
	</param>
	</params>
	<keywords>
		<keyword>IDoc</keyword>
		<keyword>Options</keyword>
		<keyword>Settings</keyword>
		<keyword>JSArchiverOptions:Class</keyword>
	</keywords>
	<categories>
	<category>IDoc</category>
	<category>OptionClass</category>
	</categories>
	</constructor>
	---------------------------------------------------------------------------- */
	public JSArchiverOptions(final HashMap<String, String> JSSettings) throws Exception {
		this.setAllOptions(JSSettings);
	} // public JSArchiverOptions (HashMap JSSettings)

	@Override
	public void toOut() {
		System.out.println(this.getAllOptionsAsString());
	} // public void toOut ()

	@Override
	public String toString() {
		return this.getAllOptionsAsString();
	} // public String toString ()

	private String getAllOptionsAsString() {
		String strT = this.conClassName + "\n";
		strT += "Create the archive-file-name using a timestamp : " + this.CreateTimeStamp() + "\n";
		strT += "File has to be archived after processing : " + this.UseArchive() + "\n";

		return strT;
	} // private String getAllOptionsAsString ()

	@Override
	public void setAllOptions(final HashMap<String, String> JSSettings) {
		this.objSettings = JSSettings;
		super.Settings(this.objSettings);

		String strT = super.getItem(this.conArchiveFolderNameSettingsKey);
		if (!this.isEmpty(strT)) {
			this.ArchiveFolderName(super.getItem(this.conArchiveFolderNameSettingsKey));
		}
		strT = super.getItem(this.conFileNameSettingsKey);
		if (!this.isEmpty(strT)) {
			this.FileName(super.getItem(this.conFileNameSettingsKey));
		}
		this.CompressArchivedFile(super.getBoolItem(this.conCompressArchivedFileSettingsKey));
		this.DeleteFileAfterArchiving(super.getBoolItem(this.conDeleteFileAfterArchivingSettingsKey));
		this.CreateTimeStamp(super.getBoolItem(this.conCreateTimeStampSettingsKey));
		this.UseArchive(super.getBoolItem(this.conUseArchiveSettingsKey));

	} // public void setAllOptions (HashMap <String, String> JSSettings)

	@Override
	public void CheckMandatory() throws Exception {
		this.FileName(this.FileName());
		this.ArchiveFolderName(this.ArchiveFolderName());

	} // public void CheckMandatory ()

	/** ---------------------------------------------------------------------------
	<method type="smcw" version="1.0">
	<name>ArchiveFolderName</name>
	<title>Name of Archive-Folder</title>
	<description>
	<para>
	Name of Archive-Folder
	</para>
	<para>
	Initial-Wert (Default) ist "./archive" (ohne Anf�hrungszeichen).
	</para>
	<mandatory>true</mandatory>
	</description>
	<params>
		<param name="param1" type=" " ref="byref|byvalue|out" >
			<para>
			</para>
		</param>
	</params>
	<keywords>
		<keyword>Archiver</keyword>
		<keyword>Options</keyword>
	</keywords>
	<categories>
	<category>Options</category>
	</categories>
	</method>
	---------------------------------------------------------------------------- */
	/**
	 * ArchiveFolderName - Name of Archive-Folder
	 *
	 * @return Returns the ArchiveFolderName.
	 */
	public String ArchiveFolderName() {
		// final String conMethodName = conClassName + "::ArchiveFolderName";
		if (this.strArchiveFolderName == null) {
			this.strArchiveFolderName = "./";
		}
		return this.strArchiveFolderName;
	} // String ArchiveFolderName()

	/**
	 * ArchiveFolderName - Name of Archive-Folder
	 *
	 * @param pstrArchiveFolderName The String ArchiveFolderName to set.
	 */
	public JSArchiverOptions ArchiveFolderName(final String pstrArchiveFolderName) {
		final String conMethodName = this.conClassName + "::ArchiveFolderName";
		this.strArchiveFolderName = this.CheckFolder(pstrArchiveFolderName, conMethodName, true);
		return this;
	} // public void ArchiveFolderName(String pstrArchiveFolderName)

	/** ---------------------------------------------------------------------------
	<method type="smcw" version="1.0">
	<name>FileName</name>
	<title>Name of File to archive</title>
	<description>
	<para>
	Name of File to archive
	</para>
	<para>
	Diese Property/Option ist mandatory.
	</para>
	<mandatory>true</mandatory>
	</description>
	<params>
		<param name="param1" type=" " ref="byref|byvalue|out" >
			<para>
			</para>
		</param>
	</params>
	<keywords>
		<keyword>Archiver</keyword>
		<keyword>Options</keyword>
	</keywords>
	<categories>
	<category>Options</category>
	</categories>
	</method>
	---------------------------------------------------------------------------- */
	/**
	 * FileName - Name of File to archive
	 *
	 * @return Returns the FileName.
	 */
	public String FileName() {
		// final String conMethodName = conClassName + "::FileName";
		return this.strFileName;
	} // String FileName()

	/**
	 * FileName - Name of File to archive
	 *
	 * @param pstrFileName The String FileName to set.
	 */
	public JSArchiverOptions FileName(final String pstrFileName) {
		final String conMethodName = this.conClassName + "::FileName";
		if (this.isEmpty(pstrFileName)) {
			this.SignalError(String.format(this.conNullButMandatory, "FileName", this.conFileNameSettingsKey, conMethodName));
		}

		if (this.isNotEqual(this.strFileName, pstrFileName)) {
			this.strFileName = this.CheckFileIsReadable(pstrFileName, conMethodName);
		}

		return this;
	} // public void FileName(String pstrFileName)

	/** ---------------------------------------------------------------------------
	<method type="smcw" version="1.0">
	<name>CompressArchivedFile</name>
	<title>Compress archived file or not</title>
	<description>
	<para>
	Compress archived file or not
	</para>
	<para>
	Initial-Wert (Default) ist "false" (ohne Anf�hrungszeichen).
	</para>
	<mandatory>true</mandatory>
	</description>
	<params>
		<param name="param1" type=" " ref="byref|byvalue|out" >
			<para>
			</para>
		</param>
	</params>
	<keywords>
		<keyword>Archiver</keyword>
		<keyword>Options</keyword>
	</keywords>
	<categories>
	<category>Options</category>
	</categories>
	</method>
	---------------------------------------------------------------------------- */
	/**
	 * CompressArchivedFile - Compress archived file or not
	 *
	 * @return Returns the CompressArchivedFile.
	 */
	public boolean CompressArchivedFile() {
		// final String conMethodName = conClassName + "::CompressArchivedFile";
		return this.flgCompressArchivedFile;
	} // boolean CompressArchivedFile()

	/**
	 * CompressArchivedFile - Compress archived file or not
	 *
	 * @param pflgCompressArchivedFile The boolean CompressArchivedFile to set.
	 */
	public JSArchiverOptions CompressArchivedFile(final boolean pflgCompressArchivedFile) {
		// final String conMethodName = conClassName + "::CompressArchivedFile";
		this.flgCompressArchivedFile = pflgCompressArchivedFile;
		return this;
	} // public void CompressArchivedFile(boolean pflgCompressArchivedFile)

	/** ---------------------------------------------------------------------------
	<method type="smcw" version="1.0">
	<name>DeleteFileAfterArchiving</name>
	<title>Delete File after archiving or not</title>
	<description>
	<para>
	Delete File after archiving or not
	</para>
	<para>
	Initial-Wert (Default) ist "true" (ohne Anf�hrungszeichen).
	</para>
	<mandatory>true</mandatory>
	</description>
	<params>
		<param name="param1" type=" " ref="byref|byvalue|out" >
			<para>
			</para>
		</param>
	</params>
	<keywords>
		<keyword>Archiver</keyword>
		<keyword>Options</keyword>
	</keywords>
	<categories>
	<category>Options</category>
	</categories>
	</method>
	---------------------------------------------------------------------------- */
	/**
	 * DeleteFileAfterArchiving - Delete File after archiving or not
	 *
	 * @return Returns the DeleteFileAfterArchiving.
	 */
	public boolean DeleteFileAfterArchiving() {
		// final String conMethodName = conClassName + "::DeleteFileAfterArchiving";
		return this.flgDeleteFileAfterArchiving;
	} // boolean DeleteFileAfterArchiving()

	/**
	 * DeleteFileAfterArchiving - Delete File after archiving or not
	 *
	 * @param pflgDeleteFileAfterArchiving The boolean DeleteFileAfterArchiving to set.
	 */
	public JSArchiverOptions DeleteFileAfterArchiving(final boolean pflgDeleteFileAfterArchiving) {
		// final String conMethodName = conClassName + "::DeleteFileAfterArchiving";
		this.flgDeleteFileAfterArchiving = pflgDeleteFileAfterArchiving;
		return this;
	} // public void DeleteFileAfterArchiving(boolean pflgDeleteFileAfterArchiving)

	/** ---------------------------------------------------------------------------
	<method type="smcw" version="1.0">
	<name>CreateTimeStamp</name>
	<title>Create the archive-file-name using a timestamp</title>
	<description>
	<para>
	Create the archive-file-name using a timestamp
	</para>
	<para>
	Initial-Wert (Default) ist "true" (ohne Anf�hrungszeichen).
	</para>
	<mandatory>true</mandatory>
	</description>
	<params>
		<param name="param1" type=" " ref="byref|byvalue|out" >
			<para>
			</para>
		</param>
	</params>
	<keywords>
		<keyword>TimeStamp</keyword>
	</keywords>
	<categories>
	<category>File</category>
	</categories>
	</method>
	---------------------------------------------------------------------------- */
	/**
	 * CreateTimeStamp - Create the archive-file-name using a timestamp
	 *
	 * @return Returns the CreateTimeStamp.
	 */
	public boolean CreateTimeStamp() {
		@SuppressWarnings("unused")
		final String conMethodName = this.conClassName + "::CreateTimeStamp";
		return this.flgCreateTimeStamp;
	} // boolean CreateTimeStamp()

	/**
	 * CreateTimeStamp - Create the archive-file-name using a timestamp
	 *
	 * @param pflgCreateTimeStamp The boolean CreateTimeStamp to set.
	 */
	public JSArchiverOptions CreateTimeStamp(final boolean pflgCreateTimeStamp) {
		@SuppressWarnings("unused")
		final String conMethodName = this.conClassName + "::CreateTimeStamp";
		this.flgCreateTimeStamp = pflgCreateTimeStamp;
		return this;
	} // public void CreateTimeStamp(boolean pflgCreateTimeStamp)

	/** ---------------------------------------------------------------------------
	<method type="smcw" version="1.0">
	<name>UseArchive</name>
	<title>File has to be archived after processing</title>
	<description>
	<para>
	File has to be archived after processing
	</para>
	<para>
	Initial-Wert (Default) ist "true" (ohne Anf�hrungszeichen).
	</para>
	<mandatory>true</mandatory>
	</description>
	<params>
		<param name="param1" type=" " ref="byref|byvalue|out" >
			<para>
			</para>
		</param>
	</params>
	<keywords>
		<keyword>Archive</keyword>
	</keywords>
	<categories>
	<category>Archiver</category>
	</categories>
	</method>
	---------------------------------------------------------------------------- */
	/**
	 * UseArchive - File has to be archived after processing
	 *
	 * @return Returns the UseArchive.
	 */
	public boolean UseArchive() {
		return this.flgUseArchive;
	} // boolean UseArchive()

	/**
	 * UseArchive - File has to be archived after processing
	 *
	 * @param pflgUseArchive The boolean UseArchive to set.
	 */
	public JSArchiverOptions UseArchive(final boolean pflgUseArchive) {
		this.flgUseArchive = pflgUseArchive;
		return this;
	} // public JSArchiverOptions UseArchive(boolean pflgUseArchive)

	/* ---------------------------------------------------------------------------
	<method type="smcw" version="1.0">
	<name>getFileNameSettingsKey</name>
	<title>Key der Option "Filename"</title>
	<description>
	<para>
	Key der Option "Filename"
	</para>
	</description>
	<params>
	</params>
	<keywords>
	  <keyword>ArchiverOptions</keyword>
	  <keyword>Settings</keyword>
	</keywords>
	<categories>
	<category>Settings</category>
	</categories>
	</method>
	---------------------------------------------------------------------------- */
	/*!
	 * @brief Key der Option "Filename"
	 *
	 * Key der Option "Filename"
	 *
	 */
	public String getFileNameSettingsKey() throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = this.conClassName + "::getFileNameSettingsKey";

		String RetVal = null;
		try {
			RetVal = this.conFileNameSettingsKey;
		}
		catch (final Exception e) {
			throw e;
		}
		return RetVal;
	} // public String getFileNameSettingsKey ()

} // public class JSArchiverOptions
