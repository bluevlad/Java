<%@ page contentType = "text/html; charset=euc-kr" %>
<%
//response.setHeader("Content-Type","application/vnd.ms-excel");
String filename =(String) request.getAttribute("filename");
if (filename == null) filename="excel";
response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
response.setHeader("Content-Description", "postech course support system");
%>
<html xmlns:o="urn:schemas-microsoft-com:office:office" 
		xmlns:x="urn:schemas-microsoft-com:office:excel" 
		xmlns="http://www.w3.org/TR/REC-html40">
<head> 
<meta http-equiv=Content-Type content="text/html; charset=ks_c_5601-1987">

<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author></o:Author>
  <o:LastAuthor></o:LastAuthor>
  <o:Created><%=new java.util.Date()%></o:Created>
  <o:LastSaved><%=new java.util.Date()%></o:LastSaved>
  <o:Company></o:Company>
 </o:DocumentProperties>
</xml><![endif]-->
<style>
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{margin:1.0in .75in 1.0in .75in;
	mso-header-margin:.5in;
	mso-footer-margin:.5in;}
tr
	{mso-height-source:auto;
	mso-ruby-visibility:none;}
col
	{mso-width-source:auto;
	mso-ruby-visibility:none;}
br
	{mso-data-placement:same-cell;}
.style17
	{mso-number-format:"_-* \#\,\#\#0_-\;\\-* \#\,\#\#0_-\;_-* \0022-\0022_-\;_-\@_-";
	mso-style-name:"굴림 \[0\]";
	mso-style-id:6;}
.style0
	{mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	white-space:nowrap;
	mso-rotate:0;
	mso-background-source:auto;
	mso-pattern:auto;
	color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:굴림, monospace;
	mso-font-charset:129;
	border:none;
	mso-protection:locked visible;
	mso-style-name:굴림;
	mso-style-id:0;}
td
	{mso-style-parent:style0;
	padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:굴림, monospace;
	mso-font-charset:129;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border:none;
	mso-background-source:auto;
	mso-pattern:auto;
	mso-protection:locked visible;
	white-space:nowrap;
	mso-rotate:0;}
.x_title   
	{mso-style-parent:style0;
	text-align:center;
	font-weight:700;}	
.x_num 
	{mso-style-parent:style0;
	text-align:right;}
.x_money 
	{mso-style-parent:style17;
	mso-number-format:"_-* \#\,\#\#0_-\;\\-* \#\,\#\#0_-\;_-* \0022-\0022_-\;_-\@_-";}	
.x_date  
	{mso-style-parent:style0;
	mso-number-format:"yyyy\0022-\0022m\0022-\0022d\\ h\:mm\;\@";}
.x_chr  
	{mso-style-parent:style0;
	mso-number-format:"\@";}	
.x_percent
	{mso-style-parent:style0;
	mso-number-format:0%;}	
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:8.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:굴림, monospace;
	mso-font-charset:129;
	mso-char-type:none;
	display:none;}	
.th {mso-style-parent:style0;
		font-size:12px; background-color: #ebebeb;font-weight:bold; line-height:20px; color:#505050; 
		text-align:center;}
.td {mso-style-parent:style0;
	font-size:12px; line-height:20px;background-color:#ffffff;}	
-->
</style>
<!--[if gte mso 9]><xml>
 <x:ExcelWorkbook>
  <x:ExcelWorksheets>
   <x:ExcelWorksheet>
    <x:Name>Sheet1</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>270</x:DefaultRowHeight>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:RangeSelection>$A1:$A1</x:RangeSelection>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet2</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>270</x:DefaultRowHeight>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet3</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>270</x:DefaultRowHeight>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
  </x:ExcelWorksheets>
  <x:WindowHeight>7875</x:WindowHeight>
  <x:WindowWidth>15345</x:WindowWidth>
  <x:WindowTopX>240</x:WindowTopX>
  <x:WindowTopY>120</x:WindowTopY>
  <x:ProtectStructure>False</x:ProtectStructure>
  <x:ProtectWindows>False</x:ProtectWindows>
 </x:ExcelWorkbook>
</xml><![endif]-->
<BODY>
<jsp:include page="${MAF_INFO.file}" flush="true"/>
</BODY>
</HTML>